package br.com.sotero.checklistsmk.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public class ClassEntity<ID> implements Serializable {

	private ID id;

	public ID getId() {
		return id;
	}

	public void setId(ID id) {
		this.id = id;
	}
}
