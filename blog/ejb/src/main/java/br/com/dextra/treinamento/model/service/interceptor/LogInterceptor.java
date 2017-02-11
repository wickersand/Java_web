package br.com.dextra.treinamento.model.service.interceptor;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.PostActivate;
import javax.ejb.PrePassivate;
import javax.interceptor.InvocationContext;

public class LogInterceptor {

	@PostConstruct
	public void construct(InvocationContext ctx) {
		System.out.println("EJB FOI CRIADO: " + ctx.getTarget().getClass().getSimpleName());
	}

	@PostActivate
	public void active(InvocationContext ctx) {
		System.out.println("EJB FOI ATIVADO: " + ctx.getTarget().getClass().getSimpleName());
	}

	@PrePassivate
	public void passivate(InvocationContext ctx) {
		System.out.println("EJB FOI PASSIVATED: " + ctx.getTarget().getClass().getSimpleName());
	}

	@PreDestroy
	public void destroy(InvocationContext ctx) {
		System.out.println("EJB FOI DESTRUIDO: " + ctx.getTarget().getClass().getSimpleName());
	}

}
