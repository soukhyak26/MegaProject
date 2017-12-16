package com.affaince.subscription.subscriber.event.listener;

import com.affaince.subscription.subscriber.event.DeliveryDispatchStatusUpdatedEvent;
import com.affaince.subscription.subscriber.query.repository.SubscriptionViewRepository;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mandark on 30-08-2015.
 */
@Component
public class BasketDispatchStatusUpdatedEventListener {
    private final SubscriptionViewRepository basketRepository;

    @Autowired
    public BasketDispatchStatusUpdatedEventListener(SubscriptionViewRepository basketRepository) {
        this.basketRepository = basketRepository;
    }

    @EventHandler
    public void on(DeliveryDispatchStatusUpdatedEvent deliveryDispatchStatusUpdatedEvent) {

    }

}
