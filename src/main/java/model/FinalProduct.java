package model;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;

public class FinalProduct {
	private ProductBean prodotto;
	private List <DisponibilitaBean> disponibilitaTaglie;
	
	public FinalProduct(ProductBean prodotto, Collection<DisponibilitaBean> dispo) {
		this.prodotto = prodotto;
		this.disponibilitaTaglie = (List<DisponibilitaBean>) dispo;
		
	}

	public ProductBean getProdotto() {
		return prodotto;
	}

	public List <DisponibilitaBean> getDisponibilitaTaglie() {
		return disponibilitaTaglie;
	}
	
	
}
