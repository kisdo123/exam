package Filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import User.DTO.User;

public class Admin implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {
	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chainObj)
			throws IOException, ServletException {

		HttpServletRequest request = (HttpServletRequest) req;
		HttpSession session = request.getSession();
		User loginUser = (User)session.getAttribute("loginUser");
		
		
		if (loginUser==null || !loginUser.getAdmin()) {
			
			HttpServletResponse response = (HttpServletResponse) resp;

			response.sendRedirect(request.getContextPath() + "/adminFilter.do");
		} else {
			
			chainObj.doFilter(req, resp);
			
		}

	}

	public void destroy() {
	}
}