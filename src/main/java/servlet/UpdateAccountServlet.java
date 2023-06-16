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
		String indirizzoSpedizione = HelperClass.filter(request.getParameter("address_sp")); 
		String pswVecchia = HelperClass.filter(request.getParameter("passwordVecchia"));
		String pswdNuova =  HelperClass.filter(request.getParameter("nuovaPassword"));
		
		boolean check = false;
		
		if(pswVecchia == null && pswdNuova == null)
			check=true;
			
		
		UserDAO dao = new UserDAO();
		
		UserBean user = (UserBean) request.getSession().getAttribute("user");
		String email = null;
		String pswDB = null;
		
		if(user != null) {
			email = user.getEmail();
			 pswDB = user.getPasswd();
		}
		
		
		//controllo se vecchia password è giusta
		
		if(!check) {
		
			String hashPswd = HelperClass.toHash(pswVecchia);
		
			if(!hashPswd.equals(pswDB)) {
				String error = "La vecchia password non è corretta!";
				request.setAttribute("errorPSW", error);
				request.getRequestDispatcher(request.getContextPath() + "/common/account.jsp").forward(request, response);
				return;
			}
		
		}
		
		
		try {
			dao.doUpdate(email, indirizzo, indirizzoSpedizione, HelperClass.toHash(pswdNuova));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("/LogoutServlet").forward(request, response);
		
		
	}
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

}
