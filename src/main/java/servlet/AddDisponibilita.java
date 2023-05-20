package servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DisponibilitaBean;
import model.DisponibilitaDAO;
import model.HelperClass;
import model.ProductBean;
import model.ProductDAO;

/**
 * Servlet implementation class AddDisponibilita
 */
@WebServlet("/AddDisponibilita")

public class AddDisponibilita extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
 
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		int code = Integer.parseInt(request.getParameter("code"));
		int taglia = Integer.parseInt(request.getParameter("taglia"));
		int quantita = Integer.parseInt(request.getParameter("quantita"));

		
		DisponibilitaBean bean = new DisponibilitaBean();
		bean.setCodice_prod(code);
		bean.setTaglia(taglia);
		bean.setQuantita(quantita);		
		DisponibilitaDAO  dao = new DisponibilitaDAO();
		
		try {
			DisponibilitaBean disp = dao.doRetrieveByKey(code, taglia);
			if (disp != null) {
				dao.doDelete(code, taglia);
				bean.setQuantita(quantita+disp.getQuantita());
			}
			dao.doSave(bean);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/admin/AggiungiDisponibilita.jsp");
		dispatcher.forward(request, response);
		
		
	}

}
