package model;

public class DisponibilitaBean {
	private int codice_prod;
	private int quantita;
	private int taglia;	
	
	
	public DisponibilitaBean() {
		
		codice_prod = 0;
		quantita = 0;
		taglia = 0;
		
	}


	public int getCodice_prod() {
		return codice_prod;
	}


	public void setCodice_prod(int codice_prod) {
		this.codice_prod = codice_prod;
	}


	public int getQuantita() {
		return quantita;
	}


	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}


	public int getTaglia() {
		return taglia;
	}


	public void setTaglia(int taglia) {
		this.taglia = taglia;
	}
	
	
}
