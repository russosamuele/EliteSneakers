package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import model.UserBean;
import model.UserDAO;

/**
 * Servlet implementation class EmailDisponibilityJSON
 */

@WebServlet("/EmailDisponibility")
public class EmailDisponibility extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	 
	
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws SQLException, IOException, ParseException {
    	
    	UserDAO dao = new UserDAO();
    	UserBean user = null;
    	    	
    	response.setContentType("text/xml");
        PrintWriter out = response.getWriter();
        String email = request.getParameter("email");
        
        if (email != null && !email.equals("")) {
            user = dao.doRetrieveByKey(email);
        }
        String risultato = null;
        if (user != null && !user.getEmail().trim().equals("")) {
            risultato = "email già utilizzata";
        } else {
            risultato = " ";
        }
        out.println("<?xml version=\"1.0\" encoding=\"iso-8859-1\" ?>");
        out.println("<response>");
        out.println("<functionName>handleEmail</functionName>");
        out.println("<result>" + risultato + "</result>");
        out.println("</response>");
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
       
			try {
				processRequest(request, response);
			} catch (SQLException | IOException | ParseException e) {
				e.printStackTrace();
			}
		
		
    }
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
			processRequest(request, response);
		} catch (IOException | SQLException | ParseException e) {
			e.printStackTrace();
		}
    }
    

}
