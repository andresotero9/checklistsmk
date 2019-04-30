package br.com.sotero.checklistsmk.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public abstract class ClassEntity<ID> implements Serializable {

	public abstract ID getId();

}
