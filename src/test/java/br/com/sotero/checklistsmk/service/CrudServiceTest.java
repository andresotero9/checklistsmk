package br.com.sotero.checklistsmk.service;

import static org.junit.Assert.fail;

import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;

import br.com.sotero.checklistsmk.ICrudCommonTest;
import br.com.sotero.checklistsmk.exception.BusinessException;
import br.com.sotero.checklistsmk.model.ClassEntity;

public abstract class CrudServiceTest<T, ID> implements ICrudCommonTest<T, ID> {

	// :::... INICIO - TESTES UNITARIOS ...:::

	@Test
	public void testSave() {
		System.out.println("::: CrudServiceTest.testSave() :::");

		ClassEntity<ID> result = null;
		try {
			result = castClassEntity(getService().save(entity()));
		} catch (BusinessException e) {
			fail(e.getMessage());
		}
		Assert.assertTrue(result.getId() != null);
	}

	@Override
	public void testSaveAll() {
		System.out.println("::: CrudServiceTest.testSaveAll() :::");

	}

	@Test
	public void testFindById() throws BusinessException {
		System.out.println("::: CrudServiceTest.testFindById() :::");

		// Verifica objeto nulo
		{
			Optional<T> result = getService().findById(null);
			Assert.assertFalse(result.isPresent());
		}

		// Verifica ID Zerado
		{
			Optional<T> result = getService().findById(getIDZerado());
			Assert.assertFalse(result.isPresent());
		}

		// Verificando com ID que não existe
		{
			Optional<T> result = getService().findById(getIDNaoExiste());
			Assert.assertFalse(result.isPresent());
		}

		// Verificando com ID válido
		{
			ClassEntity<ID> entity = null;

			try {
				entity = castClassEntity(getService().save(entity()));
			} catch (Exception e) {
				fail(e.getMessage());
			}

			Optional<T> result = getService().findById(entity.getId());
			Assert.assertTrue(result.isPresent());
		}
	}

	protected abstract CrudService<T, ID> getService();

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

	@Override
	public T entity() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T entityNoId() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public T entityOnlyInstanced() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<T> listEntity() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ID getIDNaoExiste() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ID getIDZerado() {
		// TODO Auto-generated method stub
		return null;
	}

	@SuppressWarnings("unchecked")
	@Override
	public ClassEntity<ID> castClassEntity(T t) {
		return (ClassEntity<ID>) t;
	}

	@Override
	public void alteracaoNaEntidadeParaUpdate(ClassEntity<ID> entity) {
		// TODO Auto-generated method stub

	}

	@Override
	public void alteracaoNasEntidadesParaUpdate(List<ClassEntity<ID>> listEntity) {
		// TODO Auto-generated method stub

	}
}
