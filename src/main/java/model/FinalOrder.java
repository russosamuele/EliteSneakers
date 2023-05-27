package model;

import java.util.List;

public class FinalOrder {
	
	private OrdineBean ordine;
	private List<DettaglioOrdineBean> dettagli;
	
	
	public FinalOrder(OrdineBean ordine, List<DettaglioOrdineBean> dettagli) {
		this.ordine = ordine;
		this.dettagli = dettagli;
	}


	public OrdineBean getOrdine() {
		return ordine;
	}

	public List<DettaglioOrdineBean> getDettagli() {
		return dettagli;
	}	
	
	

}
