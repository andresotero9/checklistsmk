package br.com.sotero.checklistsmk.control;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.sotero.checklistsmk.dto.CategoriaDTO;
import br.com.sotero.checklistsmk.dto.IDTO;
import br.com.sotero.checklistsmk.exception.BusinessException;
import br.com.sotero.checklistsmk.model.Categoria;
import br.com.sotero.checklistsmk.model.ClassEntity;
import br.com.sotero.checklistsmk.service.CategoriaService;
import br.com.sotero.checklistsmk.service.CrudService;

@RestController
@RequestMapping("/categoria")
public class CategoriaController extends Controller {
	private static final Logger log = LoggerFactory.getLogger(CategoriaController.class);

	@Autowired
	private CategoriaService categoriaService;

	@PutMapping
	@SuppressWarnings("rawtypes")
	public ResponseEntity save(@RequestBody CategoriaDTO dto) {
		return super.save(dto);
	}

	@PutMapping(value = "/saveAll")
	@SuppressWarnings("rawtypes")
	public ResponseEntity save(@RequestBody List<CategoriaDTO> dtos) {
		return super.saveAll(dtos);
	}

	@Override
	public ClassEntity<Long> convertDtoToObject(IDTO dto) {
		CategoriaDTO dtoCategoria = (CategoriaDTO) dto;
		Categoria categoria = new Categoria();
		categoria.setNmeCategoria(dtoCategoria.getNmeCategoria());
		return categoria;
	}

	@Override
	protected Logger getLog() {
		return log;
	}

	@Override
	protected CrudService<Categoria, Long> getService() throws BusinessException {
		return categoriaService;
	}

}
