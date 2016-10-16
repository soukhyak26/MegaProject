package com.affaince.subscription.product.web.request;

/**
 * Created by mandar on 16-10-2016.
 */
public class RegisterOpeningPriceRequest {
    private double openingPrice;
    private double purchasePrice;
    private double MRP;

    public double getOpeningPrice() {
        return openingPrice;
    }

    public void setOpeningPrice(double openingPrice) {
        this.openingPrice = openingPrice;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public double getMRP() {
        return MRP;
    }

    public void setMRP(double MRP) {
        this.MRP = MRP;
    }
}
