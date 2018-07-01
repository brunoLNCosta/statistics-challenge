package com.n26.challenge.application.handler;

import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.n26.challenge.application.handler.error.ApiErrors;

// With Roxana framework (my framework) you dont not to do GlobalHandler.
// https://rooting-company.github.io/roxana/ - In construction.
@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ApiErrors handler(MethodArgumentNotValidException e) {
		return new ApiErrors(e.getBindingResult().getFieldErrors());
	}
	
}
