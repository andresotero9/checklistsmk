package br.com.sotero.checklistsmk.repository;

import org.springframework.data.repository.CrudRepository;

import br.com.sotero.checklistsmk.model.Item;

public interface ItemRepository extends CrudRepository<Item, Long> {

}
