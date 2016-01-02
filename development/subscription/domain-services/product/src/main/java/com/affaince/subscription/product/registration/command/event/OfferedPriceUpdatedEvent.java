package com.affaince.subscription.product.registration.command.event;

import org.joda.time.LocalDate;

/**
 * Created by rbsavaliya on 25-07-2015.
 */
public class OfferedPriceUpdatedEvent {
    private String productId;
    private double offeredPrice;
    private LocalDate currentPriceDate;

    public OfferedPriceUpdatedEvent(String productId, double offeredPrice, LocalDate currentPriceDate) {
        this.productId = productId;
        this.offeredPrice = offeredPrice;
        this.currentPriceDate = currentPriceDate;
    }

    public OfferedPriceUpdatedEvent() {
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setCurrentPriceDate(LocalDate currentPrizeDate) {
        this.currentPriceDate = currentPrizeDate;
    }

    public double getOfferedPrice() {
        return this.offeredPrice;
    }

    public void setOfferedPrice(double offeredPrice) {
        this.offeredPrice = offeredPrice;
    }

    public LocalDate getCurrentPriceDate() {
        return this.currentPriceDate;
    }
}
