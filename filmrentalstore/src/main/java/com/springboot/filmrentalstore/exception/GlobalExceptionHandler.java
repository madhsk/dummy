package com.springboot.filmrentalstore.exception;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	@ExceptionHandler({ ResourceNotFoundException.class })
	public ResponseEntity<Object> handleResouceNotFoundException(ResourceNotFoundException exc) {
		ErrorDetails errorDetails = new ErrorDetails();
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorDetails);
	}

	@ExceptionHandler(InvalidInputException.class)
	public ResponseEntity<ErrorDetails> handleInvalidInputException(InvalidInputException ex) {
		ErrorDetails errorDetails = new ErrorDetails();
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorDetails);
	}

	@ExceptionHandler(UnauthorizedException.class)
	public ResponseEntity<ErrorDetails> handleUnauthorizedException(UnauthorizedException ex) {
		ErrorDetails errorDetails = new ErrorDetails();
		return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(errorDetails);
	}

	@ExceptionHandler(ForbiddenException.class)
	public ResponseEntity<ErrorDetails> handleForbiddenException(ForbiddenException ex) {
		ErrorDetails errorDetails = new ErrorDetails();
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(errorDetails);
	}

	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorDetails> handleGenericException(Exception ex) {
		ErrorDetails errorDetails = new ErrorDetails();
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(errorDetails);
	}

}
