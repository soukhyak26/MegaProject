package com.verifier.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;
import com.verifier.domains.product.repository.ProductBusinessAccountViewRepository;
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
@EnableMongoRepositories(basePackageClasses = {ProductBusinessAccountViewRepository.class,
com.verifier.domains.product.repository.CategoryDetailsViewRepository.class,
com.verifier.domains.product.repository.FixedExpensePerProductViewRepository.class,
        com.verifier.domains.product.repository.PriceBucketTransactionViewRepository.class,
        com.verifier.domains.product.repository.PriceBucketViewRepository.class,
        com.verifier.domains.product.repository.ProductActivationStatusViewPagingRepository.class,
        com.verifier.domains.product.repository.ProductActivationStatusViewRepository.class,
        com.verifier.domains.product.repository.ProductActualMetricsViewRepository.class,
        com.verifier.domains.product.repository.ProductActualsViewRepository.class,
        com.verifier.domains.product.repository.ProductConfigurationViewRepository.class,
        com.verifier.domains.product.repository.ProductForecastTrendViewRepository.class,
        com.verifier.domains.product.repository.ProductForecastViewRepository.class,
        com.verifier.domains.product.repository.ProductPseudoActualsViewRepository.class,
        com.verifier.domains.product.repository.ProductViewPagingRepository.class,
        com.verifier.domains.product.repository.ProductViewRepository.class,
        com.verifier.domains.product.repository.RecommendedPriceBucketViewRepository.class,
        com.verifier.domains.product.repository.TaggedPriceVersionsViewRepository.class,
        com.verifier.domains.product.repository.TargetSettingViewRepository.class,
        com.verifier.domains.product.repository.VariableExpensePerProductViewRepository.class})
public class ProductMongoConfiguration {
    @Bean
    @Qualifier("ProductMongoTemplate")
    public MongoTemplate mongoTemplate(@Qualifier("ProductMongoDbFactory") MongoDbFactory factory) {
        System.out.println("###INside MOngoTemplate creation");
        MongoTemplate mongoTemplate = new MongoTemplate(factory);
        return mongoTemplate;
    }

    @Bean
    @Qualifier("ProductMongo")
    public MongoClient mongo(@Qualifier("ProductMongoClientURI") MongoClientURI mongoClientURI) throws UnknownHostException {
        System.out.println("###INside MOngoClient creation");
        return new MongoClient(mongoClientURI);
    }

    @Bean
    @Qualifier("ProductMongoClientURI")
    public MongoClientURI mongoClientURI(@Value("${view.db.product.host}") String host, @Value("${view.db.product.port}") int port,
                                         @Value("${view.db.product.name}") String dbName,
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
    @Qualifier("ProductMongoDbFactory")
    public MongoDbFactory mongoDbFactory(@Value("${view.db.product.host}") String host, @Value("${view.db.product.port}") int port,
                                         @Value("${view.db.product.name}") String dbName) throws UnknownHostException {
        System.out.println("###INside mongoDbFactory creation");
        return new SimpleMongoDbFactory(new MongoClient(new ServerAddress(host, port)), dbName);
    }
} 
