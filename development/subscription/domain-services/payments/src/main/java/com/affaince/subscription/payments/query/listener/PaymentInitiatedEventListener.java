package com.affaince.subscription.payments.query.listener;

import com.affaince.subscription.payments.command.event.PaymentInitiatedEvent;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by anayonkar on 24/8/16.
 */
@Component
public class PaymentInitiatedEventListener {

    @Autowired
    public PaymentInitiatedEventListener() {
    }

    @EventHandler
    public void on(PaymentInitiatedEvent event) {
    }
}
