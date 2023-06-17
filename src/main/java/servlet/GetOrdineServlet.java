package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.DettaglioOrdineBean;
import model.DettaglioOrdineDAO;
import model.DisponibilitaDAO;
import model.FinalOrder;
import model.FinalProduct;
import model.OrdineDAO;
import model.ProductBean;
import model.ProductDAO;

/**
 * Servlet implementation class GetOrdineServlet
 */
@WebServlet("/GetOrdineServlet")
public class GetOrdineServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ProductDAO PDao = new ProductDAO();
		DettaglioOrdineDAO DODao = new DettaglioOrdineDAO();
		ProductBean pBean = null;
		
		OrdineDAO ODao = new OrdineDAO();
		
		
		int code = Integer.parseInt(request.getParameter("code"));
		List <DettaglioOrdineBean> listaDettagliOrdine = null;
		
		try {
			listaDettagliOrdine = (List<DettaglioOrdineBean>) DODao.doRetrieveByKey(code);
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		List <String> nomeSneaker = new LinkedList<>();
		
		try {
		for (int i=0; i< listaDettagliOrdine.size(); i++) {
			
			pBean = PDao.doRetrieveByKey(listaDettagliOrdine.get(i).getCodiceProdotto());
			String nomeBrandModello = pBean.getBrand() +" "+ pBean.getModello();
			nomeSneaker.add(i, nomeBrandModello);
		}
		}catch (SQLException e) {
			e.printStackTrace();
		}
		
		FinalOrder finalToPut = null;
		try {
			finalToPut = new FinalOrder(ODao.doRetrieveByKey(code), listaDettagliOrdine, nomeSneaker);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		request.setAttribute("ordine", finalToPut);
		RequestDispatcher dispatcher = this.getServletContext().getRequestDispatcher("/common/DettaglioOrdine.jsp");
		dispatcher.forward(request, response);
			
		
	
	}

}
