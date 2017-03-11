package br.com.dextra.treinamento.model.service.sessionbean;

import javax.ejb.Stateful;
import javax.interceptor.Interceptors;

import br.com.dextra.treinamento.model.service.interceptor.LogInterceptor;

@Stateful
@Interceptors(value = LogInterceptor.class)
public class TestarStatefulImpl implements TestarStatefulLocal {

	Integer contador = 0;
	
	@Override
	public Integer adicionar() {
		contador++;
		return contador;
	}

}
