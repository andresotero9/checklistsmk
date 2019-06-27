package br.com.sotero.checklistsmk.data;

import br.com.sotero.checklistsmk.model.Item;

public class ItemData {
	public static Item getItem() {
		Item item = new Item();
		item.setNmeItem("Macarrão");
		return item;
	}
}
