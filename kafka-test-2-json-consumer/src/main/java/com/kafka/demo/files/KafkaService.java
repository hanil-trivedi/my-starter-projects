package com.kafka.demo.files;

import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Service;

@Service
public class KafkaService {

	// Annotation required to listen
	// the message from Kafka server
	@KafkaListener(topics = "JsonTopic2", groupId = "id", containerFactory = "studentListner")

	// both getting Student Object Directly and getting ConsumerRecord then TypeCast
	// to Student Object
	// both works
	public void publish(Student student)
	// publish(@Payload ConsumerRecord consumerRecord)
	{

		// Student student = (Student) consumerRecord.value();
		System.out.println("New Entry: " + student);
	}
}
