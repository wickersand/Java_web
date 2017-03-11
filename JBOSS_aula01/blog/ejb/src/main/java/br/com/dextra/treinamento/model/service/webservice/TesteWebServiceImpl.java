package br.com.dextra.treinamento.model.service.webservice;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

import br.com.dextra.treinamento.model.domain.Post;
import br.com.dextra.treinamento.model.service.jpa.PostServiceLocal;

@Stateless
@WebService(name = TesteWebServiceInterface.WEB_SERVICE_NAME,
	serviceName = TesteWebServiceInterface.SERVICE_NAME,
	portName = TesteWebServiceInterface.PORT_NAME,
	targetNamespace = TesteWebServiceInterface.TARGET_NAME_SPACE)
@SOAPBinding(style = Style.RPC)
public class TesteWebServiceImpl implements TesteWebServiceInterface {

	@EJB
	private PostServiceLocal postServiceLocal;
	
	@WebMethod
	@Override
	public void testarChamadaWS() {
		System.out.println("O WS foi chamado..");
	}

	@WebMethod
	@Override
	public void criarPost(@WebParam(name = "titulo") String titulo, 
			@WebParam(name = "descricao")String descricao) {
		postServiceLocal.salvar(new Post(titulo, descricao));
	}

}
