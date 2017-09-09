package com.affiance.prediction.sink;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.annotation.StreamListener;
import org.springframework.cloud.stream.messaging.Sink;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessageCreator;
import org.springframework.messaging.Message;
import org.springframework.stereotype.Component;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;

/**
 * Created by mandar on 8/14/2017.
 */
@EnableBinding(Sink.class)
@Component
public class MasterMessageListener {
/*
    @Autowired
    private JmsTemplate jmsTemplate;
    @Value("${forecast.queueName}")
    private String destination;
    @StreamListener(Sink.INPUT)
    public void listen(String data) {
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@IN Sink: " + data);
        // Post message to the message queue named "ForecastOutputQueue"
        jmsTemplate.convertAndSend(destination, data);
        System.out.println("@@@@@@@@@@@@@@@@@@@@@@@@@@IN Sink:After convertAndSend");

    }
*/
}
