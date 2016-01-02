package com.affaince.subscription.subscriber.query.listener;

import com.affaince.subscription.subscriber.command.event.SubscriptionCreatedEvent;
import com.affaince.subscription.subscriber.query.repository.SubscriptionViewRepository;
import com.affaince.subscription.subscriber.query.view.SubscriptionView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 09-08-2015.
 */
@Component
public class SubscriptionCreatedEventListener {

    private final SubscriptionViewRepository basketRepository;

    @Autowired
    public SubscriptionCreatedEventListener(SubscriptionViewRepository basketRepository) {
        this.basketRepository = basketRepository;
    }

    @EventHandler
    public void on(SubscriptionCreatedEvent event) {
        SubscriptionView subscriptionView = new SubscriptionView(event.getSubscriptionId(),
                event.getSubscriberId(), event.getConsumerBasketStatus(), null, null, null, null, 0.0,
                event.getBasketCreatedDate(), event.getBasketExpiredDate());
        basketRepository.save(subscriptionView);
    }
}
