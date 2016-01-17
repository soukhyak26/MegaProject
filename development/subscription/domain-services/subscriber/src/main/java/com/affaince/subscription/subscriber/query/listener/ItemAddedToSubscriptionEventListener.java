package com.affaince.subscription.subscriber.query.listener;

import com.affaince.subscription.common.type.Period;
import com.affaince.subscription.common.type.PeriodUnit;
import com.affaince.subscription.subscriber.command.event.ItemAddedToSubscriptionEvent;
import com.affaince.subscription.subscriber.query.repository.SubscriptionViewRepository;
import com.affaince.subscription.subscriber.query.view.BasketItemView;
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
        BasketItemView basketItemView = new BasketItemView(event.getItemId(),
                event.getCountPerPeriod(),event.getPeriod(), event.getDiscountedOfferedPrice(),
                event.getOfferedPriceWithBasketLevelDiscount(), event.getNoOfCycles());
        SubscriptionView subscriptionView = repository.findOne(event.getSubscriptionId());
        List<BasketItemView> basketItemViews = subscriptionView.getBasketItemViews();
        if (basketItemViews == null) {
            basketItemViews = new ArrayList<>();
        }
        basketItemViews.add(basketItemView);
        subscriptionView.setBasketItemViews(basketItemViews);
        repository.save(subscriptionView);
    }
}
