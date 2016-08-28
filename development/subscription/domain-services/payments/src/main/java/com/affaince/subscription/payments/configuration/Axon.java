package com.affaince.subscription.payments.configuration;

import com.affaince.subscription.configuration.ActiveMQConfiguration;
import com.affaince.subscription.payments.command.domain.Payment;
import com.affaince.subscription.payments.command.event.*;
import org.axonframework.commandhandling.disruptor.DisruptorCommandBus;
import org.axonframework.eventsourcing.GenericAggregateFactory;
import org.axonframework.repository.Repository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jms.annotation.EnableJms;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by anayonkar on 21/8/16.
 */
@Configuration
@EnableJms
public class Axon extends ActiveMQConfiguration {


    @Bean
    public Repository<Payment> createPaymentRepository(DisruptorCommandBus commandBus) {
        Repository<Payment> repository = commandBus.createRepository(new GenericAggregateFactory<>(Payment.class));
        return repository;
    }

    @Override
    protected Map<String, String> types() {
        return new HashMap<String, String>() {{
            put("com.affaince.subscription.subscriber.command.event.DeliveryStatusAndDispatchDateUpdatedEvent", DeliveryStatusAndDispatchDateUpdatedEvent.class.getName());
            put("com.affaince.subscription.integration.command.event.paymentreceipt.PaymentReceivedEvent", PaymentReceivedEvent.class.getName());
            put("com.affaince.subscription.product.command.event.DeliveryCreatedEvent", DeliveryCreatedEvent.class.getName());
            put("com.affaince.subscription.payments.command.event.DeliveryCostAccountCreditedEvent", DeliveryCostAccountCreditedEvent.class.getName());
            put("com.affaince.subscription.payments.command.event.DeliveryCostAccountDebitedEvent", DeliveryCostAccountDebitedEvent.class.getName());
            put("com.affaince.subscription.payments.command.event.DeliveryInitiatedEvent", DeliveryInitiatedEvent.class.getName());
            put("com.affaince.subscription.payments.command.event.PaymentInitiatedEvent", PaymentInitiatedEvent.class.getName());
            put("com.affaince.subscription.payments.command.event.TotalBalanceAccountCreditedEvent", TotalBalanceAccountCreditedEvent.class.getName());
            put("com.affaince.subscription.payments.command.event.TotalBalanceAccountDebitedEvent", TotalBalanceAccountDebitedEvent.class.getName());
            put("com.affaince.subscription.payments.command.event.TotalReceivableCostAccountCreditedEvent", TotalReceivableCostAccountCreditedEvent.class.getName());
            put("com.affaince.subscription.payments.command.event.TotalReceivableCostAccountDebitedEvent", TotalReceivableCostAccountDebitedEvent.class.getName());
            put("com.affaince.subscription.payments.command.event.TotalReceivedCostAccountCreditedEvent", TotalReceivedCostAccountCreditedEvent.class.getName());
            put("com.affaince.subscription.payments.command.event.TotalReceivedCostAccountDebitedEvent", TotalReceivedCostAccountDebitedEvent.class.getName());
            put("com.affaince.subscription.payments.command.event.TotalSubscriptionCostAccountCreditedEvent", TotalSubscriptionCostAccountCreditedEvent.class.getName());
            put("com.affaince.subscription.payments.command.event.TotalSubscriptionCostAccountDebitedEvent", TotalSubscriptionCostAccountDebitedEvent.class.getName());
        }};
    }

}
