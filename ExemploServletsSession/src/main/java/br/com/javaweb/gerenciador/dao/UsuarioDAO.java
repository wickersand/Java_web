package br.com.javaweb.gerenciador.dao;

import java.util.HashMap;
import java.util.Map;

import br.com.javaweb.gerenciador.Usuario;

public class UsuarioDAO {

	private final static Map<String, Usuario> USUARIOS = new HashMap<>();
	static {
		USUARIOS.put("wcks@gmail.com", new Usuario("wcks@gmail.com","123456"));
		//USUARIOS.put("rodrigo.turini@alura.com.br", new Usuario("rodrigo.turini@alura.com.br","turini"));
	}

	public Usuario buscaPorEmailESenha(String email, String senha) {
		if (!USUARIOS.containsKey(email))
			return null;

		Usuario usuario = USUARIOS.get(email);
		if (usuario.getSenha().equals(senha))
			return usuario;
		
		return null;
	}

}
