package com.affaince.subscription.consumerbasket.configuration;

import com.affaince.subscription.configuration.Default;
import com.affaince.subscription.consumerbasket.command.domain.Basket;
import com.affaince.subscription.consumerbasket.command.domain.BasketRule;
import com.affaince.subscription.consumerbasket.command.domain.ConsumerBasket;
import com.affaince.subscription.consumerbasket.command.event.BasketDispatchedStatusEvent;
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

    @Bean
    public Repository<BasketRule> createBasketRuleRepository(DisruptorCommandBus commandBus) {
        Repository<BasketRule> repository = commandBus.createRepository(new GenericAggregateFactory<>(BasketRule.class));
        return repository;
    }

    @Bean
    public Repository<Basket> createBasketRepository(DisruptorCommandBus commandBus) {
        Repository<Basket> repository = commandBus.createRepository(new GenericAggregateFactory<>(Basket.class));
        return repository;
    }

    @Override
    protected Map<String, String> types() {
        return new HashMap<String, String>() {{
            put("com.affaince.subscription.integration.command.event.basketdispatch.BasketDispatchedStatusEvent", BasketDispatchedStatusEvent.class.getName());

        }};
    }
}
