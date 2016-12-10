package br.com.javaweb.gerenciador.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import br.com.javaweb.gerenciador.Empresa;
import br.com.javaweb.gerenciador.dao.EmpresaDAO;

/**
 * Servlet implementation class AdicionarEmpresa
 */
@WebServlet("/addcompany")
public class AdicionarEmpresa extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AdicionarEmpresa() {
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

		String nome = request.getParameter("nome"); // Criar objeto nome
		int statusCode = HttpServletResponse.SC_OK; //Retorna o Status Code (200 = PASS)
		Empresa empresa = new Empresa(nome); // Criar objeto empresa
		new EmpresaDAO().adiciona(empresa); //Invocar o metodo adiciona da EmpresaDAO
		PrintWriter writer = response.getWriter(); //Criar buffer do writer - arcaico
		writer.println("<html><body>Empresa " 
				+ nome 
				+ " Adicionada com Sucesso!" 
				+ " Status: \n" 
				+ statusCode
				+ "</body></html>");

		doGet(request, response);
	}

}
