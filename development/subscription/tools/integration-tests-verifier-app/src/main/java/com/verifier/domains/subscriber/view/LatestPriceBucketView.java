package com.verifier.domains.subscriber.view;

import org.joda.time.LocalDateTime;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by rbsavaliya on 04-09-2016.
 */
@Document(collection = "LatestPriceBucketView")
public class LatestPriceBucketView {
    @Id
    private String productId;
    private String priceBucketId;
    private double offeredPricePerUnit;
    private LocalDateTime currentPriceDate;

    public LatestPriceBucketView() {
    }

    public LatestPriceBucketView(String productId, String priceBucketId, double offeredPricePerUnit, LocalDateTime currentPriceDate) {
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
