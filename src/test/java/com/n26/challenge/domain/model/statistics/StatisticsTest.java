package com.n26.challenge.domain.model.statistics;

import static com.n26.challenge.test.TransactionGenerator.generateRandomTransactions;
import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.n26.challenge.domain.model.transaction.Transaction;

public class StatisticsTest {

	@Test
	public void emptyStatisticTest() {
		Statistics s = new Statistics();
		assertEquals(new Double(0.0), s.getSum(), 0.01);
		assertEquals(new Double(0.0), s.getAvg(), 0.01);
		assertEquals(null, s.getMax());
		assertEquals(null, s.getMin());
		assertEquals(new Long(0L), s.getCount());
	}
	
	@Test
	public void updateStatisticTest() {
		Statistics s = new Statistics();
		List<Transaction> transactions = generateRandomTransactions();
		
		transactions.forEach(t -> {
			
			Double sum = s.getSum() + t.getAmmount();
			
			Double max = t.getAmmount();
			if(s.getMax() != null && s.getMax() > t.getAmmount()) {
				max = s.getMax();
			}
			
			Double min = t.getAmmount();
			if(s.getMin() != null && s.getMin() < t.getAmmount()) {
				min = s.getMin();
			}
			
			Long count = s.getCount() +1;
			Double avg = sum / count;
			
			s.update(t);
			
//			assertEquals(sum, s.getSum(), 0.01);
//			assertEquals(avg, s.getAvg(), 0.01);
//			assertEquals(max, s.getMax(), 0.01);
//			assertEquals(min, s.getMin(), 0.01);
			assertEquals(count, s.getCount());
		});
	}
	
	@Test
	public void mergeStatisticTest() {
		List<Statistics> statistics = generateRandomStatistics();
		
		Double sum = 0.0;
		Long count = 0L;
		Double max = 0.0;
		Double min = 0.0;
		
		for(Statistics s : statistics) {
			sum += s.getSum();
			
			if(s.getMax() != null && s.getMax() > max) {
				max = s.getMax();
			}
			
			if(s.getMin() != null && s.getMin() < min) {
				min = s.getMin();
			}
			count += s.getCount();
		};
		
		Double avg = sum / count;
		
		Statistics s = new Statistics();
		statistics.forEach(s::merge);
		
//		assertEquals(sum, s.getSum(), 0.01);
//		assertEquals(avg, s.getAvg(), 0.01);
//		assertEquals(max, s.getMax(), 0.01);
//		assertEquals(min, s.getMin(), 0.01);
		assertEquals(count, s.getCount());
		
	}
	
	private List<Statistics> generateRandomStatistics() {
		List<Statistics> statistics = new ArrayList<>();
		final Long quant = Math.round(Math.random() * 1);
		
		for(int i = 0; i < quant; i++) {
			statistics.add(new Statistics(generateRandomTransactions()));
		}
		
		return statistics;
	}
	
}
