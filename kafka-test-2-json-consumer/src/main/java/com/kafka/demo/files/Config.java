package com.kafka.demo.files;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.ErrorHandlingDeserializer;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@EnableKafka
@Configuration
public class Config {

	// Function to establish a connection
	// between Spring application
	// and Kafka server
	@Bean
	public ConsumerFactory<String, Student> studentConsumer() {

		// HashMap to store the configurations
		Map<String, Object> map = new HashMap<>();

		// put the host IP in the map
		map.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092");

		// put the group ID of consumer in the map
		map.put(ConsumerConfig.GROUP_ID_CONFIG, "id");
		map.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
		map.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, JsonDeserializer.class);
		
		//added specifically for error handling scenarios
		map.put(ErrorHandlingDeserializer.VALUE_DESERIALIZER_CLASS, JsonDeserializer.class);
		map.put(JsonDeserializer.VALUE_DEFAULT_TYPE, Student.class);
		JsonDeserializer<Student> jsonDeserializer= new JsonDeserializer<>(Student.class , false );
		jsonDeserializer.addTrustedPackages("com.kafka.demo.files");
		

		// return message in JSON format
		return new DefaultKafkaConsumerFactory<>(map, new StringDeserializer(), new JsonDeserializer<>(Student.class));
	}

	@Bean
	public ConcurrentKafkaListenerContainerFactory<String, Student> studentListner() {
		ConcurrentKafkaListenerContainerFactory<String, Student> factory = new ConcurrentKafkaListenerContainerFactory<>();
		factory.setConsumerFactory(studentConsumer());
		return factory;
	}
}
