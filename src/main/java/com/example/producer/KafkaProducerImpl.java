package com.example.producer;

import java.io.IOException;
import java.util.Properties;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.example.producer.config.KafkaProducerConfig;

public class KafkaProducerImpl<K, V> implements Producer<K, V> {

	private static final Logger log = LoggerFactory.getLogger(KafkaProducerImpl.class);

	private org.apache.kafka.clients.producer.Producer<K, V> producer;

	public KafkaProducerImpl(KafkaProducerConfig kafkaProducerConfig) {
		Properties props = new Properties();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaProducerConfig.getBootstrapServers());
		props.put(ProducerConfig.ACKS_CONFIG, kafkaProducerConfig.getAcks());
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName()); // no need to externalize
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName()); // no need to externalize
		props.put(ProducerConfig.CLIENT_ID_CONFIG, kafkaProducerConfig.getProducerClientName());
		//TODO other config if needed
		this.producer = new KafkaProducer<>(props);
	}

	@Override
	public boolean send(String topic, K key, V value) {
		log.debug("sending record to kafka broker");
		try {
			producer.send(new ProducerRecord<K, V>(topic, key, value));
		} catch (Exception e) {
			log.error("Exception while sending record to kafka topic: {}, key: {} , value: {}", topic, key, value, e);
			return false;
		}
		return true;
	}

	@Override
	public void close() throws IOException {
		log.debug("Closing KafkaProducer");
		producer.close(); // kafka producer is closable by nature
	}

}
