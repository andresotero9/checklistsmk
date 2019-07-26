package br.com.sotero.checklistsmk.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.InvalidDataAccessApiUsageException;
import org.springframework.data.repository.CrudRepository;

import br.com.sotero.checklistsmk.exception.BusinessException;

public class CrudService<T, ID> implements ICrudService<T, ID> {

	@Override
	public T save(T entity) throws BusinessException {

		if (entity == null) {
			throw new BusinessException("Objeto \"entity\" é nulo");
		}

		getLog().info("Salvando objeto: {}", entity);

		T save = null;

		try {
			save = getRepository().save(entity);
		} catch (DataIntegrityViolationException | InvalidDataAccessApiUsageException e) {
			throw new BusinessException("Falha ao salvar o objeto. ", e);
		}

		return save;
	}

	@Override
	public Optional<T> findById(ID id) throws BusinessException {
		getLog().info("Buscando por ID: {}", id);
		if (id != null) {
			try {
				return getRepository().findById(id);
			} catch (Exception e) {
				throw new BusinessException("Falha ao procurar o objeto. ", e); 
			}
		} else {
			return Optional.empty();
		}
	}

	@Override
	public Iterable<T> saveAll(Iterable<T> entities) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean existsById(ID id) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Iterable<T> findAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterable<T> findAllById(Iterable<ID> ids) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public long count() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void deleteById(ID id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(T entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll(Iterable<? extends T> entities) {
		// TODO Auto-generated method stub

	}

	@Override
	public void deleteAll() {
		// TODO Auto-generated method stub

	}

	@Override
	public CrudRepository<T, ID> getRepository() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Logger getLog() {
		// TODO Auto-generated method stub
		return null;
	}

//
//	@Override
//	public Iterable<Categoria> saveAll(Iterable<Categoria> entities) {
//		log.info("Salvando Categorias : {}", entities);
//		return categoriaRepository.saveAll(entities);
//	}
//
//	@Override
//	public boolean existsById(Long id) {
//		log.info("Verificando se existe Categoria por ID: {}", id);
//		return categoriaRepository.existsById(id);
//	}
//
//	@Override
//	public Iterable<Categoria> findAll() {
//		log.info("Buscando todas as Categorias");
//		return categoriaRepository.findAll();
//	}
//
//	@Override
//	public Iterable<Categoria> findAllById(Iterable<Long> ids) {
//		log.info("Buscando todas as Categorias por IDs: {}", ids);
//		return categoriaRepository.findAllById(ids);
//	}
//
//	@Override
//	public long count() {
//		log.info("Contando a quantidade de Categorias");
//		return categoriaRepository.count();
//	}
//
//	@Override
//	public void deleteById(Long id) {
//		log.info("Deletando Categoria por ID: {}", id);
//		categoriaRepository.deleteById(id);
//	}
//
//	@Override
//	public void delete(Categoria entity) {
//		log.info("Deletando Categoria selecionada: {}", entity);
//		categoriaRepository.delete(entity);
//	}
//
//	@Override
//	public void deleteAll(Iterable<Categoria> entities) {
//		log.info("Deletando todas as Categorias selecionadas: {}", entities);
//		categoriaRepository.deleteAll(entities);
//	}
//
//	@Override
//	public void deleteAll() {
//		log.info("Deletando todas as Categorias");
//		categoriaRepository.deleteAll();
//	}

}
