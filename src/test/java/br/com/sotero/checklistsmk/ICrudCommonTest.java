package br.com.sotero.checklistsmk;

import java.util.List;

import br.com.sotero.checklistsmk.model.ClassEntity;

public interface ICrudCommonTest<T, ID> {

	// :::... INICIO - TESTES UNITARIOS ...:::
	void testSave();

	void testSaveAll();

	void testFindById();

	void testExistsById();

	void testFindAll();

	void testFindAllById();

	void testCount();

	void testDeleteById();

	void testDelete();

	void testDeleteAll();

	void alteracaoNaEntidadeParaUpdate(T t);

	void alteracaoNasEntidadesParaUpdate(Iterable<T> result);

	// :::... FIM - TESTES UNITARIOS ...:::

	T entity();

	T entityOnlyInstanced();

	List<T> listEntity();

	ID getIDNaoExiste();

	ID getIDZerado();

	ClassEntity<ID> castClassEntity(T t);

}
