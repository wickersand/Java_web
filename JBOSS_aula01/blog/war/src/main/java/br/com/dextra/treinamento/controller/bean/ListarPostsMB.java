package br.com.dextra.treinamento.controller.bean;

import java.util.List;

import javax.ejb.EJB;
import javax.ejb.EJBAccessException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import br.com.dextra.treinamento.model.domain.Post;
import br.com.dextra.treinamento.model.service.jpa.PostServiceLocal;

@ManagedBean(name = "listarPostsMB")
@SessionScoped
public class ListarPostsMB {

	private static final String INDEX_XHTML = "index.xhtml";

	private static final String INCLUIR_POST = "incluirPost.xhtml";

	private static final String LISTAR_POSTS = "listarPosts.xhtml";

	private List<Post> posts;

	private Post post = new Post();

	private Post novoPost;

	@EJB
	private PostServiceLocal postServiceLocal;

	public String listar() {
		posts = this.postServiceLocal.listar();
		return LISTAR_POSTS;
	}

	public String incluir() {
		this.novoPost = new Post();
		return INCLUIR_POST;
	}

	public String salvar() {
		this.postServiceLocal.salvar(novoPost);
		return listar();
	}

	public String cancelar() {
		return LISTAR_POSTS;
	}

	public String prepararAlteracao() {
		post = this.postServiceLocal.buscarPorId(Long.valueOf(this.obterParametro("id")));
		return "alterarPost.xhtml";
	}

	public String alterar() {
		this.postServiceLocal.salvar(post);
		return listar();
	}

	public String remover() {

		try {
			this.postServiceLocal.excluir(Long.valueOf(this.obterParametro("id")));
		} catch (EJBAccessException e) {
			// mandar msg de erro
			adicionarMensagem("USUARIO SEM PERMISSAO A ESSA FUNCIONALIDADE!");
		}

		return listar();
	}

	private void adicionarMensagem(String mensagem) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		FacesMessage facesMessage = new FacesMessage(mensagem);
		facesContext.addMessage(null, facesMessage);
	}

	private String obterParametro(String nome) {
		return FacesContext.getCurrentInstance().getExternalContext().getRequestParameterMap().get(nome);
	}

	public String voltar() {
		return INDEX_XHTML;
	}

	public List<Post> getPosts() {
		return posts;
	}

	public void setPosts(List<Post> posts) {
		this.posts = posts;
	}

	public Post getNovoPost() {
		return novoPost;
	}

	public void setNovoPost(Post novoPost) {
		this.novoPost = novoPost;
	}

	public Post getPost() {
		return post;
	}

	public void setPost(Post post) {
		this.post = post;
	}
}
