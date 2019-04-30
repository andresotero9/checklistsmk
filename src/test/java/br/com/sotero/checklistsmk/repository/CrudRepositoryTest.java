package br.com.sotero.checklistsmk.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.List;
import java.util.Optional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.repository.CrudRepository;

import br.com.sotero.checklistsmk.model.ClassEntity;

public abstract class CrudRepositoryTest<T, ID> implements ICrudRepositoryTest<T, ID> {

	private ID idFindById;

	@Autowired
	protected CrudRepository<T, ID> crudRepository;

	@Test
	@Override
	public void testSave() {
		System.out.println("::: CrudRepositoryTest.testSave() :::");
		T result = this.crudRepository.save(entity());
		assertNotNull(result);
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
			fail();
		}
	}

	@Test
	@Override
	public void testFindById() {
		System.out.println("::: CrudRepositoryTest.testFindById() :::");
		Optional<T> listEntity = this.crudRepository.findById(idFindById);
		if (listEntity.isPresent()) {
			ClassEntity<?> t = (ClassEntity<?>) listEntity.get();
			assertTrue(t.getId() == idFindById);
		} else {
			fail();
		}
	}

	@Test
	@Override
	public void testFindByIdNotExists() {
		System.out.println("::: CrudRepositoryTest.testFindByIdNotExists() :::");
		Optional<T> result = this.crudRepository.findById(notExistFindById());
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
		boolean result = this.crudRepository.existsById(notExistFindById());
		assertFalse(result);
	}

	@Override
	public void testFindAll() {
		this.crudRepository.findAll();

	}

	@Override
	public void testFindAllById() {
		this.crudRepository.findAllById(null);

	}

	@Override
	public void testCount() {
		this.crudRepository.count();

	}

	@Override
	public void testDeleteById() {
		this.crudRepository.deleteById(null);

	}

	@Override
	public void testDelete() {
		this.crudRepository.delete(null);

	}

	@Override
	public void testDeleteAll() {
		this.crudRepository.deleteAll();

	}

	public abstract T entity();

	public abstract T entityOnlyInstanced();

	public abstract List<T> listEntity();

	public abstract List<T> listEntitySaveAll();

	public abstract ID notExistFindById();

	@SuppressWarnings("unchecked")
	@Before
	public void setUp() {
		System.out.println("::: CrudRepositoryTest.setUp() :::");
		Iterable<T> list = this.crudRepository.saveAll(listEntity());
		idFindById = ((ClassEntity<ID>) list.iterator().next()).getId();
	}

	@After
	public void tearDown() {
		System.out.println("::: CrudRepositoryTest.tearDown() :::");
		this.crudRepository.deleteAll();
	}
}
