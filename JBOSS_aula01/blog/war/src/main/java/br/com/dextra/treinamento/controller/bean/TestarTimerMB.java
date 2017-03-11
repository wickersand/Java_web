package br.com.dextra.treinamento.controller.bean;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.dextra.treinamento.model.service.timer.TesteTimerLocal;

@ManagedBean(name = "testarTimerMB")
@RequestScoped
public class TestarTimerMB {
	
	@EJB
	private TesteTimerLocal testeTimerLocal;
	
	public void agendar() {
		long intevarlo = 1*30*1000;
		testeTimerLocal.agendar(intevarlo, intevarlo);
		System.out.println("timer agendado :D");
	}
	
	public void cancelar() {
		testeTimerLocal.cancelar();
		System.out.println("timer cancelado :/");
	}
	

}
