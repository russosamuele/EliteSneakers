package servlet;

import java.io.IOException;
import java.sql.SQLException;

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
       
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int code = Integer.parseInt(request.getParameter("code"));
		String brand = HelperClass.filter(request.getParameter("brand"));
		String modello = HelperClass.filter(request.getParameter("modello"));
		String descrizione = HelperClass.filter(request.getParameter("descrizione"));
		int quantita = Integer.parseInt(request.getParameter("quantita"));
		double prezzo = Double.parseDouble(request.getParameter("prezzo"));
		
		ProductBean bean = new ProductBean();
		bean.setCode(code);
		bean.setBrand(brand);
		bean.setModello(modello);
		bean.setDescrizione(descrizione);
		bean.setQuantity(quantita);
		bean.setPrice(prezzo);
		
		
		ProductDAO dao = new ProductDAO();
		
		try {
			dao.doSave(bean);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/UploadPhoto");
		dispatcher.forward(request, response);
		
		
	}

}
