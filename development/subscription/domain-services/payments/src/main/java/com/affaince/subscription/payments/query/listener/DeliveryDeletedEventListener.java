package com.affaince.subscription.payments.query.listener;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.date.SysDate;
import com.affaince.subscription.payments.command.DeleteDeliveryCommand;
import com.affaince.subscription.payments.command.event.DeliveryDeletedEvent;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeliveryDeletedEventListener {
    @Autowired
    private SubscriptionCommandGateway commandGateway;

    public DeliveryDeletedEventListener() {
    }

    @EventHandler
    public void on(DeliveryDeletedEvent event) throws Exception {
        DeleteDeliveryCommand deleteDeliveryCommand = new DeleteDeliveryCommand(event.getSubscriberId(), event.getSubscriptionId(),event.getDeliveryId(), event.getSequence(),SysDate.now());
        commandGateway.executeAsync(deleteDeliveryCommand);
    }
}
