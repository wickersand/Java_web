package br.com.javaweb.gerenciador.web;

import java.io.IOException;
//import java.io.PrintWriter;
import java.util.Collection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import br.com.javaweb.gerenciador.Empresa;
import br.com.javaweb.gerenciador.dao.EmpresaDAO;

/**
 * Servlet implementation class BuscaEmpresa
 */
@WebServlet("/buscaempresa")
public class BuscaEmpresa extends HttpServlet {
	// String filtro;
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public BuscaEmpresa() {
		super();

	}

	@Override
	public void init() throws ServletException {
		super.init();
		System.out.println("Inicializando a Servlet" + this);
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String filtro = request.getParameter("filtro"); //Criar string filtro para informar os acessos no console.
		
		Collection<Empresa> empresas = 
				new EmpresaDAO().buscaPorSimilaridade(filtro); // criar uma colecao para listar as empresas
		request.setAttribute("empresas", empresas); //requisita o conteudo das empresas.
		request.getRequestDispatcher("/WEB-INF/paginas/buscaEmpresa.jsp").
		forward(request, response); //direciona para a pagina protegida e mostra a lista de empresas.
				
	}

	@Override
	public void destroy() {
		super.destroy();
		System.out.println("Destruindo a Servlet" + this);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
