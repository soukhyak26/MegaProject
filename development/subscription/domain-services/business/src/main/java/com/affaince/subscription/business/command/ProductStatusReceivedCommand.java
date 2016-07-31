package com.affaince.subscription.business.command;

import org.joda.time.LocalDate;

/**
 * Created by anayonkar on 8/5/16.
 */
public class ProductStatusReceivedCommand {
    private String productId;
    private Double currentPurchasePrice;
    private Double currentMRP;
    private Integer currentStockInUnits;
    private LocalDate currentPriceDate;

    public ProductStatusReceivedCommand(String productId, Double currentPurchasePrice, Double currentMRP, Integer currentStockInUnits, LocalDate currentPriceDate) {
        this.productId = productId;
        this.currentPurchasePrice = currentPurchasePrice;
        this.currentMRP = currentMRP;
        this.currentStockInUnits = currentStockInUnits;
        this.currentPriceDate = currentPriceDate;
    }

    public ProductStatusReceivedCommand(Double currentPurchasePrice, Integer currentStockInUnits) {
        this.currentPurchasePrice = currentPurchasePrice;
        this.currentStockInUnits = currentStockInUnits;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Double getCurrentPurchasePrice() {
        return currentPurchasePrice;
    }

    public void setCurrentPurchasePrice(Double currentPurchasePrice) {
        this.currentPurchasePrice = currentPurchasePrice;
    }

    public Double getCurrentMRP() {
        return currentMRP;
    }

    public void setCurrentMRP(Double currentMRP) {
        this.currentMRP = currentMRP;
    }

    public Integer getCurrentStockInUnits() {
        return currentStockInUnits;
    }

    public void setCurrentStockInUnits(Integer currentStockInUnits) {
        this.currentStockInUnits = currentStockInUnits;
    }

    public LocalDate getCurrentPriceDate() {
        return currentPriceDate;
    }

    public void setCurrentPriceDate(LocalDate currentPriceDate) {
        this.currentPriceDate = currentPriceDate;
    }

    public String getBusinessAccountId() {
        return currentPriceDate == null ? null : Integer.valueOf(currentPriceDate.getYear()).toString();
    }

    public Double getTotalPurchaseCost() {
        return currentPurchasePrice * currentStockInUnits;
    }
}
