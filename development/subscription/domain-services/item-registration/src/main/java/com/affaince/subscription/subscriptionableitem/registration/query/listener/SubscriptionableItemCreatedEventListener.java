package com.affaince.subscription.subscriptionableitem.registration.query.listener;

import com.affaince.subscription.subscriptionableitem.registration.command.event.CreateSubscriptionableItemEvent;
import com.affaince.subscription.subscriptionableitem.registration.query.repository.SubscriptionableItemRepository;
import com.affaince.subscription.subscriptionableitem.registration.query.view.PriceParameters;
import com.affaince.subscription.subscriptionableitem.registration.query.view.SubscriptionableItemView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by rbsavaliya on 19-07-2015.
 */
@Component
public class SubscriptionableItemCreatedEventListener {
    private final SubscriptionableItemRepository itemRepository;

    @Autowired
    public SubscriptionableItemCreatedEventListener(SubscriptionableItemRepository repository) {
        this.itemRepository = repository;
    }


    @EventHandler
    public void on(CreateSubscriptionableItemEvent event) {
        final PriceParameters priceParameters = new PriceParameters(
                event.getPurchasePricePerUnit(),
                event.getCurrentMRP(),
                event.getCurrentStockInUnits(),
                event.getCurrentOfferedPrice(),
                event.getCurrentPriceDate()
        );
        final SubscriptionableItemView subscriptionableItemView = new SubscriptionableItemView(
                event.getItemId(),
                event.getBatchId(),
                event.getCategoryId(),
                event.getCategoryName(),
                event.getSubCategoryId(),
                event.getSubCategoryName(),
                event.getProductId(),
                priceParameters,
                null,
                null
        );
        itemRepository.save(subscriptionableItemView);
    }
}
