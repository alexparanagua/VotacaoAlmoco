package br.com.alura.listavip.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import br.com.alura.listavip.Service.ConvidadoService;
import br.com.alura.listavip.model.TbUsuario;

@Controller
public class ConvidadoController {
	@Autowired
	private ConvidadoService serv;
	
	@RequestMapping("/")
	public String index(){
		return "index";
	}
	
	@RequestMapping("listavip")
	public String listaVip(Model model){
		carregarLista(model);
		return "listavip";
	}
	
	public void carregarLista(Model model){
		Iterable<TbUsuario> usuarios = serv.obterTodos();
		model.addAttribute("usuarios", usuarios);
	}
	
	@RequestMapping(value="salvar", method= RequestMethod.POST)
	public String salvar(@RequestParam("id") Long id,@RequestParam("login") String login, @RequestParam("senha") String senha, Model model){
		TbUsuario user = new TbUsuario(id, login,senha);
		serv.salvar(user);
		
		serv.enviarEmail(login, "alexparanagua@icloud.com", "spring boot teste");
		
		carregarLista(model);
		return "listavip";
	}
	
//	@RequestMapping(value="excluir", method= RequestMethod.POST)
//	public String excluir(@RequestParam("cod") String cod, Model model){
//		System.out.println(cod);
//		rep.delete(Long.valueOf(cod));
//		carregarLista(model);
//		return "listavip";
//	}
	
}
