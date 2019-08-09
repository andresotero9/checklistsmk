package br.com.sotero.checklistsmk.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.junit.Assert;
import org.junit.Test;

import br.com.sotero.checklistsmk.ICrudCommonTest;
import br.com.sotero.checklistsmk.constants.ConstantsMessageException;
import br.com.sotero.checklistsmk.exception.BusinessException;
import br.com.sotero.checklistsmk.model.ClassEntity;

public abstract class CrudServiceTest<T, ID> implements ICrudCommonTest<T, ID> {

	public abstract CrudService<T, ID> getService();

	// :::... INICIO - TESTES UNITARIOS ...:::

	@Test
	public void testSave() {
		getLog().info("testSave()");

		// Teste passando a entidade nula
		{
			try {
				getService().save(null);
				fail();
			} catch (BusinessException e) {
				Assert.assertSame(ConstantsMessageException.MSG_FALHA_PARAMETRO_NULO, e.getMessage());
			}
		}

		// Teste passando a entidade somente instanciada
		{
			try {
				getService().save(entityOnlyInstanced());
				fail();
			} catch (BusinessException e) {
				Assert.assertSame(ConstantsMessageException.MSG_FALHA_AO_SALVAR_O_REGISTRO, e.getMessage());
			}
		}

		// Teste passando a entidade real
		{
			T result = null;
			try {
				result = getService().save(entity());
			} catch (BusinessException e) {
				fail(e.getMessage());
			}
			Assert.assertTrue(castClassEntity(result).getId() != null);

			// Teste passando a mesma entidade para evitar duplicidade
			try {
				getService().save(entity());
				fail();
			} catch (BusinessException e) {
				assertSame(ConstantsMessageException.MSG_FALHA_AO_SALVAR_O_REGISTRO, e.getMessage());
			}

			// Teste update no objeto
			T resultUpdate = null;

			alteracaoNaEntidadeParaUpdate(result);

			try {
				resultUpdate = getService().save(result);
				assertEquals(castClassEntity(result).getId(), castClassEntity(resultUpdate).getId());
			} catch (Exception e) {
				fail(e.getMessage());
			}
		}

	}

	@Test
	public void testSaveAll() {
		getLog().info("testSaveAll()");

		// Testando lista nula
		{
			try {
				getService().saveAll(null);
				fail();
			} catch (BusinessException e) {
				assertSame(ConstantsMessageException.MSG_FALHA_PARAMETRO_NULO, e.getMessage());
			}
		}

		// Testando lista vazia
		{
			try {
				getService().saveAll(new ArrayList<>());
				fail();
			} catch (BusinessException e) {
				assertSame(ConstantsMessageException.MSG_LISTA_DE_REGISTROS_VAZIA, e.getMessage());
			}
		}

		{
			// Teste passando a lista real
			Iterable<T> result = null;

			result = popularTabela();
			assertTrue(result.iterator().hasNext());

			// Teste passando a mesma lista
			try {
				getService().saveAll(listEntity());
				fail();
			} catch (BusinessException e) {
				assertSame(ConstantsMessageException.MSG_FALHA_AO_SALVAR_O_REGISTRO, e.getMessage());
			}

			// Teste passando a lista alterada
			alteracaoNasEntidadesParaUpdate(result);

			try {
				Iterable<T> result2 = getService().saveAll(result);
				assertTrue(result2.iterator().hasNext());
			} catch (BusinessException e) {
				fail(e.getMessage());
			}
		}

	}

	@Test
	public void testFindById() {
		getLog().info("testFindById()");

		// Verifica objeto nulo
		{
			Optional<T> result = null;
			try {
				result = getService().findById(null);
			} catch (BusinessException e) {
				fail(e.getMessage());
			}

			assertFalse(result.isPresent());
		}

		// Verifica ID Zerado
		{
			Optional<T> result = null;
			try {
				result = getService().findById(getIDZerado());
			} catch (BusinessException e) {
				fail(e.getMessage());
			}
			assertFalse(result.isPresent());
		}

		// Verificando com ID que não existe
		{
			Optional<T> result = null;
			try {
				result = getService().findById(getIDNaoExiste());
			} catch (BusinessException e) {
				fail(e.getMessage());
			}
			assertFalse(result.isPresent());
		}

		// Verificando com ID válido
		{
			ClassEntity<ID> entity = null;

			try {
				entity = castClassEntity(getService().save(entity()));
			} catch (Exception e) {
				fail(e.getMessage());
			}

			Optional<T> result = null;
			try {
				result = getService().findById(entity.getId());
			} catch (BusinessException e) {
				fail(e.getMessage());
			}
			assertTrue(result.isPresent());
		}
	}

	@Test
	public void testExistsById() {
		getLog().info("testExistsById()");

		Iterable<T> list = popularTabela();

		try {
			// Teste passando valor nulo
			assertFalse(getService().existsById(null));
			// Teste passando valor Zerado
			assertFalse(getService().existsById(getIDZerado()));
			// Teste passando valor que não existe
			assertFalse(getService().existsById(getIDNaoExiste()));
			// Teste passando valor real
			assertTrue(getService().existsById((castClassEntity(list.iterator().next()).getId())));
		} catch (BusinessException e) {
			fail(e.getMessage());
		}

	}

