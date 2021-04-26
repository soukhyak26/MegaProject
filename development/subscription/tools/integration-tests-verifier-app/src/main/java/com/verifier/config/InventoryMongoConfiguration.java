package com.verifier.config;

import com.affaince.subscription.common.idconverter.LocalDateTimeToStringConverter;
import com.affaince.subscription.common.idconverter.LocalDateToStringConverter;
import com.affaince.subscription.common.idconverter.StringToLocalDateConverter;
import com.affaince.subscription.common.idconverter.StringToLocalDateTimeConverter;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;
import com.verifier.domains.inventory.repository.*;
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
@EnableMongoRepositories(mongoTemplateRef ="inventoryMongoTemplate", basePackageClasses = {
        InventoryInOutRecordsViewRepository.class,
        InventoryProvisionCalendarViewRepository.class,
        ItemDispatchRecordViewRepository.class,
        ProductInventoryViewRepository.class,
        ProductStockOrderViewRepository.class,
        ProductViewRepository.class,
        SupplierViewRepository.class,
        TaggedPriceVersionsViewRepository.class
        })
public class InventoryMongoConfiguration {
    @Bean
    @Qualifier("inventoryMongoTemplate")
    public MongoTemplate inventoryMongoTemplate(@Qualifier("inventoryMongoDbFactory") MongoDbFactory factory,@Qualifier("inventoryMappingMongoConverter") MappingMongoConverter mappingMongoConverter) {
        System.out.println("###INside inventoryMongoTemplate creation " + factory.getDb().getName());
        MongoTemplate mongoTemplate = new MongoTemplate(factory,mappingMongoConverter);
        return mongoTemplate;
    }

    @Bean
    @Qualifier("inventoryMongo")
    public MongoClient mongo(@Qualifier("inventoryMongoClientURI") MongoClientURI mongoClientURI) throws UnknownHostException {
        System.out.println("###INside MOngoClient creation");
        return new MongoClient(mongoClientURI);
    }

    @Bean //(name="inventoryMongoClientURI")
    @Qualifier("inventoryMongoClientURI")
    public MongoClientURI mongoClientURI(@Value("${view.db.inventory.host}") String host, @Value("${view.db.inventory.port}") int port,
                                         @Value("${view.db.inventory.name}") String dbName,
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
    @Qualifier("inventoryMongoDbFactory")
    public MongoDbFactory mongoDbFactory(@Value("${view.db.inventory.host}") String host, @Value("${view.db.inventory.port}") int port,
                                         @Value("${view.db.inventory.name}") String dbName) throws UnknownHostException {
        System.out.println("###INside mongoDbFactory creation");
        return new SimpleMongoDbFactory(new MongoClient(new ServerAddress(host, port)), dbName);
    }
    @Bean
    @Qualifier("inventoryMappingMongoConverter")
    public MappingMongoConverter inventoryMappingMongoConverter(@Qualifier("inventoryMongoDbFactory")MongoDbFactory mongoDbFactory,
                                                       @Qualifier("inventoryCustomConversions") CustomConversions customConversions) throws Exception {
        MongoMappingContext mappingContext = new MongoMappingContext();
        DbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoDbFactory);
        MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, mappingContext);
        converter.setCustomConversions(customConversions);
        converter.afterPropertiesSet();
        return converter;
    }

    @Bean
    @Qualifier("inventoryCustomConversions")
    public CustomConversions inventoryCustomConversions(){
        List<Converter<?, ?>> converters = new ArrayList<Converter<?, ?>>();
        converters.add(new LocalDateToStringConverter());
        converters.add(new LocalDateTimeToStringConverter());
        converters.add(new StringToLocalDateConverter());
        converters.add(new StringToLocalDateTimeConverter());

        return new CustomConversions(converters);
    }

} 
