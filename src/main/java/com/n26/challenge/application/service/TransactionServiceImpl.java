package com.n26.challenge.application.service;

import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.n26.challenge.domain.exception.transaction.TimeDiscrepancyNotAllowedException;
import com.n26.challenge.domain.model.statistics.Statistics;
import com.n26.challenge.domain.model.statistics.StatisticsRepository;
import com.n26.challenge.domain.model.transaction.Transaction;
import com.n26.challenge.domain.model.transaction.TransactionRepository;

// It is package because we should always Autowire the interface.
@Service
class TransactionServiceImpl implements TransactionService {
	
	private static final Long REAL_TIME_SEARCH = 60L;
	
	// DO NOT WORRY, IT IS A IN MEMORY REPOSITORY, LOOK THE IMPLEMENTATION
	@Autowired
	private TransactionRepository transactionRepository;
	
	@Autowired
	private StatisticsRepository statisticsRepository;
	
	@Override
	public Transaction registerNew(Double ammount, OffsetDateTime date) throws TimeDiscrepancyNotAllowedException {
		Transaction transaction = new Transaction(ammount, date);
		transactionRepository.add(transaction);
		
		//  In real app, I would not save statistics, I would calculate, and make a in memory cache
		//	The staticts would be stracted from a log base, like elasticsearch/kibana for example.
		//	So the Transactional date base would not be affected.
		statisticsRepository.updateStatistics(transaction);
		return transaction;
	}
	
	@Override
	public Statistics getRealTimeStatistics() {
		return statisticsRepository.getFromLastSeconds(REAL_TIME_SEARCH);
	}
	
}
