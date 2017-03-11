package br.com.dextra.treinamento.model.service.interceptor;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import br.com.dextra.treinamento.model.domain.PostAudit;

public class AuditInterceptor {

	@PersistenceContext(unitName = "blog-pu")
	private EntityManager em;

	@Resource
	private SessionContext context;

	@AroundInvoke
	public Object logar(InvocationContext ctx) throws Exception {
		String classe = ctx.getTarget().getClass().getSimpleName();
		String metodo = ctx.getMethod().getName();
		String parametros = ctx.getParameters()[0].toString();

		String login = context.getCallerPrincipal().getName();

		PostAudit postAudit = new PostAudit();
		postAudit.setClasse(classe);
		postAudit.setMetodo(metodo);
		postAudit.setParametros(parametros);
		postAudit.setLogin(login);

		em.persist(postAudit);

		return ctx.proceed();
	}

}
