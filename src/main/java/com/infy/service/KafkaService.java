package com.infy.service;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.Acknowledgment;
import org.springframework.stereotype.Service;

import com.infy.model.Customer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class KafkaService {
	
//	@Value("${server.port}") 
//	private String topic;
//	
	@KafkaListener(topics = "${topic.name}", groupId = "${spring.kafka.consumer.group-id}")
	public void consume(ConsumerRecord<String, Customer> record, Acknowledgment acknowledgment) {
		Customer customer = record.value();
		log.info("Consumed Successfully: "+ customer.toString());
		if(acknowledgment != null) {
			acknowledgment.acknowledge();
			log.info("Acknowledgement Provided");
		}
	}
}
