package br.com.dextraining.web.controller;

import javax.faces.event.PhaseEvent;
import javax.faces.event.PhaseId;
import javax.faces.event.PhaseListener;

public class PhaseListenerSample implements PhaseListener {

	public void afterPhase(PhaseEvent event) {
		System.out.println("Iniciando a fase: " + event.getPhaseId().getName());
	}
	
	public void beforePhase(PhaseEvent event) {
		System.out.println("Finalizando a fase: " + event.getPhaseId().getName());
	}
	
	public PhaseId getPhaseId() {
		return PhaseId.ANY_PHASE;
	}
}
