package com.in28minutes.rest.webservices.exception;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

// == applies to all controllers
@ControllerAdvice
@RestController
public class CustomizedResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	// == my personal exception message tester
	private final String jamesExceptionMessage = "James testing customized execption for understanding";
	
	// == applies to all controller exceptions
	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException
		(Exception ex, WebRequest request) throws Exception {
		
		ExceptionResponse exceptionResponse = 
				new ExceptionResponse(new Date(), ex.getMessage(), 
				request.getDescription(false), jamesExceptionMessage);
		
		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	// == applies to all UserNotFoundException
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundException
		(UserNotFoundException ex, WebRequest request) throws Exception {
		
		ExceptionResponse exceptionResponse = 
				new ExceptionResponse(new Date(), ex.getMessage(), 
				request.getDescription(true), jamesExceptionMessage);
		
		return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
	}
	
	// == applies to all UserPostNotFoundException
		@ExceptionHandler(UserPostNotFoundException.class)
		public final ResponseEntity<Object> handleUserPostNotFoundException
			(UserNotFoundException ex, WebRequest request) throws Exception {
			
			ExceptionResponse exceptionResponse = 
					new ExceptionResponse(new Date(), ex.getMessage(), 
					request.getDescription(true), jamesExceptionMessage);
			
			return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
		}
	
	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(
			MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
		
		ExceptionResponse exceptionResponse = 
				new ExceptionResponse(new Date(), "Validation failed", 
				ex.getBindingResult().toString(), jamesExceptionMessage);

		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}
}
