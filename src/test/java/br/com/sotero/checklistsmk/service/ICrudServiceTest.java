package br.com.sotero.checklistsmk.service;

interface ICrudServiceTest {

	void testSave();

	void testSaveAll();

	void testSaveOnlyInstancedEntity();

	void testFindById();

	void testFindByIdNotExists();

	void testExistsById();

	void testNotExistsById();

	void testFindAll();

	void testFindAllById();

	void testCount();

	void testDeleteById();

	void testDelete();

	void testDeleteAll();

}
