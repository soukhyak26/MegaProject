package com.affaince.subscription.payments.event.listener;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.payments.command.UpdateDeliveryStatusCommand;
import com.affaince.subscription.payments.event.DeliveryStatusAndDispatchDateUpdatedEvent;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeliveryStatusAndDispatchDateUpdatedEventListener {

    @Autowired
    private SubscriptionCommandGateway commandGateway;

    public DeliveryStatusAndDispatchDateUpdatedEventListener() {
    }

    //TODO:CHeck for failed/returned goods and adjust payment for the same
    @EventHandler
    public void on(DeliveryStatusAndDispatchDateUpdatedEvent event) throws Exception {
        UpdateDeliveryStatusCommand command= new UpdateDeliveryStatusCommand(event.getSubscriptionId(),event.getBasketId(),event.getDispatchDate(),event.getBasketDeliveryStatus(),event.getItemDispatchStatuses(),event.getDeliveryCharges());
        commandGateway.executeAsync(command);
    }
}
