package com.prashant.app;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.kafka.KafkaConstants;
import org.apache.qpid.jms.JmsConnectionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class QpidToKafkaRoute extends RouteBuilder {

    @Autowired
    JmsConnectionFactory jmsConnectionFactory;

    @Bean
    public org.apache.camel.component.amqp.AMQPComponent amqpConnection() {
        org.apache.camel.component.amqp.AMQPComponent amqp = new org.apache.camel.component.amqp.AMQPComponent();
        amqp.setConnectionFactory(jmsConnectionFactory);
        return amqp;
    }

    public void configure() throws Exception {
        from("amqp:queue:test")
                .log("Received key : ${header.JMSMessageID}, message : ${body}")
                .setHeader(KafkaConstants.KEY, header("JMSMessageID"))
                .to("kafka:camel")
                .log("Sent key : ${headers[kafka.KEY]}, message : ${body}");
    }
}
