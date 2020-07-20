package com.prashant.app;

import org.apache.qpid.jms.JmsConnectionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.jms.connection.CachingConnectionFactory;

@SpringBootApplication
@EnableAutoConfiguration
public class CamelSpringJmsKafkaApplication {

	public static void main(String[] args) {
		SpringApplication.run(CamelSpringJmsKafkaApplication.class, args);
	}

	@Bean
	public JmsConnectionFactory jmsConnectionFactory(@Value("${qpidUser}") String qpidUser, @Value("${qpidPassword}") String qpidPassword, @Value("${qpidBrokerUrl}") String qpidBrokerUrl) {
		JmsConnectionFactory jmsConnectionFactory = new JmsConnectionFactory(qpidPassword, qpidPassword, qpidBrokerUrl);
		return jmsConnectionFactory;
	}

	//@Bean(name = "jmsCachingConnectionFactory")
	//@Primary
	public CachingConnectionFactory jmsCachingConnectionFactory(JmsConnectionFactory jmsConnectionFactory) {
		CachingConnectionFactory cachingConnectionFactory = new CachingConnectionFactory(jmsConnectionFactory);

		return cachingConnectionFactory;
	}


}
