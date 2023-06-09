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

public class DisponibilitaDAO {
	
	private static Logger logger = Logger.getAnonymousLogger();
	

	private static DataSource ds;
	static {
		try {
			Context initCtx = new InitialContext();
			Context envCtx = (Context) initCtx.lookup("java:comp/env");

			ds = (DataSource) envCtx.lookup("jdbc/elitesneakers");

		} catch (NamingException e) {
			logger.log(Level.WARNING, "Problema accesso DB!");
		}
	}

	private static final String TABLE_NAME = "DisponibilitaTaglia";
	
	//stringhe costanti per evitare duplicazioni (sono i nomi delle colonne nel db)
	private static final String CODICE_P = "codice_prod";
	private static final String TAGLIA = "taglia";
	private static final String QUANTITA = "quantita";
	private static final String CONST_WHERE = " WHERE codice_prod = ? AND taglia = ?";
	private static final String CONST_SELECT = "SELECT * FROM ";
	

	public synchronized void doSave(DisponibilitaBean disponibilita) throws SQLException {

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		

		String insertSQL = "INSERT INTO " + DisponibilitaDAO.TABLE_NAME
				+ " (codice_prod,taglia, quantita) VALUES (?, ?, ?)";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(insertSQL);
			preparedStatement.setInt(1, disponibilita.getCodiceProd());
			preparedStatement.setInt(2, disponibilita.getTaglia());
			preparedStatement.setInt(3, disponibilita.getQuantita());
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

	public synchronized boolean doDelete(int code, int taglia) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + DisponibilitaDAO.TABLE_NAME + CONST_WHERE;

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, code);
			preparedStatement.setInt(2, taglia);

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
	
	public synchronized boolean doDelete(int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "DELETE FROM " + DisponibilitaDAO.TABLE_NAME + " WHERE codice_prod = ?";

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
	
	public synchronized boolean doUpdate(int code, int taglia, int quantita) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		int result = 0;

		String deleteSQL = "UPDATE " + DisponibilitaDAO.TABLE_NAME + " SET quantita = quantita - ?"
				+ CONST_WHERE;

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(deleteSQL);
			preparedStatement.setInt(1, quantita);
			preparedStatement.setInt(2, code);
			preparedStatement.setInt(3, taglia);

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

	public synchronized DisponibilitaBean doRetrieveByKey(int code, int taglia) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		DisponibilitaBean bean = new DisponibilitaBean();
		String selectSQL = CONST_SELECT + DisponibilitaDAO.TABLE_NAME + CONST_WHERE;
		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, code);
			preparedStatement.setInt(2, taglia);

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				bean.setCodiceProd(rs.getInt(CODICE_P));
				bean.setQuantita(rs.getInt(QUANTITA));
				bean.setTaglia(rs.getInt(TAGLIA));
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
	
	public synchronized Collection<DisponibilitaBean> doRetrieveByKey(int code) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<DisponibilitaBean> dispo = new LinkedList<>();

		String selectSQL = CONST_SELECT + DisponibilitaDAO.TABLE_NAME + " WHERE codice_prod = ?";

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			preparedStatement.setInt(1, code);

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				DisponibilitaBean bean = new DisponibilitaBean();
				bean.setCodiceProd(rs.getInt(CODICE_P));
				bean.setQuantita(rs.getInt(QUANTITA));
				bean.setTaglia(rs.getInt(TAGLIA));
				dispo.add(bean);
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
		return dispo;
	}
	
		

	public synchronized Collection<DisponibilitaBean> doRetrieveAll(String order) throws SQLException {
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		Collection<DisponibilitaBean> dispo = new LinkedList<>();

		String selectSQL = CONST_SELECT + DisponibilitaDAO.TABLE_NAME + " ORDER BY ?";

		

		try {
			connection = ds.getConnection();
			preparedStatement = connection.prepareStatement(selectSQL);
			if (order != null && !order.equals("")) {
				preparedStatement.setString(1, order);
			}

			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				DisponibilitaBean bean = new DisponibilitaBean();
				bean.setCodiceProd(rs.getInt(CODICE_P));
				bean.setQuantita(rs.getInt(QUANTITA));
				bean.setTaglia(rs.getInt(TAGLIA));
				dispo.add(bean);
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
		return dispo;
	}

}
