package br.com.sotero.checklistsmk.data;

import br.com.sotero.checklistsmk.model.Categoria;
import br.com.sotero.checklistsmk.model.Item;

public class ItemData {
	public static Item getItem() {
		Item item = new Item();
		Categoria categoria = CategoriaData.getCategoria();
		categoria.setId(1L);
		item.setCategoria(categoria);
		item.setNmeItem("Macarrão");
		return item;
	}
}
