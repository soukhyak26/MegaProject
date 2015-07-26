package com.affaince.subscription.integration.command.handler;

import com.affaince.subscription.integration.command.event.SubscriptionableItemReceivedEvent;
import org.axonframework.eventhandling.annotation.EventHandler;

/**
 * Created by mandark on 26-07-2015.
 */
public class SubscriptionableItemReceivedListener {
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
}
