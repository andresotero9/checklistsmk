package br.com.sotero.checklistsmk.control;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import br.com.sotero.checklistsmk.dto.IDTO;
import br.com.sotero.checklistsmk.exception.BusinessException;
import br.com.sotero.checklistsmk.model.ClassEntity;
import br.com.sotero.checklistsmk.service.CrudService;

public abstract class Controller {

	protected abstract Logger getLog();

	@SuppressWarnings("rawtypes")
	protected abstract CrudService getService() throws BusinessException;

	// ********************** INICIO - REQUESTS **********************
	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected ResponseEntity save(IDTO dto) {
		getLog().info("save({})", dto);

		ClassEntity classEntity = null;

		try {
			classEntity = (ClassEntity) getService().save(convertDtoToObject(dto));
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}

		return ResponseEntity.ok(classEntity);
	}

	@SuppressWarnings({ "rawtypes", "unchecked" })
	protected ResponseEntity saveAll(List<? extends IDTO> dtos) {
		getLog().info("saveAll({})", dtos);

		List<ClassEntity> listClassEntity = null;

		try {
			listClassEntity = (List<ClassEntity>) getService().saveAll(convertDtoToObject((List<IDTO>) dtos));
		} catch (BusinessException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}

		return ResponseEntity.ok(listClassEntity);
	}

	@GetMapping
	@SuppressWarnings("rawtypes")
	private ResponseEntity findAll() {
		getLog().info("findAll()");

		Iterable findAll = null;
		try {
			findAll = getService().findAll();
		} catch (BusinessException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}

		return ResponseEntity.ok(findAll);
	}

	@GetMapping(value = "/{id}")
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ResponseEntity findById(@PathVariable("id") long id) {
		getLog().info("findById({})", id);

		Optional findById = null;
		try {
			findById = getService().findById(id);
		} catch (BusinessException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}

		return ResponseEntity.ok(findById);
	}

	@GetMapping(value = "/existsById/{id}")
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ResponseEntity existsById(@PathVariable("id") long id) {
		getLog().info("existsById({})", id);

		boolean existsById = Boolean.FALSE;

		try {
			existsById = getService().existsById(id);
		} catch (BusinessException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}

		return ResponseEntity.ok(existsById);
	}

	@GetMapping(value = "/count")
	@SuppressWarnings("rawtypes")
	public ResponseEntity count() {
		getLog().info("count()");

		long count = 0L;

		try {
			count = getService().count();
		} catch (BusinessException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}

		return ResponseEntity.ok(count);
	}

	@DeleteMapping(value = "/deleteById/{id}")
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public ResponseEntity deleteById(@PathVariable("id") long id) {
		getLog().info("deleteById({})", id);

		try {
			boolean existsById = getService().existsById(id);

			if (existsById) {
				getService().deleteById(id);
				return ResponseEntity.ok("Registro [ID: " + id + "] excluido com sucesso.");
			} else {
				return ResponseEntity.ok("Registro [ID: " + id + "] não existe.");
			}
		} catch (BusinessException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}
	}

	@DeleteMapping
	@SuppressWarnings("rawtypes")
	public ResponseEntity deleteAll() {
		getLog().info("deleteAll()");

		try {
			getService().deleteAll();
		} catch (BusinessException e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
		}

		return ResponseEntity.ok("Todos os registros foram excluidos com sucesso.");
	}
	// ********************** FIM - REQUESTS **********************

	@SuppressWarnings("rawtypes")
	private List<ClassEntity> convertDtoToObject(List<IDTO> dtos) {
		List<ClassEntity> list = null;

		if (dtos != null && dtos.size() > 0) {
			list = new ArrayList<ClassEntity>();
			for (IDTO dto : dtos) {
				list.add(convertDtoToObject(dto));
			}
		}

		return list;
	}

	@SuppressWarnings("rawtypes")
	abstract ClassEntity convertDtoToObject(IDTO dto);

//	findAllById(Iterable<ID>)
//	delete(T)
//	deleteAll(Iterable<? extends T>)
}
