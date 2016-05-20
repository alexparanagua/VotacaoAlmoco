package br.com.restVotacao.model;

public class Voto {
	
	private String rest;
	private String matricula;
	private int dia;
	private int semana;
	
	public Voto(String rest, String matricula, int dia, int semana) {
		this.rest = rest;
		this.matricula = matricula;
		this.dia=dia;
		this.semana=semana;
	}
	public String getRest() {
		return rest;
	}
	public void setRest(String rest) {
		this.rest = rest;
	}
	public String getMatricula() {
		return matricula;
	}
	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}
	public int getDia() {
		return dia;
	}
	public void setDia(int dia) {
		this.dia = dia;
	}
	public int getSemana() {
		return semana;
	}
	public void setSemana(int semana) {
		this.semana = semana;
	}
	
}
