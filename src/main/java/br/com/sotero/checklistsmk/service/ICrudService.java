package br.com.sotero.checklistsmk.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.data.repository.CrudRepository;

import br.com.sotero.checklistsmk.exception.BusinessException;

public interface ICrudService<T, ID> {

	T save(T entity) throws BusinessException;

	Iterable<T> saveAll(Iterable<T> entities);

	Optional<T> findById(ID id) throws BusinessException;

	boolean existsById(ID id);

	Iterable<T> findAll();

	Iterable<T> findAllById(Iterable<ID> ids);

	long count();

	void deleteById(ID id);

	void delete(T entity);

	void deleteAll(Iterable<? extends T> entities);

	void deleteAll();

	CrudRepository<T, ID> getRepository();

	Logger getLog();

}
