package com.affaince.subscription.payments.event.listener;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.date.SysDate;
import com.affaince.subscription.payments.command.CreateDeliveryCommand;
import com.affaince.subscription.payments.event.DeliveryCreatedEvent;
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
                event.getDeliverySequence(),
                event.getDeliveryItems(),
                event.getDeliveryDate(),
                event.getDispatchDate(),
                event.getStatus(),
                event.getDeliveryWeightInGrms(),
                event.getRewardPoints(), SysDate.now());
        commandGateway.executeAsync(createDeliveryCommand);
    }
}
