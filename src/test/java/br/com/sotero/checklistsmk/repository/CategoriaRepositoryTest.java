package br.com.sotero.checklistsmk.repository;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.sotero.checklistsmk.data.CategoriaData;
import br.com.sotero.checklistsmk.model.CategoriaModel;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class CategoriaRepositoryTest extends CrudRepositoryTest<CategoriaModel, Long> {

	@Override
	public CategoriaModel entity() {
		return CategoriaData.getCategoria();
	}

	@Override
	public CategoriaModel entityOnlyInstanced() {
		return new CategoriaModel();
	}
	
	@Override
	public List<CategoriaModel> listEntity() {
		List<CategoriaModel> listCategoria = new ArrayList<CategoriaModel>();
		listCategoria.add(new CategoriaModel("Bebidas"));
		listCategoria.add(new CategoriaModel("Carnes"));
		listCategoria.add(new CategoriaModel("Limpeza"));
		listCategoria.add(new CategoriaModel(11L, "Perfumaria"));
		listCategoria.add(new CategoriaModel(12L, "Bebês"));
		listCategoria.add(new CategoriaModel(13L, "Peixaria"));
		return listCategoria;
	}

	@Override
	public List<CategoriaModel> listEntitySaveAll() {
		List<CategoriaModel> listCategoria = new ArrayList<CategoriaModel>();
		listCategoria.add(new CategoriaModel("Automotivo"));
		listCategoria.add(new CategoriaModel("Eletrônicos"));
		listCategoria.add(new CategoriaModel("Eletrodomésticos"));
		return listCategoria;
	}

	@Test
	public void testSaveNmeCategoriaUnique() {
		System.out.println("::: testSaveNmeCategoriaUnique() :::");
		try {
			crudRepository.save(new CategoriaModel("Bebidas"));
			fail();
		} catch (DataIntegrityViolationException e) {
			assertTrue(true);
		} catch (Exception e) {
			fail();
		}
	}

	@Override
	public Long existFindById() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Long notExistFindById() {
		// TODO Auto-generated method stub
		return null;
	}




}
