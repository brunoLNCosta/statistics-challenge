package com.n26.challenge.application.resource.transaction;

import static java.time.Instant.ofEpochMilli;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;

import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class TransactionRequest {

	@NotNull
	private Double ammount;
	
	@NotNull
	private Long timestamp;

	public Double getAmmount() {
		return ammount;
	}

	public void setAmmount(Double ammount) {
		this.ammount = ammount;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}
	
	// TODO Test
	@JsonIgnore
	public OffsetDateTime getFormatedDate() {
		if(timestamp != null) {
			 return OffsetDateTime.ofInstant(ofEpochMilli(timestamp), ZoneOffset.UTC);
		}
		return null;
	}
	
}
