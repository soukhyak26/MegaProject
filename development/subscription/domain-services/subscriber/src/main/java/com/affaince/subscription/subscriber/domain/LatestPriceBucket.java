package com.affaince.subscription.subscriber.domain;

import org.joda.time.LocalDateTime;


/**
 * Created by rbsavaliya on 04-09-2016.
 */
public class LatestPriceBucket {

    private String productId;
    private String priceBucketId;
    private double offeredPricePerUnit;
    private LocalDateTime currentPriceDate;

    public LatestPriceBucket(String productId, String priceBucketId, double offeredPricePerUnit, LocalDateTime currentPriceDate) {
        this.productId = productId;
        this.priceBucketId = priceBucketId;
        this.offeredPricePerUnit = offeredPricePerUnit;
        this.currentPriceDate = currentPriceDate;
    }

    public String getProductId() {
        return productId;
    }

    public String getPriceBucketId() {
        return priceBucketId;
    }

    public double getOfferedPricePerUnit() {
        return offeredPricePerUnit;
    }

    public LocalDateTime getCurrentPriceDate() {
        return currentPriceDate;
    }
}
