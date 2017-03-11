package br.com.dextra.treinamento.model.service.transaction;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.dextra.treinamento.model.domain.Post;

@Stateless
public class TestarTransacoesImpl implements TestarTransacoesLocal {

	@PersistenceContext(unitName = "blog-pu")
	private EntityManager em;

	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@Override
	public void criarPostRequired(String titulo) {
		Post post = new Post();
		post.setTitulo(titulo.toString());
		em.persist(post);

	}

	@TransactionAttribute(TransactionAttributeType.REQUIRES_NEW)
	@Override
	public void criarPostRequiresNew(String titulo) {

		Post post = new Post();
		post.setTitulo(titulo.toString());
		em.persist(post);
	}

}
