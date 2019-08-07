package br.com.sotero.checklistsmk.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.function.Consumer;

import org.junit.After;
import org.junit.Test;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.repository.CrudRepository;

import br.com.sotero.checklistsmk.ICrudCommonTest;
import br.com.sotero.checklistsmk.model.ClassEntity;

public abstract class CrudRepositoryTest<T, ID> implements ICrudCommonTest<T, ID> {

	protected abstract CrudRepository<T, ID> getRepository();

	protected abstract T entityNoId();

	// :::... INICIO - TESTES UNITARIOS ...:::
	@Test
	public void testSave() {
		System.out.println("::: CrudRepositoryTest.testSave() :::");

		// Teste somente objeto instanciado
		{
			try {
				getRepository().save(entityOnlyInstanced());
				fail();
			} catch (DataIntegrityViolationException e) {
				assertTrue(true);
			} catch (Exception e) {
				fail(e.getMessage());
			}
		}

		// Teste com o objeto nulo
		{
			try {
				getRepository().save(null);
				fail();
			} catch (InvalidDataAccessApiUsageException e) {
				assertTrue(true);
			} catch (Exception e) {
				fail(e.getMessage());
			}
		}

		// Teste objeto real
		{
			// Persistindo objeto
			T result = getRepository().save(entity());
			assertTrue(castClassEntity(result).getId() != null);

			// Tentando persistir objeto que já existe
			try {
				getRepository().save(entity());
				fail();
			} catch (DataIntegrityViolationException e) {
				assertTrue(true);
			} catch (Exception e) {
				fail(e.getMessage());
			}

			// Update no objeto
			alteracaoNaEntidadeParaUpdate(result);
			T result2 = getRepository().save(result);
			assertEquals(castClassEntity(result).getId(), castClassEntity(result2).getId());
		}
	}

	@Test
	public void testSaveAll() {
		System.out.println("::: CrudRepositoryTest.testSaveAll() :::");

		// Persistir lista de objetos
		Iterable<T> result = getRepository().saveAll(listEntity());

		assertTrue(result.iterator().hasNext());

		// Persistir lista de objetos alterados (update)
		alteracaoNasEntidadesParaUpdate(result);

		Iterable<T> result2 = getRepository().saveAll(result);

		assertTrue(result2.iterator().hasNext());
	}

	@Test
	public void testFindById() {
		System.out.println("::: CrudRepositoryTest.testFindById() :::");

		// Procurar objeto pelo qual não existe o ID
		{
			try {
				getRepository().findById(null);
				fail();
			} catch (InvalidDataAccessApiUsageException e) {
				assertTrue(true);
			} catch (Exception e) {
				fail(e.getMessage());
			}
		}

		// Before test
		Iterable<T> saveAll = getRepository().saveAll(listEntity());
		ClassEntity<ID> entity = castClassEntity(saveAll.iterator().next());

		// Procurar objeto por ID
		Optional<T> result = getRepository().findById(entity.getId());
		assertTrue(result.isPresent());

		// Procurar objeto pelo qual não existe o ID
		Optional<T> result2 = getRepository().findById(getIDNaoExiste());
		assertFalse(result2.isPresent());
	}

