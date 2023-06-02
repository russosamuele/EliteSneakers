package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.OrdineBean;
import model.OrdineDAO;
import model.UserBean;

/**
 * Servlet implementation class VisualizzaOrdiniUtente
 */
@WebServlet("/VisualizzaOrdiniUtente")
public class VisualizzaOrdiniUtente extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		OrdineDAO oDAO = new OrdineDAO();
		List <OrdineBean> ordineList = null;
		
		UserBean user = (UserBean) request.getSession().getAttribute("user");
		String email = null;
		if(user != null)
			email = user.getEmail();
		
		try {
			ordineList = (List<OrdineBean>) oDAO.doRetrieveByEmail(email ,"data");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		request.setAttribute("listOrdini", ordineList);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/common/VisualizzaOrdiniUtente.jsp");
		dispatcher.forward(request, response);
		
		
		
	}

}
