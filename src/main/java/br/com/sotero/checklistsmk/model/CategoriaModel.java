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
public class CategoriaModel extends Model<Long> {

	public CategoriaModel() {
	}

	public CategoriaModel(String nmeCategoria) {
		this.nmeCategoria = nmeCategoria;
	}

	public CategoriaModel(Long id, String nmeCategoria) {
		this.id = id;
		this.nmeCategoria = nmeCategoria;
	}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_CATEGORIA")
	public Long id;

	@Override
	public Long getId() {
		return this.id;
	}

	@Column(name = "NME_CATEGORIA", nullable = false, unique = true)
	private String nmeCategoria;

	public String getNmeCategoria() {
		return nmeCategoria;
	}

	public void setNmeCategoria(String nmeCategoria) {
		this.nmeCategoria = nmeCategoria;
	}

}
