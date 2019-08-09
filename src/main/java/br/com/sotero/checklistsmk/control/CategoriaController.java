package br.com.sotero.checklistsmk.control;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sotero.checklistsmk.dto.CategoriaDTO;
import br.com.sotero.checklistsmk.exception.BusinessException;
import br.com.sotero.checklistsmk.model.Categoria;
import br.com.sotero.checklistsmk.response.Response;
import br.com.sotero.checklistsmk.service.CategoriaService;

@RestController
@RequestMapping("/categoria")
public class CategoriaController {
	private static final Logger log = LoggerFactory.getLogger(CategoriaController.class);

	@Autowired
	private CategoriaService categoriaService;

	@PostMapping
	public ResponseEntity<Response<CategoriaDTO>> save(@Valid @RequestBody CategoriaDTO dto) {
		log.info("save({})", dto);

		Response<CategoriaDTO> response = new Response<>();

		Categoria categoria = null;

		try {
			categoria = categoriaService.save(convertDtoToObject(dto));
		} catch (Exception e) {
			response.getErrors().add(e.getMessage());
		}

		response.setData(convertObjectToDto(categoria));

		return ResponseEntity.ok(response);
	}

	@GetMapping
	public Iterable<Categoria> consultar() {
		try {
			return this.categoriaService.findAll();
		} catch (BusinessException e) {
			e.printStackTrace();
		}
		return null;
	}

	private Categoria convertDtoToObject(CategoriaDTO dto) {
		Categoria categoria = new Categoria();
		categoria.setNmeCategoria(dto.getNmeCategoria());
		return categoria;
	}

	private CategoriaDTO convertObjectToDto(Categoria categoria) {
		CategoriaDTO dto = new CategoriaDTO();
		dto.setIdCategoria(categoria.getId());
		dto.setNmeCategoria(categoria.getNmeCategoria());
		return dto;
	}

}
