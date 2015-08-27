package com.affaince.subscription.subscriptionableitem.registration.query.listener;

import com.affaince.subscription.subscriptionableitem.registration.query.event.SubscriptionableItemReceivedEvent;
import com.affaince.subscription.subscriptionableitem.registration.query.repository.SubscriptionableItemRepository;
import com.affaince.subscription.subscriptionableitem.registration.query.view.PriceParameters;
import com.affaince.subscription.subscriptionableitem.registration.query.view.SubscriptionableItemView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mandark on 26-07-2015.
 */
@Component
public class SubscriptionableItemReceivedListener {
    private final SubscriptionableItemRepository itemRepository;

    @Autowired
    public SubscriptionableItemReceivedListener(SubscriptionableItemRepository repository) {
        this.itemRepository = repository;
    }

    @EventHandler
    public void on(SubscriptionableItemReceivedEvent event) {
        PriceParameters priceParameters = new PriceParameters(
                event.getCurrentPurchasePricePerUnit(),
                event.getCurrentMRP(),
                event.getCurrentStockInUnits(),
                0.0,
                event.getCurrentPriceDate()
        );
        SubscriptionableItemView subscriptionableItemView = new SubscriptionableItemView(
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
        System.out.println("@@@@Item saved successfully");
    }

/*
    @EventHandler
    public void handle(SubscriptionableItemReceivedEvent subscriptionableItemReceivedEvent) {
        System.out.println("@@@@@BatchId:" + subscriptionableItemReceivedEvent.getBatchId());
        System.out.println("@@@@@CategoryId:" + subscriptionableItemReceivedEvent.getCategroyId());
        System.out.println("@@@@@CategoryName:" + subscriptionableItemReceivedEvent.getCategoryName());
        System.out.println("@@@@@ProductId:" + subscriptionableItemReceivedEvent.getProductId());
        System.out.println("@@@@@SubCategoryId:" + subscriptionableItemReceivedEvent.getSubCategoryId());
        System.out.println("@@@@@SubCategoryName:" + subscriptionableItemReceivedEvent.getSubCategoryName());
        System.out.println("@@@@@Current MRP:" + subscriptionableItemReceivedEvent.getCurrentMRP());
        System.out.println("@@@@@Current Purchase Price Per Unit:" + subscriptionableItemReceivedEvent.getCurrentPurchasePricePerUnit());
        System.out.println("@@@@@Current Stocks:" + subscriptionableItemReceivedEvent.getCurrentStockInUnits());

    }
*/
}
