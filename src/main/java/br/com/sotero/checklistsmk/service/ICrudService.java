package br.com.sotero.checklistsmk.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.data.repository.CrudRepository;

import br.com.sotero.checklistsmk.exception.BusinessException;

public interface ICrudService<T, ID> {
	
	CrudRepository<T, ID> getRepository() throws BusinessException;

	Logger getLog() throws BusinessException;
	
	
	T save(T entity) throws BusinessException;

	Iterable<T> saveAll(Iterable<T> entities) throws BusinessException;

	Optional<T> findById(ID id) throws BusinessException;

	boolean existsById(ID id) throws BusinessException;

	Iterable<T> findAll() throws BusinessException;

	Iterable<T> findAllById(Iterable<ID> ids) throws BusinessException;

	long count() throws BusinessException;

	void deleteById(ID id) throws BusinessException;

	void delete(T entity) throws BusinessException;

	void deleteAll(Iterable<? extends T> entities) throws BusinessException;

	void deleteAll() throws BusinessException;


}
