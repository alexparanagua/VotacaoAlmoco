package com.xtest.models;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Usuario implements Serializable{

	private static final long serialVersionUID = -244243983264906596L;

	@Id
	@GeneratedValue
	private int id;
	private String nome;
	private String email;
	private String password;
	private String dt_create;
	
//    @OneToMany(cascade=CascadeType.ALL)
//	private List<Telefone> phones;
	
	public Usuario(){}

	public Usuario(String nome, String email, String password, String dt_create) {
		this.nome = nome;
		this.email = email;
		this.password = password;
		this.dt_create = dt_create;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNome() {
		return nome;
	}
	public void setNome(String nome) {
		this.nome = nome;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getDt_create() {
		return dt_create;
	}
	public void setDt_create(String dt_create) {
		this.dt_create = dt_create;
	}

//	public List<Telefone> getPhones() {
//		return phones;
//	}
//
//	public void setPhones(List<Telefone> phones) {
//		this.phones = phones;
//	}

	public String toString(){
		return "Usuario [ Id:"+id+" Nome:"+nome+" Email:"+email+" Data Criacao:"+dt_create+" ]";
	}
	

}
