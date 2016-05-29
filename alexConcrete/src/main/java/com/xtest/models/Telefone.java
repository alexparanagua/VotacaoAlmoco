package com.xtest.models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

@Entity 
@XmlRootElement
public class Telefone {
	
	@Id
	@GeneratedValue
	private int id;
	private int ddd;
	private int numero;
	
	public Telefone() {
		super();
	}

	public Telefone(int ddd, int numero) {
		super();
		this.ddd = ddd;
		this.numero = numero;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDdd() {
		return ddd;
	}

	public void setDdd(int ddd) {
		this.ddd = ddd;
	}

	public int getNumero() {
		return numero;
	}

	public void setNumero(int numero) {
		this.numero = numero;
	}

}
