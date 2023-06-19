package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DettaglioOrdineBean;
import model.DettaglioOrdineDAO;
import model.FinalOrder;
import model.OrdineDAO;
import model.ProductBean;
import model.ProductDAO;

/**
 * Servlet implementation class GetOrdineServlet
 */
@WebServlet("/GetOrdineServlet")
public class GetOrdineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static Logger logger = Logger.getAnonymousLogger();
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductDAO pDao = new ProductDAO();
		DettaglioOrdineDAO dODao = new DettaglioOrdineDAO();
		ProductBean pBean = null;
		
		OrdineDAO oDao = new OrdineDAO();
		
		final String LOG_MSG = "Problema accesso DB!";
		
		
		int code = Integer.parseInt(request.getParameter("code"));
		List <DettaglioOrdineBean> listaDettagliOrdine = null;
		
		try {
			listaDettagliOrdine = (List<DettaglioOrdineBean>) dODao.doRetrieveByKey(code);
		} catch (SQLException e) {
			logger.log(Level.WARNING, LOG_MSG);
		}	
		List <String> nomeSneaker = new LinkedList<>();
		
		try {
		for (int i=0; i< listaDettagliOrdine.size(); i++) {
			
			pBean = pDao.doRetrieveByKey(listaDettagliOrdine.get(i).getCodiceProdotto());
			String nomeBrandModello = pBean.getBrand() +" "+ pBean.getModello();
			nomeSneaker.add(i, nomeBrandModello);
		}
		}catch (SQLException e) {
			logger.log(Level.WARNING, LOG_MSG);
		}
		
		FinalOrder finalToPut = null;
		try {
			finalToPut = new FinalOrder(oDao.doRetrieveByKey(code), listaDettagliOrdine, nomeSneaker);
		} catch (SQLException e) {
			logger.log(Level.WARNING, LOG_MSG);
		}
		
		request.setAttribute("ordine", finalToPut);
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/common/DettaglioOrdine.jsp");
		dispatcher.forward(request, response);
			
		
	
	}

}
