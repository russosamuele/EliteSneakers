package model;

import java.io.Serializable;
import java.util.Date;

public class OrdineBean implements Serializable{

	private static final long serialVersionUID = 1L;
	
	int numeroOrd;
	String email;
	Date dataOrdine;
	
	
	
	OrdineBean(){
		email = "";
		numeroOrd = 0;
	}



	public int getNumeroOrd() {
		return numeroOrd;
	}



	public void setNumeroOrd(int numeroOrd) {
		this.numeroOrd = numeroOrd;
	}



	public String getEmail() {
		return email;
	}



	public void setEmail(String email) {
		this.email = email;
	}



	public Date getDataOrdine() {
		return dataOrdine;
	}



	public void setDataOrdine(Date dataOrdine) {
		this.dataOrdine = dataOrdine;
	}
	
	
}
