package br.com.dextra.loja;

import java.io.IOException;
import java.net.URI;

/*
 * https://grizzly.java.net/httpserverframework.html
 */
import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.server.ResourceConfig;

public class Servidor {

	public static HttpServer inicializaServidor(){
		ResourceConfig config =	new ResourceConfig().packages("br.com.dextra.loja");
		URI uri = URI.create("http://localhost:8080/");
		HttpServer server =	GrizzlyHttpServerFactory.createHttpServer(uri, config);
		return server;
	}
	
	
	public static void main(String[] args) throws IOException {

		URI uri = URI.create("http://localhost:8080/");
		ResourceConfig config = new ResourceConfig().packages("br.com.dextra.loja");

		//Grizzly - emulador para efetuar os testes unitarios.
		HttpServer server = GrizzlyHttpServerFactory.createHttpServer(uri, config);
		System.out.println("Servidor rodando");
		System.in.read();
		server.stop();
	}
}
