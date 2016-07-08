package com.affaince.subscription.subscriber.configuration;

import com.affaince.subscription.configuration.ActiveMQConfiguration;
import com.affaince.subscription.subscriber.command.domain.BasketRule;
import com.affaince.subscription.subscriber.command.domain.DeliveryChargesRule;
import com.affaince.subscription.subscriber.command.domain.Subscriber;
import com.affaince.subscription.subscriber.command.domain.Subscription;
import com.affaince.subscription.subscriber.command.event.BenefitAddedEvent;
import com.affaince.subscription.subscriber.command.event.PaymentReceivedFromSourceEvent;
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
public class Axon extends ActiveMQConfiguration {

    @Bean
    public Repository<Subscriber> createRepository(DisruptorCommandBus commandBus) {

        Repository<Subscriber> repository = commandBus.createRepository(new GenericAggregateFactory<>(Subscriber.class));
        return repository;
    }

    @Bean
    public Repository<Subscription> createSubscriptionRepository(DisruptorCommandBus commandBus) {

        Repository<Subscription> repository = commandBus.createRepository(new GenericAggregateFactory<>(Subscription.class));
        return repository;
    }

    @Bean
    public Repository<BasketRule> createBasketRuleRepository(DisruptorCommandBus commandBus) {
        Repository<BasketRule> repository = commandBus.createRepository(new GenericAggregateFactory<>(BasketRule.class));
        return repository;
    }

    @Bean
    public Repository<DeliveryChargesRule> createDeliveryChargesRuleRepository(DisruptorCommandBus commandBus) {
        Repository<DeliveryChargesRule> repository = commandBus.createRepository(new GenericAggregateFactory<>(DeliveryChargesRule.class));
        return repository;
    }

    @Override
    protected Map<String, String> types() {
        return new HashMap<String, String>() {{
            put("com.affaince.subscription.subscriber.command.event.*", "");
            put("com.affaince.subscription.integration.command.event.paymentreceipt.PaymentReceivedEvent", PaymentReceivedFromSourceEvent.class.getName());
            put("com.affaince.subscription.benefits.command.event.BenefitAddedEvent", BenefitAddedEvent.class.getName());
        }};
    }
}
