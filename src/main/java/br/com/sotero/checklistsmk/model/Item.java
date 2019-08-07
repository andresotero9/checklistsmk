package br.com.sotero.checklistsmk.model;

import javax.persistence.Column;
import javax.persistence.Entity;
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
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "ID_ITEM")
	private Long idItem;

	@Column(name = "NME_ITEM", nullable = false, unique = true, length = 50)
	private String nmeItem;

	@ManyToOne
	@JoinColumn(name = "ID_CATEGORIA")
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

	public Item(String nmeItem, Categoria categoria) {
		this.nmeItem = nmeItem;
		this.categoria = categoria;
	}

	/*
	 * Getters
	 */
	@Override
	public Long getId() {
		return idItem;
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
	@Override
	public void setId(Long idItem) {
		this.idItem = idItem;
	}

	public void setNmeItem(String nmeItem) {
		this.nmeItem = nmeItem;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((categoria == null) ? 0 : categoria.hashCode());
		result = prime * result + ((idItem == null) ? 0 : idItem.hashCode());
		result = prime * result + ((nmeItem == null) ? 0 : nmeItem.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Item other = (Item) obj;
		if (categoria == null) {
			if (other.categoria != null)
				return false;
		} else if (!categoria.equals(other.categoria))
			return false;
		if (idItem == null) {
			if (other.idItem != null)
				return false;
		} else if (!idItem.equals(other.idItem))
			return false;
		if (nmeItem == null) {
			if (other.nmeItem != null)
				return false;
		} else if (!nmeItem.equals(other.nmeItem))
			return false;
		return true;
	}

}
