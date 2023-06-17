package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

public class OrdineDAO {
	
	
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

	private static final String TABLE_NAME = "Ordine";
	
	
	
	
	public synchronized void doSave(OrdineBean ordine) throws SQLException { //salva un ordine

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		

		String insertSQL = "INSERT INTO " + OrdineDAO.TABLE_NAME
				+ " (numero_ord, email, data) VALUES (?, ?, ?)";

		try {
			connection = ds.getConnection();
			connection.setAutoCommit(true);
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, ordine.getNumeroOrd());
			preparedStatement.setString(2, ordine.getEmail());
			preparedStatement.setDate(3, new java.sql.Date(ordine.getDataOrdine().getTime()));
			
			
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

	
	public synchronized boolean doDelete(OrdineBean ordine) throws SQLException { //cancella un ordine
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + OrdineDAO.TABLE_NAME + " WHERE numero_ord = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, ordine.getNumeroOrd());

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

	
	public synchronized OrdineBean doRetrieveByKey(int numeroOrd) throws SQLException{ //trova un ordine
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		OrdineBean bean = new OrdineBean();
		String selectSQL = "SELECT * FROM " + OrdineDAO.TABLE_NAME + " WHERE numero_ord = ?";
		try {
			connection = ds.getConnection();	
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, numeroOrd);
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				bean.setNumeroOrd(rs.getInt("numero_ord"));
				bean.setEmail(rs.getString("email"));
				bean.setDataOrdine(rs.getDate("data"));
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

	
	public synchronized Collection<OrdineBean> doRetrieveAll(String order) throws SQLException { //trova tutti gli ordini
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<OrdineBean> ordini = new LinkedList<>();

		String selectSQL = "SELECT * FROM " + OrdineDAO.TABLE_NAME;

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = ds.getConnection();
			connection.setAutoCommit(true);
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				OrdineBean bean = new OrdineBean();
				bean.setNumeroOrd(rs.getInt("numero_ord"));
				bean.setEmail(rs.getString("email"));
				bean.setDataOrdine(rs.getDate("data"));
				
				
				ordini.add(bean);
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
		return ordini;
	}
	
	
	public synchronized Collection<OrdineBean> doRetrieveByEmail(String email, String order) throws SQLException { //trova tutti gli ordini di un determinato cliente
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<OrdineBean> ordini = new LinkedList<>();

		String selectSQL = "SELECT * FROM " + OrdineDAO.TABLE_NAME + " WHERE email=?";

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = ds.getConnection();
			connection.setAutoCommit(true);
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, email);
			
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				OrdineBean bean = new OrdineBean();
				bean.setNumeroOrd(rs.getInt("numero_ord"));
				bean.setEmail(rs.getString("email"));
				bean.setDataOrdine(rs.getDate("data"));
				
				ordini.add(bean);
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
		return ordini;
	}
	
	
	public synchronized int doRetrieveMaxNumOrdine() throws SQLException{ //trova il numero ordine da utulizzare
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String selectSQL = "SELECT MAX(numero_ord) AS MAX FROM " + OrdineDAO.TABLE_NAME;
		
		int max = 0 ;
		
		try {
			connection = ds.getConnection();	
			preparedStatement = connection.prepareStatement(selectSQL);
			ResultSet rs = preparedStatement.executeQuery();
			
			
			while (rs.next()) {
				max = rs.getInt("MAX");
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
		return max;
	}
	
	public synchronized Collection<OrdineBean> doRetrieveByUserData(String email, Date startDate, Date endDate) throws SQLException { //trova tutti gli ordini di un determinato cliente
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<OrdineBean> ordini = new LinkedList<>();

		String selectSQL = "SELECT * FROM " + OrdineDAO.TABLE_NAME + " WHERE email=? "
				+ " AND data BETWEEN ? AND ?";

		
		try {
			connection = ds.getConnection();
			connection.setAutoCommit(true);
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setString(1, email);
			preparedStatement.setDate(2, startDate);
			preparedStatement.setDate(3, endDate);

			
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				OrdineBean bean = new OrdineBean();
				bean.setNumeroOrd(rs.getInt("numero_ord"));
				bean.setEmail(rs.getString("email"));
				bean.setDataOrdine(rs.getDate("data"));
				
				ordini.add(bean);
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
		return ordini;
	}
	
	public synchronized Collection<OrdineBean> doRetrieveByData(Date startDate, Date endDate) throws SQLException { //trova tutti gli ordini di un determinato cliente
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<OrdineBean> ordini = new LinkedList<>();

		String selectSQL = "SELECT * FROM " + OrdineDAO.TABLE_NAME + " WHERE "
				+ "data BETWEEN ? AND ?";

		
		try {
			connection = ds.getConnection();
			connection.setAutoCommit(true);
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setDate(1, startDate);
			preparedStatement.setDate(2, endDate);

			
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				OrdineBean bean = new OrdineBean();
				bean.setNumeroOrd(rs.getInt("numero_ord"));
				bean.setEmail(rs.getString("email"));
				bean.setDataOrdine(rs.getDate("data"));
				
				ordini.add(bean);
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
		return ordini;
	}
	
	
	

}
