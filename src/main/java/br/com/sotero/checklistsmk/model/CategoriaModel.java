package br.com.sotero.checklistsmk.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name = "CATEGORIA")
public class CategoriaModel extends ClassEntity<Long> {

	public CategoriaModel() {
	}

	public CategoriaModel(String nmeCategoria) {
		this.nmeCategoria = nmeCategoria;
	}

	public CategoriaModel(Long idCategoria, String nmeCategoria) {
		this.idCategoria = idCategoria;
		this.nmeCategoria = nmeCategoria;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_CATEGORIA")
	public Long idCategoria;

	@Column(name = "NME_CATEGORIA", nullable = false, unique = true)
	private String nmeCategoria;

	@Override
	public Long getId() {
		return this.idCategoria;
	}

	public String getNmeCategoria() {
		return nmeCategoria;
	}

	public void setNmeCategoria(String nmeCategoria) {
		this.nmeCategoria = nmeCategoria;
	}

}
