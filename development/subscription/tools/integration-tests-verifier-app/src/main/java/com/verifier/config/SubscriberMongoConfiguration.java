package com.verifier.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;
import com.verifier.domains.subscriber.repository.SubscriberProductViewRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.net.UnknownHostException;

@Configuration
@EnableMongoRepositories(basePackageClasses = {com.verifier.domains.subscriber.repository.BenefitViewRepository.class,
com.verifier.domains.subscriber.repository.DeliveryActualsViewRepository.class,
com.verifier.domains.subscriber.repository.DeliveryChargesRuleViewRepository.class,
        com.verifier.domains.subscriber.repository.DeliveryForecastTrendViewRepository.class,
        com.verifier.domains.subscriber.repository.DeliveryForecastViewRepository.class,
        com.verifier.domains.subscriber.repository.DeliveryPseudoActualsViewRepository.class,
        com.verifier.domains.subscriber.repository.DeliveryViewPagingRepository.class,
        com.verifier.domains.subscriber.repository.DeliveryViewRepository.class,
        com.verifier.domains.subscriber.repository.LatestPriceBucketViewRepository.class,
        com.verifier.domains.subscriber.repository.PaymentSchemesViewRepository.class,
        com.verifier.domains.subscriber.repository.SubscriberProductViewRepository.class,
        com.verifier.domains.subscriber.repository.SubscriberForecastTrendViewRepository.class,
        com.verifier.domains.subscriber.repository.SubscriberPseudoActualsViewRepository.class,
        com.verifier.domains.subscriber.repository.SubscribersActualsViewRepository.class,
        com.verifier.domains.subscriber.repository.SubscribersForecastViewRepository.class,
        com.verifier.domains.subscriber.repository.SubscriberViewRepository.class,
        com.verifier.domains.subscriber.repository.SubscriptionActualsViewRepoitory.class,
        com.verifier.domains.subscriber.repository.SubscriptionForecastTrendViewRepository.class,
        com.verifier.domains.subscriber.repository.SubscriptionForecastViewRepository.class,
        com.verifier.domains.subscriber.repository.SubscriptionPseudoActualsViewRepository.class,
        com.verifier.domains.subscriber.repository.SubscriptionReceivedPaymentViewRepository.class,
        com.verifier.domains.subscriber.repository.SubscriptionRuleViewRepository.class,
        com.verifier.domains.subscriber.repository.SubscriptionSummaryViewRepository.class,
        com.verifier.domains.subscriber.repository.SubscriptionViewRepository.class,
        })
public class SubscriberMongoConfiguration {
    @Bean
    @Qualifier("SubscriberMongoTemplate")
    public MongoTemplate mongoTemplate(@Qualifier("SubscriberMongoDbFactory") MongoDbFactory factory) {
        System.out.println("###INside MOngoTemplate creation");
        MongoTemplate mongoTemplate = new MongoTemplate(factory);
        return mongoTemplate;
    }

    @Bean
    @Qualifier("SubscriberMongo")
    public MongoClient mongo(@Qualifier("SubscriberMongoClientURI") MongoClientURI mongoClientURI) throws UnknownHostException {
        System.out.println("###INside MOngoClient creation");
        return new MongoClient(mongoClientURI);
    }

    @Bean
    @Qualifier("SubscriberMongoClientURI")
    public MongoClientURI mongoClientURI(@Value("${view.db.subscriber.host}") String host, @Value("${view.db.subscriber.port}") int port,
                                         @Value("${view.db.subscriber.name}") String dbName,
                                         @Value("${affaince.db.username}") String username,
                                         @Value("${affaince.db.password}") String password) {
        return new MongoClientURI("mongodb://" /*+ username + ":" + password + "@" */
                + host
                + ":"
                + port
                + "/" +
                dbName);
    }

    @Bean
    @Qualifier("SubscriberMongoDbFactory")
    public MongoDbFactory mongoDbFactory(@Value("${view.db.subscriber.host}") String host, @Value("${view.db.subscriber.port}") int port,
                                         @Value("${view.db.subscriber.name}") String dbName) throws UnknownHostException {
        System.out.println("###INside mongoDbFactory creation");
        return new SimpleMongoDbFactory(new MongoClient(new ServerAddress(host, port)), dbName);
    }
} 
