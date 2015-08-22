package com.affaince.subscription.inventory.configuration;

import com.affaince.subscription.configuration.Default;
import com.affaince.subscription.inventory.query.event.BasketDispatchedStatusEvent;
import com.affaince.subscription.inventory.command.domain.SubscriptionBasket;
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
    public Repository<SubscriptionBasket> createRepository(DisruptorCommandBus commandBus) {

        Repository<SubscriptionBasket> repository = commandBus.createRepository(new GenericAggregateFactory<>(SubscriptionBasket.class));
        return repository;
    }

    @Override
    protected Map<String, String> types() {
        return new HashMap<String, String>() {{
            put("com.affaince.subscription.inventory.query.event.BasketDispatchedStatusEvent", BasketDispatchedStatusEvent.class.getName());
        }};
    }
}
