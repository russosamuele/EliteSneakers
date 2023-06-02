package servlet;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;

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
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = (String) request.getAttribute("utente");
		String startD = (String) request.getAttribute("startDate");
		String endD = (String) request.getAttribute("endDate");
		
		Date startDate = null;
		Date endDate = null;
		
		boolean ricercaUtente = false;
		
		if(email != null)
			ricercaUtente = true;
		
		if (startD != null)
			try {
				startDate = new java.sql.Date(new SimpleDateFormat("yyyy-mm-dd").parse(startD).getTime());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		else
			try {
				startDate = new java.sql.Date(new SimpleDateFormat("yyyy-mm-dd").parse("2023-15-05").getTime());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		if(endD != null)
			try {
				endDate = new java.sql.Date(new SimpleDateFormat("yyyy-mm-dd").parse(endD).getTime());
			} catch (ParseException e) {
				e.printStackTrace();
			}
		else
			endDate = new java.sql.Date(System.currentTimeMillis());
	
		OrdineDAO oDAO = new OrdineDAO();
		List <OrdineBean> ordineList = null;
		
		if (email.equals("tutti"))
			try {
				ordineList = (List<OrdineBean>) oDAO.doRetrieveByData(startDate, endDate);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		else {
			try {
				ordineList = (List<OrdineBean>) oDAO.doRetrieveByUserData(email, startDate, endDate);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			request.setAttribute("listOrdine", ordineList);
			RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/admin/visualizzaOrdiniAdmin.jsp");
			dispatcher.forward(request, response);	
			

		}
		
			
		
		
		
		
		
				
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
