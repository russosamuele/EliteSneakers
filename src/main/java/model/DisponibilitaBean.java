package model;

public class DisponibilitaBean {
	private int codiceProd;
	private int quantita;
	private int taglia;	
	
	
	public DisponibilitaBean() {
		
		codiceProd = 0;
		quantita = 0;
		taglia = 0;
		
	}


	public int getCodiceProd() {
		return codiceProd;
	}


	public void setCodiceProd(int codiceProd) {
		this.codiceProd = codiceProd;
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
