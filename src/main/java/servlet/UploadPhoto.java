package servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import model.PhotoControl;

@WebServlet("/UploadPhoto")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, // 2MB
		maxFileSize = 1024 * 1024 * 10, // 10MB
		maxRequestSize = 1024 * 1024 * 50) // 50MB

public class UploadPhoto extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public UploadPhoto() {
		super();
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int code = Integer.parseInt(request.getParameter("code"));
		
		System.out.println(code);
		
		Part filePart = request.getPart("photo");
                
        try {
			PhotoControl.updatePhoto(code, filePart.getInputStream());
		} catch (SQLException sqlException) {
			System.out.println(sqlException);
		}
        
        

		RequestDispatcher view = request.getRequestDispatcher("/admin/AggiungiProdotto.jsp");
		view.forward(request, response);
	}

}

