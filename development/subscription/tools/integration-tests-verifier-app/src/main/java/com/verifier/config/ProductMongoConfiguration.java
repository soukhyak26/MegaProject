package com.verifier.config;

import com.affaince.subscription.common.idconverter.*;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;
import com.verifier.domains.product.repository.ProductBusinessAccountViewRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.convert.support.DefaultConversionService;
import org.springframework.core.convert.support.GenericConversionService;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.CustomConversions;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import javax.annotation.PostConstruct;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableMongoRepositories(mongoTemplateRef = "productMongoTemplate", basePackageClasses = {ProductBusinessAccountViewRepository.class,
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
        com.verifier.domains.product.repository.VariableExpensePerProductViewRepository.class,
        com.verifier.domains.product.repository.ProductAnalyserViewRepository.class,
        com.verifier.domains.product.repository.ProductInventoryViewRepository.class})
public class ProductMongoConfiguration {
    @Bean
    @Qualifier("productMongoTemplate")
    public MongoTemplate productMongoTemplate(@Qualifier("productMongoDbFactory") MongoDbFactory factory) {
        System.out.println("###INside ProductMongoTemplate creation " + factory.getDb().getName());
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
    @Qualifier("productMongoDbFactory")
    public MongoDbFactory productMongoDbFactory(@Value("${view.db.product.host}") String host, @Value("${view.db.product.port}") int port,
                                                @Value("${view.db.product.name}") String dbName) throws UnknownHostException {
        System.out.println("###INside mongoDbFactory creation");
        return new SimpleMongoDbFactory(new MongoClient(new ServerAddress(host, port)), dbName);
    }

    @Bean
    @Qualifier("ProductCustomConversions")
    @Primary
    public CustomConversions productCustomConversions(){
        List<Converter<?, ?>> converters = new ArrayList<Converter<?, ?>>();
        converters.add(new LocalDateToStringConverter());
        converters.add(new LocalDateTimeToStringConverter());
        converters.add(new StringToLocalDateConverter());
        converters.add(new StringToLocalDateTimeConverter());
        return new CustomConversions(converters);
    }

    @Bean
    @Qualifier("ProductMappingMongoConverter")
    public MappingMongoConverter mappingMongoConverter(@Qualifier("ProductMongo")Mongo mongo,
                                                       @Qualifier("productMongoDbFactory") MongoDbFactory mongoDbFactory,
                                                       @Qualifier("ProductCustomConversions") CustomConversions customConversions) {
        MongoMappingContext mappingContext = new MongoMappingContext();
        DbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoDbFactory);
        MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, mappingContext);
        converter.setCustomConversions(customConversions);
        return converter;
    }

}
