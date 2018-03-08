package com.test.verifier.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.ServerAddress;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

import java.net.UnknownHostException;
@Configuration
@EnableMongoRepositories(basePackages = "com.test.verifier.domain.business")
public class BusinessMongoConfiguration {
    @Bean(name = "axonmongo")
    public MongoTemplate mongoTemplate(MongoClient mongo, @Value("${view.db.name}") String dbName) {
        MongoTemplate mongoTemplate =
                new MongoTemplate(mongo, dbName);
        return mongoTemplate;
    }

    @Bean
    public MongoClient mongo(MongoClientURI mongoClientURI) throws UnknownHostException {
        return new MongoClient(mongoClientURI);
    }

    @Bean
    public MongoClientURI mongoClientURI(@Value("${view.db.host}") String host, @Value("${view.db.port}") int port,
                                         @Value("${view.db.name}") String dbName,
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
    public MongoDbFactory mongoDbFactory(@Value("${view.db.host}") String host, @Value("${view.db.port}") int port,
                                         @Value("${view.db.name}") String dbName) throws UnknownHostException {
        return new SimpleMongoDbFactory(new MongoClient(new ServerAddress(host, port)), dbName);
    }
} 
