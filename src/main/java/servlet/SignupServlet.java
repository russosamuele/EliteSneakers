package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.HelperClass;
import model.UserBean;
import model.UserDAO;

/**
 * Servlet implementation class SignupServlet
 */
@WebServlet("/SignupServlet")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static Logger logger = Logger.getAnonymousLogger();
       
    
    public SignupServlet() {
        super();
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String email = HelperClass.filter(request.getParameter("email"));
		String nome = HelperClass.filter(request.getParameter("nome"));
		String cognome = HelperClass.filter(request.getParameter("cognome"));
		String passwd = HelperClass.filter(request.getParameter("password"));
		String confPass = HelperClass.filter(request.getParameter("conferma_password"));
		String indirizzo = HelperClass.filter(request.getParameter("indirizzo"));
		String indirizzoSpedizione = HelperClass.filter(request.getParameter("indirizzo_spedizione"));
		String dNascita = (String) request.getParameter("dataNascita");
		Date dataNascita = null;
		
		try {
			dataNascita = new SimpleDateFormat("yyyy-mm-dd").parse(dNascita);
		} catch (ParseException e2) {
			logger.log(Level.WARNING, "Problema parse!");
		}
	
		
		if(!passwd.equals(confPass)) {
			String error = "Password e conferma password non corrispondono";
			request.setAttribute("error", error);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/common/signup.jsp");
			dispatcher.forward(request, response);
			
		}
		
		
		UserBean user = null;
		try {
			user = new UserBean();
		} catch (ParseException e1) {
			logger.log(Level.WARNING, "Problema parse!");
		}
		
		user.setEmail(email);
		user.setNome(nome);
		user.setCognome(cognome);
		user.setPasswd(HelperClass.toHash(passwd));
		user.setIndirizzo(indirizzo);
		user.setIndirizzo_spedizione(indirizzoSpedizione);
		user.setDataNascita(dataNascita);
		
		UserDAO userdao = new UserDAO();
		try {
			userdao.doSave(user);
			
		} catch (SQLException e) {
			logger.log(Level.WARNING, "Problema accesso DB!");
		}
		
		
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/common/index.jsp");
		dispatcher.forward(request, response);
	}

}
