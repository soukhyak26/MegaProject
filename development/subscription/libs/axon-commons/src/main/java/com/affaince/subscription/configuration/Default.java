package com.affaince.subscription.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mongodb.Mongo;
import org.apache.activemq.ActiveMQConnection;
import org.apache.activemq.ActiveMQConnectionFactory;
import org.axonframework.commandhandling.disruptor.DisruptorCommandBus;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.commandhandling.gateway.DefaultCommandGateway;
import org.axonframework.commandhandling.gateway.IntervalRetryScheduler;
import org.axonframework.commandhandling.gateway.RetryScheduler;
import org.axonframework.domain.IdentifierFactory;
import org.axonframework.eventhandling.EventBus;
import org.axonframework.eventhandling.EventBusTerminal;
import org.axonframework.eventhandling.EventTemplate;
import org.axonframework.eventhandling.SimpleEventBus;
import org.axonframework.eventstore.EventStore;
import org.axonframework.eventstore.mongo.DefaultMongoTemplate;
import org.axonframework.eventstore.mongo.MongoEventStore;
import org.axonframework.eventstore.mongo.MongoTemplate;
import org.axonframework.saga.ResourceInjector;
import org.axonframework.saga.spring.SpringResourceInjector;
import org.axonframework.serializer.SerializedType;
import org.axonframework.serializer.Serializer;
import org.axonframework.serializer.json.JacksonSerializer;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.jms.SubscribableJmsChannel;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.AbstractMessageListenerContainer;
import org.springframework.jms.listener.DefaultMessageListenerContainer;
import org.springframework.jms.support.converter.MessagingMessageConverter;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.util.ErrorHandler;

import javax.jms.ConnectionFactory;
import javax.jms.Session;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import static java.util.concurrent.Executors.newScheduledThreadPool;

/**
 * Created by NIKUNJ on 7/12/2015.
 */
public class Default {
    private static final int DEFAULT_JGROUPS_PORT = 12001;
    //private static final Logger = getLogger (Default.class);

    @Bean
    public IdentifierFactory identifierFactory () {
        return IdentifierFactory.getInstance();
    }

    @Bean
    public EventStore eventStore (@Qualifier("axonmongo")MongoTemplate mongoTemplate) throws SQLException {
        MongoEventStore mongoEventStore = new MongoEventStore(mongoTemplate);
        return mongoEventStore;
    }

    @Bean (name = "axonmongo")
    public MongoTemplate mongoTemplate (Mongo mongo) {
        MongoTemplate mongoTemplate = new DefaultMongoTemplate(mongo, "items", "domainevents", "snapshotevents", null, null);
        return mongoTemplate;
    }

    @Bean
    public Mongo mongo () {
        try {
            return new Mongo ("localhost", 27017);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Bean
    public EventTemplate eventTemplate (EventBus eventBus) {
        return new EventTemplate(eventBus);
    }

    @Bean
    public JmsTemplate jmsTemplate (@Value("${axon.eventBus.topicName}") String topicName, ConnectionFactory connectionFactory) {
        JmsTemplate jmsTemplate = new JmsTemplate(connectionFactory);
        jmsTemplate.setDefaultDestinationName(topicName);
        jmsTemplate.setPubSubDomain(true);
        jmsTemplate.setMessageConverter(new MessagingMessageConverter());
        return jmsTemplate;
    }

    @Bean
    public ConnectionFactory connectionFactory (@Value("${spring.activemq.brokerurl}") String brokerURL) {
        return new ActiveMQConnectionFactory(ActiveMQConnection.DEFAULT_BROKER_URL);
    }

    @Bean
    public ErrorHandler errorHandler () {
        return new ErrorHandler() {
            @Override
            public void handleError(Throwable throwable) {

            }
        };
    }

    @Bean
    public AbstractMessageListenerContainer listenerContainer (@Value("${axon.eventBus.queuename}") String queueName, ConnectionFactory connectionFactory, ErrorHandler errorHandler) {
        DefaultMessageListenerContainer listenerContainer =
                new DefaultMessageListenerContainer();
        listenerContainer.setDestinationName(queueName);
        listenerContainer.setConnectionFactory(connectionFactory);
        listenerContainer.setErrorHandler(errorHandler);
        listenerContainer.setConcurrency("2-20");
        listenerContainer.setIdleConsumerLimit(10);
        listenerContainer.setSessionAcknowledgeMode(Session.AUTO_ACKNOWLEDGE);
        return listenerContainer;
    }

    @Bean
    public SubscribableChannel eventChannel (JmsTemplate jmsTemplate, AbstractMessageListenerContainer listenerContainer) {
        return new SubscribableJmsChannel(listenerContainer, jmsTemplate);
    }

    @Bean
    public EventBus eventBus () {
        return new SimpleEventBus();
    }

    protected Map<String, String> types () {
        return  new HashMap<>();
    }

    @Bean
    public Serializer serializer (ObjectMapper mapper) {
        final JacksonSerializer serializer = new JacksonSerializer(mapper) {
          private Map <String, String> types = types();

            public String resolveClassName (SerializedType serializedType) {
                String name = serializedType.getName();
                String result = types.get(name);
                return result == null? name:result;
            }
        };
        return serializer;
    }

    @Bean
    public DisruptorCommandBus localSegment (EventStore eventStore, EventBus eventBus) {
        return new DisruptorCommandBus(eventStore, eventBus);
    }

    @Bean
    public CommandGateway commandGateway (DisruptorCommandBus commandBus) {
        RetryScheduler retryScheduler = new IntervalRetryScheduler(newScheduledThreadPool(1), 100, 3);
        CommandGateway commandGateway = new DefaultCommandGateway(commandBus, retryScheduler);
        return commandGateway;
    }

    @Bean
    public ResourceInjector  resourceInjector () {
        return new SpringResourceInjector();
    }
}
