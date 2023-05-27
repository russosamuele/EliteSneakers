package model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Cart implements Serializable{

	private static final long serialVersionUID = 1L;
	private List<CartItem> items;

	public Cart() {
		items = new ArrayList<>();
	}

	public void addProduct(ProductBean product, int taglia) {
		for (CartItem item : items) {
			if (item.getProductBean().getCode() == product.getCode() && item.getTaglia()==taglia) {
				item.setQuantita(item.getQuantita() + 1);
				return;
			}
		}
		items.add(new CartItem(product, 1, taglia));
	}

	public void deleteProduct(ProductBean product, int taglia) {
		for (CartItem item : items) {
			if (item.getProductBean().getCode() == product.getCode() && item.getTaglia()==taglia) {
				if (item.getQuantita() > 1) {
					item.setQuantita(item.getQuantita() - 1);
				} else {
					items.removeIf((a) -> a.getProductBean().getCode() == product.getCode());
				}
				return;
			}
		}
	}
	

	public List<CartItem> getProducts() {
		return items;
	}
	
	public int getItemsCount() {
		return items.stream().mapToInt(i->i.getQuantita()).sum();
	}
	
	public double getTotale() {
		return items.stream().mapToDouble(i->i.getProductBean().getPrice()*i.getQuantita()).sum();
	}
	
}
