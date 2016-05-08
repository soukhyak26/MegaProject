package com.affaince.subscription.business.query.listener;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.business.command.PaymentProcessedCommand;
import com.affaince.subscription.business.command.event.PaymentProcessedEvent;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by anayonkar on 8/5/16.
 */
@Component
public class PaymentProcessedEventListener {
    @Autowired
    private SubscriptionCommandGateway commandGateway;

    public PaymentProcessedEventListener() {
    }

    @EventHandler
    public void on(PaymentProcessedEvent event) throws Exception {
        PaymentProcessedCommand command = new PaymentProcessedCommand(event.getSubscriptionId(), event.getSubscriberId(), event.getPaymentAmount(), event.getPaymentDate());
        commandGateway.executeAsync(command);
    }
}
