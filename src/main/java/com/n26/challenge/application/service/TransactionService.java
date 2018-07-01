package com.n26.challenge.application.service;

import java.time.OffsetDateTime;

import com.n26.challenge.domain.exception.transaction.TimeDiscrepancyNotAllowedException;
import com.n26.challenge.domain.model.statistics.Statistics;
import com.n26.challenge.domain.model.transaction.Transaction;

public interface TransactionService {
	
	Transaction registerNew(Double ammount, OffsetDateTime date) throws TimeDiscrepancyNotAllowedException;

	Statistics getRealTimeStatistics();
	
}
