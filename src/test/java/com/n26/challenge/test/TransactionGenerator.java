package com.n26.challenge.test;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.List;

import com.n26.challenge.domain.model.transaction.Transaction;

public class TransactionGenerator {

	private TransactionGenerator() {}
	
	public static List<Transaction> generateRandomTransactions() {
		final Long quant = Math.round(Math.random() * 10);
		return generateTransactions(quant.intValue());
	}
	
	public static List<Transaction> generateTransactions(Integer quantity) {
		List<Transaction> transactions = new ArrayList<>(quantity);
		
		for(int i = 0; i < quantity; i++) {
			Integer sinalFactor = -1;
			
			if(Math.round(Math.random() * 1) % 2 == 0) {
				sinalFactor = 1;
			}
			
			// I need to work with integer number, because double is too risk. It has a chance that the test fails
			// The correct thing is to use BigDecimal in the whole app, but I do have the time now.
			Double ammout = (Math.random() * 7 * sinalFactor);
			
			try {
				transactions.add(new Transaction(ammout, OffsetDateTime.now(ZoneOffset.UTC)));
			} catch(Exception e) {
				continue;
			}
		}
		return transactions;
	}
	
}
