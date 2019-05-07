package br.com.sotero.checklistsmk.repository;

interface ICrudRepositoryTest {

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
