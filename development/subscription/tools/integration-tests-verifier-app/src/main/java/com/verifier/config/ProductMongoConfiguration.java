package com.verifier.config;

import com.affaince.subscription.common.idconverter.*;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;
import com.verifier.domains.product.repository.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
        CategoryDetailsViewRepository.class,
        FixedExpensePerProductViewRepository.class,
        PriceBucketTransactionViewRepository.class,
        PriceBucketViewRepository.class,
        ProductActualMetricsViewRepository.class,
        ProductActualsViewRepository.class,
        ProductConfigurationViewRepository.class,
        ProductForecastTrendViewRepository.class,
        ProductForecastViewRepository.class,
        ProductPseudoActualsViewRepository.class,
        ProductViewPagingRepository.class,
        ProductViewRepository.class,
        RecommendedPriceBucketViewRepository.class,
        TaggedPriceVersionsViewRepository.class,
        TargetSettingViewRepository.class,
        VariableExpensePerProductViewRepository.class,
        ProductAnalyserViewRepository.class,
        ProductInventoryViewRepository.class})
public class ProductMongoConfiguration {
    @Bean //(name="productMongoTemplate")
    @Qualifier("productMongoTemplate")
    public MongoTemplate productMongoTemplate(@Qualifier("productMongoDbFactory") MongoDbFactory factory,@Qualifier("ProductMappingMongoConverter") MappingMongoConverter mappingMongoConverter) {
        System.out.println("###INside ProductMongoTemplate creation " + factory.getDb().getName());
        MongoTemplate mongoTemplate = new MongoTemplate(factory,mappingMongoConverter);
        return mongoTemplate;
    }

    @Bean //(name="ProductMongo")
    @Qualifier("ProductMongo")
    public MongoClient mongo(@Qualifier("ProductMongoClientURI") MongoClientURI mongoClientURI) throws UnknownHostException {
        System.out.println("###INside MOngoClient creation");
        return new MongoClient(mongoClientURI);
    }

    @Bean //(name="ProductMongoClientURI")
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

    @Bean //(name="productMongoDbFactory")
    @Qualifier("productMongoDbFactory")
    public MongoDbFactory productMongoDbFactory(@Value("${view.db.product.host}") String host, @Value("${view.db.product.port}") int port,
                                                @Value("${view.db.product.name}") String dbName) throws UnknownHostException {
        System.out.println("###INside mongoDbFactory creation");
        return new SimpleMongoDbFactory(new MongoClient(new ServerAddress(host, port)), dbName);
    }

    @Bean //(name = "ProductCustomConversions")
    @Qualifier("ProductCustomConversions")
    public CustomConversions productCustomConversions(){
        List<Converter<?, ?>> converters = new ArrayList<Converter<?, ?>>();
        converters.add(new LocalDateToStringConverter());
        converters.add(new LocalDateTimeToStringConverter());
        converters.add(new StringToLocalDateConverter());
        converters.add(new StringToLocalDateTimeConverter());
        return new CustomConversions(converters);
    }

    @Bean //(name="ProductMappingMongoConverter")
    @Qualifier("ProductMappingMongoConverter")
    public MappingMongoConverter productMappingMongoConverter(@Qualifier("productMongoDbFactory") MongoDbFactory mongoDbFactory,
                                                       @Qualifier("ProductCustomConversions") CustomConversions customConversions) {
        MongoMappingContext mappingContext = new MongoMappingContext();
        DbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoDbFactory);
        MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, mappingContext);
        converter.setCustomConversions(customConversions);
        converter.afterPropertiesSet();
        return converter;
    }

}
