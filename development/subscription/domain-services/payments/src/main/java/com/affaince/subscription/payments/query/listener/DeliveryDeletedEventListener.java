package com.affaince.subscription.payments.query.listener;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.payments.command.DeliveryDeletedCommand;
import com.affaince.subscription.payments.command.event.DeliveryDeletedEvent;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by anayonkar on 20/11/16.
 */
@Component
public class DeliveryDeletedEventListener {
    @Autowired
    private SubscriptionCommandGateway commandGateway;

    public DeliveryDeletedEventListener() {
    }

    @EventHandler
    public void on(DeliveryDeletedEvent event) throws Exception {
        DeliveryDeletedCommand deliveryDeletedCommand = new DeliveryDeletedCommand(event.getSubscriberId(), event.getDeliveryId());
        commandGateway.executeAsync(deliveryDeletedCommand);
    }
}
