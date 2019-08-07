package br.com.sotero.checklistsmk.service;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.sotero.checklistsmk.data.CategoriaData;
import br.com.sotero.checklistsmk.model.Categoria;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class CategoriaServiceTest extends CrudServiceTest<Categoria, Long> {

	@Autowired
	private CategoriaService categoriaService;

	@Override
	public CrudService<Categoria, Long> getService() {
		return categoriaService;
	}

	// :::... INICIO - setUp() / tearDown() ...:::
	@Before
	public void setUp() {

	}

	@After
	public void tearDown() {
		System.out.println("::: tearDown() :::");
		try {
			getService().deleteAll();
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}
	// :::... FIM - setUp() / tearDown() ...:::

	@Override
	public Categoria entity() {
		return CategoriaData.getCategoria("Alimentos");
	}

	@Override
	public Categoria entityOnlyInstanced() {
		return new Categoria();
	}

	@Override
	public List<Categoria> listEntity() {
		List<Categoria> listCategoria = new ArrayList<>();
		listCategoria.add(new Categoria(1L, "Bebidas"));
		listCategoria.add(new Categoria(2L, "Carnes"));
		listCategoria.add(new Categoria(3L, "Limpeza"));
		listCategoria.add(new Categoria(4L, "Perfumaria"));
		listCategoria.add(new Categoria(5L, "Bebês"));
		listCategoria.add(new Categoria(6L, "Peixaria"));
		return listCategoria;
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
	public void alteracaoNaEntidadeParaUpdate(Categoria t) {
		t.setNmeCategoria(t.getNmeCategoria() + " update");
	}

}
