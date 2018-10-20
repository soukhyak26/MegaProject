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
@EnableMongoRepositories(mongoTemplateRef="benefitsMongoTemplate",basePackageClasses = {BenefitsBenefitViewRepository.class})
public class BenefitMongoConfiguration {
    @Bean //(name="benefitsMongoTemplate")
    @Qualifier("benefitsMongoTemplate")
    @Primary
    public MongoTemplate benefitsMongoTemplate(@Qualifier("benefitsMongoDbFactory") MongoDbFactory factory,@Qualifier("BenefitMappingMongoConverter")MappingMongoConverter mappingMongoConverter) {
        System.out.println("###INside benefitsMongoTemplate creation " + factory.getDb().getName());
        MongoTemplate benefitsMongoTemplate = new MongoTemplate(factory,mappingMongoConverter);
        return benefitsMongoTemplate;
    }

    @Bean //(name="BenefitsMongo")
    @Qualifier("BenefitsMongo")
    public MongoClient benefitsMongo(@Qualifier("BenefitsMongoClientURI") MongoClientURI mongoClientURI) throws UnknownHostException {
        System.out.println("###INside MOngoClient creation");
        return new MongoClient(mongoClientURI);
    }

    @Bean //(name="BenefitsMongoClientURI")
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

    @Bean //(name="benefitsMongoDbFactory")
    @Qualifier("benefitsMongoDbFactory")
    public MongoDbFactory benefitsMongoDbFactry(@Value("${view.db.benefits.host}") String host, @Value("${view.db.benefits.port}") int port,
                                                 @Value("${view.db.benefits.name}") String dbName) {
        System.out.println("###INside mongoDbFactory creation");
        return new SimpleMongoDbFactory(new MongoClient(new ServerAddress(host, port)), dbName);
    }

    @Bean //(name="BenefitsCustomConversions")
    @Qualifier("BenefitsCustomConversions")
    @Primary
    public CustomConversions benefitsCustomConversions(){
        List<Converter<?, ?>> converters = new ArrayList<Converter<?, ?>>();
        return new CustomConversions(converters);
    }

    @Bean //(name="BenefitMappingMongoConverter")
    @Qualifier("BenefitMappingMongoConverter")
    public MappingMongoConverter benefitsMappingMongoConverter(@Qualifier("benefitsMongoDbFactory") MongoDbFactory mongoDbFactory,
                                                       @Qualifier("BenefitsCustomConversions") CustomConversions customConversions) {
        MongoMappingContext mappingContext = new MongoMappingContext();
        DbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoDbFactory);
        MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, mappingContext);
        converter.setCustomConversions(customConversions);
        converter.afterPropertiesSet();
        return converter;
    }

} 
