package com.affaince.subscription.benefits.configuration;

import com.affaince.subscription.benefits.command.domain.Benefit;
import com.affaince.subscription.configuration.Default;
import com.mongodb.Mongo;
import org.axonframework.commandhandling.disruptor.DisruptorCommandBus;
import org.axonframework.commandhandling.model.Repository;
import org.axonframework.eventsourcing.GenericAggregateFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.jms.annotation.EnableJms;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rbsavaliya on 19-07-2015.
 */
@Configuration
@EnableJms
public class Axon extends Default {

    @Bean
    public Repository<Benefit> createRepository(DisruptorCommandBus commandBus) {

        Repository<Benefit> repository = commandBus.createRepository(new GenericAggregateFactory<>(Benefit.class));
        return repository;
    }

    @Override
    @Bean(name = "types")
    protected Map<String, String> types() {
        return new HashMap<String, String>() {{
            put("com.affaince.subscription.benefits.command.event.*", "");
        }};
    }
}
