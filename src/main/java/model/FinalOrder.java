package model;

import java.util.List;

public class FinalOrder {
	
	private OrdineBean ordine;
	private List<DettaglioOrdineBean> dettagli;
	private List<String> nomeSnekear;
	
	
	public FinalOrder(OrdineBean ordine, List<DettaglioOrdineBean> dettagli, List<String> nomeSnekear) {
		this.ordine = ordine;
		this.dettagli = dettagli;
		this.nomeSnekear = nomeSnekear;
	}


	public OrdineBean getOrdine() {
		return ordine;
	}

	public List<DettaglioOrdineBean> getDettagli() {
		return dettagli;
	}
	
	public List<String> getNomi() {
		return nomeSnekear;
	}	
	
	

}
