package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.ProductDAO;

/**
 * Servlet implementation class DeleteProdotto
 */
@WebServlet("/DeleteProdotto")
public class DeleteProdotto extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String c = request.getParameter("code");
		String message = "";
		
		if(c!=null) {
			int code = Integer.parseInt(c);
			ProductDAO dao = new ProductDAO();
			try {
				dao.doDelete(code);
				message = "prodotto eliminato con successo!";
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		request.setAttribute("message", message);
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/admin/RimuoviProdotto.jsp");
		dispatcher.forward(request, response);
		
		
			
		
		
		
	}

}
