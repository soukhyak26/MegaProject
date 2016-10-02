package com.affaince.subscription.subscriber.command.event;

import org.joda.time.LocalDateTime;

/**
 * Created by rbsavaliya on 25-07-2015.
 */
public class OfferedPriceChangedEvent {
    private String productId;
    private String priceBucketId;
    private double offeredPricePerUnit;
    private LocalDateTime currentPriceDate;

    public OfferedPriceChangedEvent() {
    }

    public OfferedPriceChangedEvent(String productId, String priceBucketId, double offeredPricePerUnit, LocalDateTime currentPriceDate) {
        this.productId = productId;
        this.priceBucketId = priceBucketId;
        this.offeredPricePerUnit = offeredPricePerUnit;
        this.currentPriceDate = currentPriceDate;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getPriceBucketId() {
        return priceBucketId;
    }

    public void setPriceBucketId(String priceBucketId) {
        this.priceBucketId = priceBucketId;
    }

    public double getOfferedPricePerUnit() {
        return offeredPricePerUnit;
    }

    public void setOfferedPricePerUnit(double offeredPricePerUnit) {
        this.offeredPricePerUnit = offeredPricePerUnit;
    }

    public LocalDateTime getCurrentPriceDate() {
        return currentPriceDate;
    }

    public void setCurrentPriceDate(LocalDateTime currentPriceDate) {
        this.currentPriceDate = currentPriceDate;
    }
}