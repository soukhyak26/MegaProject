package com.affaince.subscription.subscriber.query.listener;

import com.affaince.subscription.subscriber.command.event.ItemRemovedFromSubscriptionEvent;
import com.affaince.subscription.subscriber.query.repository.SubscriptionRepository;
import com.affaince.subscription.subscriber.query.view.SubscriptionView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 10-09-2015.
 */
@Component
public class ItemRemovedFromSubscriptionEventListener {

    private final SubscriptionRepository repository;

    @Autowired
    public ItemRemovedFromSubscriptionEventListener(SubscriptionRepository repository) {
        this.repository = repository;
    }

    @EventHandler
    public void on(ItemRemovedFromSubscriptionEvent event) {
        SubscriptionView subscriptionView = repository.findOne(event.getSubscriptionId());
        subscriptionView.getBasketItems().removeIf(item -> item.getItemId().equals(event.getItemId()));
        repository.save(subscriptionView);
    }
}
