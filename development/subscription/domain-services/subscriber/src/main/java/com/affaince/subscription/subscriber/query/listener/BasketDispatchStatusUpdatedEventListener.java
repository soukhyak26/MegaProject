package com.affaince.subscription.subscriber.query.listener;

import com.affaince.subscription.subscriber.command.event.BasketDispatchStatusUpdatedEvent;
import com.affaince.subscription.subscriber.query.repository.SubscriptionRepository;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mandark on 30-08-2015.
 */
@Component
public class BasketDispatchStatusUpdatedEventListener {
    private final SubscriptionRepository basketRepository;

    @Autowired
    public BasketDispatchStatusUpdatedEventListener(SubscriptionRepository basketRepository) {
        this.basketRepository = basketRepository;
    }

    @EventHandler
    public void on(BasketDispatchStatusUpdatedEvent basketDispatchStatusUpdatedEvent) {
        System.out.println("@@@@@BasketDispatchStatusUpdatdEventListener ID: " + basketDispatchStatusUpdatedEvent.getBasketId());
        System.out.println("@@@@@@@BasketDispatchStatusUpdatdEventListener status code: " + basketDispatchStatusUpdatedEvent.getDispactchStatusCode());
        System.out.println("@@@@@@BasketDispatchStatusUpdatdEventListener dispatch date: " + basketDispatchStatusUpdatedEvent.getDispatchDate());
        System.out.println("@@@@@@@BasketDispatchStatusUpdatdEventListener reason code: " + basketDispatchStatusUpdatedEvent.getReasonCode());

    }

}
