package br.com.sotero.checklistsmk.repository;

import static org.junit.Assert.assertFalse;
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

	@Autowired
	protected CrudRepository<T, ID> crudRepository;

	private int count;

	@SuppressWarnings("unchecked")
	@Test
	@Override
	public void testSave() {
		System.out.println("::: CrudRepositoryTest.testSave() :::");
		T result = this.crudRepository.save(entity());
		assertTrue(((ClassEntity<ID>) result).getId() != null);
	}

	@Test
	@Override
	public void testSaveAll() {
		System.out.println("::: CrudRepositoryTest.testSaveAll() :::");
		List<T> listEntitySaveAll = listEntitySaveAll();
		List<T> saveAll = (List<T>) this.crudRepository.saveAll(listEntitySaveAll);
		assertTrue(saveAll.size() == listEntitySaveAll.size());
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
		Optional<T> listEntity = this.crudRepository.findById(getExistFindById());
		if (listEntity.isPresent()) {
			assertTrue(((ClassEntity<ID>) listEntity.get()).getId() == getExistFindById());
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
		boolean result = this.crudRepository.existsById(getExistFindById());
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
		boolean existsById = this.crudRepository.existsById(getExistFindById());
		assertTrue(existsById);
		this.crudRepository.deleteById(getExistFindById());
		existsById = this.crudRepository.existsById(getExistFindById());
		assertFalse(existsById);

		// Testando com id que não existe na base de dados
		try {
			this.crudRepository.deleteById(getExistFindById());
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
		Optional<T> entity = this.crudRepository.findById(getExistFindById());
		this.crudRepository.delete(entity.get());

		// testando com id que não existe
		boolean existsById = this.crudRepository.existsById(getExistFindById());
		assertFalse(existsById);

		// testando com o objeto sem id
		this.crudRepository.delete(entity());
		assertTrue(true);
	}

	@Test
	@Override
	public void testDeleteAll() {
		System.out.println("::: CrudRepositoryTest.testDeleteAll() :::");
		boolean existsById = this.crudRepository.existsById(getExistFindById());
		assertTrue(existsById);
		this.crudRepository.deleteAll();
		existsById = this.crudRepository.existsById(getExistFindById());
		assertFalse(existsById);
	}

	protected abstract T entity();

	protected abstract T entityOnlyInstanced();

	protected abstract List<T> listEntity();

	protected abstract List<T> listEntitySaveAll();

	protected abstract ID getExistFindById();

	protected abstract ID getNotExistFindById();

	protected abstract Iterable<ID> getIterableById();

	@Before
	public void setUp() {
		System.out.println("::: setUp() :::");
		List<T> listEntity = listEntity();
		count = listEntity.size();
		this.crudRepository.saveAll(listEntity);
	}

	@After
	public void tearDown() {
		this.crudRepository.deleteAll();
	}

}
