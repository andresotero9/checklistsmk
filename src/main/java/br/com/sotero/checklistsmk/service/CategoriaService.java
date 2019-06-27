package br.com.sotero.checklistsmk.service;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.sotero.checklistsmk.model.Categoria;
import br.com.sotero.checklistsmk.repository.CategoriaRepository;

//@RestController
//@RequestMapping("/service")
@Service
public class CategoriaService implements ICrudService<Categoria, Long> {

	private static final Logger log = LoggerFactory.getLogger(CategoriaService.class);

	@Autowired
	private CategoriaRepository categoriaRepository;

	@Override
	public Optional<Categoria> findById(Long id) {
		log.info("Buscando Categoria por ID: {}", id);
		return categoriaRepository.findById(id);
	}

	@Override
	public Categoria save(Categoria entity) {
		log.info("Salvando Categoria: {}", entity);
		return categoriaRepository.save(entity);
	}

	@Override
	public Iterable<Categoria> saveAll(Iterable<Categoria> entities) {
		log.info("Salvando Categorias : {}", entities);
		return categoriaRepository.saveAll(entities);
	}

	@Override
	public boolean existsById(Long id) {
		log.info("Verificando se existe Categoria por ID: {}", id);
		return categoriaRepository.existsById(id);
	}

	@Override
	public Iterable<Categoria> findAll() {
		log.info("Buscando todas as Categorias");
		return categoriaRepository.findAll();
	}

	@Override
	public Iterable<Categoria> findAllById(Iterable<Long> ids) {
		log.info("Buscando todas as Categorias por IDs: {}", ids);
		return categoriaRepository.findAllById(ids);
	}

	@Override
	public long count() {
		log.info("Contando a quantidade de Categorias");
		return categoriaRepository.count();
	}

	@Override
	public void deleteById(Long id) {
		log.info("Deletando Categoria por ID: {}", id);
		categoriaRepository.deleteById(id);
	}

	@Override
	public void delete(Categoria entity) {
		log.info("Deletando Categoria selecionada: {}", entity);
		categoriaRepository.delete(entity);
	}

	@Override
	public void deleteAll(Iterable<Categoria> entities) {
		log.info("Deletando todas as Categorias selecionadas: {}", entities);
		categoriaRepository.deleteAll(entities);
	}

	@Override
	public void deleteAll() {
		log.info("Deletando todas as Categorias");
		categoriaRepository.deleteAll();
	}

//	/**
//	 * SALVAR UM NOVO REGISTRO
//	 * 
//	 * @param categoria
//	 * @return
//	 */
//	@RequestMapping(value = "/categoria", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//	public @ResponseBody ResponseModel salvar(@RequestBody Categoria categoria) {
//
//		try {
//
//			this.categoriaRepository.save(categoria);
//
//			return new ResponseModel(1, "Registro salvo com sucesso!");
//
//		} catch (Exception e) {
//
//			return new ResponseModel(0, e.getMessage());
//		}
//	}
//
//	/**
//	 * ATUALIZAR O REGISTRO DE UMA CATEGORIA
//	 * 
//	 * @param categoria
//	 * @return
//	 */
//	@RequestMapping(value = "/categoria", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
//	public @ResponseBody ResponseModel atualizar(@RequestBody Categoria categoria) {
//
//		try {
//
//			this.categoriaRepository.save(categoria);
//
//			return new ResponseModel(1, "Registro atualizado com sucesso!");
//
//		} catch (Exception e) {
//
//			return new ResponseModel(0, e.getMessage());
//		}
//	}
//
//	/**
//	 * CONSULTAR TODAS AS CATEGORIAS
//	 * 
//	 * @return
//	 */
//	@RequestMapping(value = "/categoria", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//	public @ResponseBody Iterable<Categoria> consultar() {
//
//		return this.categoriaRepository.findAll();
//	}
//
//	/**
//	 * BUSCAR UMA CATEGORIA PELO CÓDIGO
//	 * 
//	 * @param codigo
//	 * @return
//	 */
//	@RequestMapping(value = "/categoria/{codigo}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//	public @ResponseBody Optional<Categoria> buscar(@PathVariable("codigo") Long codigo) {
//
//		return this.categoriaRepository.findById(codigo);
//	}
//
//	/***
//	 * EXCLUIR UM REGISTRO PELO CÓDIGO
//	 * 
//	 * @param codigo
//	 * @return
//	 */
//	@RequestMapping(value = "/categoria/{codigo}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
//	public @ResponseBody ResponseModel excluir(@PathVariable("codigo") Long codigo) {
//
//		Optional<Categoria> categoriaModel = categoriaRepository.findById(codigo);
//
//		if (categoriaModel != null) {
//
//			try {
//
//				categoriaRepository.delete(categoriaModel.get());
//
//				return new ResponseModel(1, "Registro excluido com sucesso!");
//
//			} catch (Exception e) {
//				return new ResponseModel(0, e.getMessage());
//			}
//		} else {
//			return new ResponseModel(0, "Nenhum registro encontrado");
//
//		}
//	}

}
