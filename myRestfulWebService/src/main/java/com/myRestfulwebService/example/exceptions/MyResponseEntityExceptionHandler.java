package com.myRestfulwebService.example.exceptions;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
@RestController
public class MyResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {

	@ExceptionHandler(Exception.class)
	public final ResponseEntity<Object> handleAllException(Exception ex, WebRequest request) throws Exception {

		MyExceptionResponse myExceptionResponse = new MyExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<Object>(myExceptionResponse, HttpStatus.INTERNAL_SERVER_ERROR);

	}

	@ExceptionHandler(UserNotFoundException.class)
	public final ResponseEntity<Object> handleUserNotFoundException(Exception ex, WebRequest request) throws Exception {

		MyExceptionResponse myExceptionResponse = new MyExceptionResponse(new Date(), ex.getMessage(),
				request.getDescription(false));
		return new ResponseEntity<Object>(myExceptionResponse, HttpStatus.NOT_FOUND);

	}

	@Override
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		MyExceptionResponse myExceptionResponse = new MyExceptionResponse(new Date(), "Validation Failed",
				ex.getBindingResult().getFieldError().getDefaultMessage().toString());
		return new ResponseEntity<Object>(myExceptionResponse, HttpStatus.BAD_REQUEST);

	}

}
