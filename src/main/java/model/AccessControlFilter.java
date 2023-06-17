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
 * Servlet Filter implementation class AccessControlFilter
 */
@WebFilter(filterName = "/AccessControlFilter", urlPatterns = "/*")
public class AccessControlFilter extends HttpFilter implements Filter {
       
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) 
			throws IOException, ServletException {
		
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;
		
		String str = (String) httpServletRequest.getSession().getAttribute("isAdmin");
		
		boolean isAdmin=false;
		if(str != null && str.equals("true"))
			isAdmin = true;
		
	
		String path = httpServletRequest.getServletPath();
		
		if (path.contains("/admin/") && (!isAdmin)) {
			httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/common/index.jsp");
			return;
		}
		
		chain.doFilter(request, response);
	}
	
	public void init(FilterConfig fConfig) throws ServletException {
	}
	
    public void destroy() {
	}

	private static final long serialVersionUID = 1L;

}


	
