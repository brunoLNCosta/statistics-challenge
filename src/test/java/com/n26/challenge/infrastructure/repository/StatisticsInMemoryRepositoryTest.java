package com.n26.challenge.infrastructure.repository;

import static com.n26.challenge.test.TransactionGenerator.generateRandomTransactions;
import static org.junit.Assert.assertEquals;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;

import com.n26.challenge.domain.model.statistics.Statistics;
import com.n26.challenge.domain.model.statistics.StatisticsRepository;
import com.n26.challenge.domain.model.transaction.Transaction;

public class StatisticsInMemoryRepositoryTest {
	
	private static final Long LAST_SECONDS = 120L;

	private StatisticsRepository repo;
	
	@Before
	public void beforeTest() {
		repo = new StatisticsInMemoryRepository();
	}
	
	@Test
	public void emptyStatisticTest() {
		Statistics s = repo.getFromLastSeconds(LAST_SECONDS);
		
		assertEquals(new Double(0.0), s.getSum(), 0.01);
		assertEquals(new Double(0.0), s.getAvg(), 0.01);
		assertEquals(null, s.getMax());
		assertEquals(null, s.getMin());
		assertEquals(new Long(0), s.getCount());
	}
	
	@Test
	public void updateTestLinear() {
		List<Transaction> transactions = generateRandomTransactions();
		
		Double sum = 0.0;
		Double max = null;
		Double min = null;
		
		for(Transaction t : transactions) {
			sum += t.getAmmount();
			
			if(max == null || t.getAmmount() > max) {
				max = t.getAmmount();
			}
			
			if(min == null || t.getAmmount() < min) {
				min = t.getAmmount();
			}
		}
		
		transactions.forEach(repo::updateStatistics);
		Statistics s = repo.getFromLastSeconds(LAST_SECONDS);
		
		Integer size = transactions.size();
		Long count = Long.valueOf(size.toString());
		Double avg = sum / count;
		
//		assertEquals(sum, s.getSum(), 0.01);
//		assertEquals(avg, s.getAvg(), 0.01);
//		assertEquals(min, s.getMin(), 0.01);
//		assertEquals(max, s.getMax(), 0.01);
		assertEquals(count, s.getCount());
	}
	
	@Test
	public void ignoreOldStatisticsTest() throws InterruptedException {
		List<Transaction> transactions = generateRandomTransactions();
		transactions.forEach(repo::updateStatistics);
		
		TimeUnit.SECONDS.sleep(1);
		
		Statistics s = repo.getFromLastSeconds(1L);
		
		assertEquals(new Double(0.0), s.getSum(), 0.01);
		assertEquals(new Double(0.0), s.getAvg(), 0.01);
		assertEquals(null, s.getMin());
		assertEquals(null, s.getMax());
		assertEquals(new Long(0L), s.getCount());
	}
	
}
