package com.affaince.subscription.subscriber.event.listener;

import com.affaince.subscription.subscriber.event.ItemAddedToSubscriptionEvent;
import com.affaince.subscription.subscriber.query.repository.SubscriptionViewRepository;
import com.affaince.subscription.subscriber.query.view.SubscriptionItem;
import com.affaince.subscription.subscriber.query.view.SubscriptionView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by rbsavaliya on 23-08-2015.
 */
@Component
public class ItemAddedToSubscriptionEventListener {

    private final SubscriptionViewRepository repository;

    @Autowired
    public ItemAddedToSubscriptionEventListener(SubscriptionViewRepository repository) {
        this.repository = repository;
    }

    @EventHandler
    public void on(ItemAddedToSubscriptionEvent event) {
        final SubscriptionItem subscriptionItem = new SubscriptionItem(event.getItemId(),
                event.getCountPerPeriod(), event.getPeriod(), event.getDiscountedOfferedPrice(),
                event.getOfferedPriceWithBasketLevelDiscount(), event.getNoOfCycles(), event.getWeightInGrms(),
                event.getProductPricingCategory());
        SubscriptionView subscriptionView = repository.findOne(event.getSubscriptionId());
        List<SubscriptionItem> subscriptionItems = subscriptionView.getSubscriptionItems();
        if (subscriptionItems == null) {
            subscriptionItems = new ArrayList<>();
        }
        subscriptionItems.add(subscriptionItem);
        subscriptionView.setSubscriptionItems(subscriptionItems);
        repository.save(subscriptionView);
    }
}
