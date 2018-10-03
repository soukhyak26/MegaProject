package com.verifier.config;

import com.affaince.subscription.common.idconverter.*;
import com.mongodb.Mongo;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;
import com.verifier.domains.benefits.repository.BenefitsBenefitViewRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
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
@EnableMongoRepositories(mongoTemplateRef="benefitsMongoTemplate",  basePackageClasses = {BenefitsBenefitViewRepository.class})
public class BenefitMongoConfiguration {
    @Bean
    @Qualifier("benefitsMongoTemplate")
    public MongoTemplate benefitsMongoTemplate(@Qualifier("benefitsMongoDbFactory") MongoDbFactory factory) {
        System.out.println("###INside benefitsMongoTemplate creation " + factory.getDb().getName());
        MongoTemplate benefitsMongoTemplate = new MongoTemplate(factory);
        return benefitsMongoTemplate;
    }

    @Bean
    @Qualifier("BenefitsMongo")
    public MongoClient benefitsMongo(@Qualifier("BenefitsMongoClientURI") MongoClientURI mongoClientURI) throws UnknownHostException {
        System.out.println("###INside MOngoClient creation");
        return new MongoClient(mongoClientURI);
    }

    @Bean
    @Qualifier("BenefitsMongoClientURI")
    public MongoClientURI benefitsMongoClientURI(@Value("${view.db.benefits.host}") String host, @Value("${view.db.benefits.port}") int port,
                                                 @Value("${view.db.benefits.name}") String dbName) {
        return new MongoClientURI("mongodb://" /*+ username + ":" + password + "@" */
                + host
                + ":"
                + port
                + "/" +
                dbName);
    }

    @Bean
    @Qualifier("benefitsMongoDbFactory")
    public MongoDbFactory benefitsMongoDbFactry(@Value("${view.db.benefits.host}") String host, @Value("${view.db.benefits.port}") int port,
                                                 @Value("${view.db.benefits.name}") String dbName) throws UnknownHostException {
        System.out.println("###INside mongoDbFactory creation");
        return new SimpleMongoDbFactory(new MongoClient(new ServerAddress(host, port)), dbName);
    }

    @Bean
    @Qualifier("BenefitsCustomConversions")
    public CustomConversions benefitsCustomConversions(){
        List<Converter<?, ?>> converters = new ArrayList<Converter<?, ?>>();
        converters.add(new LocalDateToStringConverter());
        converters.add(new LocalDateTimeToStringConverter());
        converters.add(new StringToLocalDateConverter());
        converters.add(new StringToLocalDateTimeConverter());
        return new CustomConversions(converters);
    }

    @Bean
    public MappingMongoConverter mappingMongoConverter(@Qualifier("BenefitsMongo")Mongo mongo,
                                                       @Qualifier("benefitsMongoDbFactory") MongoDbFactory mongoDbFactory,
                                                       @Qualifier("BenefitsCustomConversions") CustomConversions customConversions) {
        MongoMappingContext mappingContext = new MongoMappingContext();
        DbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoDbFactory);
        MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, mappingContext);
        converter.setCustomConversions(customConversions);
        return converter;
    }

} 
