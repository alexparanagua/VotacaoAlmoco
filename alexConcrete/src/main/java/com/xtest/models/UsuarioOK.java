package com.xtest.models;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class UsuarioOK {
	int id;
	String created;
	String modified;
	String last_login;
	String token;
	
	public UsuarioOK() {
		super();
	}

	public String toString(){
		return "[ id:"+id+" created:"+created+" modified:"+modified+" last_login:"+last_login+" token:"+token+" ]";
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCreated() {
		return created;
	}
	public void setCreated(String created) {
		this.created = created;
	}
	public String getModified() {
		return modified;
	}
	public void setModified(String modified) {
		this.modified = modified;
	}
	public String getLast_login() {
		return last_login;
	}
	public void setLast_login(String last_login) {
		this.last_login = last_login;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
	}
	
}
