package br.com.sotero.checklistsmk.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import br.com.sotero.checklistsmk.model.Categoria;
import br.com.sotero.checklistsmk.model.ResponseModel;
import br.com.sotero.checklistsmk.repository.CategoriaRepository;

@RestController
@RequestMapping("/service")
public class CategoriaService {

	@Autowired
	private CategoriaRepository categoriaRepository;

	/**
	 * SALVAR UM NOVO REGISTRO
	 * 
	 * @param categoria
	 * @return
	 */
	@RequestMapping(value = "/categoria", method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseModel salvar(@RequestBody Categoria categoria) {

		try {

			this.categoriaRepository.save(categoria);

			return new ResponseModel(1, "Registro salvo com sucesso!");

		} catch (Exception e) {

			return new ResponseModel(0, e.getMessage());
		}
	}

	/**
	 * ATUALIZAR O REGISTRO DE UMA CATEGORIA
	 * 
	 * @param categoria
	 * @return
	 */
	@RequestMapping(value = "/categoria", method = RequestMethod.PUT, consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseModel atualizar(@RequestBody Categoria categoria) {

		try {

			this.categoriaRepository.save(categoria);

			return new ResponseModel(1, "Registro atualizado com sucesso!");

		} catch (Exception e) {

			return new ResponseModel(0, e.getMessage());
		}
	}

	/**
	 * CONSULTAR TODAS AS CATEGORIAS
	 * 
	 * @return
	 */
	@RequestMapping(value = "/categoria", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Iterable<Categoria> consultar() {

		return this.categoriaRepository.findAll();
	}

	/**
	 * BUSCAR UMA CATEGORIA PELO CÓDIGO
	 * 
	 * @param codigo
	 * @return
	 */
	@RequestMapping(value = "/categoria/{codigo}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody Optional<Categoria> buscar(@PathVariable("codigo") Long codigo) {

		return this.categoriaRepository.findById(codigo);
	}

	/***
	 * EXCLUIR UM REGISTRO PELO CÓDIGO
	 * 
	 * @param codigo
	 * @return
	 */
	@RequestMapping(value = "/categoria/{codigo}", method = RequestMethod.DELETE, produces = MediaType.APPLICATION_JSON_UTF8_VALUE)
	public @ResponseBody ResponseModel excluir(@PathVariable("codigo") Long codigo) {

		Optional<Categoria> categoriaModel = categoriaRepository.findById(codigo);

		if (categoriaModel != null) {

			try {

				categoriaRepository.delete(categoriaModel.get());

				return new ResponseModel(1, "Registro excluido com sucesso!");

			} catch (Exception e) {
				return new ResponseModel(0, e.getMessage());
			}
		} else {
			return new ResponseModel(0, "Nenhum registro encontrado");

		}
	}

}
