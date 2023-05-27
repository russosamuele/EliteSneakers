package model;

public class CartItem {
	
	private ProductBean productBean;
	private int quantita;
	private int taglia;

	public CartItem(ProductBean productBean, int quantita, int taglia) {
		this.productBean = productBean;
		this.quantita = quantita;
		this.taglia = taglia;
	}

	public ProductBean getProductBean() {
		return productBean;
	}

	public void setProductBean(ProductBean productBean) {
		this.productBean = productBean;
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
