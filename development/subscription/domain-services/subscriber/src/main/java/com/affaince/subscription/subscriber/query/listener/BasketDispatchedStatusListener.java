package com.affaince.subscription.subscriber.query.listener;

import com.affaince.subscription.subscriber.command.UpdateBasketDispatchStatusCommand;
import com.affaince.subscription.subscriber.command.event.BasketDispatchedStatusEvent;
import org.axonframework.commandhandling.gateway.CommandGateway;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mandark on 22-08-2015.
 */
@Component
public class BasketDispatchedStatusListener {

    private final CommandGateway commandGateway;

    @Autowired
    public BasketDispatchedStatusListener(CommandGateway gateway) {
        this.commandGateway = gateway;
    }

    @EventHandler
    public void on(BasketDispatchedStatusEvent event) {
        UpdateBasketDispatchStatusCommand updateBasketDispatchStatusCommand =
                new UpdateBasketDispatchStatusCommand(event.getSubscriberId(), event.getDispatchDate(), event.getDispactchStatusCode(), event.getReasonCode());
        commandGateway.send(updateBasketDispatchStatusCommand);
    }
}
