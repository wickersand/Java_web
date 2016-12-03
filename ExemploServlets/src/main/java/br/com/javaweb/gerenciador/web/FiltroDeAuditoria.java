package br.com.javaweb.gerenciador.web;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet Filter implementation class FiltroDeAuditoria
 */
@WebFilter(urlPatterns = "/*")
public class FiltroDeAuditoria implements Filter {

	/**
	 * Default constructor.
	 */
	public FiltroDeAuditoria() {
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		Cookie cookie = getUsuario(req);
		String usuario = "<deslogado>";
		
		if (cookie != null) {
			cookie.setMaxAge(60*10);
			resp.addCookie(cookie);
			usuario = cookie.getValue();
		}
			
		System.out.println("Usuario " + usuario + "acessando " + req.getRequestURI());

		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

	private Cookie getUsuario(HttpServletRequest req) {
		Cookie[] cookies = req.getCookies();
		if (cookies == null)
			return null;
		for (Cookie cookie : cookies) {
			if (cookie.getName().equals("usuario.logado")) {
				return cookie;
			}
		}
		return null;
	}

}
