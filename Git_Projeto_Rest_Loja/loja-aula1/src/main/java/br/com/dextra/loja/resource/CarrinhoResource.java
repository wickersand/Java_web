package br.com.dextra.loja.resource;

import java.net.URI;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.gson.Gson;
import com.thoughtworks.xstream.XStream;

import br.com.dextra.loja.dao.CarrinhoDAO;
import br.com.dextra.loja.modelo.Carrinho;
import br.com.dextra.loja.modelo.Produto;

@Path("carrinhos")
public class CarrinhoResource {

	/* Busca por passagem de parametro - localhost:8080/carrinhos?id=1 */
	@GET
	@Produces(MediaType.APPLICATION_XML)
	//@Produces(MediaType.APPLICATION_JSON)
	public String buscaPorQuery(@QueryParam("id") long id) {
		Carrinho carrinho = new CarrinhoDAO().busca(id);
		return carrinho.toXML();
		//return carrinho.toJSON();
	}

	@Path("{id}") // Deixou de utilizar o QueryParameter e pode usar /carrinhos/1 ou x ao invés de /carrinhos/id?=1 ou x
	@GET
	@Produces(MediaType.APPLICATION_XML)
	//@Produces(MediaType.APPLICATION_JSON)
	public String buscaPorPath(@PathParam("id") long id) {
		Carrinho carrinho = new CarrinhoDAO().busca(id);
		return carrinho.toXML(); //rodando em xml
		//return carrinho.toJSON(); //rodando em JSON
	}
	
	/*Utilizar os metodos POST/GET como JSON ou XML, não misturar os dois por convenção*/
	
	
	/*Metodo para retornar status code sucesso de for brutal force*/
	/*@POST
	@Produces(MediaType.APPLICATION_XML)
	public String adiciona(String conteudo){
		Carrinho carrinho = (Carrinho) new XStream().fromXML(conteudo);
		new CarrinhoDAO().adiciona(carrinho);
		return "sucesso";
	}*/
	
	/*Metodo para retornar status code direto do HTTP - Browser - mais confiavel*/
	@POST
	@Consumes(MediaType.APPLICATION_XML)
	public Response adiciona(String conteudo){
		Carrinho carrinho = (Carrinho) new XStream().fromXML(conteudo);
		new CarrinhoDAO().adiciona(carrinho);
		URI uri = URI.create("/carrinhos/" + carrinho.getId());
		return Response.created(uri).build();
	}
	
	/*Metodo para remover um produto de um carrinho por ID*/
	@Path("{id}/produtos/{produtoId}")
	@DELETE
	public Response removeProduto(@PathParam("id") long id, @PathParam("produtoId") long produtoId){
		Carrinho carrinho = new CarrinhoDAO().busca(id);
		carrinho.remove(produtoId);
		return Response.ok().build();
	}
	
	/*Metodo para remover um carrinho por ID*/
	@Path("{id}")
	@DELETE
	public Response removeCarrinho(@PathParam("id") long id){
		new CarrinhoDAO().remove(id);
		return Response.ok().build();
	}
	
	@Path("{id}/produtos/{produtoId}")
	@PUT
	public Response alteraProduto(String novoProduto, @PathParam("id") long id, 
			@PathParam("produtoId") long produtoId){
		Carrinho carrinho = new CarrinhoDAO().busca(id);
		Produto produto = (Produto) new XStream().fromXML(novoProduto);
		//Produto produto = (Produto) new Gson.fromJson(novoProduto);
		carrinho.troca(produto);
		return Response.ok().build();
	}
	
	/*Metodo para alterar a quantidade dos produtos - forma generica*/
	@Path("{id}/produtos/{produtoId}/quantidade")
	@PUT
	@Consumes(MediaType.APPLICATION_XML)
	public Response alteraQuantidadeProduto(String novaQuantidadeProduto, @PathParam("id") long id,
			@PathParam ("produtoId") long produtoId) {
		
		Carrinho carrinho = new CarrinhoDAO().busca(id);
		Produto produto = (Produto) new XStream().fromXML(novaQuantidadeProduto);
		carrinho.trocaQuantidade(produto);
		return Response.ok().build();
	}
	
	@Path("{id}/produtos/{produtoId}/quantidadeJSON")
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response alteraProduto(@PathParam("id") long id, @PathParam("produtoId") 
    long produtoId, String conteudo) {
        Carrinho carrinho = new CarrinhoDAO().busca(id);
        Produto produto = (Produto) new Gson().fromJson(conteudo,Produto.class);
        carrinho.trocaQuantidade(produto);
        return Response.ok().build();
    }
	
}
