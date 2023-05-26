package model;

import java.io.Serializable;

public class DettaglioOrdineBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	int numeroOrd;
	int codiceProdotto;
	int quantita;
	double prezzo;
	
	public DettaglioOrdineBean() {
		numeroOrd = 0;
		codiceProdotto = 0;
		quantita = 0;
		prezzo = 0.0;
	}

	public int getNumeroOrd() {
		return numeroOrd;
	}

	public void setNumeroOrd(int numeroOrd) {
		this.numeroOrd = numeroOrd;
	}

	public int getCodiceProdotto() {
		return codiceProdotto;
	}

	public void setCodiceProdotto(int codiceProdotto) {
		this.codiceProdotto = codiceProdotto;
	}

	public int getQuantita() {
		return quantita;
	}

	public void setQuantita(int quantita) {
		this.quantita = quantita;
	}

	public double getPrezzo() {
		return prezzo;
	}

	public void setPrezzo(double prezzo) {
		this.prezzo = prezzo;
	}

}
