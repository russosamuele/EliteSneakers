package model;

import java.util.Collection;
import java.util.HashMap;

public class FinalProduct {
	private ProductBean prodotto;
	private HashMap<Integer, Integer> disponibilitaTaglie;
	
	public FinalProduct(ProductBean prodotto, Collection<DisponibilitaBean> dispo) {
		this.prodotto = prodotto;
		
		for (DisponibilitaBean disp : dispo) {
			disponibilitaTaglie.put(disp.getTaglia(), disp.getQuantita());
		}
		
		
	}

	public ProductBean getProdotto() {
		return prodotto;
	}

	public HashMap<Integer, Integer> getDisponibilitaTaglie() {
		return disponibilitaTaglie;
	}
	
	
}
