package com.affaince.subscription.payments.query.listener;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.payments.command.PaymentReceivedCommand;
import com.affaince.subscription.payments.command.event.PaymentReceivedEvent;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by anayonkar on 21/8/16.
 */
@Component
public class PaymentReceivedEventListener {
    @Autowired
    private SubscriptionCommandGateway commandGateway;

    public PaymentReceivedEventListener() {
    }

    @EventHandler
    public void on(PaymentReceivedEvent event) throws Exception {
        PaymentReceivedCommand paymentReceivedCommand = new PaymentReceivedCommand(event.getSusbcriberId(),
                event.getSubscriptionId(),
                event.getPaidAmount(),
                event.getPaymentDate());
        commandGateway.executeAsync(paymentReceivedCommand);
    }
}
