package com.affaince.subscription.business.configuration;

import com.affaince.subscription.business.command.domain.BusinessAccount;
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
    public Repository<BusinessAccount> createBusinessAccountRepository(DisruptorCommandBus commandBus) {
        Repository<BusinessAccount> repository = commandBus.createRepository(new GenericAggregateFactory<>(BusinessAccount.class));
        return repository;
    }

    @Override
    @Bean(name = "types")
    protected Map<String, String> types() {
        return new HashMap<String, String>() {{
            put("com.affaince.subscription.product.command.event.ProductRegisteredEvent", ProductRegisteredEvent.class.getName());
            //put("com.affaince.subscription.product.command.event.AnnualForecastCreatedEvent", AnnualForecastCreatedEvent.class.getName());
            put("com.affaince.subscription.product.command.event.ProductDemandIncreaseNotificationEvent", ProductDemandIncreaseNotificationEvent.class.getName());
            put("com.affaince.subscription.product.command.event.ProductDemandDecreaseNotificationEvent", ProductDemandDecreaseNotificationEvent.class.getName());
            put("com.affaince.subscription.product.command.event.ProductActivatedEvent", ProductActivatedEvent.class.getName());
            put("com.affaince.subscription.product.command.event.ExpectedProductContributionToPurchaseExpenseRevenueAndProfitAddedEvent", ExpectedProductContributionToPurchaseExpenseRevenueAndProfitAddedEvent.class.getName());
            put("com.affaince.subscription.product.command.event.ProductContributionToPurchaseExpenseRevenueAndProfitAddedEvent", ProductContributionToPurchaseExpenseRevenueAndProfitAddedEvent.class.getName());
            put("com.affaince.subscription.product.command.event.ExcessProfitDonatedToNodalAccountEvent", ExcessProfitDonatedToNodalAccountEvent.class.getName());
            put("com.affaince.subscription.integration.command.event.productstatus.ProductStatusReceivedEvent", ProductStatusReceivedEvent.class.getName());
            put("com.affaince.subscription.integration.command.event.operatingexpense.OperatingExpenseReceivedEvent", OperatingExpenseReceivedEvent.class.getName());
            put("com.affaince.subscription.subscriber.command.event.SubscriptionActivatedEvent", SubscriptionActivatedEvent.class.getName());
            put("com.affaince.subscription.subscriber.command.event.PaymentProcessedEvent", PaymentProcessedEvent.class.getName());
            put("com.affaince.subscription.subscriber.command.event.DeliveryStatusAndDispatchDateUpdatedEvent", DeliveryStatusAndDispatchDateUpdatedEvent.class.getName());
            //for debiting from the purchaseCostAccount for the delivered items;
            //put("com.affaince.subscription.product.command.event.DeliveredSubscriptionCountAddedToPriceBucket", DeliveredSubscriptionCountAddedToPriceBucket.class.getName());
            put("com.affaince.subscription.product.command.event.PriceBucketWisePurchaseCostRevenueAndProfitCalculatedEvent", PriceBucketWisePurchaseCostRevenueAndProfitCalculatedEvent.class.getName());
        }};
    }
}
