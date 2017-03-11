package br.com.dextra.treinamento.model.domain;

import java.util.ArrayList;
import java.util.Collection;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Usuario {

	@Id
	@GeneratedValue
	private Long id;
	private String login;
	private String senha;
	private String email;

	/*
	 * Criando uma lista de permissoes
	 */

	@OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER, mappedBy = "usuario")
	private Collection<Permissao> permissao = new ArrayList<Permissao>();

	/*
	 * Lista de Setters and Getters
	 */

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getSenha() {
		return senha;
	}

	public void setSenha(String senha) {
		this.senha = senha;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Collection<Permissao> getPermissao() {
		return permissao;
	}

	public void setPermissao(Collection<Permissao> permissao) {
		this.permissao = permissao;
	}

}
