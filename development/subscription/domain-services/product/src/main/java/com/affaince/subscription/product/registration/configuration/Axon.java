package com.affaince.subscription.product.registration.configuration;

import com.affaince.subscription.configuration.RabbitMQConfiguration;
import com.affaince.subscription.product.registration.command.domain.OperatingExpenseAccount;
import com.affaince.subscription.product.registration.command.domain.Product;
import com.affaince.subscription.product.registration.command.event.*;
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
    public Repository<Product> createRepository(DisruptorCommandBus commandBus) {

        Repository<Product> repository = commandBus.createRepository(new GenericAggregateFactory<>(Product.class));
        return repository;
    }

    @Bean
    public Repository<OperatingExpenseAccount> createCommonOperatingExpenseRepository(DisruptorCommandBus commandBus) {
        Repository<OperatingExpenseAccount> repository = commandBus.createRepository(new GenericAggregateFactory<>(OperatingExpenseAccount.class));
        return repository;
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
            put("com.affaince.subscription.integration.command.event.operatingexpense.OperatingExpenseReceivedEvent", CommonExpenseTypeUpdatedEvent.class.getName());
        }};
    }
}
