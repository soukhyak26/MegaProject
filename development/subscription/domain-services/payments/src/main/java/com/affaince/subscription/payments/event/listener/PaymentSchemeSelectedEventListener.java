package com.affaince.subscription.payments.event.listener;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.payments.command.SetPaymentSchemeCommand;
import com.affaince.subscription.payments.event.PaymentSchemeSelectedEvent;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mandar on 7/9/2017.
 */
@Component
public class PaymentSchemeSelectedEventListener {
    @Autowired
    SubscriptionCommandGateway commandGateway;

    @EventHandler
    public void on(PaymentSchemeSelectedEvent event) throws Exception{
        SetPaymentSchemeCommand command= new SetPaymentSchemeCommand(event.getSubscriptionId(),event.getPaymentSchemeId());
        commandGateway.executeAsync(command);
    }
}
