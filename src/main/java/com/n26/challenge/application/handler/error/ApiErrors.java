package com.n26.challenge.application.handler.error;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.validation.FieldError;

public class ApiErrors {
	
	private final List<ApiError> errors;
	
	public ApiErrors(List<FieldError> list) {
		this.errors = list.stream()
							.map(ApiError::new)
							.collect(Collectors.toList());
	}
	
	public List<ApiError> getErrors() {
		return errors;
	}

	public static class ApiError {
		
		private final String field;
		
		private final String message;
		
		ApiError(FieldError erro) {
			field = erro.getField();
			message = erro.getDefaultMessage();
		}

		public String getMessage() {
			return message;
		}

		public String getField() {
			return field;
		}
		
	}
	
}
