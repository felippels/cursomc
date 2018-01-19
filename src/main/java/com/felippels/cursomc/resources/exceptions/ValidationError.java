package com.felippels.cursomc.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandarError  {
	private static final long serialVersionUID = 1L;
	private List<FieldMessage> errors = new ArrayList();
	
	public ValidationError() {}

	public ValidationError(Integer id, String msg, Long timeStamp) {
		super(id, msg, timeStamp);
	}

	public List<FieldMessage> getErrors() {
		return errors;
	}

	public void addError(String fieldError, String Message) {
		errors.add(new FieldMessage(fieldError, Message ));
		
	}
	
}
