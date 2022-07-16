package com.learning.springboot.springboote2e.customException;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


// The below is the basic exception Handling which return a string message

/*@RestControllerAdvice
//ControllerAdvice + ResponseBody

public class E2EResponseEntityExceptionHandler {

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex){
	
		return new ResponseEntity<>("UserNotFound", HttpStatus.NOT_FOUND);
	}
}*/


//The below is the custom exception Handling which return a object with uniform fields
// for all kinds of exception across the Project


@RestControllerAdvice
//ControllerAdvice + ResponseBody
public class E2EResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllExceptions(Exception ex, WebRequest request) throws Exception {
		ExceptionResponse exceptionResponse =  
				new ExceptionResponse(new Date(), ex.getMessage(), 
						request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);
	}
	
	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundException(UserNotFoundException ex, WebRequest request) throws Exception {
		ExceptionResponse exceptionResponse =  
				new ExceptionResponse(new Date(), ex.getMessage(), 
						request.getDescription(false));
		return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
	}

}