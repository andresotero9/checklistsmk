package br.com.sotero.checklistsmk.repository;

import static org.junit.Assert.assertNotNull;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;


public class CrudRepositoryTest<T, ID> {

	@Autowired
	private CrudRepository<T, ID> crudRepository;

	void testSave(T entity) {
		T result = this.crudRepository.save(entity);
		assertNotNull(result);
	}

	
}
