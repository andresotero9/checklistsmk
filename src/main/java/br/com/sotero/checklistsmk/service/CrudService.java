package br.com.sotero.checklistsmk.service;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.InvalidDataAccessApiUsageException;

import br.com.sotero.checklistsmk.constants.ConstantsMessageException;
import br.com.sotero.checklistsmk.exception.BusinessException;
import br.com.sotero.checklistsmk.model.ClassEntity;

public abstract class CrudService<T, ID> implements ICrudService<T, ID> {

	@Override
	public T save(T entity) throws BusinessException {
		getLog().info("Método save(T entity): {}", entity);

		if (entity == null) {
			throw new BusinessException(ConstantsMessageException.MSG_FALHA_PARAMETRO_NULO);
		}

		getLog().info("Salvando objeto: {}", entity);

		T save = null;

		try {
			save = getRepository().save(entity);
		} catch (DataIntegrityViolationException | InvalidDataAccessApiUsageException e) {
			throw new BusinessException(ConstantsMessageException.MSG_FALHA_AO_SALVAR_O_REGISTRO, e);
		} catch (Exception e) {
			throw new BusinessException(ConstantsMessageException.MSG_FALHA_NO_BANCO_DE_DADOS, e);
		}

		return save;
	}

	@Override
	public Iterable<T> saveAll(Iterable<T> entities) throws BusinessException {
		getLog().info("Método saveAll(Iterable<T> entities): {}", entities);

		if (entities == null) {
			throw new BusinessException(ConstantsMessageException.MSG_FALHA_PARAMETRO_NULO);
		} else if (!entities.iterator().hasNext()) {
			throw new BusinessException(ConstantsMessageException.MSG_LISTA_DE_REGISTROS_VAZIA);
		}

		getLog().info("Salvando lista de objetos: {}", entities);

		Iterable<T> saveAll = null;

		try {
			saveAll = getRepository().saveAll(entities);
		} catch (DataIntegrityViolationException e) {
			throw new BusinessException(ConstantsMessageException.MSG_FALHA_AO_SALVAR_O_REGISTRO, e);
		} catch (Exception e) {
			throw new BusinessException(ConstantsMessageException.MSG_FALHA_NO_BANCO_DE_DADOS, e);
		}

		return saveAll;
	}

	@Override
	public Optional<T> findById(ID id) throws BusinessException {
		getLog().info("Método findById(ID id): {}" + id);

		getLog().info("Buscando por ID: {}", id);
		if (id != null) {
			try {
				return getRepository().findById(id);
			} catch (Exception e) {
				throw new BusinessException(ConstantsMessageException.MSG_FALHA_AO_PROCURAR_O_REGISTRO, e);
			}
		} else {
			return Optional.empty();
		}
	}

	@Override
	public boolean existsById(ID id) throws BusinessException {
		getLog().info("Método existsById(ID id): {}", id);

		if (id == null) {
			return false;
		}

		try {
			return getRepository().existsById(id);
		} catch (Exception e) {
			throw new BusinessException(ConstantsMessageException.MSG_FALHA_AO_PROCURAR_O_REGISTRO, e);
		}
	}

	@Override
	public Iterable<T> findAll() throws BusinessException {
		getLog().info("Método findAll()");

		try {
			return getRepository().findAll();
		} catch (Exception e) {
			throw new BusinessException(ConstantsMessageException.MSG_FALHA_AO_PROCURAR_O_REGISTRO, e);
		}
	}

	@Override
	public Iterable<T> findAllById(Iterable<ID> ids) throws BusinessException {
		getLog().info("Método findAllById(Iterable<ID> ids): {}", ids);
		
		if (ids == null) {
			return new ArrayList<>();
		}

		try {
			return getRepository().findAllById(ids);
		} catch (Exception e) {
			throw new BusinessException(ConstantsMessageException.MSG_FALHA_AO_PROCURAR_REGISTROS_POR_IDS, e);
		}
	}

	@Override
	public long count() throws BusinessException {
		getLog().info("Método count()");
		
		try {
			return getRepository().count();
		} catch (Exception e) {
			throw new BusinessException(ConstantsMessageException.MSG_FALHA_AO_CONTAR_REGISTROS);
		}
	}

	@Override
	public void deleteById(ID id) throws BusinessException {
		getLog().info("Método deleteById(ID id): {}", id);
		
		if (id == null) {
			throw new BusinessException(ConstantsMessageException.MSG_FALHA_PARAMETRO_NULO);
		}

		try {
			getRepository().deleteById(id);
		} catch (InvalidDataAccessApiUsageException e) {
			throw new BusinessException(ConstantsMessageException.MSG_FALHA_AO_DELETAR_REGISTRO_POR_ID, e);
		} catch (EmptyResultDataAccessException e) {
			throw new BusinessException(ConstantsMessageException.MSG_NENHUM_REGISTRO_FOI_DELETADO_COM_ID_INFORMADO, e);
		} catch (Exception e) {
			throw new BusinessException(ConstantsMessageException.MSG_FALHA_NO_BANCO_DE_DADOS, e);
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public void delete(T entity) throws BusinessException {
		getLog().info("Método delete(T entity): {}", entity);
		
		if (entity == null) {
			throw new BusinessException(ConstantsMessageException.MSG_FALHA_PARAMETRO_NULO);
		} else if( ((ClassEntity<ID>)  entity).getId() == null ) {
			throw new BusinessException(ConstantsMessageException.MSG_FALHA_ID_DO_REGISTRO_NULO);
		}
		
		getRepository().delete(entity);
	}

	@Override
	public void deleteAll(Iterable<? extends T> entities) throws BusinessException {
		getLog().info("Método deleteAll(Iterable<? extends T> entities): {}", entities);
		
		if (entities == null) {
			throw new BusinessException(ConstantsMessageException.MSG_FALHA_PARAMETRO_NULO);
		}
		
		if(!entities.iterator().hasNext()) {
			throw new BusinessException(ConstantsMessageException.MSG_LISTA_DE_REGISTROS_VAZIA);
		}
		
		try {
			getRepository().deleteAll(entities);
		} catch (Exception e) {
			throw new BusinessException("");
		}
		
	}

	@Override
	public void deleteAll() throws BusinessException {
		getLog().info("Método deleteAll()");
		
		try {
			getRepository().deleteAll();
		} catch (Exception e) {
			throw new BusinessException(ConstantsMessageException.MSG_FALHA_AO_DELETAR_REGISTROS, e);
		}
	}

}
