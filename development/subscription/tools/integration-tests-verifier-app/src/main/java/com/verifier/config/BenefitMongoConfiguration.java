package com.verifier.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;
import com.verifier.domains.benefits.repository.BenefitsBenefitViewRepository;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.net.UnknownHostException;

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

} 
