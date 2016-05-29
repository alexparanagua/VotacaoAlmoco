package com.xtest.restful.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xtest.impl.UsuarioDaoImpl;
import com.xtest.models.Usuario;
import com.xtest.models.UsuarioOK;


@Path("/usuarios")
public class UsuarioResource {

	static final String ERRO_MSG = "{\"mensagem\": \"mensagem de erro\"}";
	static final String ERRO_USER = "Usuário e/ou senha inválidos";
	
	/**
	 * Traz a lista de todos os usuarios
	 * @return JSON da lista de todos os usuarios
	 */
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public List<Usuario> getUsuarios(){
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("META-INF/applicationContext.xml");
		UsuarioDaoImpl dao = (UsuarioDaoImpl) context.getBean("usuDao");
		List<Usuario> usuarios = dao.getAll();
		context.close();	
		return usuarios;
	}
	
	/**
	 * Cadastra Usuario
	 * @param uu
	 * @return resposta validada
	 */
	@SuppressWarnings("unused")
	@POST 
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public Response addUsuario(Usuario uu) { 
		ResponseBuilder resp;
		String emailExist=null;
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("META-INF/applicationContext.xml");
		UsuarioDaoImpl dao = (UsuarioDaoImpl) context.getBean("usuDao");
		if(existEmail(dao.getAll(), uu.getEmail())){
			emailExist="E-mail já existente";			
		}
		if (emailExist==null) {
		Integer id = dao.save(uu);
		if(id==null){
			resp = Response.ok(ERRO_MSG);
		} else {
			UsuarioOK uok = new UsuarioOK();
			uok.setId(uu.getId());
			uok.setCreated(uu.getDt_create());
			uok.setModified(uu.getDt_create());
			uok.setLast_login(uu.getDt_create());
			uok.setToken("TOKEN "+uu.getId());
			resp = Response.ok(uu+"\n"+uok);
		}
		} else {
			resp = Response.ok(emailExist);
		}
		context.close();	
		return resp.build();
	}
	
	/**
	 * Pesquisa usuario com um determinado ID
	 * @param id
	 * @return JSON com a resposta
	 */
	@Path("{id}") 
	@GET 
	public Response getBanda(@PathParam("id") int id) { 
		ResponseBuilder resp;
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("META-INF/applicationContext.xml");
		UsuarioDaoImpl dao = (UsuarioDaoImpl) context.getBean("usuDao");		
		if (id>=dao.getAll().size()){
			resp = Response.status(Status.NOT_FOUND).entity(ERRO_MSG);
		} else {
			Usuario uu = dao.getAll().get(id);
			resp = Response.ok(uu, MediaType.APPLICATION_JSON);
		}
		context.close();
		return resp.build();
	}
	
	/**
	 * Pesquisa existencia de usuario conforme parametros
	 * @param email
	 * @param pw
	 * @return JSON com a resposta
	 */
	@Path("logar")
	@GET
	public Response logar(@QueryParam("email") String email, @QueryParam("pw")String pw){
		ResponseBuilder resp;
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("META-INF/applicationContext.xml");
		UsuarioDaoImpl dao = (UsuarioDaoImpl) context.getBean("usuDao");	
		Usuario uu = dao.getLogon(email, pw);
		if(uu==null)
			if(existEmail(dao.getAll(),email))
				resp = Response.status(Status.UNAUTHORIZED).entity(ERRO_USER);
		    else 
		    	resp = Response.status(Status.NOT_FOUND).entity(ERRO_USER);			
		else
			resp = Response.ok(uu,MediaType.APPLICATION_JSON);
		context.close();
		return resp.build();
	}
	
	public boolean existEmail(List<Usuario> usuarios, String email){
		for(Usuario usuario : usuarios){
			if(usuario.getEmail().equals(email)){
				return true;
			}
		}
		return false;
	}
}

