package com.affaince.subscription.delivery.configuration;

import com.affaince.subscription.configuration.Default;
import com.affaince.subscription.delivery.build.DeliveryRetriever;
import com.mongodb.Mongo;
import org.apache.camel.builder.RouteBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rbsavaliya on 25-11-2016.
 */
@Configuration
@ComponentScan("com.affaince")
public class Axon extends Default {

    @Bean
    public MongoDbFactory mongoDbFactory(Mongo mongo, @Value("${view.db.name}") String dbName) throws Exception {
        return new SimpleMongoDbFactory(mongo, dbName);
    }

    @Bean
    public MappingMongoConverter mappingMongoConverter(Mongo mongo, MongoDbFactory mongoDbFactory) throws Exception {
        MongoMappingContext mappingContext = new MongoMappingContext();
        DbRefResolver dbRefResolver = new DefaultDbRefResolver(mongoDbFactory);
        MappingMongoConverter converter = new MappingMongoConverter(dbRefResolver, mappingContext);
        converter.setCustomConversions(customConversionsForProductVersionId());
        return converter;
    }

    @Bean
    public DeliveryRetriever deliveryRetriever() {
        return new DeliveryRetriever();
    }

    @Bean
    public RouteBuilder routes() {
        return new RouteBuilder() {
            public void configure() throws Exception {

                //job for calculating build  and pseudoActuals for eligible products.
                from("timer:start?repeatCount=1")
                        .routeId("deliveryRetriever")
                        .to("bean:deliveryRetriever");
            }
        };
    }

    @Override
    @Bean(name = "types")
    protected Map<String, String> types() {
        return new HashMap<String, String>();
    }
}
