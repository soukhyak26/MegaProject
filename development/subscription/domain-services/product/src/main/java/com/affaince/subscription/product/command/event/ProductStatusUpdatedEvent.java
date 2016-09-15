package com.affaince.subscription.product.command.event;

import org.joda.time.LocalDateTime;

/**
 * Created by mandark on 27-12-2015.
 */
public class ProductStatusUpdatedEvent {
    private String productId;
    private double currentPurchasePrice;
    private double currentMRP;
    private int currentStockInUnits;
    private LocalDateTime currentPriceDate;

    public ProductStatusUpdatedEvent(String productId, double currentPurchasePrice, double currentMRP, int currentStockInUnits, LocalDateTime currentPrizeDate) {
        this.productId = productId;
        this.currentPurchasePrice = currentPurchasePrice;
        this.currentMRP = currentMRP;
        this.currentStockInUnits = currentStockInUnits;
        this.currentPriceDate = currentPrizeDate;
    }

    public String getProductId() {
        return this.productId;
    }

    public double getCurrentPurchasePrice() {
        return this.currentPurchasePrice;
    }

    public double getCurrentMRP() {
        return this.currentMRP;
    }

    public int getCurrentStockInUnits() {
        return this.currentStockInUnits;
    }

    public LocalDateTime getCurrentPriceDate() {
        return this.currentPriceDate;
    }
}
