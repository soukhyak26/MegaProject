package com.affiance.prediction.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;

/**
 * Created by mandar on 8/15/2017.
 */
@SpringBootApplication
@EnableAutoConfiguration
@PropertySource({"classpath:Application.properties"})
@EnableBinding(Sink.class)
public class Application {
    @Autowired
    private JmsTemplate jmsTemplate;
    @Value("${forecast.queueName}")
    private String destination;

    public static void main(String[] args) {
        SpringApplication.run(
                Application.class, args);
    }

    @StreamListener(Sink.INPUT)
    public void listen(String data) {
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@IN Sink: " + data);
        // Post message to the message queue named "ForecastOutputQueue"
        jmsTemplate.convertAndSend(destination, data);
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@IN Sink:After convertAndSend");

    }

/*
    @StreamListener(Sink.INPUT)
    public void listen(String data){
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@IN Sink: "+ data);
    }
*/

}