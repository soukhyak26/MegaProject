package com.affaince.subscription.subscriptionableitem.registration.query.view;

import org.joda.time.LocalDate;

/**
 * Created by rbsavaliya on 27-08-2015.
 */
public class PriceParameters {
    private double currentPurchasePricePerUnit;
    private double currentMRP;
    private int currentStockInUnits;
    private double currentOfferedPrice;
    private LocalDate currentPriceDate;

    public PriceParameters(double currentPurchasePricePerUnit, double currentMRP, int currentStockInUnits, double currentOfferedPrice, LocalDate currentPriceDate) {
        this.currentPurchasePricePerUnit = currentPurchasePricePerUnit;
        this.currentMRP = currentMRP;
        this.currentStockInUnits = currentStockInUnits;
        this.currentOfferedPrice = currentOfferedPrice;
        this.currentPriceDate = currentPriceDate;
    }

    public double getCurrentPurchasePricePerUnit() {
        return currentPurchasePricePerUnit;
    }

    public void setCurrentPurchasePricePerUnit(double currentPurchasePricePerUnit) {
        this.currentPurchasePricePerUnit = currentPurchasePricePerUnit;
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

    public double getCurrentOfferedPrice() {
        return currentOfferedPrice;
    }

    public void setCurrentOfferedPrice(double currentOfferedPrice) {
        this.currentOfferedPrice = currentOfferedPrice;
    }

    public LocalDate getCurrentPriceDate() {
        return currentPriceDate;
    }

    public void setCurrentPriceDate(LocalDate currentPriceDate) {
        this.currentPriceDate = currentPriceDate;
    }
}
