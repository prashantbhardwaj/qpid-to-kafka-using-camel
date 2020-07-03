package com.prashant.app;

import org.apache.camel.builder.RouteBuilder;
import org.apache.camel.component.kafka.KafkaConstants;
import org.springframework.stereotype.Component;

@Component
public class QpidToKafkaRoute extends RouteBuilder {

    public void configure() throws Exception {
        from("amqp:queue:test")
                .log("Received key : ${header.JMSMessageID}, message : ${body}")
                .setHeader(KafkaConstants.KEY, header("JMSMessageID"))
                .to("kafka:camel")
                .log("Sent key : ${headers[kafka.KEY]}, message : ${body}");
    }
}
