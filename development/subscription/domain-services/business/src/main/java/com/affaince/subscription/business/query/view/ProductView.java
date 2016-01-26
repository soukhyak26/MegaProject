package com.affaince.subscription.business.query.view;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by mandark on 26-01-2016.
 */
@Document(collection = "ProductView")
public class ProductView {
    @Id
    private String productId;
    private double purchasePrice;
    private double MRP;
    private double offeredPrice;

    public ProductView(String productId, double purchasePrice, double MRP, double offeredPrice) {
        this.productId = productId;
        this.purchasePrice = purchasePrice;
        this.MRP = MRP;
        this.offeredPrice = offeredPrice;
    }

    public String getProductId() {
        return this.productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public double getPurchasePrice() {
        return this.purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public double getMRP() {
        return this.MRP;
    }

    public void setMRP(double MRP) {
        this.MRP = MRP;
    }

    public double getOfferedPrice() {
        return this.offeredPrice;
    }

    public void setOfferedPrice(double offeredPrice) {
        this.offeredPrice = offeredPrice;
    }
}
