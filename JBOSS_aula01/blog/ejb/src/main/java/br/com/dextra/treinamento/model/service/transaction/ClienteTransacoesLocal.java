package br.com.dextra.treinamento.model.service.transaction;

import javax.ejb.Local;

@Local
public interface ClienteTransacoesLocal {

	void testarRequired();

	void testarRequiresNew();

}
