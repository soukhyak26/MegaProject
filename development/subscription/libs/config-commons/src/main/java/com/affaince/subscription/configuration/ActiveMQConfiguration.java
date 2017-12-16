package com.affaince.subscription.configuration;

import com.affaince.subscription.events.SubscriptionEventBusTerminal;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.axonframework.eventhandling.Cluster;
import org.axonframework.eventhandling.EventBusTerminal;
import org.axonframework.eventhandling.amqp.spring.ListenerContainerFactory;
import org.axonframework.serializer.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.jms.SubscribableJmsChannel;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.AbstractMessageListenerContainer;
import org.springframework.jms.support.converter.MessagingMessageConverter;
import org.springframework.messaging.SubscribableChannel;

import javax.annotation.Resource;
import javax.jms.ConnectionFactory;
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
        jmsTemplate.setMessageConverter(new MessagingMessageConverter());
        return jmsTemplate;
    }

    @Bean
    public ConnectionFactory connectionFactory(@Value("${spring.activemq.broker-url}") String brokerURL) {
        return new ActiveMQConnectionFactory(brokerURL);
        //return new CachingConnectionFactory(new ActiveMQConnectionFactory(brokerURL));
    }

    @Bean
    public SubscribableChannel eventChannel(JmsTemplate jmsTemplate, AbstractMessageListenerContainer container) throws Exception {

        SubscribableChannel channel = new SubscribableJmsChannel(container, jmsTemplate);
        return channel;
    }

    @Bean
    public EventBusTerminal subscriptionEventBusTerminal(Cluster asyncCluster, Serializer serializer, @Qualifier("eventChannel") final SubscribableChannel subscribableChannel) {
        return new SubscriptionEventBusTerminal(serializer, subscribableChannel, asyncCluster);
    }
}
