package com.affaince.subscription.subscriptionableitem.registration.command.domain;

import com.affaince.subscription.subscriptionableitem.registration.command.event.CreateSubscriptionableItemEvent;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;

import java.time.LocalDate;

/**
 * Created by rbsavaliya on 19-07-2015.
 */
public class SubscriptionableItem extends AbstractAnnotatedAggregateRoot{

    @AggregateIdentifier
    private String id;
    private String batchId;
    private String categoryId;
    private String categoryName;
    private String subCategoryId;
    private String subCategoryNmae;
    private String productId;
    private double currentMRP;
    private int currentStockInUnits;
    private LocalDate currentPrizeDate;

    public SubscriptionableItem () {

    }

    public SubscriptionableItem(String id, String batchId, String categoryId, String categoryName, String subCategoryId, String subCategoryNmae, String productId, double currentMRP, int currentStockInUnits, LocalDate currentPrizeDate) {
        /*this.id = id;
        this.batchId = batchId;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.subCategoryId = subCategoryId;
        this.getSubCategoryNmae = getSubCategoryNmae;
        this.productId = productId;
        this.currentMRP = currentMRP;
        this.currentStockInUnits = currentStockInUnits;
        this.currentPrizeDate = currentPrizeDate;*/
        apply (new CreateSubscriptionableItemEvent(id, batchId, categoryId, categoryName, subCategoryId, subCategoryNmae, productId, currentMRP, currentStockInUnits, currentPrizeDate));
    }

    @EventSourcingHandler
    public void on (CreateSubscriptionableItemEvent event) {
        this.id = event.getItemId();
        this.batchId = event.getBatchId();
        this.categoryId = event.getCategoryId();
        this.categoryName = event.getCategoryName();
        this.subCategoryId = event.getSubCategoryId();
        this.subCategoryNmae = event.getGetSubCategoryNmae();
        this.productId = event.getProductId();
        this.currentMRP = event.getCurrentMRP();
        this.currentPrizeDate = event.getCurrentPrizeDate();
        this.currentStockInUnits = currentStockInUnits;
    }
}
