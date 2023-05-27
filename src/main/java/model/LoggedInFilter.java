package model;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class LoggedInFilter
 */
@WebFilter(filterName = "/LoggedInFilter", urlPatterns = {"/common/checkout.jsp"})
public class LoggedInFilter extends HttpFilter implements Filter {

	private static final long serialVersionUID = 1L;

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;

		String logged = (String) req.getSession().getAttribute("isLogged");
		if (logged == null || logged.equals("false")) {
			request.getRequestDispatcher("/common/login.jsp").forward(req, res);
			return;
		}

		chain.doFilter(request, response);
	}

}
