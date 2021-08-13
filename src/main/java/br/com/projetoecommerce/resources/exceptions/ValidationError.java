package br.com.projetoecommerce.resources.exceptions;

import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError {
	private static final long serialVersionUID = 1L;

	private List<FieldMessage> message = new ArrayList<>();

	public ValidationError(Instant timeStamp, Integer status, String error, String message, String path) {
		super(timeStamp, status, error, message, path);

	}

	public List<FieldMessage> getErrors() {
		return message;
	}

	public void addError(String fieldName, String message) {
		this.message.add(new FieldMessage(fieldName, message));
	}

}