	@Test
	public void testFindAll() {
		getLog().info("testFindAll()");

		// Procurar tabela vazia
		try {
			getService().findAll();
		} catch (BusinessException e) {
			fail(e.getMessage());
		}

		// Popular tabela
		popularTabela();

		// Procurar itens da tabela
		try {
			getService().findAll();
		} catch (BusinessException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testFindAllById() {
		getLog().info("testFindAllById()");

		// Teste passando valor nulo
		try {
			Iterable<T> result = getService().findAllById(null);
			assertFalse(result.iterator().hasNext());
		} catch (BusinessException e) {
			fail(e.getMessage());
		}

		// Teste passando valores reais
		{
			Iterable<T> list = popularTabela();

			List<ID> listIDs = getListIDs(list);
			try {
				Iterable<T> result = getService().findAllById(listIDs);
				assertSame(listIDs.size(), getListIDs(result).size());
			} catch (BusinessException e) {
				fail(e.getMessage());
			}
		}
	}

	@Test
	public void testCount() {
		getLog().info("testCount()");

		try {
			// Contagem de tabela vazia
			assertTrue(getService().count() == 0);

			// Inserindo registros na tabela
			popularTabela();

			// Contagem de tabela
			assertTrue(getService().count() > 0);
		} catch (BusinessException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testDeleteById() {
		getLog().info("testDeleteById()");

		// Teste parametro nulo
		try {
			getService().deleteById(null);
			fail();
		} catch (BusinessException e) {
			assertEquals(ConstantsMessageException.MSG_FALHA_PARAMETRO_NULO, e.getMessage());
		}

		// Teste com o ID zerado
		try {
			getService().deleteById(getIDZerado());
			fail();
		} catch (BusinessException e) {
			assertEquals(ConstantsMessageException.MSG_NENHUM_REGISTRO_FOI_DELETADO_COM_ID_INFORMADO, e.getMessage());
		}

		// Teste com o ID que não existe
		try {
			getService().deleteById(getIDNaoExiste());
			fail();
		} catch (BusinessException e) {
			assertEquals(ConstantsMessageException.MSG_NENHUM_REGISTRO_FOI_DELETADO_COM_ID_INFORMADO, e.getMessage());
		}

		Iterable<T> list = popularTabela();

		try {
			getService().deleteById(castClassEntity(list.iterator().next()).getId());
		} catch (BusinessException e) {
			fail(e.getMessage());
		}
	}

	@Test
	public void testDelete() {
		getLog().info("testDelete()");

		// Teste parametro nulo
		try {
			getService().delete(null);
			fail();
		} catch (BusinessException e) {
			assertEquals(ConstantsMessageException.MSG_FALHA_PARAMETRO_NULO, e.getMessage());
		}

		Iterable<T> list = popularTabela();

		// Teste com o ID do objeto nulo
		try {
			getService().delete(entityOnlyInstanced());
			fail();
		} catch (BusinessException e) {
			assertEquals(ConstantsMessageException.MSG_FALHA_ID_DO_REGISTRO_NULO, e.getMessage());
		}

		// Teste com objeto que não existe
		try {
			long count = getService().count();

			getService().delete(entity());

			assertEquals(count, getService().count());
		} catch (BusinessException e) {
			fail(e.getMessage());
		}

		// Teste com objeto real
		try {
			long count = getService().count();

			getService().delete(list.iterator().next());

			assertEquals(count - 1L, getService().count());
		} catch (BusinessException e) {
			fail(e.getMessage());
		}

	}

	@Test
	public void testDeleteAll() {
		getLog().info("testDeleteAll()");

		// Teste real
		try {
			getService().deleteAll();
			popularTabela();
			getService().deleteAll();
		} catch (BusinessException e) {
			fail(e.getMessage());
		}

		// Teste passando valor nulo
		try {
			getService().deleteAll(null);
			fail();
		} catch (BusinessException e) {
			assertSame(ConstantsMessageException.MSG_FALHA_PARAMETRO_NULO, e.getMessage());
		}

		// Teste passando lista vazia
		try {
			getService().deleteAll(new ArrayList<>());
			fail();
		} catch (BusinessException e) {
			assertSame(ConstantsMessageException.MSG_LISTA_DE_REGISTROS_VAZIA, e.getMessage());
		}

		Iterable<T> list = popularTabela();

		// Teste passando lista para delete
		try {
			getService().deleteAll(list);
			assertEquals(0L, getService().count());
		} catch (BusinessException e) {
			fail(e.getMessage());
		}
	}

	// :::... FIM - TESTES UNITARIOS ...:::

	@SuppressWarnings("unchecked")
	public ClassEntity<ID> castClassEntity(T t) {
		return (ClassEntity<ID>) t;
	}

	@Override
	public void alteracaoNasEntidadesParaUpdate(Iterable<T> result) {
		Iterator<T> iterator = result.iterator();

		while (iterator.hasNext()) {
			this.alteracaoNaEntidadeParaUpdate(iterator.next());
		}
	}

	private Iterable<T> popularTabela() {
		// Populando tabela
		try {
			return getService().saveAll(listEntity());
		} catch (BusinessException e) {
			fail(e.getMessage());
		}
		return null;
	}

	private List<ID> getListIDs(Iterable<T> list) {
		List<ID> listIDs = new ArrayList<>();

		Iterator<T> iterator = list.iterator();

		while (iterator.hasNext()) {
			listIDs.add(castClassEntity(iterator.next()).getId());
		}

		return listIDs;

	}

}
