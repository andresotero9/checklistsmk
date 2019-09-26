package br.com.sotero.checklistsmk.utils;

import br.com.sotero.checklistsmk.dto.CategoriaDTO;
import br.com.sotero.checklistsmk.dto.ItemDTO;
import br.com.sotero.checklistsmk.model.Categoria;
import br.com.sotero.checklistsmk.model.Item;

public final class ConvertObjectDTO {

	public Categoria convertDtoToCategoria(CategoriaDTO dto) {
		Categoria categoria = new Categoria();
		categoria.setId(dto.getIdCategoria());
		categoria.setNmeCategoria(dto.getNmeCategoria());
		return categoria;
	}
	
	public Item convertDtoToItem(ItemDTO dto) {
		Item item = new Item();
		item.setId(dto.getIdItem());
		item.setNmeItem(dto.getNmeItem());
		return item;
	}
}
