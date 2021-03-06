package com.kafka.demo;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import controller.MessageRequest;

@RestController
@RequestMapping(value = "/api/v1/messages")
public class MessageController {

	private KafkaTemplate<String, String >kafkaTemplate;
	
	@PostMapping
	public void publish(@RequestBody MessageRequest messageRequest) {
		kafkaTemplate.send("hanilTrivedi" , messageRequest.message());
	}

	public MessageController(KafkaTemplate<String, String> kafkaTemplate) {
		super();
		this.kafkaTemplate = kafkaTemplate;
	}
	
	
}
