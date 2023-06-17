package servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Cart;
import model.CartItem;
import model.DettaglioOrdineBean;
import model.DettaglioOrdineDAO;
import model.DisponibilitaBean;
import model.DisponibilitaDAO;
import model.OrdineBean;
import model.UserBean;
import model.OrdineDAO;

/**
 * Servlet implementation class CheckoutServlet
 */
@WebServlet("/CheckoutServlet")
public class CheckoutServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private static Logger logger = Logger.getAnonymousLogger();

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		Cart carrello = (Cart) request.getSession().getAttribute("carrello");

		if (carrello == null) {
			request.getRequestDispatcher("common/login.jsp").forward(request, response);
			return;
		}

		List<CartItem> elementi = carrello.getProducts(); // recupero elementi nel carrello

		if (elementi == null || elementi.isEmpty()) {
			String error = "non puoi procedere al pagamento con un carrello vuoto!";
			request.setAttribute("error", error);
			request.getRequestDispatcher("common/cart.jsp").forward(request, response);
			return;
		}

		OrdineBean ordine = new OrdineBean();

		UserBean user = (UserBean) request.getSession().getAttribute("user"); // recupero info utente per salvare l'ordine

		if (user == null) {
			request.getRequestDispatcher("common/login.jsp").forward(request, response);
			return;
		}
		
		//verifico se c'è disponibilità per i prodotti
		DisponibilitaDAO dao = new DisponibilitaDAO();
		
		for (CartItem elem : elementi) {
			try {
				DisponibilitaBean disp = dao.doRetrieveByKey(elem.getProductBean().getCode(), elem.getTaglia());
				if(disp.getQuantita() < elem.getQuantita()) {
					String QtaError = "Del prodotto " + elem.getProductBean().getBrand() + " " + elem.getProductBean().getModello() + " sono disponibili solo: " + disp.getQuantita() + " pezzi";
					request.setAttribute("QtaError", QtaError);
					request.getRequestDispatcher("common/cart.jsp").forward(request, response);
					return;
				}
					
			} catch (SQLException e) {
				logger.log(Level.WARNING, "Problema accesso DB!");
			}
		}
		
		//effettuo l'ordine

		OrdineDAO ordinedao = new OrdineDAO();
		
		int numeroOrd = 0;

		try {
			numeroOrd = ordinedao.doRetrieveMaxNumOrdine() + 1;
		} catch (SQLException e1) {
			logger.log(Level.WARNING, "Problema accesso DB!");
		}

		ordine.setNumeroOrd(numeroOrd);
		ordine.setEmail(user.getEmail());
		ordine.setDataOrdine(new java.sql.Date(System.currentTimeMillis()));

		try {
			ordinedao.doSave(ordine);
		} catch (SQLException e) {
			logger.log(Level.WARNING, "Problema accesso DB!");
		}

		DettaglioOrdineDAO dettaglioOrdineDAO = new DettaglioOrdineDAO();

		try {
			for (CartItem elem : elementi) {
				DettaglioOrdineBean dettaglioOrdine = new DettaglioOrdineBean();
				dettaglioOrdine.setCodiceProdotto(elem.getProductBean().getCode());
				dettaglioOrdine.setQuantita(elem.getQuantita());
				dettaglioOrdine.setPrezzo(elem.getProductBean().getPrice());
				dettaglioOrdine.setNumeroOrd(numeroOrd);

				dettaglioOrdineDAO.doSave(dettaglioOrdine);

			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		//riduco le quantità nel DB
		DisponibilitaDAO dDDAO = new DisponibilitaDAO();
		for (CartItem elem : elementi) {
			try {
				dDDAO.doUpdate(elem.getProductBean().getCode(), elem.getTaglia(), elem.getQuantita());
			} catch (SQLException e) {
				logger.log(Level.WARNING, "Problema accesso DB!");
			}
		}
		
		
		request.getSession().setAttribute("carrello", null);
		
		request.getRequestDispatcher("common/index.jsp").forward(request, response);
		
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

}
