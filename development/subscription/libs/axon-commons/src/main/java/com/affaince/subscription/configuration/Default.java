package com.affaince.subscription.configuration;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.common.idconverter.ProductMonthlyVersionIdReaderConverter;
import com.affaince.subscription.common.idconverter.ProductVersionIdReaderConverter;
import com.affaince.subscription.common.idconverter.ProductVersionIdWriterConverter;
import com.affaince.subscription.repository.DefaultIdGenerator;
import com.affaince.subscription.repository.IdGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import io.netty.handler.logging.LoggingHandler;
import org.axonframework.commandhandling.CommandBus;
import org.axonframework.commandhandling.disruptor.DisruptorCommandBus;
import org.axonframework.commandhandling.gateway.IntervalRetryScheduler;
import org.axonframework.commandhandling.gateway.RetryScheduler;
import org.axonframework.eventhandling.saga.ResourceInjector;
import org.axonframework.eventsourcing.eventstore.EmbeddedEventStore;
import org.axonframework.eventsourcing.eventstore.EventStore;
import org.axonframework.mongo.eventsourcing.eventstore.DefaultMongoTemplate;
import org.axonframework.mongo.eventsourcing.eventstore.MongoEventStorageEngine;
import org.axonframework.serialization.AnnotationRevisionResolver;
import org.axonframework.serialization.Serializer;
import org.axonframework.serialization.json.JacksonSerializer;
import org.axonframework.spring.commandhandling.gateway.CommandGatewayFactoryBean;
import org.axonframework.spring.saga.SpringResourceInjector;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.convert.CustomConversions;
import org.springframework.util.ErrorHandler;

import java.net.UnknownHostException;
import java.util.*;

import static java.util.concurrent.Executors.newScheduledThreadPool;

@Import(
        {RabbitMQConfiguration.class, ActiveMQConfiguration.class}
)
public class Default {
    //  private static final int DEFAULT_JGROUPS_PORT = 12001;
    //private static final Logger = getLogger (Default.class);

    @Bean
    public LoggingHandler loggingHandler() {
        return new LoggingHandler("DEBUG");
    }

    @Bean
    public EventStore eventStore(MongoEventStorageEngine eventStorageEngine) {
        return new EmbeddedEventStore(eventStorageEngine);
    }

    @Bean
    public MongoEventStorageEngine eventStorageEngine(org.axonframework.mongo.eventsourcing.eventstore.MongoTemplate eventStoreMongoTemplate) {
        return new MongoEventStorageEngine(eventStoreMongoTemplate);
    }

    @Bean
    public MongoTemplate mongoSpringTemplate(MongoClient mongo, @Value("${view.db.name}") String dbName) throws UnknownHostException {
        return new MongoTemplate(mongo, dbName);
    }

    @Bean(name = "axonmongo")
    public org.axonframework.mongo.eventsourcing.eventstore.MongoTemplate mongoTemplate(MongoClient mongo, @Value("${view.db.name}") String dbName) {
        org.axonframework.mongo.eventsourcing.eventstore.MongoTemplate mongoTemplate =
                new DefaultMongoTemplate(mongo, dbName, "domainevents", "snapshotevents");
        return mongoTemplate;
    }

    @Bean
    public MongoClient mongo(MongoClientURI mongoClientURI) {
        return new MongoClient(mongoClientURI);
    }

    @Bean
    public MongoClientURI mongoClientURI(@Value("${view.db.host}") String host, @Value("${view.db.port}") int port,
                                         @Value("${view.db.name}") String dbName,
                                         @Value("${affaince.db.username}") String username,
                                         @Value("${affaince.db.password}") String password) {
        return new MongoClientURI("mongodb://" + username + ":" + password + "@"
                + host
                + ":"
                + port
                + "/" +
                dbName);
    }


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


    protected Map<String, String> types() {
        return new HashMap<>();
    }

    @Bean
    public Serializer serializer(ObjectMapper objectMapper) {
        JacksonSerializer serializer = new AxonJacksonSerializer(
                objectMapper, new AnnotationRevisionResolver(), types()
        );
        SimpleModule simpleModule = new SimpleModule("Axon");
        serializer.getObjectMapper().registerModule(simpleModule);
        serializer.getObjectMapper().registerModule(new JodaModule());

        return serializer;
    }

    @Bean
    public DisruptorCommandBus localSegment(EventStore eventStore) {
        return new DisruptorCommandBus(eventStore);
    }

    @Bean
    public CommandGatewayFactoryBean commandGateway(CommandBus commandBus) {
        RetryScheduler retryScheduler = new IntervalRetryScheduler(newScheduledThreadPool(1), 100, 3);
        CommandGatewayFactoryBean<SubscriptionCommandGateway> commandGatewayFactoryBean = new CommandGatewayFactoryBean<>();
        commandGatewayFactoryBean.setGatewayInterface(SubscriptionCommandGateway.class);
        commandGatewayFactoryBean.setCommandBus(commandBus);
        commandGatewayFactoryBean.setRetryScheduler(retryScheduler);
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

    @Bean(name="customConversionsForProductVersionId")
    public CustomConversions customConversionsForProductVersionId() {
        List<Converter<?, ?>> converters = new ArrayList<Converter<?, ?>>();
        converters.add(new ProductVersionIdReaderConverter());
        converters.add(new ProductVersionIdWriterConverter());
        return new CustomConversions(converters);
    }

    @Bean(name="customConversionsForProductMonthlyVersionId")
    public CustomConversions customConversionsForProductMonthlyVersionId() {
        List<Converter<?, ?>> converters = new ArrayList<Converter<?, ?>>();
        converters.add(new ProductMonthlyVersionIdReaderConverter());
        converters.add(new ProductMonthlyVersionIdReaderConverter());
        return new CustomConversions(converters);
    }

    @Bean
    public Locale locale (@Value("${subscription.locale}") String locale) {
        return new Locale(locale);
    }
}
