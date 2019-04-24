package br.com.sotero.checklistsmk.repository;

import java.util.Optional;

public interface ICrudRepositoryTest<T, ID> {
	<S extends T> S testSave(S entity);

	<S extends T> Iterable<S> testSaveAll(Iterable<S> entities);

	Optional<T> testFindById(ID id);

	boolean testExistsById(ID id);

	Iterable<T> testFindAll();

	Iterable<T> testFindAllById(Iterable<ID> ids);

	long testCount();

	void testDeleteById(ID id);

	void testDelete(T entity);

	void testDeleteAll(Iterable<? extends T> entities);

	void testDeleteAll();
}
