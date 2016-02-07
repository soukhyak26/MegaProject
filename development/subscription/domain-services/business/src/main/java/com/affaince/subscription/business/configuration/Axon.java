package com.affaince.subscription.business.configuration;

import com.affaince.subscription.business.command.domain.CommonOperatingExpense;
import com.affaince.subscription.business.command.event.OperatingExpenseUpdatedEvent;
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


    @Override
    protected Map<String, String> types() {
        return new HashMap<String, String>() {{
            put("com.affaince.subscription.integration.command.event.operatingexpense.OperatingExpenseReceivedEvent", OperatingExpenseUpdatedEvent.class.getName());
        }};
    }
}
