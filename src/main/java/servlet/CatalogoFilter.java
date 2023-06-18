package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

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
 * Servlet implementation class FiltraCatalogoServlet
 */
@WebServlet("/CatalogoFilter")
public class CatalogoFilter extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static Logger logger = Logger.getAnonymousLogger();
       
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		String brand = request.getParameter("brand");
		String prezzoMin = request.getParameter("prezzoMin");
		String prezzoMax = request.getParameter("prezzoMax");
		
		boolean brandAll = false;
		
		if(brand == null || brand.equals("-"))
			brandAll = true;
		
		if(prezzoMin == null || prezzoMin.equals(""))
			prezzoMin = "0";
		
		if(prezzoMax == null || prezzoMax.equals(""))
			prezzoMax = "1999";
		
		
		ProductDAO dao = new ProductDAO();
		ArrayList<ProductBean> listProductBean = null;
		
		final String logString = "Problema accesso DB!";
	
		
		
		if(brandAll) {
			try {
				listProductBean = new ArrayList<>(dao.doRetrieveByFiltri(prezzoMin, prezzoMax));
			} catch (SQLException e) {
				logger.log(Level.WARNING, logString);
			}
		
		}else {
			try {
				listProductBean = new ArrayList<>(dao.doRetrieveByFiltri(prezzoMin, prezzoMax, brand));
			} catch (SQLException e) {
				logger.log(Level.WARNING, logString);
			}
			
		}
		
		List <FinalProduct> listSneakers = new ArrayList<>();
		DisponibilitaDAO dDao = new DisponibilitaDAO();
		
		for (ProductBean pBean : listProductBean) {	
			try {
				FinalProduct finalToPut = new FinalProduct(pBean, dDao.doRetrieveByKey(pBean.getCode()));
				listSneakers.add(finalToPut);
			} catch (SQLException e) {
				logger.log(Level.WARNING, logString);
			}
		}
		
		request.setAttribute("listSneakers", listSneakers);
		request.getServletContext().getRequestDispatcher("/common/catalog.jsp").forward(request, response);
		
	}

}
