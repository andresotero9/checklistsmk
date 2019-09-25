package br.com.sotero.checklistsmk.dto;

import javax.validation.constraints.NotEmpty;

import org.hibernate.validator.constraints.Length;

public class CategoriaDTO implements IDTO {

	private Long idCategoria;
	private String nmeCategoria;

	/*
	 * Getters
	 */
	public Long getIdCategoria() {
		return idCategoria;
	}

	@NotEmpty(message = "Nome da categoria não pode ser vazia.")
	@Length(min = 3, max = 50, message = "Nome da categoria deve conter entre 3 até 50 caracteres")
	public String getNmeCategoria() {
		return nmeCategoria;
	}

	/*
	 * Setters
	 */
	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}

	public void setNmeCategoria(String nmeCategoria) {
		this.nmeCategoria = nmeCategoria;
	}
}
