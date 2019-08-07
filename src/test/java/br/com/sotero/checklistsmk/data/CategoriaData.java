package br.com.sotero.checklistsmk.data;

import br.com.sotero.checklistsmk.model.Categoria;

public class CategoriaData {
	public static Categoria getCategoria(String nmeCategoria) {
		Categoria categoria = new Categoria();
		categoria.setId(999L);
		categoria.setNmeCategoria(nmeCategoria);
		return categoria;
	}

	public static Categoria getCategoriaNoId(String nmeCategoria) {
		Categoria categoria = new Categoria();
		categoria.setNmeCategoria(nmeCategoria);
		return categoria;
	}

}
