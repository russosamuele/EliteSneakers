package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.UserBean;
import model.UserDAO;

/**
 * Servlet implementation class GetSneakersList
 */
@WebServlet("/GetUsersList")
public class GetUsersList extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Logger logger = Logger.getAnonymousLogger();
		UserDAO uDao = new UserDAO();
		List<UserBean> utenti = null;
		try {
			utenti = (List<UserBean>) uDao.doRetrieveAll("nome");
		} catch (SQLException e) {
			logger.log(Level.WARNING, "Problema accesso DB!");
		} catch (ParseException e) {
			logger.log(Level.WARNING, "Problema parse");
		}
		
		request.setAttribute("listUsers", utenti);
		
		int idRequest = (int) request.getAttribute("id");
		RequestDispatcher dispatcher;
		switch(idRequest) {
		case 1: 
			dispatcher = this.getServletContext().getRequestDispatcher("/admin/VisualizzaUtenti.jsp");
			dispatcher.forward(request, response);
			break;
		case 2:
			dispatcher = this.getServletContext().getRequestDispatcher("/admin/RimuoviUtente.jsp");
			dispatcher.forward(request, response);
			break;
		case 3:
			dispatcher = this.getServletContext().getRequestDispatcher("/admin/visualizzaOrdiniAdmin.jsp");
			dispatcher.forward(request, response);
			break;
		default:
			break;
		}
		
		
	}

}
