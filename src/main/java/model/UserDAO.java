package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;


public class UserDAO{
	
	private static DataSource ds;
	private static Logger logger = Logger.getAnonymousLogger();
	
	static {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");

			ds = (DataSource) envCtx.lookup("jdbc/elitesneakers");

		} catch (NamingException e) {
			logger.log(Level.WARNING, "Problema accesso DB!");
		}
	} 

	private static final String TABLE_NAME = "Cliente";
	
	
	
	
	
	public synchronized void doSave(UserBean user) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		

		String insertSQL = "INSERT INTO " + UserDAO.TABLE_NAME
				+ " (email, nome, cognome, passwd, data_nascita, indirizzo, indirizzo_spedizione, isAdmin) VALUES (?, ?, ?, ?,?,?,?,?)";

		try {
			connection = ds.getConnection();
			connection.setAutoCommit(true);
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setString(1, user.getEmail());
			preparedStatement.setString(2, user.getNome());
			preparedStatement.setString(3, user.getCognome());
			preparedStatement.setString(4, user.getPasswd());
			preparedStatement.setDate(5, new java.sql.Date(user.getDataNascita().getTime()));
			preparedStatement.setString(6, user.getIndirizzo());
			preparedStatement.setString(7, user.getIndirizzo_spedizione());
			preparedStatement.setBoolean(8, user.isAdmin());
			preparedStatement.executeUpdate();
			
			
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
	}

	
	public synchronized boolean doDelete(String email) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + UserDAO.TABLE_NAME + " WHERE email = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setString(1, email);

			result = preparedStatement.executeUpdate();

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return (result != 0);
	}

	
	public synchronized UserBean doRetrieveByKey(String email) throws SQLException, ParseException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		UserBean bean = new UserBean();
		String selectSQL = "SELECT * FROM " + UserDAO.TABLE_NAME + " WHERE email = ?";
		try {
			connection = ds.getConnection();	
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, email);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				bean.setEmail(rs.getString("email"));
				bean.setNome(rs.getString("nome"));
				bean.setCognome(rs.getString("cognome"));
				bean.setPasswd(rs.getString("passwd"));
				bean.setDataNascita(rs.getDate("data_nascita"));
				bean.setIndirizzo(rs.getString("indirizzo"));
				bean.setIndirizzo_spedizione(rs.getString("indirizzo_spedizione"));
				bean.setAdmin(rs.getBoolean("isAdmin"));

			}
		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return bean;
	}

	
	public synchronized Collection<UserBean> doRetrieveAll(String order) throws SQLException, ParseException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<UserBean> products = new LinkedList<>();

		String selectSQL = "SELECT * FROM " + UserDAO.TABLE_NAME + " ORDER BY ?";


		try {
			connection = ds.getConnection();
			connection.setAutoCommit(true);
			preparedStatement = connection.prepareStatement(selectSQL);
			if (order != null && !order.equals("")) {
				preparedStatement.setString(1, order);
			}

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				UserBean bean = new UserBean();
				bean.setEmail(rs.getString("email"));
				bean.setNome(rs.getString("nome"));
				bean.setCognome(rs.getString("cognome"));
				bean.setPasswd(rs.getString("passwd"));
				bean.setDataNascita(rs.getDate("data_nascita"));
				bean.setIndirizzo(rs.getString("indirizzo"));
				bean.setIndirizzo_spedizione(rs.getString("indirizzo_spedizione"));
				bean.setAdmin(rs.getBoolean("isAdmin"));
				
				products.add(bean);
			}

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return products;
	}
	
	
	public synchronized boolean doUpdate(String email, String indirizzo, String indirizzoSpedizione, String pswd) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String updateSQL = "UPDATE " + UserDAO.TABLE_NAME + " SET indirizzo= ? , indirizzo_spedizione = ?, passwd= ? WHERE email = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(updateSQL);
			preparedStatement.setString(1, indirizzo);
			preparedStatement.setString(2, indirizzoSpedizione);
			preparedStatement.setString(3, pswd);
			preparedStatement.setString(4, email);

			result = preparedStatement.executeUpdate();

		} finally {
			try {
				if (preparedStatement != null)
					preparedStatement.close();
			} finally {
				if (connection != null)
					connection.close();
			}
		}
		return (result != 0);
	}
	
	
	
	

}
