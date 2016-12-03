package br.com.javaweb.gerenciador.web;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.javaweb.gerenciador.Usuario;
import br.com.javaweb.gerenciador.dao.UsuarioDAO;

/**
 * Servlet implementation class UserLogin
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
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

		String email = request.getParameter("email"); //Cria objeto email
		String senha = request.getParameter("senha"); //Cria objeto senha

		Usuario usuario = new UsuarioDAO().buscaPorEmailESenha(email, senha); //chama metodo Usuario onde contem email e senha.

		if (usuario == null) {

			response.sendRedirect("/ExemploServlets/errorlogin.html");//Redireciona para html de erro!

		} else {
			Cookie cookie = new Cookie("usuario.logado", email);
			response.addCookie(cookie);
			response.sendRedirect("/ExemploServlets/adicionaempresa.html"); //Redireciona para o html principal.
		}

		doGet(request, response);
	}

}
