package br.com.sotero.checklistsmk.repository;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

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

	@Override
	public void testFindById() {
		// TODO Auto-generated method stub

	}

	@Override
	public void testExistsById() {
		// TODO Auto-generated method stub

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

	abstract T entity();

	abstract List<T> listEntity();

	abstract List<T> listEntitySaveAll();
	
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
