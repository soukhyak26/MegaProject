package com.affaince.subscription.business.command;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;
import org.joda.time.LocalDate;

/**
 * Created by mandar on 26-01-2017.
 */
public class AddToPurchaseCostAccountCommand {
    @TargetAggregateIdentifier
    private Integer Id;
    private String productId;
    private long getTotalProductSubscriptionCount;
    private double currentProductPurchasePrice;
    private LocalDate provisionsDate;

    public AddToPurchaseCostAccountCommand(Integer id, String productId, long getTotalProductSubscriptionCount, double currentProductPurchasePrice, LocalDate provisionsDate ) {
        Id = id;
        this.productId = productId;
        this.getTotalProductSubscriptionCount = getTotalProductSubscriptionCount;
        this.currentProductPurchasePrice = currentProductPurchasePrice;
        this.provisionsDate=provisionsDate;
    }

    public Integer getId() {
        return Id;
    }

    public String getProductId() {
        return productId;
    }

    public long getGetTotalProductSubscriptionCount() {
        return getTotalProductSubscriptionCount;
    }

    public double getCurrentProductPurchasePrice() {
        return currentProductPurchasePrice;
    }

    public LocalDate getProvisionsDate() {
        return provisionsDate;
    }
}
