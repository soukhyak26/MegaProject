package com.affaince.subscription.subscriptionableitem.registration.query.listener;

import com.affaince.subscription.subscriptionableitem.registration.query.event.SubscriptionableItemReceivedEvent;
import com.affaince.subscription.subscriptionableitem.registration.query.repository.SubscriptionableItemRepository;
import com.affaince.subscription.subscriptionableitem.registration.query.view.SubscriptionableItemView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mandark on 26-07-2015.
 */
@Component
public class SubscriptionableItemReceivedListener {
    SubscriptionableItemRepository itemRepository;
    @Autowired
    public SubscriptionableItemReceivedListener(SubscriptionableItemRepository repository) {
        this.itemRepository = repository;
    }

    @EventHandler
    public void on (SubscriptionableItemReceivedEvent event) {
        System.out.println("@@@@@@@@Listener: Received even object: "+ event);
        SubscriptionableItemView subscriptionableItemView = new SubscriptionableItemView(
                event.getItemId(),
                event.getBatchId(),
                event.getCategoryId(),
                event.getCategoryName(),
                event.getSubCategoryId(),
                event.getSubCategoryName(),
                event.getProductId(),
                event.getCurrentMRP(),
                event.getCurrentStockInUnits(),
                event.getCurrentPriceDate(),
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
