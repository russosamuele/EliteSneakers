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
@WebServlet("/GetSneakersList")
public class GetSneakersList extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductDAO PDao = new ProductDAO();
		DisponibilitaDAO DDao = new DisponibilitaDAO();
		List <FinalProduct> listSneakers = new ArrayList<>();
		ArrayList<ProductBean> listProductBean = null;

		try {
			listProductBean = new ArrayList<>(PDao.doRetrieveAll("brand"));
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (ProductBean pBean : listProductBean) {	
			try {
				FinalProduct finalToPut = new FinalProduct(pBean, DDao.doRetrieveByKey(pBean.getCode()));
				listSneakers.add(finalToPut);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		request.setAttribute("listSneakers", listSneakers);
		int idRequest = (int) request.getAttribute("id");
		RequestDispatcher dispatcher;
		switch(idRequest) {
		case 1: 
			dispatcher = this.getServletContext().getRequestDispatcher("/common/catalog.jsp");
			dispatcher.forward(request, response);
			break;
		case 2:
			dispatcher = this.getServletContext().getRequestDispatcher("/admin/RimuoviProdotto.jsp");
			dispatcher.forward(request, response);
			break;
		
		case 3:
			dispatcher = this.getServletContext().getRequestDispatcher("/admin/AggiungiDisponibilita.jsp");
			dispatcher.forward(request, response);
			break;
		case 4:
			dispatcher = this.getServletContext().getRequestDispatcher("/admin/ModificaProdotto.jsp");
			dispatcher.forward(request, response);
			break;
		
		}
		
		
	}

}
