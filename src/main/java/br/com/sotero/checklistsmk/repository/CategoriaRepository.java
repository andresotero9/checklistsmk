package br.com.sotero.checklistsmk.repository;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;
import org.springframework.transaction.annotation.Transactional;

import br.com.sotero.checklistsmk.model.Categoria;

public interface CategoriaRepository extends CrudRepository<Categoria, Long> {

	@Transactional(readOnly = true) // --> pois quero dizer que realmente esse método é somente de leitura, e por isso não precisa esperar consultas que estão na fila para ser executada (executa de forma assincrona)
	Optional<Categoria> findByNmeCategoria(String nmeCategoria);
	
	//Optional<Categoria> findByNmeCategoriaOrBlau(String nmeCategoria, String blau); --> quer dizer que na clausula where posso consultar por nmeCategoria OU blau
}
