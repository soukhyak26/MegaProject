package com.affaince.subscription.subscriber.query.listener;

import com.affaince.subscription.subscriber.command.UpdateDeliveryStatusAndDispatchDateCommand;
import com.affaince.subscription.subscriber.command.event.DeliveryDispatchStatusUpdatedEvent;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mandark on 22-08-2015.
 */
@Component
public class DeliveryDispatchedStatusListener {

    private final CommandGateway commandGateway;

    @Autowired
    public DeliveryDispatchedStatusListener(CommandGateway gateway) {
        this.commandGateway = gateway;
    }

    @EventHandler
    public void on(DeliveryDispatchStatusUpdatedEvent event) {
        final UpdateDeliveryStatusAndDispatchDateCommand command = new UpdateDeliveryStatusAndDispatchDateCommand(
                event.getSubscriptionId(), event.getSubscriberId(), event.getBasketDeliveryStatus(), event.getDispatchDate(),
                event.getItemDispatchStatuses(), event.getReasonCode());
        commandGateway.send(command);
    }
}
