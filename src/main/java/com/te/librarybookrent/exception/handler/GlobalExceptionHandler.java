package com.te.librarybookrent.exception.handler;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.te.librarybookrent.book.exception.SameBookException;
import com.te.librarybookrent.dto.Response;
import com.te.librarybookrent.exception.BookNotAvailableException;
import com.te.librarybookrent.exception.DataNotFoundException;

@RestControllerAdvice
public class GlobalExceptionHandler {


	@ExceptionHandler(value = {DataNotFoundException.class})
	public ResponseEntity<Response> dataHandler(DataNotFoundException exception) {
		return ResponseEntity.ok(Response.builder().error(true).message(exception.getMessage()).data(null).build());
	}
	
	@ExceptionHandler(value =BookNotAvailableException.class)
	public ResponseEntity<Response> rentHandler(BookNotAvailableException exception) {
		return ResponseEntity.ok(Response.builder().error(true).message(exception.getMessage()).data(exception).build());
	}
	
	@ResponseStatus(value = HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleInvalidInput(MethodArgumentNotValidException methodArgumentNotValidException) {
		Map<String, String> invalidInputErrors = new HashMap<>();
		methodArgumentNotValidException.getBindingResult().getFieldErrors()
				.forEach(errors -> invalidInputErrors.put(errors.getField(), errors.getDefaultMessage()));

		return invalidInputErrors;
	}
	
	@ExceptionHandler(value = SameBookException.class)
	public ResponseEntity<Response> handlerent(SameBookException e){
		return ResponseEntity.ok(Response.builder().error(true).message(e.getMessage()).data(null).build());
	}
	
	@ExceptionHandler(value = Exception.class)
	public ResponseEntity<Response> handleException(Exception e){
		return ResponseEntity.ok(Response.builder().error(true).message(e.getMessage()).data(null).build());
	}
}
