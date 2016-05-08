package com.affaince.subscription.business.command;

import org.joda.time.LocalDate;

/**
 * Created by anayonkar on 8/5/16.
 */
public class ProductStatusReceivedCommand {
    private String productId;
    private double currentPurchasePrice;
    private double currentMRP;
    private int currentStockInUnits;
    private LocalDate currentPriceDate;

    public ProductStatusReceivedCommand(String productId, double currentPurchasePrice, double currentMRP, int currentStockInUnits, LocalDate currentPriceDate) {
        this.productId = productId;
        this.currentPurchasePrice = currentPurchasePrice;
        this.currentMRP = currentMRP;
        this.currentStockInUnits = currentStockInUnits;
        this.currentPriceDate = currentPriceDate;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public double getCurrentPurchasePrice() {
        return currentPurchasePrice;
    }

    public void setCurrentPurchasePrice(double currentPurchasePrice) {
        this.currentPurchasePrice = currentPurchasePrice;
    }

    public double getCurrentMRP() {
        return currentMRP;
    }

    public void setCurrentMRP(double currentMRP) {
        this.currentMRP = currentMRP;
    }

    public int getCurrentStockInUnits() {
        return currentStockInUnits;
    }

    public void setCurrentStockInUnits(int currentStockInUnits) {
        this.currentStockInUnits = currentStockInUnits;
    }

    public LocalDate getCurrentPriceDate() {
        return currentPriceDate;
    }

    public void setCurrentPriceDate(LocalDate currentPriceDate) {
        this.currentPriceDate = currentPriceDate;
    }

    public String getBusinessAccountId() {
        return Integer.valueOf(currentPriceDate.getYear()).toString();
    }

    public double getTotalPurchaseCost() {
        return currentPurchasePrice * currentStockInUnits;
    }
}
