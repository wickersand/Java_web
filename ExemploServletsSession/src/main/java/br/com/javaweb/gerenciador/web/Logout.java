package br.com.javaweb.gerenciador.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class LogoutSession
 */
@WebServlet("/logoutsession")
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Logout() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		if (session.getAttribute("usuario.logado") != null) {

			session.removeAttribute("usuario.logado");
			session.invalidate();
			/*
			 * Desprotegido - usuario pode digitar o URL e deslogar via atributo
			 * no browser.
			 */
			// response.sendRedirect("/ExemploServletsSession/logout.html");
			
			/*
			 * Protegido - usuario nao tem acesso para digitar URL logout.html direto no browser.
			 */
		}

		RequestDispatcher dispatcher = request.getRequestDispatcher("/WEB-INF/paginas/logout.html");
		dispatcher.forward(request, response);
	}

}
