package com.affaince.subscription.configuration;

import com.affaince.subscription.events.ListenerContainerFactory;
import com.affaince.subscription.events.SubscriptionEventBusTerminal;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.axonframework.eventhandling.Cluster;
import org.axonframework.eventhandling.EventBusTerminal;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.axonframework.serializer.Serializer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.jms.SubscribableJmsChannel;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.MessagingMessageConverter;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.util.ErrorHandler;

import javax.jms.ConnectionFactory;
import javax.jms.Session;

/**
 * Created by rsavaliya on 9/1/16.
 */
/*@EnableAutoConfiguration
@Configuration*/
public class ActiveMQConfiguration extends Default {

    @Bean
    public JmsTemplate jmsTemplate(@Value("${axon.eventBus.topicName}") String topicName, ConnectionFactory connectionFactory) {
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
        jmsTemplate.setDefaultDestinationName(topicName);
        jmsTemplate.setPubSubDomain(true);
        jmsTemplate.setPubSubNoLocal(false);
        jmsTemplate.setMessageConverter(new MessagingMessageConverter());
        return jmsTemplate;
    }

    @Bean
    public ConnectionFactory connectionFactory(@Value("${spring.activemq.broker-url}") String brokerURL) {
        return new ActiveMQConnectionFactory(brokerURL);
        //return new CachingConnectionFactory(new ActiveMQConnectionFactory(brokerURL));
    }


    @Bean
    public ListenerContainerFactory listenerContainerFactory(@Value("${axon.eventBus.queueName}") String queueName, ConnectionFactory connectionFactory, ErrorHandler errorHandler) {
        System.out.println("@@Queue Name:" + queueName);
        ListenerContainerFactory containerFactory = new ListenerContainerFactory();
        containerFactory.setDestinationName(queueName);
        containerFactory.setConnectionFactory(connectionFactory);
        containerFactory.setErrorHandler(errorHandler);
        containerFactory.setConcurrency("2-20");
        containerFactory.setIdleConsumerLimit(10);
        containerFactory.setAnnotation(EventHandler.class);
        containerFactory.setSessionAcknowledgeMode(Session.AUTO_ACKNOWLEDGE);
        containerFactory.setConsumedEventTypes(types());
        return containerFactory;

    }

    @Bean
    public SubscribableChannel eventChannel(JmsTemplate jmsTemplate, ListenerContainerFactory listenerContainerFactory) throws Exception {

        SubscribableChannel channel = new SubscribableJmsChannel(listenerContainerFactory.getObject(), jmsTemplate);
        return channel;
    }

    /*@Bean
    public EventBusTerminal subscriptionEventBusTerminal(Cluster asyncCluster, Serializer serializer, @Qualifier("eventChannel") final SubscribableChannel subscribableChannel) {
        return new SimpleEventBus(serializer, subscribableChannel, asyncCluster);
    }*/
}
