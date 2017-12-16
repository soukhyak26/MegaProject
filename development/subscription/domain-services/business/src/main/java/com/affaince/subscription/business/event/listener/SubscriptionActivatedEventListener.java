package com.affaince.subscription.business.event.listener;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.business.command.SubscriptionActivatedCommand;
import com.affaince.subscription.business.event.SubscriptionActivatedEvent;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by anayonkar on 8/5/16.
 */
@Component
public class SubscriptionActivatedEventListener {
    @Autowired
    private SubscriptionCommandGateway commandGateway;

    public SubscriptionActivatedEventListener() {

    }

    @EventHandler
    public void on(SubscriptionActivatedEvent event) throws Exception {
        SubscriptionActivatedCommand command = new SubscriptionActivatedCommand(event.getSubscriptionId(), event.getTotalSubscriptionAmountAfterDiscount(), event.getTotalDiscount());
        commandGateway.executeAsync(command);
    }
}
