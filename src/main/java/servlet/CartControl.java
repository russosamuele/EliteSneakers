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

import model.Cart;
import model.ProductDAO;

/**
 * Servlet implementation class CartControl
 */
@WebServlet("/CartControl")
public class CartControl extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static Logger logger = Logger.getAnonymousLogger();
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
}
	

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		Cart carrello = (Cart) request.getSession().getAttribute("carrello");
		if (carrello == null) {
			carrello = new Cart();
			request.getSession().setAttribute("carrello", carrello);
		}

		String action = request.getParameter("action");
		String codeStr = request.getParameter("code");
		String redirect = request.getParameter("redirect");
		String tagliaStr = request.getParameter("sizeSelect"); 
		
		

		if (action != null && codeStr != null && tagliaStr != null) {
			int code = Integer.parseInt(codeStr);
			int taglia = Integer.parseInt(tagliaStr);
			
			
			
			ProductDAO productDao = new ProductDAO();

			switch (action) {
			case "add": {
				try {
					carrello.addProduct(productDao.doRetrieveByKey(code), taglia);
				} catch (SQLException e) {
					logger.log(Level.WARNING, "Problema accesso DB!");
				}
				break;
			}
			case "delete": {
				try {
					carrello.deleteProduct(productDao.doRetrieveByKey(code), taglia);
				} catch (SQLException e) {
					logger.log(Level.WARNING, "Problema accesso DB!");
				}
				break;
			}
			default: break;
			}
		}
		
		request.getSession().setAttribute("carrello", carrello);
		RequestDispatcher dispatcher;

		if (redirect != null && redirect.equals("catalogo")) {
			dispatcher = this.getServletContext().getRequestDispatcher("/common/catalog.jsp");
			dispatcher.forward(request, response);
			return;
		}
		if (redirect != null && redirect.equals("carrello")) {
			dispatcher = this.getServletContext().getRequestDispatcher("/common/cart.jsp");
			dispatcher.forward(request, response);
		}

		
	}

}
