package br.com.dextraining.domain;

import java.util.ArrayList;
import java.util.List;

public class UsuarioService {

	private static List<Usuario> usuarios = new ArrayList<>();

	public static void salvar(Usuario usuario) {
		usuarios.add(usuario);
	}

	public static List<Usuario> listar() {
		return usuarios;
	}

	public static boolean logar(Usuario usuario) {
		if (usuario.getLogin().equals("wcks") && usuario.getSenha().equals("wcks")) {
			return true;
		}

		for (Usuario u : usuarios) {
			if (usuario.getLogin().equals(u.getLogin()) && usuario.getSenha().equals(u.getSenha())) {
				return true;
			}
		}

		return false;
	}

	public static Object buscaPorLogin(String login) {
		for (Usuario usuario: usuarios){
			if (usuario.getLogin().equals(login)) {
				return usuario;
			}
		}
		return null;
	}
}
