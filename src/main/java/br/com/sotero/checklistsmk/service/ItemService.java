package br.com.sotero.checklistsmk.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import br.com.sotero.checklistsmk.exception.BusinessException;
import br.com.sotero.checklistsmk.model.Item;
import br.com.sotero.checklistsmk.repository.ItemRepository;

@Service
public class ItemService extends CrudService<Item, Long> {

	private static final Logger log = LoggerFactory.getLogger(CategoriaService.class);

	@Autowired
	private ItemRepository itemRepository;

	@Override
	public CrudRepository<Item, Long> getRepository() throws BusinessException {
		return itemRepository;
	}

	@Override
	public Logger getLog() throws BusinessException {
		return log;
	}

}
