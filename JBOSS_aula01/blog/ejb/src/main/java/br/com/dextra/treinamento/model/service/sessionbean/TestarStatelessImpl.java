package br.com.dextra.treinamento.model.service.sessionbean;

import javax.ejb.Stateless;

@Stateless
public class TestarStatelessImpl implements TestarStatelessLocal {

	Integer contador = 0;
	
	@Override
	public Integer adicionar() {
		contador++;
		return contador;
	}

}
