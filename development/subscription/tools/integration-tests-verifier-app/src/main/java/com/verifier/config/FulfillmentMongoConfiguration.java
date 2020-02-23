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
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.net.UnknownHostException;

@Configuration
@EnableMongoRepositories(mongoTemplateRef = "fulfillmentMongoTemplate", basePackageClasses = {ProductOrderViewRepository.class,
        ProductStockProvisionRequestProxyRepository.class,
        DispatchableDeliveryViewRepository.class})
public class FulfillmentMongoConfiguration {
    @Qualifier("fulfillmentMongoTemplate")
    public MongoTemplate productMongoTemplate(@Qualifier("fulfillmentMongoDbFactory") MongoDbFactory factory,@Qualifier("fulfillmentMappingMongoConverter") MappingMongoConverter mappingMongoConverter) {
        System.out.println("###INside fulfillmentMongoTemplate creation " + factory.getDb().getName());
        MongoTemplate mongoTemplate = new MongoTemplate(factory,mappingMongoConverter);
        return mongoTemplate;
    }

    @Bean //(name="ProductMongo")
    @Qualifier("fulfillmentMongo")
    public MongoClient mongo(@Qualifier("fulfillmentMongoClientURI") MongoClientURI mongoClientURI) throws UnknownHostException {
        System.out.println("###INside MOngoClient creation");
        return new MongoClient(mongoClientURI);
    }

    @Qualifier("fulfillmentClientURI")
    public MongoClientURI mongoClientURI(@Value("${view.db.fulfillment.host}") String host, @Value("${view.db.fulfillment.port}") int port,
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

    @Qualifier("fulfillmentMongoDbFactory")
    public MongoDbFactory fulfillmentMongoDbFactory(@Value("${view.db.fulfillment.host}") String host, @Value("${view.db.fulfillment.port}") int port,
                                                @Value("${view.db.fulfillment.name}") String dbName) throws UnknownHostException {
        System.out.println("###INside mongoDbFactory creation");
        return new SimpleMongoDbFactory(new MongoClient(new ServerAddress(host, port)), dbName);
    }

}
