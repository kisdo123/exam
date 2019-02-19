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

//로그인체크 필터
public class LoginCheck implements Filter {

	public void init(FilterConfig filterConfig) throws ServletException {
	}

	public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chainObj)
			throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) resp;
		HttpSession session = request.getSession(false);

		// loginForm.do로 접속시 세션에 유저 객체가 존재 하면 메인화면으로 보낸다.
		if (session.getAttribute("loginUser") != null) {
			response.sendRedirect(request.getContextPath() + "/loging.do");
		} else {
			chainObj.doFilter(req, resp);
		}
	}

	public void destroy() {
	}
}