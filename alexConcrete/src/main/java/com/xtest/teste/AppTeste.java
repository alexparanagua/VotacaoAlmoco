package com.xtest.teste;

import java.util.List;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.xtest.impl.UsuarioDaoImpl;
import com.xtest.models.Usuario;

public class AppTeste {
	
	public static void main (String[] args) {
		ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("META-INF/applicationContext.xml");
		UsuarioDaoImpl dao = (UsuarioDaoImpl) context.getBean("usuDao");
		
//		Usuario usuario = new Usuario("JOSE","jose@teste.com","123","19/02/2010");
//		dao.save(usuario);
		
		List<Usuario> usuarios = dao.getAll();
		for (Usuario u : usuarios) {
			System.out.println(u);
		}
		context.close();
	}
}
