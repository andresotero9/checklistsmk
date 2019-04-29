package br.com.sotero.checklistsmk.model;

import java.io.Serializable;

@SuppressWarnings("serial")
public abstract class Model<ID> implements Serializable {

	public abstract ID getId();

}
