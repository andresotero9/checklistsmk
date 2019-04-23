package br.com.sotero.checklistsmk.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "CATEGORIA")
@Entity
public class CategoriaModel implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_CATEGORIA")
	private Integer idCategoria;

	@Column(name = "NME_CATEGORIA")
	private String nmeCategoria;

	public Integer getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Integer idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getNmeCategoria() {
		return nmeCategoria;
	}

	public void setNmeCategoria(String nmeCategoria) {
		this.nmeCategoria = nmeCategoria;
	}
}
