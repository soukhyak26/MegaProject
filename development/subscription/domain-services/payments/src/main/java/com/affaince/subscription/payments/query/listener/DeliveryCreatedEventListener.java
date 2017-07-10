package com.affaince.subscription.payments.query.listener;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.payments.command.CreateDeliveryCommand;
import com.affaince.subscription.payments.command.event.DeliveryCreatedEvent;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeliveryCreatedEventListener {

    @Autowired
    private SubscriptionCommandGateway commandGateway;

    //TODO: check why we fire command from event listener
    //looks like this is proper way
    public DeliveryCreatedEventListener() {
    }

    @EventHandler
    public void on(DeliveryCreatedEvent event) throws Exception {
        CreateDeliveryCommand createDeliveryCommand = new CreateDeliveryCommand(event.deliveryId,
                event.getSubscriberId(),
                event.getSubscriptionId(),
                event.getSequence(),
                event.getDeliveryItems(),
                event.getDeliveryDate(),
                event.getDispatchDate(),
                event.getStatus(),
                event.getDeliveryWeightInGrms(),
                event.getRewardPoints());
        commandGateway.executeAsync(createDeliveryCommand);
    }
}
