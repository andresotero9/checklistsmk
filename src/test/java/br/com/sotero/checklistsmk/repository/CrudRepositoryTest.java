package br.com.sotero.checklistsmk.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.repository.CrudRepository;

import br.com.sotero.checklistsmk.model.ClassEntity;

public abstract class CrudRepositoryTest<T, ID> implements ICrudRepositoryTest {

	private ID idFindById;

	@Autowired
	protected CrudRepository<T, ID> crudRepository;

	private int count;

	@Test
	@Override
	public void testSave() {
		System.out.println("::: CrudRepositoryTest.testSave() :::");
		T entity = entity();
		T result = this.crudRepository.save(entity);

		assertSame(((ClassEntity<ID>) result).getId(), ((ClassEntity<ID>) entity).getId());
	}

	@Test
	@Override
	public void testSaveAll() {
		System.out.println("::: CrudRepositoryTest.testSaveAll() :::");
		List<T> saveAll = (List<T>) this.crudRepository.saveAll(listEntitySaveAll());
		assertThat(saveAll);
	}

	@Test
	@Override
	public void testSaveOnlyInstancedEntity() {
		System.out.println("::: CrudRepositoryTest.testSaveOnlyInstancedEntity() :::");
		try {
			this.crudRepository.save(entityOnlyInstanced());
			fail();
		} catch (DataIntegrityViolationException e) {
			assertTrue(true);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	@Override
	public void testFindById() {
		System.out.println("::: CrudRepositoryTest.testFindById() :::");
		Optional<T> listEntity = this.crudRepository.findById(idFindById);
		if (listEntity.isPresent()) {
			assertTrue(((ClassEntity<ID>) listEntity.get()).getId() == idFindById);
		} else {
			fail();
		}
	}

	@Test
	@Override
	public void testFindByIdNotExists() {
		System.out.println("::: CrudRepositoryTest.testFindByIdNotExists() :::");
		Optional<T> result = this.crudRepository.findById(getNotExistFindById());
		assertFalse(result.isPresent());
	}

	@Test
	@Override
	public void testExistsById() {
		System.out.println("::: CrudRepositoryTest.testExistsById() :::");
		boolean result = this.crudRepository.existsById(idFindById);
		assertTrue(result);
	}

	@Test
	@Override
	public void testNotExistsById() {
		System.out.println("::: CrudRepositoryTest.testNotExistsById() :::");
		boolean result = this.crudRepository.existsById(getNotExistFindById());
		assertFalse(result);
	}

	@Test
	@Override
	public void testFindAll() {
		System.out.println("::: CrudRepositoryTest.testFindAll() :::");
		Iterable<T> result = this.crudRepository.findAll();
		assertTrue(result.iterator().hasNext());
	}

	@Test
	@Override
	public void testFindAllById() {
		System.out.println("::: CrudRepositoryTest.testFindAllById() :::");
		Iterable<T> result = this.crudRepository.findAllById(getIterableById());
		assertTrue(result.iterator().hasNext());

	}

	@Test
	@Override
	public void testCount() {
		System.out.println("::: CrudRepositoryTest.testCount() :::");
		long count = this.crudRepository.count();
		assertTrue(count == this.count);
	}

	@Test
	@Override
	public void testDeleteById() {
		System.out.println("::: CrudRepositoryTest.testDeleteById() :::");
		boolean existsById = this.crudRepository.existsById(idFindById);
		assertTrue(existsById);
		this.crudRepository.deleteById(idFindById);
		existsById = this.crudRepository.existsById(idFindById);
		assertFalse(existsById);

		// Testando com id que não existe na base de dados
		try {
			this.crudRepository.deleteById(idFindById);
			fail();
		} catch (EmptyResultDataAccessException e) {
			assertTrue(true);
		} catch (Exception e) {
			fail(e.getMessage());
		}
	}

	@Test
	@Override
	public void testDelete() {
		System.out.println("::: CrudRepositoryTest.testDelete() :::");
		Optional<T> entity = this.crudRepository.findById(idFindById);
		this.crudRepository.delete(entity.get());

		// testando com id que não existe
		boolean existsById = this.crudRepository.existsById(idFindById);
		assertFalse(existsById);

		// testando com o objeto sem id
		this.crudRepository.delete(entity());
		assertTrue(true);
	}

	@Test
	@Override
	public void testDeleteAll() {
		System.out.println("::: CrudRepositoryTest.testDeleteAll() :::");
		boolean existsById = this.crudRepository.existsById(idFindById);
		assertTrue(existsById);
		this.crudRepository.deleteAll();
		existsById = this.crudRepository.existsById(idFindById);
		assertFalse(existsById);
	}

	protected abstract T entity();

	protected abstract T entityOnlyInstanced();

	protected abstract List<T> listEntity();

	protected abstract List<T> listEntitySaveAll();

	protected abstract ID getNotExistFindById();

	protected abstract Iterable<ID> getIterableById();

	protected ID getIdFindById() {
		return idFindById;
	}

	@Before
	public void setUp() {
		System.out.println("::: setUp() :::");
		List<T> listEntity = listEntity();
		count = listEntity.size();
		this.crudRepository.saveAll(listEntity);
		for (T t : listEntity) {
			System.out.println("Categoria: " + ((ClassEntity<ID>) t).getId());
		}
	}

	@After
	public void tearDown() {
		this.crudRepository.deleteAll();
	}

}
