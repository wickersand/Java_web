package br.com.controlelivros.web.controller;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

import br.com.controlelivros.domain.Livro;
import br.com.controlelivros.domain.LivroService;

@FacesConverter(forClass = Livro.class)
public class IdStringConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {

		if (value == null || value.isEmpty()) {
			return null;
		}
		return LivroService.buscaPorNome(value);
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		Livro livro = (Livro) value;
		if (livro == null) {
			return null;
		}
		return livro.getNome();
	}
}
