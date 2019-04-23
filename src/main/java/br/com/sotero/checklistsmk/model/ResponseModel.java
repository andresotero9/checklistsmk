package br.com.sotero.checklistsmk.model;

public class ResponseModel {

	private int cod;
	private String msg;

	public ResponseModel() {
	}

	public ResponseModel(int cod, String msg) {
		this.cod = cod;
		this.msg = msg;
	}

	public int getCod() {
		return cod;
	}

	public String getMsg() {
		return msg;
	}

	public void setCod(int cod) {
		this.cod = cod;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

}
