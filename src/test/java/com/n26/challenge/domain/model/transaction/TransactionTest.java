package com.n26.challenge.domain.model.transaction;

import static com.n26.challenge.domain.model.transaction.Transaction.MAX_TIME_DISCREPANCY_TOLERANCE;
import static java.time.temporal.ChronoUnit.SECONDS;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoField;

import org.junit.Test;

import com.n26.challenge.domain.exception.transaction.TimeDiscrepancyNotAllowedException;

public class TransactionTest {

	@Test(expected = IllegalArgumentException.class)
	public void notNullAmmoutTest() throws TimeDiscrepancyNotAllowedException {
		new Transaction(null, getTimeAtTheLastSecondAllowed());
	}
	
	@Test(expected = IllegalArgumentException.class)
	public void notNullDateTest() throws TimeDiscrepancyNotAllowedException {
		new Transaction(-4.0, null);
	}
	
	@Test
	public void creationalTest() throws TimeDiscrepancyNotAllowedException {
		OffsetDateTime lastSecondAllowed = getTimeAtTheLastSecondAllowed();
		OffsetDateTime now = OffsetDateTime.now(ZoneOffset.UTC);
		Transaction t = new Transaction(100.00, lastSecondAllowed);
		
		assertNotNull(t.getId());
		assertEquals(lastSecondAllowed, t.getDate());
		assertEquals(now.getSecond(), t.getRegistrationDate().getSecond());
	}
	
	@Test(expected = TimeDiscrepancyNotAllowedException.class)
	public void maxTimeDiscrepancyTest() throws TimeDiscrepancyNotAllowedException {
		OffsetDateTime notAllowedDate = getTimeAtTheLastSecondAllowed().minus(1, SECONDS);
		new Transaction(100.00, notAllowedDate);
//		assertThrows(TimeDiscrepancyNotAllowedException.class, () -> new Transaction(100.00, notAllowedDate));
	}
	
	private OffsetDateTime getTimeAtTheLastSecondAllowed() {
		return OffsetDateTime.now(ZoneOffset.UTC)
							 .minus(MAX_TIME_DISCREPANCY_TOLERANCE - 1, SECONDS)
							 .with(ChronoField.MILLI_OF_SECOND, 0);
	}
	
}
