package com.affaince.subscription.subscriber.query.listener;

import com.affaince.subscription.subscriber.command.event.ItemAddedToSubscriptionEvent;
import com.affaince.subscription.subscriber.query.repository.SubscriptionViewRepository;
import com.affaince.subscription.subscriber.query.view.SubscriptionItemView;
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
        final SubscriptionItemView subscriptionItemView = new SubscriptionItemView(event.getItemId(),
                event.getCountPerPeriod(), event.getPeriod(), event.getDiscountedOfferedPrice(),
                event.getOfferedPriceWithBasketLevelDiscount(), event.getNoOfCycles(), event.getWeightInGrms());
        SubscriptionView subscriptionView = repository.findOne(event.getSubscriptionId());
        List<SubscriptionItemView> subscriptionItemViews = subscriptionView.getSubscriptionItemViews();
        if (subscriptionItemViews == null) {
            subscriptionItemViews = new ArrayList<>();
        }
        subscriptionItemViews.add(subscriptionItemView);
        subscriptionView.setSubscriptionItemViews(subscriptionItemViews);
        repository.save(subscriptionView);
    }
}
