package br.com.dextra.treinamento.model.service.timer;

import javax.ejb.Local;

@Local
public interface TesteTimerLocal {
	
	void agendar(long inicio, long intervalo);
	
	void cancelar();

}
