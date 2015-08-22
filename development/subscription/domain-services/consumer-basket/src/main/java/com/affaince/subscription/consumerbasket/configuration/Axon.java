package com.affaince.subscription.consumerbasket.configuration;

import com.affaince.subscription.configuration.Default;
import com.affaince.subscription.consumerbasket.command.domain.ConsumerBasket;
import org.axonframework.commandhandling.disruptor.DisruptorCommandBus;
import org.axonframework.eventsourcing.GenericAggregateFactory;
import org.axonframework.repository.Repository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rbsavaliya on 19-07-2015.
 */
@Configuration
public class Axon extends Default {

    @Bean
    public Repository<ConsumerBasket> createRepository(DisruptorCommandBus commandBus) {
        Repository<ConsumerBasket> repository = commandBus.createRepository(new GenericAggregateFactory<>(ConsumerBasket.class));
        return repository;
    }

    @Override
    protected Map<String, String> types() {
        return new HashMap<String, String>() {{
        }};
    }
}
