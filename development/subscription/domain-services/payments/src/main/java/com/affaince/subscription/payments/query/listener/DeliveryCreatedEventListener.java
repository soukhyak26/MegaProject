package com.affaince.subscription.payments.query.listener;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.payments.command.DeliveryCreatedCommand;
import com.affaince.subscription.payments.command.event.DeliveryCreatedEvent;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by anayonkar on 21/8/16.
 */
@Component
public class DeliveryCreatedEventListener {
    @Autowired
    private SubscriptionCommandGateway commandGateway;

    public DeliveryCreatedEventListener() {
    }

    @EventHandler
    public void on(DeliveryCreatedEvent event) throws Exception {
        DeliveryCreatedCommand deliveryCreatedCommand = new DeliveryCreatedCommand(event.deliveryId,
                event.getSubscriberId(),
                event.getSubscriptionId(),
                event.getDeliveryItems(),
                event.getDeliveryDate(),
                event.getDispatchDate(),
                event.getStatus(),
                event.getDeliveryWeightInGrms());
        commandGateway.executeAsync(deliveryCreatedCommand);
    }
}
