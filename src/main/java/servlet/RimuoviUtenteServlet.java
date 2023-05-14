package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.UserDAO;

/**
 * Servlet implementation class RimuoviUtenteServlet
 */
@WebServlet("/RimuoviUtenteServlet")
public class RimuoviUtenteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = request.getParameter("utente");
		UserDAO dao = new UserDAO();
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/RimuoviUtente.jsp");
		String mess = null;
		
		if(email != null) {
		
			try {
				dao.doDelete(email);
				mess = "Utente eliminato con successo!";
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			request.setAttribute("result", mess);
	
			dispatcher.forward(request, response);
			return ;
		}
		
		mess = "Errore nella rimozione dell'utente";
		request.setAttribute("result", mess);
		dispatcher.forward(request, response);
		
		
	}

}
