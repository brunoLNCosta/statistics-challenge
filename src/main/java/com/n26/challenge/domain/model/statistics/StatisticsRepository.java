package com.n26.challenge.domain.model.statistics;

import com.n26.challenge.domain.model.transaction.Transaction;

public interface StatisticsRepository {

	void updateStatistics(Transaction transaction);
	
	Statistics getFromLastSeconds(Long seconds);
	
}
