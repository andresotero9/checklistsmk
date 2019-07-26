package br.com.sotero.checklistsmk.service;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.sotero.checklistsmk.data.CategoriaData;
import br.com.sotero.checklistsmk.model.Categoria;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class CategoriaServiceTest extends CrudServiceTest<Categoria, Long> {

	@Autowired
	private CategoriaService categoriaService;

	@Before
	public void setUp() {

	}

	@Override
	public CrudService<Categoria, Long> getService() {
		return categoriaService;
	}

	@Override
	public Long getIDZerado() {
		return 0L;
	}

	@Override
	public Categoria entity() {
		return CategoriaData.getCategoria("Alimentos");
	}

	@Override
	public Categoria entityNoId() {
		return CategoriaData.getCategoriaNoId("Alimentos");
	}

}
