package com.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.example.producer.KafkaProducerImpl;
import com.example.producer.Producer;
import com.example.producer.config.KafkaProducerConfig;

@Configuration
@SpringBootApplication
public class KafkaProducerAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(KafkaProducerAppApplication.class, args);
	}
	
	@Bean
	Producer<String, String> createKafkaProducer(KafkaProducerConfig kafkaProducerConfig){
		return new KafkaProducerImpl<>(kafkaProducerConfig);
	}
}
