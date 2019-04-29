package br.com.sotero.checklistsmk.repository;

import static org.assertj.core.api.Assertions.assertThat;
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

import br.com.sotero.checklistsmk.model.Model;

public abstract class CrudRepositoryTest<T, ID> implements ICrudRepositoryTest<T, ID> {

	@Autowired
	protected CrudRepository<T, ID> crudRepository;

	@Test
	@Override
	public void testSave() {
		System.out.println("::: testSave() :::");
		T result = this.crudRepository.save(entity());
		assertNotNull(result);
	}

	@Test
	@Override
	public void testSaveAll() {
		System.out.println("::: testSaveAll() :::");
		List<T> saveAll = (List<T>) this.crudRepository.saveAll(listEntitySaveAll());
		assertThat(saveAll);
	}

	@Test
	@Override
	public void testSaveOnlyInstancedEntity() {
		System.out.println("::: testSaveOnlyInstancedEntity() :::");
		try {
			this.crudRepository.save(entityOnlyInstanced());
			fail();
		} catch (DataIntegrityViolationException e) {
			assertTrue(true);
		} catch (Exception e) {
			fail();
		}
	}

	@Override
	public void testFindById() {
		Optional<T> listEntity = this.crudRepository.findById(existFindById());
		if (listEntity.isPresent()) {
			Model<?> t = (Model<?>) listEntity.get();
			assertTrue(t.getId() == existFindById());
		} else {
			fail();
		}
	}

	@Override
	public void testExistsById() {
//		this.crudRepository.existsById(te)

	}

	@Override
	public void testFindAll() {
		// TODO Auto-generated method stub

	}

	@Override
	public void testFindAllById() {
		// TODO Auto-generated method stub

	}

	@Override
	public void testCount() {
		// TODO Auto-generated method stub

	}

	@Override
	public void testDeleteById() {
		// TODO Auto-generated method stub

	}

	@Override
	public void testDelete() {
		// TODO Auto-generated method stub

	}

	@Override
	public void testDeleteAll() {
		// TODO Auto-generated method stub

	}

	public abstract T entity();

	public abstract T entityOnlyInstanced();

	public abstract List<T> listEntity();

	public abstract List<T> listEntitySaveAll();

	public abstract ID existFindById();

	public abstract ID notExistFindById();

	@Before
	public void setUp() {
		System.out.println("::: setUp() :::");
		this.crudRepository.saveAll(listEntity());
	}

	@After
	public void tearDown() {
		System.out.println("::: tearDown() :::");
		this.crudRepository.deleteAll();
	}
}
