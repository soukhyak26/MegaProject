package com.affaince.subscription.product.command;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;
import org.joda.time.LocalDateTime;

/**
 * Created by mandark on 02-04-2016.
 */
public class ReceiveProductStatusCommand {
    @TargetAggregateIdentifier
    private String productId;
    private double currentPurchasePrice;
    private double currentMRP;
    private int currentStockInUnits;
    private LocalDateTime currentPrizeDate;

    public ReceiveProductStatusCommand(String productId, double currentPurchasePrice, double currentMRP, int currentStockInUnits, LocalDateTime currentPrizeDate) {
        this.productId = productId;
        this.currentPurchasePrice = currentPurchasePrice;
        this.currentMRP = currentMRP;
        this.currentStockInUnits = currentStockInUnits;
        this.currentPrizeDate = currentPrizeDate;
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

    public LocalDateTime getCurrentPrizeDate() {
        return this.currentPrizeDate;
    }
}