	@Test
	public void testExistsById() {
		System.out.println("::: CrudRepositoryTest.testExistsById() :::");

		// Before test
		Iterable<T> saveAll = getRepository().saveAll(listEntity());
		ClassEntity<ID> entity = castClassEntity(saveAll.iterator().next());

		boolean result = getRepository().existsById(entity.getId());
		assertTrue(result);

		boolean result2 = getRepository().existsById(getIDNaoExiste());
		assertFalse(result2);

		try {
			getRepository().existsById(null);
			fail();
		} catch (InvalidDataAccessApiUsageException e) {
			assertTrue(true);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testFindAll() {
		System.out.println("::: CrudRepositoryTest.testFindAll() :::");

		{
			Iterable<T> result = getRepository().findAll();
			assertFalse(result.iterator().hasNext());
		}

		// Carga de objetos
		getRepository().saveAll(listEntity());

		{
			Iterable<T> result = getRepository().findAll();
			assertTrue(result.iterator().hasNext());
		}
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testFindAllById() {
		System.out.println("::: CrudRepositoryTest.testFindAllById() :::");

		// Teste passando valor nulo
		{
			try {
				getRepository().findAllById(null);
			} catch (InvalidDataAccessApiUsageException e) {
				assertTrue(true);
			} catch (Exception e) {
				fail(e.getMessage());
			}
		}

		// Teste passando somente a lista instanciada
		{
			Iterable<T> result = getRepository().findAllById(new ArrayList<ID>());
			assertFalse(result.iterator().hasNext());
		}

		// Teste passando uma lista de IDs que não existe
		{
			List<ID> listIDs = new ArrayList<>();
			listIDs.add(getIDNaoExiste());
			Iterable<T> result = getRepository().findAllById(listIDs);
			assertFalse(result.iterator().hasNext());
		}

		// Carga de objetos
		{
			getRepository().saveAll(listEntity());

			Iterable<T> findAll = getRepository().findAll();

			List<ID> listIDs = new ArrayList<>();

			findAll.forEach(new Consumer<T>() {
				@Override
				public void accept(T t) {
					listIDs.add((castClassEntity(t)).getId());
				}
			});

			List<ClassEntity<ID>> result = (List<ClassEntity<ID>>) getRepository().findAllById(listIDs);

			assertTrue(listIDs.size() == result.size());
		}
	}

	@Test
	@SuppressWarnings("unchecked")
	public void testCount() {
		System.out.println("::: CrudRepositoryTest.testCount() :::");

		// Contagem com a tabela vazia
		{
			long count = getRepository().count();
			assertTrue(count == 0);
		}

		// Carga de objetos
		List<ClassEntity<ID>> list = (List<ClassEntity<ID>>) getRepository().saveAll(listEntity());

		// Contagem com a tabela populada
		{
			long count = getRepository().count();
			assertTrue(count == list.size());
		}
	}

	@Test
	public void testDeleteById() {
		System.out.println("::: CrudRepositoryTest.testDeleteById() :::");

		// Tentando deletar com valor nulo
		{
			try {
				getRepository().deleteById(null);
				fail();
			} catch (InvalidDataAccessApiUsageException e) {
				assertTrue(true);
			} catch (Exception e) {
				fail(e.getMessage());
			}
		}

		// Testando com id que não existe na base de dados
		{
			try {
				getRepository().deleteById(getIDNaoExiste());
				fail();
			} catch (EmptyResultDataAccessException e) {
				assertTrue(true);
			} catch (Exception e) {
				fail(e.getMessage());
			}
		}

		// Testando com id que existe
		{
			Iterable<T> iterable = getRepository().saveAll(listEntity());
			ID id = (castClassEntity(iterable.iterator().next())).getId();

			getRepository().deleteById(id);

			boolean existsById = getRepository().existsById(id);

			assertFalse(existsById);
		}
	}

	@Test
	public void testDelete() {
		System.out.println("::: CrudRepositoryTest.testDelete() :::");

		// Tentando deletar com valor nulo
		{
			try {
				getRepository().delete(null);
				fail();
			} catch (InvalidDataAccessApiUsageException e) {
				assertTrue(true);
			} catch (Exception e) {
				fail(e.getMessage());
			}
		}

		// Populando a tabela
		Iterable<T> iterable = getRepository().saveAll(listEntity());
		T objeto = iterable.iterator().next();

		// Deletando o objeto que existe
		{
			getRepository().delete(objeto);
			boolean existsById = getRepository().existsById((castClassEntity(objeto)).getId());
			assertFalse(existsById);
		}

		// Testando com objeto recem deletado
		getRepository().delete(objeto);

		// testando com o objeto sem id
		getRepository().delete(entityNoId());

		assertTrue(true);
	}

	@Test
	public void testDeleteAll() {
		System.out.println("::: CrudRepositoryTest.testDeleteAll() :::");

		// Populando tabelas
		Iterable<T> iterable = getRepository().saveAll(listEntity());
		ID id = castClassEntity(iterable.iterator().next()).getId();

		// Verifica primeiramente se existe o objeto na tabela
		boolean existsById = getRepository().existsById(id);
		assertTrue(existsById);

		// Deletando todos os dados da tabela
		getRepository().deleteAll();

		// Verifica se existe o objeto na tabela após o deleteAll
		existsById = getRepository().existsById(id);
		assertFalse(existsById);
	}
	// :::... FIM - TESTES UNITARIOS ...:::

	// :::... INICIO - OVERRIDE ...:::
	@SuppressWarnings("unchecked")
	@Override
	public ClassEntity<ID> castClassEntity(T t) {
		return (ClassEntity<ID>) t;
	}

	@Override
	public void alteracaoNasEntidadesParaUpdate(Iterable<T> result) {
		Iterator<T> iterator = result.iterator();

		while (iterator.hasNext()) {
			alteracaoNaEntidadeParaUpdate(iterator.next());
		}
	}
	// :::... FIM - OVERRIDE ...:::

	// :::... INICIO - setUp() / tearDown() ...:::
	@After
	public void tearDown() {
		System.out.println("::: tearDown() :::");
		getRepository().deleteAll();
	}
	// :::... FIM - setUp() / tearDown() ...:::
}
