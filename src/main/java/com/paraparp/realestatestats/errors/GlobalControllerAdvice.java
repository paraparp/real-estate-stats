package com.paraparp.realestatestats.errors;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@RestControllerAdvice
@Slf4j
public class GlobalControllerAdvice extends ResponseEntityExceptionHandler {

	@ExceptionHandler(FileProcessingException.class)
	public final ResponseEntity<ExceptionResponseEntity> handleFileProcessingException(FileProcessingException ex, WebRequest request) {
		ExceptionResponseEntity exceptionResponse = new ExceptionResponseEntity(
				new Date(),
				ex.getMessage(),
				ex.getDetailedMessage(), // Using detailedMessage here
				HttpStatus.BAD_REQUEST.getReasonPhrase()
		);
		log.error(String.valueOf(exceptionResponse));

		return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
	}

}