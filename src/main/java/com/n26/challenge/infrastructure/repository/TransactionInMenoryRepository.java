package com.n26.challenge.infrastructure.repository;

import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

import org.springframework.stereotype.Repository;

import com.n26.challenge.domain.model.transaction.Transaction;
import com.n26.challenge.domain.model.transaction.TransactionRepository;

//Package to force the programmer to @Autowire the interface.
@Repository
class TransactionInMenoryRepository implements TransactionRepository {
	
	private final Map<UUID, Transaction> repository = new ConcurrentHashMap<>();
	
	@Override
	public Transaction add(Transaction transaction) {
		return repository.put(transaction.getId(), transaction);
	}
	
}
