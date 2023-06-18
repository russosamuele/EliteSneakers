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
import model.FinalProduct;
import model.ProductBean;
import model.ProductDAO;

/**
 * Servlet implementation class GetSneakersList
 */
@WebServlet("/GetSneaker")
public class GetSneaker extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static Logger logger = Logger.getAnonymousLogger();

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductDAO pDao = new ProductDAO();
		DisponibilitaDAO dDao = new DisponibilitaDAO();
		ProductBean pBean = null;
		int code = (int) request.getAttribute("code");
		try {
			pBean = pDao.doRetrieveByKey(code);
		} catch (SQLException e) {
			logger.log(Level.WARNING, "Problema accesso DB!");
		}
		FinalProduct finalToPut = null;
		try {
			finalToPut = new FinalProduct(pBean, dDao.doRetrieveByKey(pBean.getCode()));
		} catch (SQLException e) {
			logger.log(Level.WARNING, "Problema accesso DB!");
		}

		request.setAttribute("sneaker", finalToPut);
		int idRequest = (int) request.getAttribute("id");
		RequestDispatcher dispatcher;
		
		switch(idRequest) {
		case 1: 
			dispatcher = this.getServletContext().getRequestDispatcher("/common/product.jsp");
			dispatcher.forward(request, response);
			break;
		default:
			break;
		}
		
		
		
	}

}
