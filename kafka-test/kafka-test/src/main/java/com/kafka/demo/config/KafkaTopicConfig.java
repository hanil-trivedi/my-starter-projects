package com.kafka.demo.config;

import org.apache.kafka.clients.admin.NewTopic;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;

import org.springframework.kafka.config.TopicBuilder;

//This @configuration class creates the Topics 

@Configuration

public class KafkaTopicConfig {

	// add bean so that this gets instantiated by spring and we get a new topic
			// every time
	@Bean
	public NewTopic hanilsTopic() {
		return TopicBuilder.name("hanilTrivedi")
				.build();
	}

}
