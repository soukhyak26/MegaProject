package com.verifier.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;
import com.verifier.domains.fulfillment.repository.DispatchableDeliveryViewRepository;
import com.verifier.domains.fulfillment.repository.ProductOrderViewRepository;
import com.verifier.domains.fulfillment.repository.ProductStockProvisionRequestProxyRepository;
import com.verifier.domains.product.repository.*;
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
@EnableMongoRepositories(mongoTemplateRef = "fulfillmentMongoTemplate", basePackageClasses = {com.verifier.domains.fulfillment.repository.ProductOrderViewRepository.class,
        com.verifier.domains.fulfillment.repository.ProductStockProvisionRequestProxyRepository.class,
        com.verifier.domains.fulfillment.repository.DispatchableDeliveryViewRepository.class})
public class FulfillmentMongoConfiguration {
    @Bean
    @Qualifier("fulfillmentMongoTemplate")
    public MongoTemplate fulfillmentMongoTemplate(@Qualifier("fulfillmentMongoDbFactory") MongoDbFactory factory,@Qualifier("fulfillmentMappingMongoConverter") MappingMongoConverter mappingMongoConverter) {
        System.out.println("###INside fulfillmentMongoTemplate creation " + factory.getDb().getName());
        MongoTemplate mongoTemplate = new MongoTemplate(factory,mappingMongoConverter);
        return mongoTemplate;
    }

    @Bean //(name="ProductMongo")
    @Qualifier("fulfillmentMongo")
    public MongoClient fulfillmentMongo(@Qualifier("fulfillmentMongoClientURI") MongoClientURI mongoClientURI) throws UnknownHostException {
        System.out.println("###INside MOngoClient creation");
        return new MongoClient(mongoClientURI);
    }
    @Bean
    @Qualifier("fulfillmentMongoClientURI")
    public MongoClientURI fulfillmentMongoClientURI(@Value("${view.db.fulfillment.host}") String host, @Value("${view.db.fulfillment.port}") int port,
                                         @Value("${view.db.fulfillment.name}") String dbName,
                                         @Value("${affaince.db.username}") String username,
                                         @Value("${affaince.db.password}") String password) {
        final MongoClientOptions options = MongoClientOptions.builder()
                .threadsAllowedToBlockForConnectionMultiplier(2)
                .connectionsPerHost(10)
                .connectTimeout(15000)
                .maxWaitTime(15000)
                .socketTimeout(15000)
                .heartbeatConnectTimeout(5000)
                .minHeartbeatFrequency(1000)
                .build();

        return new MongoClientURI("mongodb://" /*+ username + ":" + password + "@" */
                + host
                + ":"
                + port
                + "/" +
                dbName,MongoClientOptions.builder(options));
    }
    @Bean
    @Qualifier("fulfillmentMongoDbFactory")
    public MongoDbFactory fulfillmentMongoDbFactory(@Value("${view.db.fulfillment.host}") String host, @Value("${view.db.fulfillment.port}") int port,
                                                @Value("${view.db.fulfillment.name}") String dbName) throws UnknownHostException {
        System.out.println("###INside mongoDbFactory creation");
        return new SimpleMongoDbFactory(new MongoClient(new ServerAddress(host, port)), dbName);
    }

    @Bean //(name = "PaymentsCustomConversions")
    @Qualifier("fulfillmentCustomConversions")
    public CustomConversions fulfillmentCustomConversions() {
        List<Converter<?, ?>> converters = new ArrayList<Converter<?, ?>>();
        return new CustomConversions(converters);
    }

    @Bean //(name = "PaymentsMappingMongoConverter")
    @Qualifier("fulfillmentMappingMongoConverter")
    public MappingMongoConverter fulfillmentMappingMongoConverter(@Qualifier("fulfillmentMongoDbFactory") MongoDbFactory mongoDbFactory,
                                                               @Qualifier("fulfillmentCustomConversions") CustomConversions customConversions) {
        MongoMappingContext mappingContext = new MongoMappingContext();
        DbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoDbFactory);
        MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, mappingContext);
        converter.setCustomConversions(customConversions);
        converter.afterPropertiesSet();
        return converter;
    }

}
