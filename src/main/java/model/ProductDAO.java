package model;

import java.sql.Connection;
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


public class ProductDAO{
	
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

	private static final String TABLE_NAME = "Prodotto";
	
	//stringhe costanti per evitare duplicazioni
	private static final String CODICE_PRODOTTO = "codice_prod";
	private static final String BRAND = "brand";
	private static final String MODELLO = "modello";
	private static final String PHOTO = "photo";
	private static final String DESCRIZIONE = "descrizione";
	private static final String PREZZO = "prezzo";
	
	
	
	public synchronized void doSave(ProductBean product) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;

		String insertSQL = "INSERT INTO " + ProductDAO.TABLE_NAME
				+ " (codice_prod, brand, modello, photo, descrizione, prezzo) VALUES (?, ?, ?, null,?,?)";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, product.getCode());
			preparedStatement.setString(2, product.getBrand());
			preparedStatement.setString(3, product.getModello());
			preparedStatement.setString(4, product.getDescrizione());
			preparedStatement.setDouble(5, product.getPrice());
			

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

	
	public synchronized boolean doDelete(int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + ProductDAO.TABLE_NAME + " WHERE codice_prod = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, code);

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

	
	public synchronized ProductBean doRetrieveByKey(int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ProductBean bean = new ProductBean();
		String selectSQL = "SELECT * FROM " + ProductDAO.TABLE_NAME + " WHERE codice_prod = ?";
		try {
			connection = ds.getConnection();	
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, code);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				bean.setCode(rs.getInt(CODICE_PRODOTTO));
				bean.setBrand(rs.getString(BRAND));
				bean.setModello(rs.getString(MODELLO));
				bean.setPhoto(rs.getBytes(PHOTO));
				bean.setDescrizione(rs.getString(DESCRIZIONE));
				bean.setPrice(rs.getInt(PREZZO));
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

	
	public synchronized Collection<ProductBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<ProductBean> products = new LinkedList<>();

		String selectSQL = "SELECT * FROM " + ProductDAO.TABLE_NAME;

		if (order != null && !order.equals("")) {
			selectSQL += " ORDER BY " + order;
		}

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				ProductBean bean = new ProductBean();
				bean.setCode(rs.getInt(CODICE_PRODOTTO));
				bean.setBrand(rs.getString(BRAND));
				bean.setModello(rs.getString(MODELLO));
				bean.setPhoto(rs.getBytes(PHOTO));
				bean.setDescrizione(rs.getString(DESCRIZIONE));
				bean.setPrice(rs.getInt(PREZZO));
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
	
	
	
	public synchronized Collection<ProductBean> doRetrieveByFiltri(String prezzoMin, String prezzoMax) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<ProductBean> products = new LinkedList<>();

		String selectSQL = "SELECT * FROM " + ProductDAO.TABLE_NAME + " WHERE prezzo >= ? AND prezzo <= ?";

		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			preparedStatement.setDouble(1, Double.parseDouble(prezzoMin));
			preparedStatement.setDouble(2, Double.parseDouble(prezzoMax));

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				ProductBean bean = new ProductBean();
				bean.setCode(rs.getInt(CODICE_PRODOTTO));
				bean.setBrand(rs.getString(BRAND));
				bean.setModello(rs.getString(MODELLO));
				bean.setPhoto(rs.getBytes(PHOTO));
				bean.setDescrizione(rs.getString(DESCRIZIONE));
				bean.setPrice(rs.getInt(PREZZO));
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
	
	
	public synchronized Collection<ProductBean> doRetrieveByFiltri(String prezzoMin, String prezzoMax, String brand) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<ProductBean> products = new LinkedList<>();

		String selectSQL = "SELECT * FROM " + ProductDAO.TABLE_NAME + " WHERE prezzo >= ? AND prezzo <= ? AND brand = ? ";

		
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			
			preparedStatement.setDouble(1, Double.parseDouble(prezzoMin));
			preparedStatement.setDouble(2, Double.parseDouble(prezzoMax));
			preparedStatement.setString(3, brand);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				ProductBean bean = new ProductBean();
				bean.setCode(rs.getInt(CODICE_PRODOTTO));
				bean.setBrand(rs.getString(BRAND));
				bean.setModello(rs.getString(MODELLO));
				bean.setPhoto(rs.getBytes(PHOTO));
				bean.setDescrizione(rs.getString(DESCRIZIONE));
				bean.setPrice(rs.getInt(PREZZO));
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
	

}
