package br.com.dextra.treinamento.model.service.interceptor;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class LogInterceptor {
	
	@PostConstruct
	public void construct(InvocationContext ctx) {
		System.out.println("EJB FOI CRIADO: " + ctx.getTarget().getClass().getSimpleName());
	}
	
	@PreDestroy
	public void destroy(InvocationContext ctx) {
		System.out.println("EJB FOI DESTRUIDO");
	}
	
	@PrePassivate
	public void passivate(InvocationContext ctx) {
		System.out.println("EJB FOI PASSIVADO");
	}
	
	@PostActivate
	public void activate(InvocationContext ctx) {
		System.out.println("EJB FOI ATIVADO");
	}
	
	@AroundInvoke
	public Object logar(InvocationContext ctx) throws Exception {
		String classe = ctx.getTarget().getClass().getSimpleName();
		String metodo = ctx.getMethod().getName();
		System.out.println("NOME DA CLASSE:" + classe);
		System.out.println("NOME DO METODO: " + metodo);
		return ctx.proceed();
	}
	
	

}
