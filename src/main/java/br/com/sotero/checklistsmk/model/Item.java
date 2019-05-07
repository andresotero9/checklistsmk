package br.com.sotero.checklistsmk.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ITEM")
public class Item extends ClassEntity<Long> {

	private static final long serialVersionUID = -3434156313860674696L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_ITEM")
	private Long idItem;

	@Column(name = "NME_ITEM", nullable = false, unique = false)
	private String nmeItem;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "ID_CATEGORIA", nullable = false, referencedColumnName = "ID_CATEGORIA")
	private Categoria categoria;

	/*
	 * Construtores
	 */
	public Item() {
	}

	public Item(String nmeItem) {
		this.nmeItem = nmeItem;
	}

	public Item(Long idItem, String nmeItem) {
		this.idItem = idItem;
		this.nmeItem = nmeItem;
	}

	/*
	 * Getters
	 */
	@Override
	public Long getId() {
		return this.idItem;
	}

	public String getNmeItem() {
		return nmeItem;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	/*
	 * Setters
	 */
	public void setNmeItem(String nmeItem) {
		this.nmeItem = nmeItem;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

}
