package br.com.sotero.checklistsmk.repository;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.sotero.checklistsmk.data.CategoriaData;
import br.com.sotero.checklistsmk.data.ItemData;
import br.com.sotero.checklistsmk.model.Categoria;
import br.com.sotero.checklistsmk.model.Item;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ItemRepositoryTest extends CrudRepositoryTest<Item, Long> {

	@Autowired
	private CrudRepository<Categoria, Long> categoriaCrudRepository;

	@Override
	protected Item entity() {
		Item item = ItemData.getItem();
		Categoria categoria = categoriaCrudRepository.save(CategoriaData.getCategoriaNoId("Alimentos"));
		item.setCategoria(categoria);
		return item;
	}

	@Override
	protected Item entityNoId() {
		return ItemData.getItem();
	}

	@Override
	protected Item entityOnlyInstanced() {
		return new Item();
	}

	@Override
	protected List<Item> listEntity() {
		Categoria categoria = categoriaCrudRepository.save(CategoriaData.getCategoriaNoId("Bebidas"));

		List<Item> itens = new ArrayList<Item>();
		itens.add(new Item("Refrigerante", categoria));

		return itens;
	}

	@Override
	protected List<Item> listEntitySaveAll() {
		Categoria categoria = categoriaCrudRepository.save(CategoriaData.getCategoriaNoId("Bazar"));
		List<Item> itens = new ArrayList<Item>();
		itens.add(new Item("Toalha", categoria));
		itens.add(new Item("Fronha", categoria));
		itens.add(new Item("Roupa de cama", categoria));
		return itens;
	}

	@Override
	protected Long getNotExistFindById() {
		return 9999L;
	}

	@After
	public void tearDown() {
		System.out.println("::: ItemRepositoryTest.tearDown() :::");
		super.tearDown();
		this.categoriaCrudRepository.deleteAll();
	}
}
