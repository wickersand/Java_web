package br.com.dextraining.web.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;

import br.com.dextraining.domain.Usuario;
import br.com.dextraining.domain.UsuarioService;

@ManagedBean(name = "usuarioOLDMB")
public class UsuarioMB {

	private List<Usuario> usuarios;
	
	private Usuario usuario = new Usuario();
		
	public Usuario getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuario usuario) {
		this.usuario = usuario;
	}

	public List<Usuario> getUsuarios() {
		return usuarios;
	}

	public void setUsuarios(List<Usuario> usuarios) {
		this.usuarios = usuarios;
	}

	@PostConstruct
	public void carregar() {
		this.usuarios = UsuarioService.listar();
	}
	
	public String salvar(){
		UsuarioService.salvar(usuario);
		FaceUtils.createSuccessMessage("Usuario salvo com Sucesso");
		return "listagem.jsp";
	}
	
	public String irParaNovoUsuario() {
		return "/usuario/criar.jsf?faces-redirect=true";
	}
}
