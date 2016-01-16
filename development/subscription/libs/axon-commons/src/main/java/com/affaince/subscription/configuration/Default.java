package com.affaince.subscription.configuration;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.command.interceptors.CommandLoggingInterceptor;
import com.affaince.subscription.repository.DefaultIdGenerator;
import com.affaince.subscription.repository.IdGenerator;
import com.affaince.subscription.transformation.MetadataDeserializer;
import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.introspect.VisibilityChecker;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.CommandDispatchInterceptor;
import org.axonframework.commandhandling.disruptor.DisruptorCommandBus;
import org.axonframework.commandhandling.disruptor.DisruptorConfiguration;
import org.axonframework.commandhandling.gateway.CommandGatewayFactoryBean;
import org.axonframework.commandhandling.gateway.IntervalRetryScheduler;
import org.axonframework.commandhandling.gateway.RetryScheduler;
import org.axonframework.commandhandling.interceptors.BeanValidationInterceptor;
import org.axonframework.domain.IdentifierFactory;
import org.axonframework.domain.MetaData;
import org.axonframework.eventhandling.*;
import org.axonframework.eventhandling.async.AsynchronousCluster;
import org.axonframework.eventhandling.async.SequentialPerAggregatePolicy;
import org.axonframework.eventstore.EventStore;
import org.axonframework.eventstore.mongo.DefaultMongoTemplate;
import org.axonframework.eventstore.mongo.MongoEventStore;
import org.axonframework.eventstore.mongo.MongoTemplate;
import org.axonframework.saga.ResourceInjector;
import org.axonframework.saga.spring.SpringResourceInjector;
import org.axonframework.serializer.SerializedType;
import org.axonframework.serializer.Serializer;
import org.axonframework.serializer.json.JacksonSerializer;
import org.springframework.amqp.core.Queue;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.handler.LoggingHandler;
import org.springframework.messaging.SubscribableChannel;
import org.springframework.util.ErrorHandler;

import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ThreadFactory;

import static java.util.concurrent.Executors.defaultThreadFactory;
import static java.util.concurrent.Executors.newScheduledThreadPool;

/**
 * Created by NIKUNJ on 7/12/2015.
 */
public class Default {
    //  private static final int DEFAULT_JGROUPS_PORT = 12001;
    //private static final Logger = getLogger (Default.class);

    @Bean
    public LoggingHandler loggingHandler() {
        return new LoggingHandler("DEBUG");
    }

    @Bean
    public IdentifierFactory identifierFactory() {
        return IdentifierFactory.getInstance();
    }

    @Bean
    public EventStore eventStore(@Qualifier("axonmongo") MongoTemplate mongoTemplate) throws SQLException {
        MongoEventStore mongoEventStore = new MongoEventStore(mongoTemplate);
        return mongoEventStore;
    }

    @Bean(name = "axonmongo")
    public MongoTemplate mongoTemplate(Mongo mongo, @Value("${view.db.name}") String dbName) {
        MongoTemplate mongoTemplate = new DefaultMongoTemplate(mongo, dbName, "domainevents", "snapshotevents", null, null);
        return mongoTemplate;
    }

