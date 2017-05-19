package com.affaince.subscription.payments.query.listener;

import com.affaince.subscription.payments.command.event.SubscriptionDetailsRegisteredEvent;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.stereotype.Component;

/**
 * Created by mandar on 5/18/2017.
 */
@Component
public class SubscriptionDetailsRegisteredEventListener {



    @EventHandler
    public void on(SubscriptionDetailsRegisteredEvent event) {
    }
}
