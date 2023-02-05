package com.paraparp.realestatestats.errors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestControllerAdvice
@Slf4j
public class GlobalControllerAdvice extends ResponseEntityExceptionHandler {

	public final ResponseEntity<ExceptionResponseEntity> handleNotFoundException(Exception ex, WebRequest request) {

		ExceptionResponseEntity exceptionResponse = new ExceptionResponseEntity(new Date(), ex.getMessage(),
				request.getDescription(false), HttpStatus.NO_CONTENT.getReasonPhrase());
		log.error(String.valueOf(exceptionResponse));
		
		return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
	}

}