package br.com.sotero.checklistsmk;

import java.util.List;

import br.com.sotero.checklistsmk.exception.BusinessException;
import br.com.sotero.checklistsmk.model.ClassEntity;

public interface ICrudCommonTest<T, ID> {

	// :::... INICIO - TESTES UNITARIOS ...:::
	void testSave();

	void testSaveAll();

	void testFindById() throws BusinessException;

	void testExistsById();

	void testFindAll();

	void testFindAllById();

	void testCount();

	void testDeleteById();

	void testDelete();

	void testDeleteAll();
	// :::... FIM - TESTES UNITARIOS ...:::

	T entity();

	T entityNoId();

	T entityOnlyInstanced();

	List<T> listEntity();

	ID getIDNaoExiste();

	ID getIDZerado();

	ClassEntity<ID> castClassEntity(T t);

	void alteracaoNaEntidadeParaUpdate(ClassEntity<ID> entity);

	void alteracaoNasEntidadesParaUpdate(List<ClassEntity<ID>> listEntity);

}
