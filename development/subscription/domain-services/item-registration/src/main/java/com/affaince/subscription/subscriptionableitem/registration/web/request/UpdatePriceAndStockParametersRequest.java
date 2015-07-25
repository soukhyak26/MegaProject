package com.affaince.subscription.subscriptionableitem.registration.web.request;

/**
 * Created by rbsavaliya on 25-07-2015.
 */
public class UpdatePriceAndStockParametersRequest {

    private double currentMRP;
    private int currentStockInUnits;

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
}
