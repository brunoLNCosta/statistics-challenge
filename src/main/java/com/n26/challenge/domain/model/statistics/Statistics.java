package com.n26.challenge.domain.model.statistics;

import static org.apache.commons.lang3.Validate.notNull;

import java.util.Collection;

import com.n26.challenge.domain.model.transaction.Transaction;

public final class Statistics {
	
	private Double sum = 0.0;
	
	private Double avg = 0.0;
	
	private Double max;
	
	private Double min;
	
	private Long count = 0L;
	
	public Statistics() {}
	
	Statistics(Collection<Transaction> transactions) {
		notNull(transactions);
		transactions.forEach(this::update);
	}
	
	public void update(Transaction t) {
		this.sum += t.getAmmount();
		this.count++;
		
		if(max == null || t.getAmmount() > max) {
			max = t.getAmmount();
		}
		
		if(min == null || t.getAmmount() < min) {
			min = t.getAmmount();
		}
		
		recalculateAvg();
	}
	
	// Test
	public Statistics merge(Statistics s){
		if(s != null) {
			this.sum += s.sum;
			this.count += s.count;
			
			if(s.max != null) {
				if(this.max != null) {
					this.max = this.max > s.max ? this.max : s.max;
				} else {
					this.max = s.max;
				}
			}
			
			if(s.min != null) {
				if(this.min != null) {
					this.min = this.min < s.min ? this.min : s.min;
				} else {
					this.min = s.min;
				}
			}
			
			recalculateAvg();
		}
		
		return this;
	}
	
	private void recalculateAvg() {
		this.avg = this.sum / count; 
	}
	
	public Double getSum() {
		return sum;
	}

	public Double getAvg() {
		return avg;
	}

	public Double getMax() {
		return max;
	}

	public Double getMin() {
		return min;
	}

	public Long getCount() {
		return count;
	}
	
}
