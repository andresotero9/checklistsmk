package br.com.sotero.checklistsmk.repository;

import java.util.ArrayList;
import java.util.List;

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
	private CrudRepository<Categoria, Long> CategoriaCrudRepository;
	
	@Override
	protected Item entity() {
		return ItemData.getItem();
	}

	@Override
	protected Item entityNoId() {
		return ItemData.getItemNoId();
	}

	@Override
	protected Item entityOnlyInstanced() {
		return new Item();
	}

	@Override
	protected List<Item> listEntity() {
		cr
		List<Item> itens = new ArrayList<Item>();
//		List<Cal>
//		crudRepository.save(CategoriaData.getCategoria());
//		crudRepository.saveAll(new Categoria("Bebidas"));
		itens.add(new Item("Feijao"));

		return itens;
	}

	@Override
	protected List<Item> listEntitySaveAll() {
		List<Item> itens = new ArrayList<Item>();
		itens.add(new Item("Arroz", CategoriaData.getCategoria()));

		return itens;
	}

	@Override
	protected Long getNotExistFindById() {
		return 9999L;
	}

}
