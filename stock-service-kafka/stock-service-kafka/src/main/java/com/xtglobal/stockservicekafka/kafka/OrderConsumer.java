package com.xtglobal.stockservicekafka.kafka;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import com.xtglobal.basedomainskafka.dto.OrderEvent;

@Service
public class OrderConsumer {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrderConsumer.class);

	@KafkaListener(topics = "${spring.kafka.topic.name}"
			,groupId = "${spring.kafka.consumer.group-id}")
	public void consume(OrderEvent orderEvent) {
		LOGGER.info(String.format("order event recieved in stock service %s", orderEvent.toString()));
	  
		//save order data in database
	}
}
