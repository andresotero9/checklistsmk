package br.com.sotero.checklistsmk.service;

import java.util.Optional;

public interface ICrudService<T, ID> {
	T save(T entity);

	Iterable<T> saveAll(Iterable<T> entities);

	Optional<T> findById(ID id);

	boolean existsById(ID id);

	Iterable<T> findAll();

	Iterable<T> findAllById(Iterable<ID> ids);

	long count();

	void deleteById(ID id);

	void delete(T entity);

	void deleteAll(Iterable<T> entities);

	void deleteAll();
}
