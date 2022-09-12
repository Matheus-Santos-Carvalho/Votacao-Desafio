package br.com.solutis.votacao.exceptions;

import java.io.Serializable;

public class FieldMessage implements Serializable{
	private static final long serialVersionUID = 1L;
	
	private String fieldame;
	private String message;

	public FieldMessage(String fieldame, String message) {
		super();
		this.fieldame = fieldame;
		this.message = message;
	}

	public String getFieldame() {
		return fieldame;
	}

	public void setFieldame(String fieldame) {
		this.fieldame = fieldame;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	
	

}
