package br.com.dextra.treinamento.controller.bean;

import java.net.MalformedURLException;
import java.net.URL;

import javax.faces.bean.ManagedBean;
import javax.xml.namespace.QName;
import javax.xml.rpc.Service;
import javax.xml.rpc.ServiceException;
import javax.xml.rpc.ServiceFactory;

import br.com.dextra.treinamento.model.service.webservice.TesteWebServiceInterface;

@ManagedBean(name = "testarWebServiceMB")
public class TestarWebServiceMB {
	
	private String titulo;
	
	private String descricao;
	
	public void salvarPost() {
		try {
			TesteWebServiceInterface obtemWS = obtemWS();
			obtemWS.criarPost(titulo, descricao);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	
	public void testarChamada() {
		try {
			TesteWebServiceInterface obtemWS = obtemWS();
			obtemWS.testarChamadaWS();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (ServiceException e) {
			e.printStackTrace();
		}
	}
	

	private TesteWebServiceInterface obtemWS() throws ServiceException,
			MalformedURLException {
		String wsdlURL = "http://localhost:8080/blog-ejb-1.0-SNAPSHOT/TesteWebServiceImplService/TesteWebServiceImpl?wsdl";
		
		String targetNamespace = TesteWebServiceInterface.TARGET_NAME_SPACE;
		String serviceName = TesteWebServiceInterface.SERVICE_NAME;
		String portName = TesteWebServiceInterface.PORT_NAME;

		QName serviceQN = new QName(targetNamespace, serviceName);

		ServiceFactory serviceFactory = ServiceFactory.newInstance();
		Service service = serviceFactory.createService(new URL(wsdlURL), serviceQN);
		System.out.println("Criou o servico");
		
		TesteWebServiceInterface testeWebService = (TesteWebServiceInterface) service
				.getPort(new QName(targetNamespace, portName),
						TesteWebServiceInterface.class);
		return testeWebService;
	}


	public String getTitulo() {
		return titulo;
	}


	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}


	public String getDescricao() {
		return descricao;
	}


	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}
	
}
