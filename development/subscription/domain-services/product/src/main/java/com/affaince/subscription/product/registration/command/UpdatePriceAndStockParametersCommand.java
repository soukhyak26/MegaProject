package com.affaince.subscription.product.registration.command;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;
import org.joda.time.LocalDate;

/**
 * Created by rbsavaliya on 25-07-2015.
 */
public class UpdatePriceAndStockParametersCommand {
    @TargetAggregateIdentifier
    private String productId;
    private double currentMRP;
    private int currentStockInUnits;
    private LocalDate currentPrizeDate;

    public UpdatePriceAndStockParametersCommand(String productId, double currentMRP, int currentStockInUnits, LocalDate currentPrizeDate) {
        this.productId = productId;
        this.currentMRP = currentMRP;
        this.currentStockInUnits = currentStockInUnits;
        this.currentPrizeDate = currentPrizeDate;
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
