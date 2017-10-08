package com.affaince.subscription.configuration;

import com.affaince.subscription.events.ListenerContainerFactory;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.axonframework.eventhandling.EventHandler;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.support.converter.SimpleMessageConverter;
import org.springframework.util.ErrorHandler;

import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
import javax.jms.Session;
import java.util.Map;

/**
 * Created by rsavaliya on 9/1/16.
 */
/*@EnableAutoConfiguration
@Configuration*/
@Configuration
@ConditionalOnProperty(name = "subscription.ActiveMQConfiguration", havingValue = "true", matchIfMissing = true)
public class ActiveMQConfiguration {

    @Resource(name = "types")
    Map<String, String> types;

    @Bean
    public JmsTemplate jmsTemplate(@Value("${axon.eventBus.topicName}") String topicName, ConnectionFactory connectionFactory) {
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
        jmsTemplate.setDefaultDestinationName(topicName);
        jmsTemplate.setPubSubDomain(true);
        jmsTemplate.setPubSubNoLocal(false);
        jmsTemplate.setMessageConverter(new SimpleMessageConverter());
        return jmsTemplate;
    }

    @Bean
    public ConnectionFactory connectionFactory() {
        return new ActiveMQConnectionFactory();
        //return new CachingConnectionFactory(new ActiveMQConnectionFactory(brokerURL));
    }


    @Bean
    public ListenerContainerFactory listenerContainerFactory(
            @Value("${axon.eventBus.queueName}") String queueName,
            ConnectionFactory connectionFactory, ErrorHandler errorHandler) {
        ListenerContainerFactory containerFactory = new ListenerContainerFactory();
        containerFactory.setDestinationName(queueName);
        containerFactory.setConnectionFactory(connectionFactory);
        containerFactory.setErrorHandler(errorHandler);
        containerFactory.setConcurrency("2-20");
        containerFactory.setIdleConsumerLimit(10);
        containerFactory.setAnnotation(EventHandler.class);
        containerFactory.setSessionAcknowledgeMode(Session.AUTO_ACKNOWLEDGE);
        containerFactory.setConsumedEventTypes(types);
        return containerFactory;

    }
}
