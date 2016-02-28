package com.affaince.subscription.product.registration.configuration;

import com.affaince.subscription.configuration.RabbitMQConfiguration;
import com.affaince.subscription.product.registration.command.domain.Product;
import com.affaince.subscription.product.registration.command.event.*;
import com.affaince.subscription.common.idconverter.ProductVersionIdReaderConverter;
import com.affaince.subscription.common.idconverter.ProductVersionIdWriterConverter;
import com.mongodb.Mongo;
import org.axonframework.commandhandling.disruptor.DisruptorCommandBus;
import org.axonframework.eventsourcing.GenericAggregateFactory;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.data.mongodb.core.convert.CustomConversions;
import org.springframework.data.mongodb.core.convert.DbRefResolver;
import org.springframework.data.mongodb.core.convert.DefaultDbRefResolver;
import org.springframework.data.mongodb.core.convert.MappingMongoConverter;
import org.springframework.data.mongodb.core.mapping.MongoMappingContext;
import org.springframework.jms.annotation.EnableJms;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by rbsavaliya on 19072015.
 */
@Configuration
@EnableJms
public class Axon extends RabbitMQConfiguration {

    @Bean
    public Repository<Product> createRepository(DisruptorCommandBus commandBus) {

        Repository<Product> repository = commandBus.createRepository(new GenericAggregateFactory<>(Product.class));
        return repository;
    }

    public
    @Bean
    MongoDbFactory mongoDbFactory(Mongo mongo, @Value("${view.db.name}") String dbName) throws Exception {
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

    @Override
    protected Map<String, String> types() {
        return new HashMap<String, String>() {{
            put("com.affaince.subscription.integration.command.event.shoppingitemreceipt.ProductReceivedForRegistrationEvent", ProductRegisteredEvent.class.getName());
            put("com.affaince.subscription.integration.command.event.productstatus.ProductStatusReceivedEvent", ProductStatusReceivedEvent.class.getName());
            put("com.affaince.subscription.integration.command.event.forecast.ShoppingItemForecastReceivedEvent", ProductForecastReceivedEvent.class.getName());
            put("com.affaince.subscription.integration.command.event.productstatus.ProductStatusReceivedEvent", ProductStatusReceivedEvent.class.getName());
            put("com.affaince.subscription.subscriber.command.event.ProductsStatisticsCalculatedEvent", ProductsStatisticsCalculatedEvent.class.getName());
            put("com.affaince.subscription.subscriber.command.event.DeliveryCreatedEvent", DeliveryCreatedEvent.class.getName());

        }};
    }
}