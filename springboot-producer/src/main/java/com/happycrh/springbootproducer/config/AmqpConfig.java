package com.happycrh.springbootproducer.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmqpConfig {

	public static final String MQ_QUEUE_TENANT_NAME = "order-queue-worker";
	public static final String MQ_QUEUE_TENANT_NAME_JSON = "order-queue-worker-jsonobject";
	/*public static final String MQ_QUEUE_TENANT_NAME2 = "order-queue-worker2";*/

	
	@Bean
	public Queue queueTenant(){
		return new Queue(MQ_QUEUE_TENANT_NAME);
	}
	@Bean
	public Queue queueTenantJSON(){
		return new Queue(MQ_QUEUE_TENANT_NAME_JSON);
	}
	/*@Bean
	public Queue queueTenan2t(){
		return new Queue(MQ_QUEUE_TENANT_NAME2);
	}*/
	
}
