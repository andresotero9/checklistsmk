package br.com.sotero.checklistsmk.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "CATEGORIA")
public class CategoriaModel implements Serializable {

	private static final long serialVersionUID = 1L;

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
	private Long idCategoria;

	@Column(name = "NME_CATEGORIA", nullable = false, unique = true)
	private String nmeCategoria;

	public Long getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getNmeCategoria() {
		return nmeCategoria;
	}

	public void setNmeCategoria(String nmeCategoria) {
		this.nmeCategoria = nmeCategoria;
	}
}
