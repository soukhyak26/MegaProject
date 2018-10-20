package com.verifier.config;

import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;
import com.verifier.domains.subscriber.repository.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.CustomConversions;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableMongoRepositories(mongoTemplateRef ="subscriberMongoTemplate", basePackageClasses = {com.verifier.domains.subscriber.repository.BenefitViewRepository.class,
com.verifier.domains.subscriber.repository.DeliveryActualsViewRepository.class,
com.verifier.domains.subscriber.repository.DeliveryChargesRuleViewRepository.class,
        DeliveryForecastTrendViewRepository.class,
        DeliveryForecastViewRepository.class,
        DeliveryPseudoActualsViewRepository.class,
        DeliveryViewPagingRepository.class,
        DeliveryViewRepository.class,
        LatestPriceBucketViewRepository.class,
        PaymentSchemesViewRepository.class,
        SubscriberProductViewRepository.class,
        SubscriberForecastTrendViewRepository.class,
        SubscriberPseudoActualsViewRepository.class,
        SubscribersActualsViewRepository.class,
        SubscribersForecastViewRepository.class,
        SubscriberViewRepository.class,
        SubscriptionActualsViewRepoitory.class,
        SubscriptionForecastTrendViewRepository.class,
        SubscriptionForecastViewRepository.class,
        SubscriptionPseudoActualsViewRepository.class,
        SubscriptionReceivedPaymentViewRepository.class,
        SubscriptionRuleViewRepository.class,
        SubscriptionSummaryViewRepository.class,
        SubscriptionViewRepository.class,
        })
public class SubscriberMongoConfiguration {
    @Bean //(name="subscriberMongoTemplate")
    @Qualifier("subscriberMongoTemplate")
    public MongoTemplate subscriberMongoTemplate(@Qualifier("SubscriberMongoDbFactory") MongoDbFactory factory,@Qualifier("SubscriberMappingMongoConverter") MappingMongoConverter mappingMongoConverter) {
        System.out.println("###INside SubscriberMongoTemplate creation " + factory.getDb().getName());
        MongoTemplate mongoTemplate = new MongoTemplate(factory,mappingMongoConverter);
        return mongoTemplate;
    }

    @Bean //(name="SubscriberMongo")
    @Qualifier("SubscriberMongo")
    public MongoClient mongo(@Qualifier("SubscriberMongoClientURI") MongoClientURI mongoClientURI) throws UnknownHostException {
        System.out.println("###INside MOngoClient creation");
        return new MongoClient(mongoClientURI);
    }

    @Bean //(name="SubscriberMongoClientURI")
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

    @Bean //(name="SubscriberMongoDbFactory")
    @Qualifier("SubscriberMongoDbFactory")
    public MongoDbFactory mongoDbFactory(@Value("${view.db.subscriber.host}") String host, @Value("${view.db.subscriber.port}") int port,
                                         @Value("${view.db.subscriber.name}") String dbName) throws UnknownHostException {
        System.out.println("###INside mongoDbFactory creation");
        return new SimpleMongoDbFactory(new MongoClient(new ServerAddress(host, port)), dbName);
    }
    @Bean //(name="SubscriberMappingMongoConverter")
    @Qualifier("SubscriberMappingMongoConverter")
    public MappingMongoConverter subscriberMappingMongoConverter(@Qualifier("SubscriberMongoDbFactory")MongoDbFactory mongoDbFactory,
                                                       @Qualifier("SubscriberCustomConversions") CustomConversions customConversions) throws Exception {
        MongoMappingContext mappingContext = new MongoMappingContext();
        DbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoDbFactory);
        MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, mappingContext);
        converter.setCustomConversions(customConversions);
        converter.afterPropertiesSet();
        return converter;
    }

    @Bean //(name="SubscriberCustomConversions")
    @Qualifier("SubscriberCustomConversions")
    public CustomConversions subscriberCustomConversions(){
        List<Converter<?, ?>> converters = new ArrayList<Converter<?, ?>>();
        return new CustomConversions(converters);
    }

} 
