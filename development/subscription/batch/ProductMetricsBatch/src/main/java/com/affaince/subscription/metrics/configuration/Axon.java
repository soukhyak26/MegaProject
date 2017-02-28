package com.affaince.subscription.metrics.configuration;

import com.affaince.subscription.configuration.Default;
import com.affaince.subscription.metrics.build.ProductsRetriever;
import com.affaince.subscription.metrics.client.ProductMetricsCalculator;
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
    public ProductsRetriever productsRetriever() {
        return new ProductsRetriever();
    }

    @Bean
    public ProductMetricsCalculator productMetricsCalculator() {
        return new ProductMetricsCalculator();
    }

    @Bean
    public RouteBuilder routes() {
        return new RouteBuilder() {
            public void configure() throws Exception {
                from("{{subscription.metrics.timer.expression}}")
                        .routeId("MetricsCalculator")
                        .to("bean:productsRetriever")
                        .split(body())
                        .to("bean:productMetricsCalculator");
            }
        };
    }

    @Override
    @Bean(name = "types")
    protected Map<String, String> types() {
        return new HashMap<String, String>();
    }
}
