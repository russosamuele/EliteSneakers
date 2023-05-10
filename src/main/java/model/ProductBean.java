package model;

import java.io.Serializable;

public class ProductBean implements Serializable {

	private static final long serialVersionUID = 1L;

	int code;
	String brand;
	String modello;
	int price;
	int quantity;

	public ProductBean() {
		code = -1;
		brand = "";
		modello = "";
		quantity = 0;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
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

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	@Override
	public String toString() {
		return brand + modello + " (" + code + "), " + price + " " + quantity + ". ";
	}

}
