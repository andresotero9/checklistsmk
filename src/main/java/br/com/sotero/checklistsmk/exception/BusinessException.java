package br.com.sotero.checklistsmk.exception;

public class BusinessException extends Exception {

	private static final long serialVersionUID = 1934154012733662270L;

	public BusinessException(String message) {
		super(message);
	}

	public BusinessException(String message, Exception e) {
		super(message, e);
	}

}
