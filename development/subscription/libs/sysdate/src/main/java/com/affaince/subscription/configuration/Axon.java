package com.affaince.subscription.configuration;

import com.mongodb.Mongo;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;

import java.net.UnknownHostException;

/**
 * Created by rbsavaliya on 17-08-2016.
 */
public class Axon {

    @Bean
    public Mongo mongo(@Value("${view.db.host}") String host, @Value("${view.db.port}") int port) {
        try {
            return new Mongo(host, port);
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Bean
    public MongoDbFactory mongoDbFactory(Mongo mongo, @Value("${view.db.name}") String dbName) throws Exception {
        return new SimpleMongoDbFactory(mongo, dbName);
    }
}
