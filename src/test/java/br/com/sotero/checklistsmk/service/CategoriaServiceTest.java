package br.com.sotero.checklistsmk.service;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.sotero.checklistsmk.repository.CategoriaRepository;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class CategoriaServiceTest extends CrudServiceTest{

	@MockBean
	private CategoriaRepository categoriaRepository;
	
	@Autowired
	private CategoriaService categoriaService;
	
	@Before
	public void setUp() {
		
	}

}
