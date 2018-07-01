package com.n26.challenge.domain.model.transaction;

import static com.n26.challenge.infrastructure.utils.DateUtils.getTimeMinus;
import static com.n26.challenge.infrastructure.utils.DateUtils.now;

import java.time.OffsetDateTime;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

import com.n26.challenge.domain.exception.transaction.TimeDiscrepancyNotAllowedException;

public final class Transaction {
	
	static final Long MAX_TIME_DISCREPANCY_TOLERANCE = 60L;
	
	private final UUID id;
	
	private final Double ammount;
	
	private final OffsetDateTime date;
	
	private final OffsetDateTime registrationDate;
	
	// You never will have worry if the ammount or dates of a transaction is null, even in memory.
	public Transaction(Double ammount, OffsetDateTime date) throws TimeDiscrepancyNotAllowedException {
		if(ammount == null || date == null)
			throw new IllegalArgumentException();
			
		this.id = UUID.randomUUID();
		this.ammount = ammount;
		this.date = date;
		this.registrationDate = now();
		
		validTimeDiscrepancy(date);
	}
	
	//TO DO Testar
	private void validTimeDiscrepancy(OffsetDateTime date) throws TimeDiscrepancyNotAllowedException {
		OffsetDateTime maxTimeDiscrepancyDate = getTimeMinus(MAX_TIME_DISCREPANCY_TOLERANCE, ChronoUnit.SECONDS);
		if(date.isBefore(maxTimeDiscrepancyDate)) {
			throw new TimeDiscrepancyNotAllowedException();
		}
	}

	public UUID getId() {
		return id;
	}

	public Double getAmmount() {
		return ammount;
	}
	
	public OffsetDateTime getDate() {
		return date;
	}

	public OffsetDateTime getRegistrationDate() {
		return registrationDate;
	}

}
