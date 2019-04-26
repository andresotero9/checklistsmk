package br.com.sotero.checklistsmk.repository;

interface ICrudRepositoryTest<T, ID> {

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

}
