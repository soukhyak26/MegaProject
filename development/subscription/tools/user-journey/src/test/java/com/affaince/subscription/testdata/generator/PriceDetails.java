package com.affaince.subscription.testdata.generator;

/**
 * Created by rbsavaliya on 30-12-2016.
 */
public class PriceDetails {
    private int openingPrice;
    private int purchasePrice;
    private int MRP;

    public PriceDetails(int openingPrice, int purchasePrice, int MRP) {
        this.openingPrice = openingPrice;
        this.purchasePrice = purchasePrice;
        this.MRP = MRP;
    }

    public int getOpeningPrice() {
        return openingPrice;
    }

    public int getPurchasePrice() {
        return purchasePrice;
    }

    public int getMRP() {
        return MRP;
    }
}
