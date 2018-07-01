package com.n26.challenge.infrastructure.utils;

import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalUnit;

public class DateUtils {

	private DateUtils() {}
	
	// sugar sintax
	public static OffsetDateTime now() {
		return OffsetDateTime.now(ZoneOffset.UTC);
	}
	
	// sugar sintax
	public static OffsetDateTime getTimeMinus(long amountToSubtract, TemporalUnit unit){
		return now().minus(amountToSubtract, ChronoUnit.SECONDS);

	}
	
}
