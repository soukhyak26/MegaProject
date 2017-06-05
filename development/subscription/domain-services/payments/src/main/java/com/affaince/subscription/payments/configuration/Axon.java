package com.affaince.subscription.payments.configuration;

import com.affaince.subscription.configuration.Default;
import com.affaince.subscription.payments.command.domain.PaymentAccount;
import com.affaince.subscription.payments.command.domain.PaymentScheme;
import com.affaince.subscription.payments.command.event.*;
import com.mongodb.Mongo;
import org.axonframework.commandhandling.disruptor.DisruptorCommandBus;
import org.axonframework.eventsourcing.GenericAggregateFactory;
import org.axonframework.repository.Repository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.SimpleMongoDbFactory;
import org.springframework.jms.annotation.EnableJms;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by anayonkar on 21/8/16.
 */
@Configuration
@EnableJms
public class Axon extends Default {


    @Bean
    public Repository<PaymentAccount> createPaymentRepository(DisruptorCommandBus commandBus) {
        Repository<PaymentAccount> repository = commandBus.createRepository(new GenericAggregateFactory<>(PaymentAccount.class));
        return repository;
    }

    @Bean
    public Repository<PaymentScheme> createPaymentSchemeRepository(DisruptorCommandBus commandBus) {
        Repository<PaymentScheme> repository = commandBus.createRepository(new GenericAggregateFactory<>(PaymentScheme.class));
        return repository;
    }

    @Bean
    public MongoDbFactory mongoDbFactory(Mongo mongo, @Value("${view.db.name}") String dbName) throws Exception {
        return new SimpleMongoDbFactory(mongo, dbName);
    }

    @Override
    @Bean(name = "types")
    protected Map<String, String> types() {
        return new HashMap<String, String>() {{
            put("com.affaince.subscription.subscriber.command.event.DeliveryPreparedForDispatchEvent",DeliveryPreparedForDispatchEvent.class.getName());
            put("com.affaince.subscription.product.command.event.ProductRegisteredEvent",ProductRegisteredEvent.class.getName());
            put("com.affaince.subscription.product.command.event.SubscriptionSpecificDeliveriesCreatedAggregateEvent", SubscriptionSpecificDeliveriesCreatedAggregateEvent.class.getName());
            put("com.affaince.subscription.product.command.event.ProductStatusUpdatedEvent",ProductStatusUpdatedEvent.class.getName());
            put("com.affaince.subscription.product.command.event.OfferedPriceChangedEvent",OfferedPriceChangedEvent.class.getName());
            put("com.affaince.subscription.subscriber.command.event.DeliveryStatusAndDispatchDateUpdatedEvent", DeliveryStatusAndDispatchDateUpdatedEvent.class.getName());
            put("com.affaince.subscription.integration.command.event.paymentreceipt.PaymentReceivedEvent", PaymentReceivedEvent.class.getName());
            put("com.affaince.subscription.product.command.event.DeliveryCreatedEvent", DeliveryCreatedEvent.class.getName());
            put("com.affaince.subscription.subscriber.command.event.DeliveryDeletedEvent", DeliveryDeletedEvent.class.getName());
        }};
    }

}
