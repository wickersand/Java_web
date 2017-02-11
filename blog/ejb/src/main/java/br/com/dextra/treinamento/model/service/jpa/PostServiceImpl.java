package br.com.dextra.treinamento.model.service.jpa;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.dextra.treinamento.model.domain.Post;

@Stateless
public class PostServiceImpl implements PostService {

	@PersistenceContext(unitName = "blog-pu")
	private EntityManager em;

	public void salvar(Post p) {
		em.merge(p);
	}

	@SuppressWarnings("unchecked")
	public List<Post> listar() {
		String sql = "FROM Post";
		Query query = em.createQuery(sql);
		return query.getResultList();
	}

	@Override
	public void excluir(Long id) {
		Post post = buscarPorId(id);
		em.remove(post);
	}

	@Override
	public Post buscarPorId(Long id) {
		return em.find(Post.class, id);
	}
}
