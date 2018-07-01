package com.n26.challenge.infrastructure.repository;

import static com.n26.challenge.infrastructure.utils.DateUtils.now;
import static java.time.temporal.ChronoUnit.SECONDS;

import java.time.OffsetDateTime;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Repository;

import com.n26.challenge.domain.model.statistics.Statistics;
import com.n26.challenge.domain.model.statistics.StatisticsRepository;
import com.n26.challenge.domain.model.transaction.Transaction;

// Package to force the programmer to @Autowire the interface.
@Repository
class StatisticsInMemoryRepository implements StatisticsRepository {
	
	private static final Integer STATISTICS_GARBAGE_COLLECTOR_TIME = 60;
	
	private final ReadWriteLock rwLock = new ReentrantReadWriteLock();
	
	private final Map<Long, Statistics> statisticsPerSecond = new ConcurrentHashMap<>();
	
	public void updateStatistics(Transaction transaction) {
		rwLock.writeLock().lock();
	    try {
	    	Long key = transaction.getDate().toEpochSecond();
	    	Statistics statistics = statisticsPerSecond.get(key);
	    	if(statistics == null) {
	    		statistics = new Statistics();
	    	}
	    	statistics.update(transaction);
	    	statisticsPerSecond.put(key, statistics);
	    } finally {
	    	rwLock.writeLock().unlock();
	    }
	}
	
	public Statistics getFromLastSeconds(Long seconds) {
		rwLock.readLock().lock();
		try {
			OffsetDateTime date = now();
			List<Statistics> lastSecondsStatistic = new LinkedList<>();
			
			// Good and old for
			for (long i = 0; i < seconds ; i++) {
				Long key = date.toEpochSecond();
				Statistics s = statisticsPerSecond.get(key);
				
				if(s != null)
					lastSecondsStatistic.add(s);
				date = date.minus(1, SECONDS);
			}
			
			return merge(lastSecondsStatistic);
		} finally {
			rwLock.readLock().unlock();
		}
	}
	
	private Statistics merge(List<Statistics> statisticsList) {
		Statistics fromTheLastSeconds = new Statistics();
		statisticsList.forEach(fromTheLastSeconds::merge);
		return fromTheLastSeconds;
	}
	
	// It cleans, It not really necessary because a entry per seconds it not that much,
	// but this improve the performance and save memory space any way.
	// It is package so I can test with unit test.
	@Scheduled(fixedRate = 60000)
	void statisticsGarbageCollector() {
		OffsetDateTime date = now().minus(120, SECONDS);
		for (long i = 0; i < STATISTICS_GARBAGE_COLLECTOR_TIME; i++) {
			Long key = date.toEpochSecond();
			statisticsPerSecond.remove(key);
			date = date.plus(1, SECONDS);
		}
	}

}
