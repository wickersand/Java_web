package br.com.controlelivros.domain;

import java.util.ArrayList;
import java.util.List;

public class LivroService {

	private static List<Livro> livros = new ArrayList<>();

	public static void salvar(Livro livro) {
		livros.add(livro);
	}

	public static List<Livro> listar() {
		return livros;
	}

	public static Object buscaPorNome(String nome) {
		for (Livro livro : livros) {
			if (livro.getNome().equals(nome)) {
				return nome;
			}
		}
		return null;
	}

	public static Object buscaPorAutor(String autor) {
		for (Livro livro : livros) {
			if (livro.getAutor().equals(autor)) {
				return autor;
			}
		}
		return null;
	}

}
