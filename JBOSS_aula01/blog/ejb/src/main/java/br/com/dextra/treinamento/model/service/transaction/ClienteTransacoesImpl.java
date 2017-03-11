package br.com.dextra.treinamento.model.service.transaction;

import javax.ejb.EJB;
import javax.ejb.Stateless;

@Stateless
public class ClienteTransacoesImpl implements ClienteTransacoesLocal {

	@EJB
	private TestarTransacoesLocal testarTransacoesLocal;

	@Override
	public void testarRequired() {

		testarTransacoesLocal.criarPostRequired("titulo required");
		testarTransacoesLocal.criarPostRequired(null);

	}

	@Override
	public void testarRequiresNew() {
		testarTransacoesLocal.criarPostRequiresNew("titulo requires new");
		testarTransacoesLocal.criarPostRequiresNew(null);

	}

}
