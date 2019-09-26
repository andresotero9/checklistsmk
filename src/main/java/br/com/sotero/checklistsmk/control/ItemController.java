package br.com.sotero.checklistsmk.control;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.sotero.checklistsmk.dto.IDTO;
import br.com.sotero.checklistsmk.dto.ItemDTO;
import br.com.sotero.checklistsmk.exception.BusinessException;
import br.com.sotero.checklistsmk.model.ClassEntity;
import br.com.sotero.checklistsmk.model.Item;
import br.com.sotero.checklistsmk.service.CrudService;
import br.com.sotero.checklistsmk.service.ItemService;
import br.com.sotero.checklistsmk.utils.ChkUtills;

public class ItemController extends Controller {
	private static final Logger log = LoggerFactory.getLogger(ItemController.class);

	@Autowired
	private ItemService service;

	@Override
	protected Logger getLog() {
		return log;
	}

	@Override
	protected CrudService<Item, Long> getService() throws BusinessException {
		return service;
	}

	@Override
	protected ClassEntity<Long> convertDtoToObject(IDTO dto) throws BusinessException {
		ItemDTO itemDTO = (ItemDTO) dto;

		Item item = null;

		if (!ChkUtills.isNullOrEmpty(itemDTO.getIdItem()) && service.existsById(itemDTO.getIdItem())) {
			Optional<Item> optional = service.findById(itemDTO.getIdItem());
			item = optional.get();
		} else {
			item = new Item();
		}

		item.setNmeItem(itemDTO.getNmeItem());

		return item;
	}

}
