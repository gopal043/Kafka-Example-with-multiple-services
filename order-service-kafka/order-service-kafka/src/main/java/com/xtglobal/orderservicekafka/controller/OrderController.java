package com.xtglobal.orderservicekafka.controller;

import org.apache.kafka.common.Uuid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.xtglobal.basedomainskafka.dto.Order;
import com.xtglobal.basedomainskafka.dto.OrderEvent;
import com.xtglobal.orderservicekafka.kafka.OrderProducer;

@RestController
@RequestMapping("/api/v1")
public class OrderController {

	@Autowired
	private OrderProducer orderProducer;
	
	@PostMapping("/orders")
	public String placeOrder(@RequestBody Order order) {
		
		order.setOrderId(Uuid.randomUuid().toString());
		OrderEvent orderEvent = new OrderEvent();
		
		orderEvent.setStatus("PENDING");
		orderEvent.setMessage("order status is in pending");
		orderEvent.setOrder(order);
		
		orderProducer.sendMessage(orderEvent);
		
		return "Order place successfully...!";
	}
}
