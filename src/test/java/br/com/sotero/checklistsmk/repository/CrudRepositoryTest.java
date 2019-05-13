package br.com.sotero.checklistsmk.repository;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Iterator;
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
		ClassEntity<ID> result = castClassEntity(this.crudRepository.save(entity()));
		assertTrue(result.getId() != null);
	}

	@Test
	@Override
	public void testSaveAll() {
		System.out.println("::: CrudRepositoryTest.testSaveAll() :::");
		List<T> listEntitySaveAll = listEntitySaveAll();
		List<ClassEntity<ID>> saveAll = castListClassEntity(this.crudRepository.saveAll(listEntitySaveAll));
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
		List<ClassEntity<ID>> list = castListClassEntity(this.crudRepository.findAll());
		ClassEntity<ID> classEntity = list.get(0);
		ClassEntity<ID> result = castClassEntity(this.crudRepository.findById(classEntity.getId()).get());
		assertEquals(classEntity, result);
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
		List<ClassEntity<ID>> list = castListClassEntity(this.crudRepository.findAll());
		ClassEntity<ID> classEntity = list.get(0);
		ClassEntity<ID> resultClassEntity = castClassEntity(this.crudRepository.findById(classEntity.getId()).get());
		boolean result = this.crudRepository.existsById(resultClassEntity.getId());
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

		Iterable<T> findAll = this.crudRepository.findAll();

		List<ID> listIDs = new ArrayList<>();

		while (findAll.iterator().hasNext()) {
			ClassEntity<ID> castClassEntity = castClassEntity(findAll.iterator().next());
			listIDs.add(castClassEntity.getId());
		}

		List<ClassEntity<ID>> result = castListClassEntity(this.crudRepository.findAllById(listIDs));

		assertTrue(listIDs.size() == result.size());
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

	@SuppressWarnings("unchecked")
	private ClassEntity<ID> castClassEntity(T t) {
		return (ClassEntity<ID>) t;
	}

	@SuppressWarnings("unchecked")
	private List<ClassEntity<ID>> castListClassEntity(Iterable<T> iterable) {
		return (List<ClassEntity<ID>>) iterable;
	}

	protected abstract T entity();

	protected abstract T entityOnlyInstanced();

	protected abstract List<T> listEntity();

	protected abstract List<T> listEntitySaveAll();

	protected abstract ID getNotExistFindById();

	protected abstract Iterable<ID> getIterableById();

	@Before
	public void setUp() {
		System.out.println("::: setUp() :::");
		List<T> listEntity = listEntity();
		count = listEntity.size();
		List<ClassEntity<ID>> saveAll = (ArrayList<ClassEntity<ID>>) this.crudRepository.saveAll(listEntity);
		for (ClassEntity<ID> object : saveAll) {
			System.out.println(object.getId());
		}
	}

	@After
	public void tearDown() {
		this.crudRepository.deleteAll();
	}

}
