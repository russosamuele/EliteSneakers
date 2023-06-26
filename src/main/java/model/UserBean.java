package model;

import java.io.Serializable;
import java.text.ParseException;
import java.util.Date;

public class UserBean implements Serializable {

		private static final long serialVersionUID = 1L;

		String email;
		String nome;
		String cognome;
		String passwd;
		String indirizzo;
		String indirizzoSpedizione;
		boolean isAdmin;
		Date dataNascita;

		public UserBean() throws ParseException {
			email = "";
			nome = "";
			cognome = "";
			passwd = "";
			indirizzo = "";
			indirizzoSpedizione = "";
			isAdmin = false;
			
			
			
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getNome() {
			return nome;
		}

		public void setNome(String nome) {
			this.nome = nome;
		}

		public String getCognome() {
			return cognome;
		}

		public void setCognome(String cognome) {
			this.cognome = cognome;
		}

		public String getPasswd() {
			return passwd;
		}

		public void setPasswd(String passwd) {
			this.passwd = passwd;
		}

		public Date getDataNascita() {
			return dataNascita;
		}

		public void setDataNascita(Date date) {
			this.dataNascita = date;
		}

		public String getIndirizzo() {
			return indirizzo;
		}

		public void setIndirizzo(String indirizzo) {
			this.indirizzo = indirizzo;
		}

		public String getIndirizzoSpedizione() {
			return indirizzoSpedizione;
		}

		public void setIndirizzoSpedizione(String indirizzoSpedizione) {
			this.indirizzoSpedizione = indirizzoSpedizione;
		}

		public boolean isAdmin() {
			return isAdmin;
		}

		public void setAdmin(boolean isAdmin) {
			this.isAdmin = isAdmin;
		}

		@Override
		public String toString() {
			return "UserBean [email=" + email + ", nome=" + nome + ", cognome=" + cognome + ", passwd=" + passwd
					+ ", Data nascita=" + dataNascita + ", indirizzo=" + indirizzo + ", indirizzo_spedizione=" + indirizzoSpedizione
					+ ", isAdmin=" + isAdmin + "]";
		}
		

		

	}


