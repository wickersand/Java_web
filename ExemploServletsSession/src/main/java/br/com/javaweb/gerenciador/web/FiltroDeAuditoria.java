package br.com.javaweb.gerenciador.web;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;

import br.com.javaweb.gerenciador.Usuario;

/**
 * Servlet Filter implementation class FiltroDeAuditoriaSession
 */
@WebFilter("/*")
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
				
		String uri = req.getRequestURI();
		String usuario = getUsuario(req);

		System.out.println("Usuario " + usuario + " acessando " + uri);

		// pass the request along the filter chain
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

	private String getUsuario(HttpServletRequest req) {

		Usuario usuario = (Usuario) 
				req.getSession().getAttribute("usuario.logado");
		if (usuario == null)
			return "<deslogado>";
		return usuario.getEmail();

	}

}
