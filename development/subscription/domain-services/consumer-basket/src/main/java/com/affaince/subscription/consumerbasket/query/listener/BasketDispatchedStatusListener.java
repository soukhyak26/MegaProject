package com.affaince.subscription.consumerbasket.query.listener;

import com.affaince.subscription.consumerbasket.command.event.BasketDispatchedStatusEvent;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mandark on 22-08-2015.
 */
@Component
public class BasketDispatchedStatusListener {
    public BasketDispatchedStatusListener() {
    }

    @EventHandler
    public void on (BasketDispatchedStatusEvent event) {
        //BasketView basketView =subscriptionBasketRepository.findOneByItemId(event.getBasketId());
        System.out.println("@@@@@BasketDispatchStatusEvent ID: " + event.getBasketId());
        System.out.println("@@@@@@@BasketDispatchStatusEvent status code: " + event.getDispactchStatusCode());
        System.out.println("@@@@@@BasketDispatchStatusEvent dispatch date: " + event.getDispatchDate());
        System.out.println("@@@@@@@BasketDispatchStatusEvent reason code: " + event.getReasonCode());
    }
}
