package br.com.alura.listavip.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity(name="tbusuario")
public class TbUsuario {
	
	@Id
	private Long id;
	
	private String login;
	
	private String senha;

	public TbUsuario(){	
	}
	
	public TbUsuario(Long id, String login, String senha) {
		super();
		this.id=id;
		this.login = login;
		this.senha = senha;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}
	
}
