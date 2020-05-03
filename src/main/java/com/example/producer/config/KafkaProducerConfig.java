package com.example.producer.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration(proxyBeanMethods = false)
@ConfigurationProperties(prefix = "kafka")
public class KafkaProducerConfig {

	private String bootstrapServers;
	private String acks="all";
	private String topicName;
	private String producerClientName="producerDefaultName";
	private Integer replicaFactor=3;
	private Integer partionsNumber=1;

	/**
	 * @return the bootstrapServers
	 */
	public String getBootstrapServers() {
		return bootstrapServers;
	}
	/**
	 * @param bootstrapServers the bootstrapServers to set
	 */
	public void setBootstrapServers(String bootstrapServers) {
		this.bootstrapServers = bootstrapServers;
	}
	/**
	 * @return the acks
	 */
	public String getAcks() {
		return acks;
	}
	/**
	 * @param acks the acks to set
	 */
	public void setAcks(String acks) {
		this.acks = acks;
	}
	/**
	 * @return the topicName
	 */
	public String getTopicName() {
		return topicName;
	}
	/**
	 * @param topicName the topicName to set
	 */
	public void setTopicName(String topicName) {
		this.topicName = topicName;
	}
	/**
	 * @return the replicaFactor
	 */
	public Integer getReplicaFactor() {
		return replicaFactor;
	}
	/**
	 * @param replicaFactor the replicaFactor to set
	 */
	public void setReplicaFactor(Integer replicaFactor) {
		this.replicaFactor = replicaFactor;
	}
	/**
	 * @return the partionsNumber
	 */
	public Integer getPartionsNumber() {
		return partionsNumber;
	}
	/**
	 * @param partionsNumber the partionsNumber to set
	 */
	public void setPartionsNumber(Integer partionsNumber) {
		this.partionsNumber = partionsNumber;
	}
	public String getProducerClientName() {
		return producerClientName;
	}
	public void setProducerClientName(String producerClientName) {
		this.producerClientName = producerClientName;
	}
}
