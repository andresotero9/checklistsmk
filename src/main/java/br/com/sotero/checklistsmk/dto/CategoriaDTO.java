package br.com.sotero.checklistsmk.dto;

public class CategoriaDTO implements IDTO {

	private Long idCategoria;
	private String nmeCategoria;

	/*
	 * Getters
	 */
	public Long getIdCategoria() {
		return idCategoria;
	}

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
