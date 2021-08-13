package br.com.projetoecommerce.services.exceptions;

public class EmptyParameterException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	
	public EmptyParameterException() {
		super("Campos em brancos");
	}

}
