package br.com.sotero.checklistsmk.data;

import br.com.sotero.checklistsmk.model.CategoriaModel;

public class CategoriaData {
	public static CategoriaModel getCategoria() {
		CategoriaModel categoria = new CategoriaModel();
		categoria.setNmeCategoria("Alimentos");
		return categoria;
	}
}
