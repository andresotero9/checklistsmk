package br.com.sotero.checklistsmk.repository;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.repository.CrudRepository;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.sotero.checklistsmk.data.CategoriaData;
import br.com.sotero.checklistsmk.model.Categoria;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class CategoriaRepositoryTest extends CrudRepositoryTest<Categoria, Long> {

	private static final Logger log = LoggerFactory.getLogger(CategoriaRepositoryTest.class);

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Override
	protected CrudRepository<Categoria, Long> getRepository() {
		return categoriaRepository;
	}

	@Override
	public Categoria entity() {
		return CategoriaData.getCategoria("Alimentos");
	}

	@Override
	public Categoria entityNoId() {
		return CategoriaData.getCategoriaNoId("Alimentos");
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
	public void testSaveNmeCategoriaUnique() {
		getLog().info("testSaveNmeCategoriaUnique()");

		// Populando a tabela
		this.categoriaRepository.saveAll(listEntity());

		try {
			this.categoriaRepository.save(new Categoria("Bebidas"));
			fail();
		} catch (DataIntegrityViolationException e) {
			assertTrue(true);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testFindByNmeCategoria() {
		getLog().info("testFindByNmeCategoria()");

		// Testando passando o parametro nulo
		{
			Optional<Categoria> categoria = this.categoriaRepository.findByNmeCategoria(null);
			assertFalse(categoria.isPresent());
		}

		// Testando passando uma categoria que não existe
		{
			Optional<Categoria> categoria = this.categoriaRepository.findByNmeCategoria("Categoria que não existe");
			assertFalse(categoria.isPresent());
		}

		this.categoriaRepository.saveAll(listEntity());

		// Testando passando uma categoria que existe
		{
			Optional<Categoria> categoria = this.categoriaRepository.findByNmeCategoria("Limpeza");
			assertTrue(categoria.isPresent());
		}

	}

	@Override
	public Logger getLog() {
		return log;
	}
}
