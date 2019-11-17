package br.ufrn.imd.utravel.exception;

@SuppressWarnings("serial")
public class InvalidOperationException extends RuntimeException{

	public InvalidOperationException() {
		super();
	}

	public InvalidOperationException(String message) {
		super(message);
	}
}
