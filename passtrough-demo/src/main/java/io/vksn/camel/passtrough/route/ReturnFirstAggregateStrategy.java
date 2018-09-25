package io.vksn.camel.passtrough.route;

import org.apache.camel.Exchange;
import org.apache.camel.processor.aggregate.AggregationStrategy;
import org.springframework.stereotype.Component;

@Component
public class ReturnFirstAggregateStrategy implements AggregationStrategy {

	@Override
	public Exchange aggregate(Exchange oldExchange, Exchange newExchange) {
		if(oldExchange != null) {
			return oldExchange;
		}
		return newExchange;
	}

}
