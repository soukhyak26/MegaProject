package com.affaince.subscription.payments.query.listener;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.payments.command.DeliveryStatusAndDispatchDateUpdatedCommand;
import com.affaince.subscription.payments.command.event.DeliveryStatusAndDispatchDateUpdatedEvent;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by anayonkar on 21/8/16.
 */
@Component
public class DeliveryStatusAndDispatchDateUpdatedEventListener {

    @Autowired
    private SubscriptionCommandGateway commandGateway;

    public DeliveryStatusAndDispatchDateUpdatedEventListener() {
    }

    @EventHandler
    public void on(DeliveryStatusAndDispatchDateUpdatedEvent event) throws Exception {
        DeliveryStatusAndDispatchDateUpdatedCommand command = new DeliveryStatusAndDispatchDateUpdatedCommand(event.getSubscriptionId(), event.getBasketId(), event.getBasketDeliveryStatus(), event.getDispatchDate(), event.getItemDispatchStatuses(), event.getDeliveryCharges(), event.getTotalDeliveryPrice());
        commandGateway.executeAsync(command);
    }
}
