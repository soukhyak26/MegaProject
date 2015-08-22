package com.affaince.subscription.inventory.query.listener;

import com.affaince.subscription.inventory.query.event.BasketDispatchedStatusEvent;
import com.affaince.subscription.inventory.query.repository.SubscriptionBasketRepository;
import org.axonframework.eventhandling.annotation.EventHandler;

/**
 * Created by mandark on 22-08-2015.
 */
public class BasketDispatchedStatusListener {
    private final SubscriptionBasketRepository subscriptionBasketRepository;
    public BasketDispatchedStatusListener(SubscriptionBasketRepository repository) {
        this.subscriptionBasketRepository=repository;
    }

    @EventHandler
    public void on (BasketDispatchedStatusEvent event) {
        //BasketView basketView =subscriptionBasketRepository.findOneByItemId(event.getBasketId());
        System.out.println("BasketDispatchStatusEvent ID: " + event.getBasketId());
        System.out.println("BasketDispatchStatusEvent status code: " + event.getDispactchStatusCode());
        System.out.println("BasketDispatchStatusEvent dispatch date: " + event.getDispatchDate());
        System.out.println("BasketDispatchStatusEvent reason code: " + event.getReasonCode());
    }
}
