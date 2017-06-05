package com.affaince.subscription.payments.query.listener;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.payments.command.CorrectDuePaymentCommand;
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

    //TODO:CHEck for failed/returned goods and adjust payment for the same
    @EventHandler
    public void on(DeliveryStatusAndDispatchDateUpdatedEvent event) throws Exception {
    }
}
