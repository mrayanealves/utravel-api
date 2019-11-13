package br.ufrn.imd.utravel.exception;

@SuppressWarnings("serial")
public class ParametersFailException extends RuntimeException{
	public ParametersFailException() {
		super();
	}

	public ParametersFailException(String message) {
		super(message);
	}
}
