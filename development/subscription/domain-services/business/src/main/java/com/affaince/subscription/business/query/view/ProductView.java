package com.affaince.subscription.business.query.view;

import com.affaince.subscription.common.type.ProductStatus;
import com.affaince.subscription.common.type.SensitivityCharacteristic;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;

/**
 * Created by mandark on 26-01-2016.
 */
@Document(collection = "ProductView")
public class ProductView {
    @Id
    private String productId;
    private double purchasePrice;
    private double MRP;
    private Map<SensitivityCharacteristic, Double> sensitiveTo;
    private ProductStatus productStatus;

    /*
        private long totalMonthlySubscriptions;
        private double totalMonthlySaleAmount;
    */
    private double currentOperatingExpensePerUnit;

    public ProductView(String productId, Map<SensitivityCharacteristic, Double> sensitiveTo, double purchasePrice, double MRP, ProductStatus productStatus) {
        this.productId = productId;
        this.sensitiveTo = sensitiveTo;
        this.purchasePrice = purchasePrice;
        this.MRP = MRP;
/*
        switch (sensitiveTo) {
            case 0:
                this.sensitiveTo.put(SensitivityCharacteristic.NONE, 1.0);
                break;
            case 1:
                this.sensitiveTo.put(SensitivityCharacteristic.ELECTRICITY_CONSUMPTION, sensitivityWeight);
                break;
            case 2:
                this.sensitiveTo.put(SensitivityCharacteristic.STORAGE_SPACE_CONSUMPTION, sensitivityWeight);
                break;
            default:
                this.sensitiveTo.put(SensitivityCharacteristic.NONE, 1.0);
                ;
                break;

        }
*/
        this.productStatus = productStatus;
/*
        this.totalMonthlySubscriptions = totalMonthlySubscriptions;
        this.totalMonthlySaleAmount = this.totalMonthlySubscriptions * this.MRP;
*/
    }


    public String getProductId() {
        return this.productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public Map<SensitivityCharacteristic, Double> getSensitiveTo() {
        return this.sensitiveTo;
    }

    public void setSensitiveTo(Map<SensitivityCharacteristic, Double> sensitiveTo) {
        this.sensitiveTo = sensitiveTo;
    }

    public double getCurrentOperatingExpensePerUnit() {
        return this.currentOperatingExpensePerUnit;
    }

    public void setCurrentOperatingExpensePerUnit(double currentOperatingExpensePerUnit) {
        this.currentOperatingExpensePerUnit = currentOperatingExpensePerUnit;
    }

    public ProductStatus getProductStatus() {
        return productStatus;
    }

    public void setProductStatus(ProductStatus productStatus) {
        this.productStatus = productStatus;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public double getMRP() {
        return MRP;
    }
}
