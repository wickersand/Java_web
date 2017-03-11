package br.com.dextra.treinamento.controller.bean;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.dextra.treinamento.model.service.jms.TesteJMSLocal;

@ManagedBean(name = "testarMensagemMB")
@RequestScoped
public class TestarMensagemMB {
	
	@EJB
	private TesteJMSLocal testeJMSLocal;
	
	public void enviarMensagem() {
		testeJMSLocal.enviarMensagemQueue();
	}

}
