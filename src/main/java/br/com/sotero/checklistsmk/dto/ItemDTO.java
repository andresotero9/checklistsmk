package br.com.sotero.checklistsmk.dto;

public class ItemDTO implements IDTO {
	private Long idItem;
	private String nmeItem;
	private CategoriaDTO categoriaDTO;
	
	/*
	 * Getters
	 */
	public Long getIdItem() {
		return idItem;
	}
	public String getNmeItem() {
		return nmeItem;
	}
	public CategoriaDTO getCategoriaDTO() {
		return categoriaDTO;
	}
	
	/*
	 * Setters
	 */
	public void setIdItem(Long idItem) {
		this.idItem = idItem;
	}
	public void setNmeItem(String nmeItem) {
		this.nmeItem = nmeItem;
	}
	public void setCategoriaDTO(CategoriaDTO categoriaDTO) {
		this.categoriaDTO = categoriaDTO;
	}
	
	
}
