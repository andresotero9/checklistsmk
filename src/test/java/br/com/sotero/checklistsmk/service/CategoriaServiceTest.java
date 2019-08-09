package br.com.sotero.checklistsmk.service;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.sotero.checklistsmk.constants.ConstantsMessageException;
import br.com.sotero.checklistsmk.data.CategoriaData;
import br.com.sotero.checklistsmk.exception.BusinessException;
import br.com.sotero.checklistsmk.model.Categoria;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class CategoriaServiceTest extends CrudServiceTest<Categoria, Long> {

	private static final Logger log = LoggerFactory.getLogger(CategoriaServiceTest.class);

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

	@Test
	public void testFindByNmeCategoria() {
		log.info("testFindByNmeCategoria()");

		// Testando passando o parametro nulo
		{
			try {
				this.categoriaService.findByNmeCategoria(null);
				fail();
			} catch (BusinessException e) {
				assertSame(ConstantsMessageException.MSG_FALHA_PARAMETRO_NULO, e.getMessage());
			}
		}

		// Testando passando uma categoria que não existe
		{
			try {
				Optional<Categoria> categoria = this.categoriaService.findByNmeCategoria("Categoria que não existe");
				assertFalse(categoria.isPresent());
			} catch (BusinessException e) {
				fail(e.getMessage());
			}
		}

		try {
			this.categoriaService.saveAll(listEntity());
		} catch (BusinessException e1) {
			fail(e1.getMessage());
		}

		// Testando passando uma categoria que existe
		{
			Optional<Categoria> categoria;
			try {
				categoria = this.categoriaService.findByNmeCategoria("Limpeza");
				assertTrue(categoria.isPresent());
			} catch (BusinessException e) {
				fail(e.getMessage());
			}
		}

	}

	@Override
	public Logger getLog() {
		return log;
	}
}
