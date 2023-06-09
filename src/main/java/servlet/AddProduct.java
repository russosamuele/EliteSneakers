package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.HelperClass;
import model.ProductBean;
import model.ProductDAO;

/**
 * Servlet implementation class ManagementProduct
 */

@WebServlet("/AddProduct")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
maxFileSize = 1024 * 1024 * 10, // 10MB
maxRequestSize = 1024 * 1024 * 50) // 50MB

public class AddProduct extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static Logger logger = Logger.getAnonymousLogger();
       
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int code = Integer.parseInt(request.getParameter("code"));
		String brand = HelperClass.filter(request.getParameter("brand"));
		String modello = HelperClass.filter(request.getParameter("modello"));
		String descrizione = HelperClass.filter(request.getParameter("descrizione"));
		double prezzo = Double.parseDouble(request.getParameter("prezzo"));
		
		ProductBean bean = new ProductBean();
		bean.setCode(code);
		bean.setBrand(brand);
		bean.setModello(modello);
		bean.setDescrizione(descrizione);
		bean.setPrice(prezzo);
		
		
		ProductDAO dao = new ProductDAO();
		
		try {
			dao.doSave(bean);
		} catch (SQLException e) {
			logger.log(Level.WARNING, "Problema accesso DB!");
		}
		
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/UploadPhoto");
		dispatcher.forward(request, response);
		
		
	}

}
