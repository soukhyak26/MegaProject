package com.affaince.subscription.product.command;

import org.joda.time.LocalDateTime;

/**
 * Created by mandar on 03-01-2017.
 */
public class RecalculateOfferPriceCommand {

    private final String productId;
    private final String taggedPriceVersionId;
    private final double newPurchasePrice;
    private final double newMRP;
    private final LocalDateTime newTaggedPriceVersionStartDate;
    private final LocalDateTime newTaggedPriceVersionEndDate;

    public RecalculateOfferPriceCommand(String productId,String taggedPriceVersionId, double newPurchasePrice, double newMRP, LocalDateTime newTaggedPriceVersionStartDate, LocalDateTime newTaggedPriceVersionEndDate) {
        this.productId=productId;
        this.taggedPriceVersionId = taggedPriceVersionId;
        this.newPurchasePrice = newPurchasePrice;
        this.newMRP = newMRP;
        this.newTaggedPriceVersionStartDate = newTaggedPriceVersionStartDate;
        this.newTaggedPriceVersionEndDate = newTaggedPriceVersionEndDate;
    }

    public String getProductId() {
        return productId;
    }

    public String getTaggedPriceVersionId() {
        return taggedPriceVersionId;
    }

    public double getNewPurchasePrice() {
        return newPurchasePrice;
    }

    public double getNewMRP() {
        return newMRP;
    }

    public LocalDateTime getNewTaggedPriceVersionStartDate() {
        return newTaggedPriceVersionStartDate;
    }

    public LocalDateTime getNewTaggedPriceVersionEndDate() {
        return newTaggedPriceVersionEndDate;
    }
}
