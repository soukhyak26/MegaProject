package com.affaince.subscription.payments.query.listener;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.payments.command.CreateSubscriptionSpecificPaymentAccountCommand;
import com.affaince.subscription.payments.command.event.SubscriptionCreatedEvent;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mandar on 5/17/2017.
 */
@Component
public class SubscriptionCreatedEventListener {
    @Autowired
    private SubscriptionCommandGateway commandGateway;
    @EventHandler
    public void on(SubscriptionCreatedEvent event) throws Exception{
        CreateSubscriptionSpecificPaymentAccountCommand command = new CreateSubscriptionSpecificPaymentAccountCommand(event.getSubscriberId(),event.getSubscriptionId(),event.getCreationDate());
        commandGateway.executeAsync(command);

    }
}

