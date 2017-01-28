package br.com.controlelivros.web.controller;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import br.com.controlelivros.domain.Livro;
import br.com.controlelivros.domain.LivroService;

@ManagedBean(name = "livrosMB")
@SessionScoped
public class LivrosMB {

	private List<Livro> livros;

	private Livro livro;

	public List<Livro> getLivros() {
		return livros;
	}

	public void setLivros(List<Livro> livros) {
		this.livros = livros;
	}

	public Livro getLivro() {
		return livro;
	}

	public void setLivro(Livro livro) {
		this.livro = livro;
	}

	public String salvar() {
		LivroService.salvar(livro);
		FacesUtils.createSuccessMessage("Livro Salvo com Sucesso!");
		return "listagem?faces-redirect=true";
	}

	public String irParaNovoLivro() {
		this.livro = new Livro();
		return "novoLivro?faces-redirect=true";
	}

}
