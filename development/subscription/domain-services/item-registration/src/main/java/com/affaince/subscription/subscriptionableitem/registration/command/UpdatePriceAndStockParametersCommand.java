package com.affaince.subscription.subscriptionableitem.registration.command;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;
import org.joda.time.LocalDate;

/**
 * Created by rbsavaliya on 25-07-2015.
 */
public class UpdatePriceAndStockParametersCommand {
    @TargetAggregateIdentifier
    private String itemId;
    private double currentMRP;
    private int currentStockInUnits;
    private LocalDate currentPrizeDate;

    public UpdatePriceAndStockParametersCommand(String itemId, double currentMRP, int currentStockInUnits, LocalDate currentPrizeDate) {
        this.itemId = itemId;
        this.currentMRP = currentMRP;
        this.currentStockInUnits = currentStockInUnits;
        this.currentPrizeDate = currentPrizeDate;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
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
