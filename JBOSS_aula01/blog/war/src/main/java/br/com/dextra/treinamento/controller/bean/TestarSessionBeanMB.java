package br.com.dextra.treinamento.controller.bean;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import br.com.dextra.treinamento.model.service.sessionbean.TestarStatefulLocal;
import br.com.dextra.treinamento.model.service.sessionbean.TestarStatelessLocal;

@ManagedBean(name = "testarSessionBeanMB")
@RequestScoped
public class TestarSessionBeanMB {
	
	public void testarStateless() {
		try {
			List<Integer> lista = new ArrayList<Integer>();
			InitialContext ctx = new InitialContext();
			TestarStatelessLocal local = (TestarStatelessLocal) ctx.lookup("blog/TestarStatelessImpl/local");
			lista.add(local.adicionar());
			local = (TestarStatelessLocal) ctx.lookup("blog/TestarStatelessImpl/local");
			lista.add(local.adicionar());
			System.out.println("RESULTADO STATETELES:" + lista.toString());
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public void testarStateful() {
		try {
			List<Integer> lista = new ArrayList<Integer>();
			InitialContext ctx = new InitialContext();
			TestarStatefulLocal local = (TestarStatefulLocal) ctx.lookup("blog/TestarStatefulImpl/local");
			lista.add(local.adicionar());
			local = (TestarStatefulLocal) ctx.lookup("blog/TestarStatefulImpl/local");
			lista.add(local.adicionar());
			System.out.println("RESULTADO STATEFUL:" + lista.toString());
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	

}
