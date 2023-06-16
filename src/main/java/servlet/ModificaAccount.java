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
 * Servlet implementation class ModificaAccount
 */
@WebServlet("/ModificaAccount")
public class ModificaAccount extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ModificaAccount() {
		super();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String indirizzo = null;
		String indirizzoSpedizione = null;
		String pswVecchia = null;
		String pswdNuova = null;
		
		

		if (request.getParameter("address") != null)
			indirizzo = HelperClass.filter(request.getParameter("address"));

		if (request.getParameter("address_sp") != null)
			indirizzoSpedizione = HelperClass.filter(request.getParameter("address_sp"));

		if (request.getParameter("passwordVecchia") != null)
			pswVecchia = HelperClass.filter(request.getParameter("passwordVecchia"));

		if (request.getParameter("nuovaPassword") != null)
			pswdNuova = HelperClass.filter(request.getParameter("nuovaPassword"));
		
		
		boolean check = false;

		if (pswdNuova == null || pswdNuova.trim().equals(""))
			check = true;

		UserDAO dao = new UserDAO();

		UserBean user = (UserBean) request.getSession().getAttribute("user");
		String email = null;
		String pswDB = null;

		if (user != null) {
			email = user.getEmail();
			pswDB = user.getPasswd();
		}
		

		// controllo se vecchia password è giusta
		
		String hashPswd = HelperClass.toHash(pswVecchia);

		if (!hashPswd.equals(pswDB)) {
			String error = "La vecchia password non è corretta!";
			request.setAttribute("errorPSW", error);
			request.getRequestDispatcher("/common/account.jsp").forward(request,response);
			return;
		}
		
		
	
		if (check) { //non voglio aggiornare la password

			try {
				dao.doUpdate(email, indirizzo, indirizzoSpedizione, pswDB);
				request.getRequestDispatcher("/LogoutServlet").forward(request, response);
				return;
			} catch (SQLException e) {
				e.printStackTrace();
			}

			
		}

		// significa che voglio modificare la password
		try {
			String pswdToInsert = HelperClass.toHash(pswdNuova);
			dao.doUpdate(email, indirizzo, indirizzoSpedizione, pswdToInsert);
			request.getRequestDispatcher("/LogoutServlet").forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();
		}

		

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

}
