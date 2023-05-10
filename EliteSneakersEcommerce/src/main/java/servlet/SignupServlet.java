package servlet;

import java.io.IOException;
import java.sql.SQLException;

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
 * Servlet implementation class SignupServlet
 */
@WebServlet("/SignupServlet")
public class SignupServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
    public SignupServlet() {
        super();
    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String email = request.getParameter("email");
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String passwd = request.getParameter("password");
		String confPass = request.getParameter("conferma_password");
		String indirizzo = request.getParameter("indirizzo");
		String indirizzo_spedizione = request.getParameter("indirizzo_spedizione");
		int age = Integer.parseInt(request.getParameter("age"));
		
		if(!passwd.equals(confPass)) {
			String error = "Password e conferma password non corrispondono";
			request.setAttribute("error", error);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/common/signup.jsp");
			dispatcher.forward(request, response);
			
		}
		
		
		UserBean user = new UserBean();
		user.setEmail(email);
		user.setNome(nome);
		user.setCognome(cognome);
		user.setPasswd(HelperClass.toHash(passwd));
		user.setIndirizzo(indirizzo);
		user.setIndirizzo_spedizione(indirizzo_spedizione);
		user.setAge(age);
		
		UserDAO userdao = new UserDAO();
		try {
			userdao.doSave(user);
			HttpSession session = request.getSession(); //imposto un booleano is Logged
			session.setAttribute("isLogged", "true");
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/common/index.jsp");
		dispatcher.forward(request, response);
	}

}
