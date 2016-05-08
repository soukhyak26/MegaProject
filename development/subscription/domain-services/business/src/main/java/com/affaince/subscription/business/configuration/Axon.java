package com.affaince.subscription.business.configuration;

import com.affaince.subscription.business.command.domain.BusinessAccount;
import com.affaince.subscription.business.command.domain.CommonOperatingExpense;
import com.affaince.subscription.business.command.event.*;
import com.affaince.subscription.configuration.RabbitMQConfiguration;
import org.axonframework.commandhandling.disruptor.DisruptorCommandBus;
import org.axonframework.eventsourcing.GenericAggregateFactory;
import org.axonframework.repository.Repository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rbsavaliya on 19-07-2015.
 */
@Configuration
@EnableJms
public class Axon extends RabbitMQConfiguration {


    @Bean
    public Repository<CommonOperatingExpense> createCommonOperatingExpenseRepository(DisruptorCommandBus commandBus) {
        Repository<CommonOperatingExpense> repository = commandBus.createRepository(new GenericAggregateFactory<>(CommonOperatingExpense.class));
        return repository;
    }

    @Bean
    public Repository<BusinessAccount> createBusinessAccountRepository(DisruptorCommandBus commandBus) {
        Repository<BusinessAccount> repository = commandBus.createRepository(new GenericAggregateFactory<>(BusinessAccount.class));
        return repository;
    }


    @Override
    protected Map<String, String> types() {
        return new HashMap<String, String>() {{
            put("com.affaince.subscription.integration.command.event.operatingexpense.OperatingExpenseReceivedEvent", OperatingExpenseUpdatedEvent.class.getName());
            put("com.affaince.subscription.integration.command.event.productstatus.ProductStatusReceivedEvent", ProductStatusReceivedEvent.class.getName());
            put("com.affaince.subscription.integration.command.event.operatingexpense.OperatingExpenseReceivedEvent", OperatingExpenseReceivedEvent.class.getName());
            //put("com.affaince.subscription.subscriber.command.event.SubscriptionActivatedEvent", SubscriptionActivatedEvent.class.getName());
            put("com.affaince.subscription.subscriber.command.event.PaymentProcessedEvent", PaymentProcessedEvent.class.getName());
            put("com.affaince.subscription.business.command.event.*","");
        }};
    }
}
