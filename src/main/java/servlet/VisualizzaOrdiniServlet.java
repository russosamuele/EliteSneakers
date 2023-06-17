package servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.OrdineBean;
import model.OrdineDAO;

/**
 * Servlet implementation class VisualizzaOrdiniServlet
 */
@WebServlet("/VisualizzaOrdiniServlet")
public class VisualizzaOrdiniServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static Logger logger = Logger.getAnonymousLogger();
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = (String)request.getParameter("utente");
		String startD =  (String)request.getParameter("startDate");
		String endD = (String)request.getParameter("endDate");
		
		Date startDate = null;
		Date endDate = null;
		

		
		
		if (startD != null && !startD.equals(""))
			startDate = java.sql.Date.valueOf(startD);
		else
			startDate = java.sql.Date.valueOf("2023-05-15");
	
		if(endD != null && !endD.equals(""))
			endDate = java.sql.Date.valueOf(endD);
		else
			endDate = new java.sql.Date(System.currentTimeMillis());
						
		OrdineDAO oDAO = new OrdineDAO();
		List <OrdineBean> ordineList = null;
		
		
		
		
		if (email != null && email.equals("tutti"))
				try {
					ordineList = (List<OrdineBean>) oDAO.doRetrieveByData(startDate, endDate);
				} catch (SQLException e) {
					logger.log(Level.WARNING, "Problema accesso DB!");
				}
		else{
			try {
				ordineList = (List<OrdineBean>) oDAO.doRetrieveByUserData(email, startDate, endDate);
			} catch (SQLException e) {
				logger.log(Level.WARNING, "Problema accesso DB!");
			}
		}
			
			request.setAttribute("listOrdine", ordineList);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/visualizzaOrdiniAdmin.jsp");
			dispatcher.forward(request, response);	
			

}
		
			
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
