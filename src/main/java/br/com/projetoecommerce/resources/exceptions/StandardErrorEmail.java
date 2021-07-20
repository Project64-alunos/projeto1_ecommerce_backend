package br.com.projetoecommerce.resources.exceptions;

import java.io.Serializable;
import java.time.Instant;

import com.fasterxml.jackson.annotation.JsonFormat;

public class StandardErrorEmail implements Serializable{
	private static final long serialVersionUID = 1L;

	@JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd'T'HH:mm:ss'Z'", timezone = "GMT")
	private Instant timestamp;
	private Integer status;
	private String error;
	
	public StandardErrorEmail() {
		
	}

	public StandardErrorEmail(Instant timestamp, Integer status, String error) {
		super();
		this.timestamp = timestamp;
		this.status = status;
		this.error = error;
	}

	
	public Instant getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Instant timestamp) {
		this.timestamp = timestamp;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

	@Override
	public String toString() {
		return "[timestamp=" + timestamp + ", status=" + status + ", error=" + error + "]";
	}


	
}
