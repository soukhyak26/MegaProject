package com.affaince.subscription.configuration;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.command.interceptors.CommandLoggingInterceptor;
import com.affaince.subscription.common.idconverter.ProductMonthlyVersionIdReaderConverter;
import com.affaince.subscription.common.idconverter.ProductVersionIdReaderConverter;
import com.affaince.subscription.common.idconverter.ProductVersionIdWriterConverter;
import com.affaince.subscription.common.service.interpolate.config.Interpolate;
import com.affaince.subscription.repository.DefaultIdGenerator;
import com.affaince.subscription.repository.IdGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.datatype.joda.JodaModule;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.mongodb.Mongo;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoURI;
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
import org.axonframework.serializer.AnnotationRevisionResolver;
import org.axonframework.serializer.Serializer;
import org.axonframework.serializer.json.JacksonSerializer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Import;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.CustomConversions;
import org.springframework.integration.handler.LoggingHandler;
import org.springframework.util.ErrorHandler;

import java.net.UnknownHostException;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ThreadFactory;

import static java.util.concurrent.Executors.defaultThreadFactory;
import static java.util.concurrent.Executors.newScheduledThreadPool;

@Import(
        {RabbitMQConfiguration.class, ActiveMQConfiguration.class, Interpolate.class,SparkConfig.class}
)
public class CommonConfig {
    //  private static final int DEFAULT_JGROUPS_PORT = 12001;
    //private static final Logger = getLogger (CommonConfig.class);

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
        MongoTemplate mongoTemplate =
                new DefaultMongoTemplate(mongo, dbName, "domainevents", "snapshotevents", null, null);
        return mongoTemplate;
    }

    @Bean
    public Mongo mongo(MongoClientURI mongoClientURI) {
        try {
            return new Mongo(new MongoURI(mongoClientURI));
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return null;
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
    public EventTemplate eventTemplate(EventBus eventBus) {
        return new EventTemplate(eventBus);
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

    @Bean
    public ClusterSelector selector(@Qualifier("asyncCluster") Cluster cluster) {
        return new DefaultClusterSelector(cluster);
        //return new AnnotationClusterSelector(EventHandler.class,cluster);
    }

    @Bean(name = "asyncCluster")
    public Cluster asyncCluster(@Value("${asyncCluster.pool.size:20}") int maximumPoolSize,
                                @Value("${asyncCluster.identifier}") String asyncClusterIdentifier) {
        ThreadFactory threadFactory = new ThreadFactoryBuilder().setThreadFactory(defaultThreadFactory())
                .setNameFormat("asyncCluster-%d").build();
        return new AsynchronousCluster(asyncClusterIdentifier,
                newScheduledThreadPool(maximumPoolSize, threadFactory), new SequentialPerAggregatePolicy());
    }

    @Bean
    public EventBus eventBus(ClusterSelector selector, EventBusTerminal eventBusTerminal) {
        return new ClusteringEventBus(selector, eventBusTerminal);
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

/*
    @Bean(name="customConversionsForEndDate")
    public CustomConversions customConversionsForEndDate() {
        List<Converter<?, ?>> converters = new ArrayList<Converter<?, ?>>();
        converters.add(new LocalDateWritingConverter());
        converters.add(new LocalDateReadingConverter());
        return new CustomConversions(converters);
    }
*/

    @Bean
    public Locale locale (@Value("${subscription.locale}") String locale) {
        return new Locale(locale);
    }

    @Bean
    public SimpleMongoDbFactory mongoDbFactory(MongoClientURI mongoClientURI) throws Exception {
        return new SimpleMongoDbFactory(new MongoURI(mongoClientURI));
    }
}