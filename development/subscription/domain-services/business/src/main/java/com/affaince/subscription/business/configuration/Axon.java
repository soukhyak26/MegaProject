package com.affaince.subscription.business.configuration;

import com.affaince.subscription.business.command.domain.BusinessAccount;
import com.affaince.subscription.business.command.domain.CommonOperatingExpense;
import com.affaince.subscription.business.command.event.*;
import com.affaince.subscription.configuration.Default;
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
 * Created by rbsavaliya on 19-07-2015.
 */
@Configuration
@EnableJms
public class Axon extends Default {


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

    @Bean
    public MongoDbFactory mongoDbFactory(Mongo mongo, @Value("${view.db.name}") String dbName) throws Exception {
        return new SimpleMongoDbFactory(mongo, dbName);
    }

    @Override
    @Bean(name = "types")
    protected Map<String, String> types() {
        return new HashMap<String, String>() {{
            put("com.affaince.subscription.product.command.event.ProductRegisteredEvent", ProductRegisteredEvent.class.getName());
            put("com.affaince.subscription.product.command.event.ManualForecastAddedEvent", ManualForecastAddedEvent.class.getName());
            put("com.affaince.subscription.product.command.event.ProductActivatedEvent", ProductActivatedEvent.class.getName());
            put("com.affaince.subscription.integration.command.event.operatingexpense.OperatingExpenseReceivedEvent", OperatingExpenseUpdatedEvent.class.getName());
            put("com.affaince.subscription.integration.command.event.productstatus.ProductStatusReceivedEvent", ProductStatusReceivedEvent.class.getName());
            put("com.affaince.subscription.integration.command.event.operatingexpense.OperatingExpenseReceivedEvent", OperatingExpenseReceivedEvent.class.getName());
            put("com.affaince.subscription.subscriber.command.event.SubscriptionActivatedEvent", SubscriptionActivatedEvent.class.getName());
            put("com.affaince.subscription.subscriber.command.event.PaymentProcessedEvent", PaymentProcessedEvent.class.getName());
            put("com.affaince.subscription.subscriber.command.event.DeliveryStatusAndDispatchDateUpdatedEvent", DeliveryStatusAndDispatchDateUpdatedEvent.class.getName());
            //put("com.affaince.subscription.business.command.event.*","");
            put("com.affaince.subscription.business.command.event.BenefitCreditedEvent", BenefitCreditedEvent.class.getName());
            put("com.affaince.subscription.business.command.event.BenefitDebitedEvent", BenefitDebitedEvent.class.getName());
            put("com.affaince.subscription.business.command.event.BookingAmountCreditedEvent", BookingAmountCreditedEvent.class.getName());
            put("com.affaince.subscription.business.command.event.BookingAmountDebitedEvent", BookingAmountDebitedEvent.class.getName());
            put("com.affaince.subscription.business.command.event.CommonExpenseCreditedEvent", CommonExpenseCreditedEvent.class.getName());
            put("com.affaince.subscription.business.command.event.CommonExpenseDebitedEvent", CommonExpenseDebitedEvent.class.getName());
            put("com.affaince.subscription.business.command.event.CreateProvisionEvent", CreateProvisionEvent.class.getName());
            put("com.affaince.subscription.business.command.event.DeliveryStatusAndDispatchDateUpdatedEvent", DeliveryStatusAndDispatchDateUpdatedEvent.class.getName());
            put("com.affaince.subscription.business.command.event.OperatingExpenseReceivedEvent", OperatingExpenseReceivedEvent.class.getName());
            put("com.affaince.subscription.business.command.event.PaymentProcessedEvent", PaymentProcessedEvent.class.getName());
            put("com.affaince.subscription.business.command.event.PurchaseCostCreditedEvent", PurchaseCostCreditedEvent.class.getName());
            put("com.affaince.subscription.business.command.event.PurchaseCostDebitedEvent", PurchaseCostDebitedEvent.class.getName());
            put("com.affaince.subscription.business.command.event.SubscriptionActivatedEvent", SubscriptionActivatedEvent.class.getName());
            put("com.affaince.subscription.business.command.event.SubscriptionSpecificExpenseCreditedEvent", SubscriptionSpecificExpenseCreditedEvent.class.getName());
            put("com.affaince.subscription.business.command.event.SubscriptionSpecificExpenseDebitedEvent", SubscriptionSpecificExpenseDebitedEvent.class.getName());
        }};
    }
}
