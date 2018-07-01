package com.n26.challenge.application.resource.statistic;

import static org.springframework.beans.BeanUtils.copyProperties;
import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

import org.springframework.beans.factory.annotation.Autowired;

import com.n26.challenge.application.service.TransactionService;
import com.n26.challenge.domain.model.statistics.Statistics;
import com.n26.challenge.infrastructure.rest.annotation.RestResource;
import com.n26.challenge.infrastructure.rest.annotation.RestService;

import io.swagger.annotations.Api;

@Api(tags="Challenge")
@RestResource(path= "/statistics")
public class StatisticResource {
	
	@Autowired
	private TransactionService service;
	
	@RestService(code=CREATED, method=GET)
	public StatisticResponse create() {
		Statistics statistics = service.getRealTimeStatistics();
		
		// Do not return your Models, return DTOs in APIs.
		StatisticResponse response = new StatisticResponse();
		copyProperties(statistics, response);
		return response;
	}
	
}
