package com.affaince.subscription.payments.query.listener;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.payments.command.UpdateDeliveryStatusAndDispatchDateCommand;
import com.affaince.subscription.payments.command.event.DeliveryStatusAndDispatchDateUpdatedEvent;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeliveryStatusAndDispatchDateUpdatedEventListener {

    @Autowired
    private SubscriptionCommandGateway commandGateway;

    public DeliveryStatusAndDispatchDateUpdatedEventListener() {
    }

    @EventHandler
    public void on(DeliveryStatusAndDispatchDateUpdatedEvent event) throws Exception {
        UpdateDeliveryStatusAndDispatchDateCommand command =
                new UpdateDeliveryStatusAndDispatchDateCommand(event.getSubscriptionId(), event.getBasketId(),
                        event.getBasketDeliveryStatus(), event.getDispatchDate(), event.getItemDispatchStatuses(),
                        event.getDeliveryCharges(), event.getTotalDeliveryPrice());
        commandGateway.executeAsync(command);
    }
}
