package br.com.sotero.checklistsmk.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "CATEGORIA")
public class Categoria extends ClassEntity<Long> {

	private static final long serialVersionUID = -2546899515280442365L;

	@Column(name = "NME_CATEGORIA", nullable = false, unique = true)
	private String nmeCategoria;

	@OneToMany
	@JoinColumn(name = "ID_ITEM", nullable = true)
	private List<Item> itens;

	/*
	 * Construtores
	 */
	public Categoria() {
	}

	public Categoria(String nmeCategoria) {
		this.nmeCategoria = nmeCategoria;
	}

	public Categoria(Long idCategoria, String nmeCategoria) {
		super.setId(idCategoria);
		this.nmeCategoria = nmeCategoria;
	}

	/*
	 * Getters
	 */
	@Override
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_CATEGORIA")
	public Long getId() {
		return super.getId();
	}

	public String getNmeCategoria() {
		return nmeCategoria;
	}

	/*
	 * Setters
	 */
	public void setNmeCategoria(String nmeCategoria) {
		this.nmeCategoria = nmeCategoria;
	}

}
