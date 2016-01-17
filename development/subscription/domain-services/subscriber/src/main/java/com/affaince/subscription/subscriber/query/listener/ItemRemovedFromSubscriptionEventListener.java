package com.affaince.subscription.subscriber.query.listener;

import com.affaince.subscription.subscriber.command.event.ItemRemovedFromSubscriptionEvent;
import com.affaince.subscription.subscriber.query.repository.SubscriptionViewRepository;
import com.affaince.subscription.subscriber.query.view.SubscriptionView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 10-09-2015.
 */
@Component
public class ItemRemovedFromSubscriptionEventListener {

    private final SubscriptionViewRepository repository;

    @Autowired
    public ItemRemovedFromSubscriptionEventListener(SubscriptionViewRepository repository) {
        this.repository = repository;
    }

    @EventHandler
    public void on(ItemRemovedFromSubscriptionEvent event) {
        SubscriptionView subscriptionView = repository.findOne(event.getSubscriptionId());
        subscriptionView.getSubscriptionItemViews().removeIf(item -> item.getProductId().equals(event.getItemId()));
        repository.save(subscriptionView);
    }
}
