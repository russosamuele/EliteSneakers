package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DisponibilitaBean;
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

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductDAO PDao = new ProductDAO();
		DisponibilitaDAO DDao = new DisponibilitaDAO();
		ProductBean pBean = null;
		int code = (int) request.getAttribute("code");
		try {
			pBean = PDao.doRetrieveByKey(code);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		FinalProduct finalToPut = null;
		try {
			finalToPut = new FinalProduct(pBean, DDao.doRetrieveByKey(pBean.getCode()));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		request.setAttribute("sneaker", finalToPut);
		int idRequest = (int) request.getAttribute("id");
		RequestDispatcher dispatcher;
		switch(idRequest) {
		case 1: 
			dispatcher = this.getServletContext().getRequestDispatcher("/common/product.jsp");
			dispatcher.forward(request, response);
			break;
		}
		
		
	}

}
