package com.affaince.subscription.business.query.listener;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.business.command.BasketDispatchStatusUpdatedCommand;
import com.affaince.subscription.business.command.event.BasketDispatchStatusUpdatedEvent;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by anayonkar on 8/5/16.
 */
@Component
public class BasketDispatchStatusUpdatedEventListener {
    @Autowired
    private SubscriptionCommandGateway commandGateway;

    public BasketDispatchStatusUpdatedEventListener() {
    }

    @EventHandler
    public void on(BasketDispatchStatusUpdatedEvent event) throws Exception {
        BasketDispatchStatusUpdatedCommand command = new BasketDispatchStatusUpdatedCommand(event.getBasketId(), event.getDispatchDate(), event.getDispactchStatusCode(), event.getReasonCode());
        commandGateway.executeAsync(command);
    }
}
