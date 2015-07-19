package com.affaince.subscription.subscriptionableitem.registration.configuration;

import com.affaince.subscription.configuration.Default;
import com.affaince.subscription.subscriptionableitem.registration.command.domain.SubscriptionableItem;
import com.affaince.subscription.subscriptionableitem.registration.command.event.CreateSubscriptionableItemEvent;
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
    public Repository<SubscriptionableItem> createRepository (DisruptorCommandBus commandBus) {
        Repository <SubscriptionableItem> repository = commandBus.createRepository(new GenericAggregateFactory<>(SubscriptionableItem.class));
        return repository;
    }

    @Override
    protected Map<String, String> types () {
        return new HashMap<String, String>() {{
            put("com.affaince.subscription.subscriptionableitem.registration.command.event.CreateSubscriptionableItemEvent", "CreateSubscriptionableItemEvent");
        }};
    }
}
