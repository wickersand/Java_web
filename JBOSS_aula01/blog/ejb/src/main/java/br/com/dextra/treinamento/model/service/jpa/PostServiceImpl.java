package br.com.dextra.treinamento.model.service.jpa;

import java.util.List;

import javax.annotation.security.RolesAllowed;
import javax.ejb.Stateless;
import javax.interceptor.Interceptors;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.dextra.treinamento.model.domain.Post;
import br.com.dextra.treinamento.model.service.interceptor.AuditInterceptor;
import br.com.dextra.treinamento.model.service.interceptor.LogInterceptor;

@Stateless
@Interceptors(value = {LogInterceptor.class})
public class PostServiceImpl implements PostServiceLocal {
	
	@PersistenceContext(unitName = "blog-pu")
	private EntityManager em;

	@Interceptors(value = {AuditInterceptor.class})
	public void salvar(Post p) {
		em.merge(p);
	}
	
	@SuppressWarnings("unchecked")
	public List<Post> listar() {
		String sql = "FROM Post";
		Query query = em.createQuery(sql);
		return query.getResultList();
	}

	@RolesAllowed("administrador")
	@Override
	@Interceptors(value = {AuditInterceptor.class})
	public void excluir(Long id) {
		Post post = buscarPorId(id);
		em.remove(post);
	}

	@Override
	public Post buscarPorId(Long id) {
		return em.find(Post.class, id);
	}

}
