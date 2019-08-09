package br.com.sotero.checklistsmk.repository;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

	private static final Logger log = LoggerFactory.getLogger(ItemRepositoryTest.class);

	@Autowired
	private ItemRepository itemRepository;

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Override
	public Item entity() {
		Item item = ItemData.getItem();
		Categoria categoria = categoriaRepository.save(CategoriaData.getCategoriaNoId("Alimentos"));
		item.setCategoria(categoria);
		return item;
	}

	@Override
	public Item entityNoId() {
		return ItemData.getItem();
	}

	@Override
	public Item entityOnlyInstanced() {
		return new Item();
	}

	@Override
	public List<Item> listEntity() {
		Categoria categoria = categoriaRepository.save(CategoriaData.getCategoriaNoId("Bebidas"));

		List<Item> itens = new ArrayList<Item>();
		itens.add(new Item("Refrigerante", categoria));
		itens.add(new Item("Cerveja", categoria));
		itens.add(new Item("Suco", categoria));

		return itens;
	}

	@Override
	public Long getIDNaoExiste() {
		return 9999L;
	}

	@After
	public void tearDown() {
		getLog().info("tearDown");
		super.tearDown();
		this.categoriaRepository.deleteAll();
	}

	@Override
	public Long getIDZerado() {
		return 0L;
	}

	@Override
	public void alteracaoNaEntidadeParaUpdate(Item t) {
		t.setNmeItem(t.getNmeItem() + "update");
	}

	@Override
	protected CrudRepository<Item, Long> getRepository() {
		return itemRepository;
	}

	@Override
	public Logger getLog() {
		return log;
	}
}
