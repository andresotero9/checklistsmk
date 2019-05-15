package br.com.sotero.checklistsmk.data;

import br.com.sotero.checklistsmk.model.Categoria;

public class CategoriaData {
	public static Categoria getCategoria() {
		Categoria categoria = new Categoria();
		categoria.setNmeCategoria("Alimentos");
		return categoria;
	}

	public static Categoria getCategoriaNoId() {
		Categoria categoria = new Categoria();
		categoria.setNmeCategoria("Alimentos");
		return categoria;
	}
}
