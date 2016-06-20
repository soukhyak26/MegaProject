package com.affaince.subscription.product.web.request;

/**
 * Created by rbsavaliya on 25-07-2015.
 */
public class UpdateProductStatusRequest {

    private double productId;
    private double currentPurchasePrice;
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

    public double getProductId() {
        return this.productId;
    }

    public void setProductId(double productId) {
        this.productId = productId;
    }

    public double getCurrentPurchasePrice() {
        return this.currentPurchasePrice;
    }

    public void setCurrentPurchasePrice(double currentPurchasePrice) {
        this.currentPurchasePrice = currentPurchasePrice;
    }

}
