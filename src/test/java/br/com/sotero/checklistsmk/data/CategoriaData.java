package br.com.sotero.checklistsmk.data;

import br.com.sotero.checklistsmk.model.Categoria;

public class CategoriaData {
	public static Categoria getCategoria() {
		Categoria categoria = new Categoria();
		categoria.setId(7L);
		categoria.setNmeCategoria("Alimentos");
		return categoria;
	}
}
