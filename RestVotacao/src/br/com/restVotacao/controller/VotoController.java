package br.com.restVotacao.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.primefaces.model.chart.PieChartModel;

import br.com.restVotacao.model.Voto;
import br.com.restVotacao.model.VotoTotal;

public class VotoController {
	
	private List<String> listaRestaurantes;
	private List<VotoTotal> listaVotosDia;
	private List<VotoTotal> listaVotosTotal;
	private List<VotoTotal> listaVotosSemana;
	private List<Voto> listaVotos;
	private String restauranteEscolhido;
	private String matricula;
    private PieChartModel pieModel1;

    @PostConstruct
    public void init(){
    	createPieModel1();
    }

    private void createPieModel1() {
        pieModel1 = new PieChartModel();
        if(listaVotosDia !=null) {
        	for(VotoTotal vv : listaVotosDia){
        		pieModel1.set(vv.getRest(),vv.getVotos());
        	}
        }
        pieModel1.setTitle("Resultado do Dia");
        pieModel1.setLegendPosition("Votos");
        pieModel1.setFill(false);
        pieModel1.setShowDataLabels(true);
        pieModel1.setDiameter(150);
    }
    
    public PieChartModel getPieModel1() {
        return pieModel1;
    }
    
	public List<VotoTotal> getListaVotosSemana() {
		return listaVotosSemana;
	}

	public void setListaVotosSemana(List<VotoTotal> listaVotosSemana) {
		this.listaVotosSemana = listaVotosSemana;
	}

	public List<VotoTotal> getListaVotosTotal() {
		return listaVotosTotal;
	}

	public void setListaVotosTotal(List<VotoTotal> listaVotosTotal) {
		this.listaVotosTotal = listaVotosTotal;
	}

	public String getRestauranteEscolhido() {
		return restauranteEscolhido;
	}

	public void setRestauranteEscolhido(String restauranteEscolhido) {
		this.restauranteEscolhido = restauranteEscolhido;
	}

	public String getMatricula() {
		return matricula;
	}

	public void setMatricula(String matricula) {
		this.matricula = matricula;
	}

	public List<String> getListaRestaurantes() {
		listaRestaurantes = new ArrayList<String>();
		listaRestaurantes.add("Rango Bar");
		listaRestaurantes.add("OutBack");
		listaRestaurantes.add("Nazare");
		listaRestaurantes.add("Java Restaurante");
		listaRestaurantes.add("Moqueca Bar");
		listaRestaurantes.add("Feijoada da Vovo");
		return listaRestaurantes;
	}

	public void setListaRestaurantes(List<String> listaRestaurantes) {
		this.listaRestaurantes = listaRestaurantes;
	}

	public List<Voto> getListaVotos() {
		return listaVotos;
	}

	public void setListaVotos(List<Voto> listaVotos) {
		this.listaVotos = listaVotos;
	}

	public void limparCampos(){
		matricula = null;
		restauranteEscolhido =null;
	}
	
	public int getSemanaAtual(){
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		int semana = cal.get(Calendar.WEEK_OF_YEAR);
		return semana;
	}
	
	public int getDiaSemanaAtual(){
		Calendar cal = Calendar.getInstance();
		cal.setTime(new Date());
		int semana = cal.get(Calendar.DAY_OF_WEEK);
		return semana;
	}
	
	public String votar(){
		boolean votar=true;
		if(listaVotos==null) 
			listaVotos=new ArrayList<Voto>();
		for(Voto vo : listaVotos){
			if(vo.getMatricula().equals(matricula) && vo.getDia()==getDiaSemanaAtual() 
					&& vo.getSemana()==getSemanaAtual() && verExisteRestSemana(vo.getRest())){
				votar=false;
			}
		}
		if(votar)	
			listaVotos.add(new Voto(restauranteEscolhido, matricula, getDiaSemanaAtual(),getSemanaAtual()));
		limparCampos();
		listaVotosDia = restVotosDia();
		listaVotosTotal = restVotadosDias();
		listaVotosSemana = maisVotadoSemana();
    	createPieModel1();
		return "index";
	}
	
	public List<VotoTotal> restVotosDia(){
		listaVotosTotal = new ArrayList<VotoTotal>();
		for(String rest : listaRestaurantes){
				int tvoto = 0;
				for(Voto v : listaVotos){
					if(v.getRest().equals(rest) && v.getDia()==getDiaSemanaAtual() && v.getSemana()==getSemanaAtual()){
						tvoto++;
					}
				}
				if(tvoto!=0)
				listaVotosTotal.add(new VotoTotal(rest, tvoto, getDiaSemanaAtual()));
		}
		return listaVotosTotal;
	}
	
	public List<VotoTotal> restVotadosDias(){
		listaVotosTotal = new ArrayList<VotoTotal>();
		for(String rest : listaRestaurantes){
			for(int i=1; i<=7 ; i++){
				int tvoto = 0;
				for(Voto v : listaVotos){
					if(v.getRest().equals(rest) && v.getDia()==i && v.getSemana()==getSemanaAtual()){
						tvoto++;
					}
				}
				if(tvoto!=0)
				listaVotosTotal.add(new VotoTotal(rest, tvoto, i));
			}
		}
		return listaVotosTotal;
	}
	
	public List<VotoTotal> maisVotadoSemana(){
		List<VotoTotal> ll = new ArrayList<VotoTotal>();
		for(int i=1 ; i<=7 ; i++){
			int maior=0;
			VotoTotal vo = null;
			for(VotoTotal vv : listaVotosTotal){
				if(vv.getVotos()>=maior && vv.getDia()==i){
					maior=vv.getVotos();
					vo = new VotoTotal(vv.getRest(), vv.getVotos(), vv.getDia());
				}
			}
			if(vo!=null)
			ll.add(vo);
		}
		return ll;
	}
	
	public boolean verExisteRestSemana(String restAtual){
		for(VotoTotal v : maisVotadoSemana()){
			if(v.getRest().equals(restAtual) && v.getDia()!=getDiaSemanaAtual()){
				return true;
			}
		}
		return false;
	}
	
	public String detalhar(){
		return "detalhar";
	}
	public String voltar(){
		return "index";
	}
}
