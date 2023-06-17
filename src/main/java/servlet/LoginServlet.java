package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.HelperClass;
import model.UserBean;
import model.UserDAO;


/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static Logger logger = Logger.getAnonymousLogger();
    
    public LoginServlet() {
        super();
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		UserDAO tool = new UserDAO();
		UserBean user = null;
		
		String email = null;
		String passwd = null;
		
		if(request.getParameter("email") != null)
			email = HelperClass.filter(request.getParameter("email"));
		
		if(request.getParameter("password") != null)
			passwd = request.getParameter("password");
		
		try {
			user = tool.doRetrieveByKey(email);
		} catch (SQLException | ParseException e) {
			logger.log(Level.WARNING, "Problema Parse/Sql!");
			}
		
		
		boolean controlloPasswd=false;
		
		
		if(user!=null && user.getEmail().equals(email) ){
			
			String passToCompare = user.getPasswd();
			String passwdInserita = HelperClass.toHash(passwd);
			
			if(passToCompare.equals(passwdInserita)) {
				controlloPasswd=true;
				
			}else
				controlloPasswd=false;
		}
		
		
		if(!controlloPasswd){ 
			String error="Username o password errati";
			HttpSession session = request.getSession();
			session.setAttribute("isLogged", "false");
			request.setAttribute("error", error);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/common/login.jsp");
			dispatcher.forward(request, response);
			return;
		}
		
		
		
		//significa che ho trovato l'utente
		HttpSession session = request.getSession();
		session.setAttribute("isLogged", "true"); 
		session.setAttribute("user", user); //mi serve per recuperare le info dell'utente per account
		
		
		String res;
		if(user.isAdmin())
			res = "true";
		else
			res = "false";
		
			
		
		session.setAttribute("isAdmin", res);
	
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/common/index.jsp");
		dispatcher.forward(request, response);
		
		
	}
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
	
	
	
}


