package com.n26.challenge.application.resource.statistic;

class StatisticResponse {
	
	private Double sum;
	
	private Double avg;
	
	private Double max;
	
	private Double min;
	
	private Long count;

	public Double getSum() {
		return sum;
	}

	public void setSum(Double sum) {
		this.sum = sum;
	}

	public Double getAvg() {
		return avg;
	}

	public void setAvg(Double avg) {
		this.avg = avg;
	}

	public Double getMax() {
		return max == null ? 0.0 : max;
	}

	public void setMax(Double max) {
		this.max = max;
	}

	public Double getMin() {
		return min == null ? 0.0 : min;
	}

	public void setMin(Double min) {
		this.min = min;
	}

	public Long getCount() {
		return count;
	}

	public void setCount(Long count) {
		this.count = count;
	}
	
}
