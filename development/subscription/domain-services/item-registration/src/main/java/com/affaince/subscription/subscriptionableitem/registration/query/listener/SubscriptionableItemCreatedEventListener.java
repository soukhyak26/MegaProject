package com.affaince.subscription.subscriptionableitem.registration.query.listener;

import com.affaince.subscription.subscriptionableitem.registration.command.event.CreateSubscriptionableItemEvent;
import com.affaince.subscription.subscriptionableitem.registration.query.repository.SubscriptionableItemRepository;
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
        SubscriptionableItemView subscriptionableItemView = new SubscriptionableItemView(
                event.getItemId(),
                event.getBatchId(),
                event.getCategoryId(),
                event.getCategoryName(),
                event.getSubCategoryId(),
                event.getSubCategoryNmae(),
                event.getProductId(),
                event.getPurchasePricePerUnit(),
                event.getCurrentMRP(),
                event.getCurrentOfferedPrice(),
                event.getCurrentStockInUnits(),
                event.getCurrentPriceDate(),
                null,
                null
        );
        itemRepository.save(subscriptionableItemView);
    }
}
