package br.com.sotero.checklistsmk.repository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.sotero.checklistsmk.model.CategoriaModel;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class CategoriaRepositoryTest extends CrudRepositoryTest<CategoriaModel, Long> {

	@Test
	public void testSave() {
		super.testSave(new CategoriaModel("Alimentos"));
	}

}
