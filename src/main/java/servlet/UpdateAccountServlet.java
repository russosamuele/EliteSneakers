package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.HelperClass;
import model.UserBean;
import model.UserDAO;

/**
 * Servlet implementation class UpdateAccountServlet
 */
@WebServlet("/UpdateAccountServlet")
public class UpdateAccountServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String indirizzo = HelperClass.filter(request.getParameter("address"));  
		String indirizzo_spedizione = HelperClass.filter(request.getParameter("address_sp")); 
		
		
		UserDAO dao = new UserDAO();
		
		UserBean user = (UserBean) request.getSession().getAttribute("user");
		String email = null;
		
		if(user != null)
			email = user.getEmail();
		
		
		try {
			dao.doUpdate(email, indirizzo, indirizzo_spedizione);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("/LogoutServlet").forward(request, response);
		
		
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
