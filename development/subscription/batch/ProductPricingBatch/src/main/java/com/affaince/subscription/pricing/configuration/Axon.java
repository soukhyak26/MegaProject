package com.affaince.subscription.pricing.configuration;

import com.affaince.subscription.common.publisher.GenericEventPublisher;
import com.affaince.subscription.configuration.RabbitMQConfiguration;
import com.affaince.subscription.pricing.batchprocessor.ProductStatisticsItemReader;
import com.affaince.subscription.pricing.query.repository.PriceBucketViewRepository;
import com.affaince.subscription.pricing.query.repository.ProductConfigurationViewRepository;
import com.affaince.subscription.pricing.query.repository.ProductStatisticsViewRepository;
import com.affaince.subscription.pricing.query.repository.ProductViewRepository;
import com.affaince.subscription.pricing.query.view.ProductStatisticsView;
import com.affaince.subscription.pricing.query.view.ProductVersionId;
import com.mongodb.MongoClient;
import org.axonframework.eventhandling.EventTemplate;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.authentication.UserCredentials;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mandark on 19-07-2015.
 */
@Configuration
public class Axon extends RabbitMQConfiguration {


    public static final String OVERRIDEN_BY_EXPRESSION_VALUE = "overriden by expression value";

    @Bean
    public GenericEventPublisher publisher(EventTemplate template) {
        return new GenericEventPublisher(template);
    }

    @Bean
    public MongoDbFactory mongoDbFactory() throws Exception {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        UserCredentials userCredentials = new UserCredentials("", "");
        return new SimpleMongoDbFactory(mongoClient, "concretepage", userCredentials);
    }

    @Bean
    public MongoTemplate mongoTemplate() throws Exception {
        MongoTemplate mongoTemplate = new MongoTemplate(mongoDbFactory());
        return mongoTemplate;
    }

    @Autowired
    ProductViewRepository productViewRepository;
    @Autowired
    ProductConfigurationViewRepository productConfigurationViewRepository;
    @Autowired
    PriceBucketViewRepository priceBucketViewRepository;
    @Autowired
    ProductStatisticsViewRepository productStatisticsViewRepository;

    @Bean(name = "productStatisticsViewReader")
    public RepositoryItemReader ProductStatisticsViewItemReader(ProductStatisticsViewRepository productStatisticsViewRepository) {
        RepositoryItemReader itemReader = new RepositoryItemReader();
        itemReader.setRepository(productStatisticsViewRepository);
        itemReader.setMethodName("findAll");
        return itemReader;
    }

    @Bean
    ItemReader ProductStatisticsItemReader(@Qualifier("productStatisticsViewReader") RepositoryItemReader ProductStatisticsViewItemReader){
        return new ProductStatisticsItemReader(ProductStatisticsViewItemReader);
    }

    @Bean(name = "productViewReader")
    public RepositoryItemReader ProductViewItemReader(ProductViewRepository productViewRepository) {
        RepositoryItemReader itemReader = new RepositoryItemReader();
        itemReader.setRepository(productViewRepository);
        itemReader.setMethodName("findById");
        return itemReader;
    }

    @Bean(name = "productConfigurationViewReader")
    public RepositoryItemReader ProductConfigurationViewItemReader(ProductConfigurationViewRepository productConfigurationViewRepository) {
        RepositoryItemReader itemReader = new RepositoryItemReader();
        itemReader.setRepository(productConfigurationViewRepository);
        itemReader.setMethodName("findAll");
        return itemReader;
    }

    @Bean(name = "priceBucketViewReader")
    public RepositoryItemReader PriceBucketViewItemReader(PriceBucketViewRepository priceBucketViewRepository) {
        RepositoryItemReader itemReader = new RepositoryItemReader();
        itemReader.setRepository(priceBucketViewRepository);
        itemReader.setMethodName("findAll");
        return itemReader;
    }


}
