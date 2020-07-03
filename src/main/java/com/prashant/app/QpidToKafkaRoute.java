package com.prashant.app;

import org.apache.camel.builder.RouteBuilder;
import org.springframework.stereotype.Component;

@Component
public class QpidToKafkaRoute extends RouteBuilder {

    public void configure() throws Exception {
        from("amqp:queue:test")
                .to("kafka:camel");
    }
}
