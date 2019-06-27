package br.com.sotero.checklistsmk.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "CATEGORIA")
public class Categoria extends ClassEntity<Long> {

	private static final long serialVersionUID = -2546899515280442365L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ID_CATEGORIA")
	private Long idCategoria;

	@Column(name = "NME_CATEGORIA", unique = true, nullable = false, length = 50)
	private String nmeCategoria;

	@OneToMany(mappedBy = "categoria")
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
		this.idCategoria = idCategoria;
		this.nmeCategoria = nmeCategoria;
	}

	/*
	 * Getters
	 */

	@Override
	public Long getId() {
		return this.idCategoria;
	}

	public String getNmeCategoria() {
		return nmeCategoria;
	}

	public List<Item> getItens() {
		return itens;
	}

	/*
	 * Setters
	 */
	@Override
	public void setId(Long idCategoria) {
		this.idCategoria = idCategoria;
	}

	public void setItens(List<Item> itens) {
		this.itens = itens;
	}

	public void setNmeCategoria(String nmeCategoria) {
		this.nmeCategoria = nmeCategoria;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((idCategoria == null) ? 0 : idCategoria.hashCode());
		result = prime * result + ((nmeCategoria == null) ? 0 : nmeCategoria.hashCode());
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
		Categoria other = (Categoria) obj;
		if (idCategoria == null) {
			if (other.idCategoria != null)
				return false;
		} else if (!idCategoria.equals(other.idCategoria))
			return false;
		if (nmeCategoria == null) {
			if (other.nmeCategoria != null)
				return false;
		} else if (!nmeCategoria.equals(other.nmeCategoria))
			return false;
		return true;
	}

}
