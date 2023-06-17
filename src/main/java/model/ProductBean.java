package model;

import java.io.Serializable;

public class ProductBean implements Serializable {

	private static final long serialVersionUID = 1L;

	int code;
	String brand;
	String modello;
	String descrizione;
	double price;
	byte[] photo;

	

	public ProductBean() {
		code = -1;
		brand = "";
		modello = "";
		descrizione = "";
		price = 0.0;
	}


	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}
	
	public byte[] getPhoto() {
		return photo;
	}

	public void setPhoto(byte[] photo) {
		this.photo = photo;
	}

	public String getBrand() {
		return brand;
	}

	public void setBrand(String brand) {
		this.brand = brand;
	}

	public String getModello() {
		return modello;
	}
	
	public void setModello(String modello) {
		this.modello = modello;
	}
	
	public String getDescrizione() {
		return descrizione;
	}
	
	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public double getPrice() {
		return price;
	}
	
	public void setPrice(double price) {
		this.price = price;
	}



	

	

}
