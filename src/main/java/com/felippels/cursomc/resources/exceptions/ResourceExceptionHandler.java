package com.felippels.cursomc.resources.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.felippels.cursomc.services.exceptions.DataIntegrityViolationException;
import com.felippels.cursomc.services.exceptions.ObjectNotFoundException;

@ControllerAdvice
public class ResourceExceptionHandler {
	
	@ExceptionHandler
	public ResponseEntity <StandarError> objectNotFound(ObjectNotFoundException e, HttpServletRequest request){
		
		StandarError error = new StandarError (HttpStatus.NOT_FOUND.value(), e.getMessage(), System.currentTimeMillis()); 
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
		
	}
	@ExceptionHandler(DataIntegrityViolationException.class)
	public ResponseEntity <StandarError> dataIntegrity (DataIntegrityViolationException e, HttpServletRequest request){
		
		StandarError error = new StandarError (HttpStatus.BAD_REQUEST.value(), e.getMessage(), System.currentTimeMillis()); 
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
		
	}
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity <StandarError> validation (MethodArgumentNotValidException e, HttpServletRequest request){
		
		ValidationError error = new ValidationError(HttpStatus.BAD_REQUEST.value(), "Erro de validação", System.currentTimeMillis());
		
		for(FieldError fieldError :  e.getBindingResult().getFieldErrors()) {
			error.addError(fieldError.getField(), fieldError.getDefaultMessage());
			
		}
		
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
		
	}
	
}
