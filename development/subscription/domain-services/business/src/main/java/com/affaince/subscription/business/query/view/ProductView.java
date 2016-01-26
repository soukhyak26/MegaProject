package com.affaince.subscription.business.query.view;

import com.affaince.subscription.common.type.SensitivityCharacteristic;
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
    private SensitivityCharacteristic sensitiveTo;

    public ProductView(String productId, double purchasePrice, double MRP, double offeredPrice, int sensitiveTo) {
        this.productId = productId;
        this.purchasePrice = purchasePrice;
        this.MRP = MRP;
        this.offeredPrice = offeredPrice;
        switch (sensitiveTo) {
            case 0:
                this.sensitiveTo = SensitivityCharacteristic.NONE;
                break;
            case 1:
                this.sensitiveTo = SensitivityCharacteristic.ELECTRICITY_CONSUMPTION;
                break;
            case 2:
                this.sensitiveTo = SensitivityCharacteristic.STORAGE_SPACE_CONSUMPTION;
                break;
            default:
                this.sensitiveTo = SensitivityCharacteristic.NONE;
                break;

        }
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

    public SensitivityCharacteristic getSensitiveTo() {
        return this.sensitiveTo;
    }

    public void setSensitiveTo(SensitivityCharacteristic sensitiveTo) {
        this.sensitiveTo = sensitiveTo;
    }
}
