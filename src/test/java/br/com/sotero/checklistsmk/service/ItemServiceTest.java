package br.com.sotero.checklistsmk.service;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.sotero.checklistsmk.data.CategoriaData;
import br.com.sotero.checklistsmk.data.ItemData;
import br.com.sotero.checklistsmk.exception.BusinessException;
import br.com.sotero.checklistsmk.model.Categoria;
import br.com.sotero.checklistsmk.model.Item;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class ItemServiceTest extends CrudServiceTest<Item, Long> {

	@Autowired
	private ItemService itemService;

	@Autowired
	private CategoriaService categoriaService;

	@Override
	public void alteracaoNaEntidadeParaUpdate(Item t) {
		t.setNmeItem(t.getNmeItem() + " update");
	}

	@Override
	public Item entity() {
		Item item = ItemData.getItem();
		Categoria categoria = null;

		try {
			Optional<Categoria> optCategoria = this.categoriaService.findByNmeCategoria("Alimentos");

			if (optCategoria.isPresent()) {
				categoria = optCategoria.get();
			} else {
				categoria = this.categoriaService.save(CategoriaData.getCategoriaNoId("Alimentos"));
			}
		} catch (BusinessException e) {
			fail(e.getMessage());
		}

		item.setCategoria(categoria);
		return item;
	}

	@Override
	public Item entityOnlyInstanced() {
		return new Item();
	}

	@Override
	public List<Item> listEntity() {
		Categoria categoria = null;
		try {
			Optional<Categoria> optCategoria = this.categoriaService.findByNmeCategoria("Bebidas");

			if (optCategoria.isPresent()) {
				categoria = optCategoria.get();
			} else {
				categoria = categoriaService.save(CategoriaData.getCategoriaNoId("Bebidas"));
			}

		} catch (BusinessException e) {
			fail(e.getMessage());
		}

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

	@Override
	public Long getIDZerado() {
		return 0L;
	}

	@Override
	public CrudService<Item, Long> getService() {
		return itemService;
	}

	@After
	public void tearDown() {
		System.out.println("::: ItemRepositoryTest.tearDown() :::");

		try {
			this.itemService.deleteAll();
		} catch (BusinessException e) {
			fail(e.getMessage());
		}

		try {
			this.categoriaService.deleteAll();
		} catch (BusinessException e) {
			fail(e.getMessage());
		}
	}

}
