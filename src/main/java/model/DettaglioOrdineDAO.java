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

public class DettaglioOrdineDAO {
	
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

	private static final String TABLE_NAME = "DettaglioOrdine";
	
	
	public synchronized void doSave(DettaglioOrdineBean dettOrdine) throws SQLException { //salva un ordine

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		

		String insertSQL = "INSERT INTO " + DettaglioOrdineDAO.TABLE_NAME
				+ " (numero_ord, codice_prod, quantita, prezzo) VALUES (?, ?, ?, ?)";

		try {
			connection = ds.getConnection();
			connection.setAutoCommit(true);
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, dettOrdine.getNumeroOrd());
			preparedStatement.setInt(2, dettOrdine.getCodiceProdotto());
			preparedStatement.setInt(3, dettOrdine.getQuantita());
			preparedStatement.setDouble(4, dettOrdine.getPrezzo());
			
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

	
	public synchronized boolean doDelete(DettaglioOrdineBean ordine) throws SQLException { //cancella un ordine
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + DettaglioOrdineDAO.TABLE_NAME + " WHERE numero_ord = ? AND codice_prod = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, ordine.getNumeroOrd());
			preparedStatement.setInt(2, ordine.getCodiceProdotto());

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

	
	public synchronized Collection<DettaglioOrdineBean> doRetrieveByKey(int numeroOrd) throws SQLException{ //trova un ordine
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		Collection<DettaglioOrdineBean> dettagliOrdine = new LinkedList<>();
		
		String selectSQL = "SELECT * FROM " + DettaglioOrdineDAO.TABLE_NAME + " WHERE numero_ord = ?";
		try {
			connection = ds.getConnection();	
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, numeroOrd);
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				DettaglioOrdineBean bean = new DettaglioOrdineBean();
				bean.setNumeroOrd(rs.getInt("numero_ord"));
				bean.setCodiceProdotto(rs.getInt("codice_prod"));
				bean.setQuantita(rs.getInt("quantita"));
				bean.setPrezzo(rs.getDouble("prezzo"));
				
				dettagliOrdine.add(bean);
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
		return dettagliOrdine;
	}

	
	public synchronized Collection<DettaglioOrdineBean> doRetrieveAll(String order) throws SQLException { //trova tutti gli ordini
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<DettaglioOrdineBean> dettagliOrdini = new LinkedList<>();
		DettaglioOrdineBean bean = new DettaglioOrdineBean();

		String selectSQL = "SELECT * FROM " + DettaglioOrdineDAO.TABLE_NAME + " ORDER BY ?";

		
		try {
			connection = ds.getConnection();
			connection.setAutoCommit(true);
			preparedStatement = connection.prepareStatement(selectSQL);
			if (order != null && !order.equals("")) {
				preparedStatement.setString(0, order);
			}

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				bean.setNumeroOrd(rs.getInt("numero_ord"));
				bean.setCodiceProdotto(rs.getInt("codice_prod"));
				bean.setQuantita(rs.getInt("quantita"));
				bean.setPrezzo(rs.getDouble("prezzo"));
				
				dettagliOrdini.add(bean);
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
		return dettagliOrdini;
	}

}
