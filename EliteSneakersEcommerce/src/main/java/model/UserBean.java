package model;

import java.io.Serializable;

public class UserBean implements Serializable {

		private static final long serialVersionUID = 1L;

		String email;
		String nome;
		String cognome;
		String passwd;
		int age;
		String indirizzo;
		String indirizzo_spedizione;
		boolean isAdmin;

		public UserBean() {
			email = "";
			nome = "";
			cognome = "";
			passwd = "";
			age = 0;
			indirizzo = "";
			indirizzo_spedizione = "";
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

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		public String getIndirizzo() {
			return indirizzo;
		}

		public void setIndirizzo(String indirizzo) {
			this.indirizzo = indirizzo;
		}

		public String getIndirizzo_spedizione() {
			return indirizzo_spedizione;
		}

		public void setIndirizzo_spedizione(String indirizzo_spedizione) {
			this.indirizzo_spedizione = indirizzo_spedizione;
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
					+ ", age=" + age + ", indirizzo=" + indirizzo + ", indirizzo_spedizione=" + indirizzo_spedizione
					+ ", isAdmin=" + isAdmin + "]";
		}
		

		

	}


