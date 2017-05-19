package com.affaince.subscription.payments.query.listener;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.payments.command.CreateSubscriptionSpecificPaymentAccountCommand;
import com.affaince.subscription.payments.command.event.SubscriptionSpecificDeliveriesCreatedAggregateEvent;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mandar on 5/17/2017.
 */
@Component
public class SubscriptionSpecificDeliveriesCreatedAggregateEventListener {
    @Autowired
    private SubscriptionCommandGateway commandGateway;
    @EventHandler
    public void on(SubscriptionSpecificDeliveriesCreatedAggregateEvent event) throws Exception{
        CreateSubscriptionSpecificPaymentAccountCommand command = new CreateSubscriptionSpecificPaymentAccountCommand(event.getSubscriberId(),event.getSubscriptionId(),event.getTotalSubscriptionDeliveries());
        commandGateway.executeAsync(command);

    }
}