    @Bean
    public Mongo mongo(@Value("${view.db.host}") String host, @Value("${view.db.port}") int port) {
        try {
            return new MongoClient(host, port);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Bean
    public EventTemplate eventTemplate(EventBus eventBus) {
        return new EventTemplate(eventBus);
    }

    /*@Bean
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
    }*/

    @Bean
    public ErrorHandler errorHandler() {
        return new ErrorHandler() {
            @Override
            public void handleError(Throwable throwable) {
                System.out.println("@@@@@@IN Error Handler");
                throwable.printStackTrace();
            }
        };
    }
/*
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

    }*/

    @Bean
    public SubscribableChannel eventChannel() throws Exception {

        //SubscribableChannel channel = new SubscribableJmsChannel(listenerContainerFactory.getObject(), jmsTemplate);
        return new DirectChannel();
    }
/*
    @Bean
    public EventBusTerminal subscriptionEventBusTerminal(Cluster asyncCluster, Serializer serializer, @Qualifier("eventChannel") final SubscribableChannel subscribableChannel) {
        return new SubscriptionEventBusTerminal(serializer, subscribableChannel, asyncCluster);
    }*/

    @Bean
    public ClusterSelector selector(Queue queue) {
        return new DefaultClusterSelector(new SimpleCluster(queue.getName()));
        //return new AnnotationClusterSelector(EventHandler.class,cluster);
    }

    @Bean(name = "asyncCluster")
    public Cluster asyncCluster(@Value("${asyncCluster.pool.size:20}") int maximumPoolSize) {
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setThreadFactory(defaultThreadFactory())
                .setNameFormat("asyncCluster-%d").build();
        return new AsynchronousCluster("asyncCluster",
                newScheduledThreadPool(maximumPoolSize, threadFactory), new SequentialPerAggregatePolicy());
    }

    @Bean
    public EventBus eventBus(EventBusTerminal subscriptionEventBusTerminal, ClusterSelector selector) {
        return new ClusteringEventBus(selector, subscriptionEventBusTerminal);
    }

    protected Map<String, String> types() {
        return new HashMap<>();
    }

    @Bean
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enableDefaultTyping();
        objectMapper.setVisibilityChecker(VisibilityChecker.Std.defaultInstance().withFieldVisibility(JsonAutoDetect.Visibility.ANY));
        return objectMapper;
    }

    @Bean
    public Serializer serializer(ObjectMapper objectMapper) {
        final JacksonSerializer serializer = new JacksonSerializer(objectMapper) {
            private Map<String, String> types = types();

            public String resolveClassName(SerializedType serializedType) {
                String name = serializedType.getName();
                String result = types.get(name);
                return result == null ? name : result;
            }
        };
        serializer.getObjectMapper().registerModule(new SimpleModule("Axon").addDeserializer(MetaData.class, new MetadataDeserializer()));
        serializer.getObjectMapper().registerModule(new JodaModule());
        return serializer;
    }

    @Bean
    public DisruptorCommandBus localSegment(EventStore eventStore, EventBus eventBus) {
        DisruptorConfiguration configuration = new DisruptorConfiguration();
        List<CommandDispatchInterceptor> dispatchInterceptors = new ArrayList<>();
        dispatchInterceptors.add(new BeanValidationInterceptor());
        configuration.setDispatchInterceptors(dispatchInterceptors);
        return new DisruptorCommandBus(eventStore, eventBus, configuration);
    }

    @Bean
    public CommandGatewayFactoryBean commandGateway(CommandBus commandBus) {
        RetryScheduler retryScheduler = new IntervalRetryScheduler(newScheduledThreadPool(1), 100, 3);
        CommandGatewayFactoryBean<SubscriptionCommandGateway> commandGatewayFactoryBean = new CommandGatewayFactoryBean<>();
        commandGatewayFactoryBean.setGatewayInterface(SubscriptionCommandGateway.class);
        commandGatewayFactoryBean.setCommandBus(commandBus);
        commandGatewayFactoryBean.setRetryScheduler(retryScheduler);
        commandGatewayFactoryBean.setCommandDispatchInterceptors(new CommandLoggingInterceptor("commandlogging"));
        return commandGatewayFactoryBean;
    }

    @Bean
    public ResourceInjector resourceInjector() {
        return new SpringResourceInjector();
    }

    @Bean
    public IdGenerator generator() {
        return new DefaultIdGenerator();
    }

    @Bean
    public Locale locale(@Value("${subscription.language}") String language,
                         @Value("${subscription.country}") String country) {
        return new Locale(language, country);
    }

    @Bean
    public Currency currency(Locale locale) {
        return Currency.getInstance(locale);
    }
}
