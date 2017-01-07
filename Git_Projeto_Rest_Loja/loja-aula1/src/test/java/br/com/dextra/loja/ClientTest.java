package br.com.dextra.loja;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.core.Response;

import junit.framework.Assert;

import org.glassfish.grizzly.http.server.HttpServer;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;

import br.com.dextra.loja.modelo.Carrinho;
import br.com.dextra.loja.modelo.Produto;

public class ClientTest {

	private HttpServer server;

	@Before
	public void inicializaServidor() {
		this.server = Servidor.inicializaServidor();
	}

	@After
	public void paraServidor() {
		server.stop();
	}
	

	@Test // Anotação de definição de testes
	public void testaQueBuscarUmCarrinhoTrazOCarrinhoEsperado() {
		Client client = ClientBuilder.newClient();

		WebTarget target = client.target("http://localhost:8080");
		
		String conteudo = target.path("/carrinhos").queryParam("id", 1).request().get(String.class);

		/* Outro metodo para declarar o objeto conteudo*/
		//String conteudo = target.path("/carrinhos/1").request().get(String.class);
		
		/* Objeto para mostrar em XML*/
		Carrinho carrinho = (Carrinho) new XStream().fromXML(conteudo);
	
		/* Metodo de objeto utilizando JSON od google*/
		//Carrinho carrinho = new Gson().fromJson(conteudo, Carrinho.class);
		
		Assert.assertEquals("Polo de Tecnologia", carrinho.getRua());
		
		System.out.println(conteudo);
	}
	
	
	@Test
	public void testaCriacaoNovoCarrinho(){
		
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080");
		Carrinho carrinho = new Carrinho();
		Produto produto = new Produto(3, "Cerveja Bohemia", 3.5, 1 );
		
		carrinho.adiciona(produto);
		carrinho.setCidade("Campinas");
		carrinho.setId(2);
		carrinho.setRua("Polis");
		
		String converteCarrinhoXML = carrinho.toXML();
		
		Entity<String> entity = Entity.entity(converteCarrinhoXML, javax.ws.rs.core.MediaType.APPLICATION_XML);
		
		Response response = target.path("/carrinhos").request().post(entity);
		
		/*Utilizado para o metodo brutal force do sucesso*/
		//Assert.assertEquals("sucesso", response.readEntity(String.class));
		
		/*Metodo de testes do status code do http - browser*/
		Assert.assertEquals(201, response.getStatus());
	}
	
	@Test
	public void testaAtualizaCarrinho() {
		
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080");		
		Produto produto = new Produto(3467, "Cerveja Duvel", 8.5, 1);
		String converteProdutoXML = new XStream().toXML(produto);
		
		Entity<String> entity = Entity.entity(converteProdutoXML, 
				javax.ws.rs.core.MediaType.APPLICATION_XML);
		
		Response response = target.path("/carrinhos/1/produtos/3467")
				.request().put(entity);
		Assert.assertEquals(200, response.getStatus());
			
	}
	
	
	@Test
	public void testaDeletaProdutoDoCarrinho() {
		
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080");
		
		Response response = target.path("/carrinhos/1/produtos/6237").request().delete();
		
		Assert.assertEquals(200, response.getStatus());
		
	}
	
	
	@Test
	public void testaDeletaCarrinho() {
		
		Client client = ClientBuilder.newClient();
		WebTarget target = client.target("http://localhost:8080");
		
		Response response = target.path("/carrinhos/1").request().delete();
		
		/*Quando utiliza o 200, o servidor retora o erro 405 Not allowed...debugar...*/
		Assert.assertEquals(200, response.getStatus());
		
	}

		
	/*@Test // Anotação de definição de testes
	public void testaQueAConexaoComOServidorFunciona() {
		Client client = ClientBuilder.newClient();
		
		WebTarget target = client.target("http://localhost:8080");
		String conteudo = target.path("/carrinhos").request().get(String.class);

		 Executa o teste buscando um conteudo especifico no xml 
		Assert.assertTrue(conteudo.contains("Polo de Tecnologia"));

		System.out.println(conteudo);
	}*/
}
