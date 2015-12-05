package com.affaince.subscription.product.registration.query.listener;

import com.affaince.subscription.product.registration.command.event.ProductReceivedEvent;
import com.affaince.subscription.product.registration.query.repository.ProductRepository;
import com.affaince.subscription.product.registration.query.view.PriceParameters;
import com.affaince.subscription.product.registration.query.view.ProductView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mandark on 26-07-2015.
 */
@Component
public class ProductReceivedListener {
    private final ProductRepository itemRepository;

    @Autowired
    public ProductReceivedListener(ProductRepository repository) {
        this.itemRepository = repository;
    }

    @EventHandler
    public void on(ProductReceivedEvent event) {
        PriceParameters priceParameters = new PriceParameters(
                event.getCurrentPurchasePricePerUnit(),
                event.getCurrentMRP(),
                event.getCurrentStockInUnits(),
                0.0,
                event.getCurrentPriceDate()
        );
        ProductView productView = new ProductView(
                event.getProductId(),
                event.getBatchId(),
                event.getCategoryId(),
                event.getCategoryName(),
                event.getSubCategoryId(),
                event.getSubCategoryName(),
                priceParameters
        );
        itemRepository.save(productView);
        System.out.println("@@@@Item saved successfully");
    }

/*
    @EventHandler
    public void handle(SubscriptionableItemReceivedEvent subscriptionableItemReceivedEvent) {
        System.out.println("@@@@@BatchId:" + subscriptionableItemReceivedEvent.getBatchId());
        System.out.println("@@@@@CategoryId:" + subscriptionableItemReceivedEvent.getCategoryId());
        System.out.println("@@@@@CategoryName:" + subscriptionableItemReceivedEvent.getCategoryName());
        System.out.println("@@@@@ProductId:" + subscriptionableItemReceivedEvent.getShoppingSiteProductId());
        System.out.println("@@@@@SubCategoryId:" + subscriptionableItemReceivedEvent.getSubCategoryId());
        System.out.println("@@@@@SubCategoryName:" + subscriptionableItemReceivedEvent.getSubCategoryName());
        System.out.println("@@@@@Current MRP:" + subscriptionableItemReceivedEvent.getCurrentMRP());
        System.out.println("@@@@@Current Purchase Price Per Unit:" + subscriptionableItemReceivedEvent.getCurrentPurchasePricePerUnit());
        System.out.println("@@@@@Current Stocks:" + subscriptionableItemReceivedEvent.getCurrentStockInUnits());

    }
*/
}
