package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import model.DisponibilitaDAO;

/**
 * Servlet implementation class DeleteProdotto
 */
@WebServlet("/DeleteProdotto")
public class DeleteProdotto extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static Logger logger = Logger.getAnonymousLogger();
       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String c = request.getParameter("code");
		String message = "";
		
		/*per eliminare del tutto il prodotto basta modificare i vicnoli nel DB (noi abbiamo scelto 
			di non eliminare definitivamente il prodotto dal DB) 
		*/
		
		if(c!=null) {
			int code = Integer.parseInt(c);
			DisponibilitaDAO dao2 = new DisponibilitaDAO();
			
			try {
				dao2.doDelete(code);
				message = "prodotto eliminato con successo!";
			} catch (SQLException e) {
				logger.log(Level.WARNING, "Problema eliminazione prodotto!");
			}
		}
		
		request.setAttribute("message", message);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/RimuoviProdotto.jsp");
		dispatcher.forward(request, response);
		
	}

}
