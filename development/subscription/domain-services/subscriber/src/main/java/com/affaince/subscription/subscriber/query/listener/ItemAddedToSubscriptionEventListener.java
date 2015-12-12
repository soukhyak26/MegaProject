package com.affaince.subscription.subscriber.query.listener;

import com.affaince.subscription.common.type.Frequency;
import com.affaince.subscription.common.type.Period;
import com.affaince.subscription.common.type.PeriodUnit;
import com.affaince.subscription.subscriber.command.event.ItemAddedToSubscriptionEvent;
import com.affaince.subscription.subscriber.query.repository.SubscriptionRepository;
import com.affaince.subscription.subscriber.query.view.BasketItem;
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

    private final SubscriptionRepository repository;

    @Autowired
    public ItemAddedToSubscriptionEventListener(SubscriptionRepository repository) {
        this.repository = repository;
    }

    @EventHandler
    public void on(ItemAddedToSubscriptionEvent event) {
        final Frequency frequency = new Frequency(event.getQuantityPerBasket(),
                new Period(event.getFrequency(), PeriodUnit.valueOf(event.getFrequencyUnit())));
        BasketItem basketItem = new BasketItem(event.getItemId(), frequency, event.getDiscountedOfferedPrice());
        SubscriptionView subscriptionView = repository.findOne(event.getSubscriptionId());
        List<BasketItem> basketItems = subscriptionView.getBasketItems();
        if (basketItems == null) {
            basketItems = new ArrayList<>();
        }
        basketItems.add(basketItem);
        subscriptionView.setBasketItems(basketItems);
        repository.save(subscriptionView);
    }
}
