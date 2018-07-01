package com.n26.challenge.domain.exception.transaction;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// I prefer use RunException as bussiness Exceptions, because the Java 8 support,
// but this is just a challenge.
@ResponseStatus(HttpStatus.NO_CONTENT)
public class TimeDiscrepancyNotAllowedException extends Exception {

	private static final long serialVersionUID = 1L;
	
}
