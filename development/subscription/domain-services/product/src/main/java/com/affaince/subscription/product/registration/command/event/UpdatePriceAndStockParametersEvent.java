package com.affaince.subscription.product.registration.command.event;

import org.joda.time.LocalDate;

/**
 * Created by rbsavaliya on 25-07-2015.
 */
public class UpdatePriceAndStockParametersEvent {
    private String productId;
    private double currentMRP;
    private int currentStockInUnits;
    private LocalDate currentPrizeDate;

    public UpdatePriceAndStockParametersEvent(String productId, double currentMRP, int currentStockInUnits, LocalDate currentPriceDate) {
        this.productId = productId;
        this.currentMRP = currentMRP;
        this.currentStockInUnits = currentStockInUnits;
        this.currentPrizeDate = currentPriceDate;
    }

    public UpdatePriceAndStockParametersEvent() {
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
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

    public LocalDate getCurrentPrizeDate() {
        return currentPrizeDate;
    }

    public void setCurrentPrizeDate(LocalDate currentPrizeDate) {
        this.currentPrizeDate = currentPrizeDate;
    }

}
