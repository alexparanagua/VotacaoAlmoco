package br.com.restVotacao.model;

public class VotoTotal {
	private String rest;
	private int votos;
	private int dia;
	
	public VotoTotal(String rest, int votos, int dia) {
		this.rest = rest;
		this.votos = votos;
		this.dia = dia;
	}
	public String getRest() {
		return rest;
	}
	public void setRest(String rest) {
		this.rest = rest;
	}
	public int getVotos() {
		return votos;
	}
	public void setVotos(int votos) {
		this.votos = votos;
	}
	public int getDia() {
		return dia;
	}
	public void setDia(int dia) {
		this.dia = dia;
	}
}
