package com.n26.challenge.application.resource.transaction;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.n26.challenge.application.service.TransactionService;
import com.n26.challenge.domain.exception.transaction.TimeDiscrepancyNotAllowedException;
import com.n26.challenge.infrastructure.rest.annotation.RestResource;
import com.n26.challenge.infrastructure.rest.annotation.RestService;

import io.swagger.annotations.Api;

@Api(tags="Challenge")
@RestResource(path= "/transactions")
public class TransactionResource {
	
	@Autowired
	private TransactionService service;
	
	@RestService(code=CREATED, method=POST)
	public ResponseEntity<?> create(@Valid @RequestBody TransactionRequest request)
			throws TimeDiscrepancyNotAllowedException {
		
		service.registerNew(request.getAmmount(), request.getFormatedDate());
		return ResponseEntity.status(CREATED).build();
	}
	
}
