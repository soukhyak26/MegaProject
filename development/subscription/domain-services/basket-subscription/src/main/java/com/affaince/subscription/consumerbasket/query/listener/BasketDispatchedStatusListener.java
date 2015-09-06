package com.affaince.subscription.consumerbasket.query.listener;

import com.affaince.subscription.consumerbasket.command.UpdateBasketDispatchStatusCommand;
import com.affaince.subscription.consumerbasket.command.event.BasketDispatchedStatusEvent;
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
        System.out.println("@@@@@BasketDispatchStatusEvent ID: " + event.getBasketId());
        System.out.println("@@@@@@@BasketDispatchStatusEvent status code: " + event.getDispactchStatusCode());
        System.out.println("@@@@@@BasketDispatchStatusEvent dispatch date: " + event.getDispatchDate());
        System.out.println("@@@@@@@BasketDispatchStatusEvent reason code: " + event.getReasonCode());

        UpdateBasketDispatchStatusCommand updateBasketDispatchStatusCommand = new UpdateBasketDispatchStatusCommand(event.getBasketId(), event.getDispatchDate(), event.getDispactchStatusCode(), event.getReasonCode());
        commandGateway.send(updateBasketDispatchStatusCommand);
    }
}
