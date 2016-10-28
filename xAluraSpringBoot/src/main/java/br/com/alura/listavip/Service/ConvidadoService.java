package br.com.alura.listavip.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.alura.enviadorEmail.EmailService;
import br.com.alura.listavip.model.TbUsuario;
import br.com.alura.listavip.repository.ConvidadoRepository;

@Service
public class ConvidadoService {
	
	@Autowired
	private ConvidadoRepository rep;
	
	public Iterable<TbUsuario> obterTodos(){
		return rep.findAll();
	}
	
	public void salvar(TbUsuario user){
		rep.save(user);
	}
	
	public void enviarEmail(String nome, String emailDestino, String msg){
		new EmailService().enviar(nome, emailDestino, msg);
	}
}
