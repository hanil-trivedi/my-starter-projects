package com.kafka.demo;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class KafkaListeners {

//	we provide the group id because in case we are scaling and create new instance of same
//	application , the new application listener instance will listen to same partition or topic topic
	@KafkaListener(topics = "hanilTrivedi" , groupId="goToSleepId")
	void listener(String data) {
		System.out.println("Listener received: "+data+"😀 ");
	}
}
