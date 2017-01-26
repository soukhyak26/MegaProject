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
    private long totalAnticipatedSubscriptions;
    private double currentOperatingExpensePerUnit;
    private double productPurchaseBudgetedAmount;

    public ProductView(String productId, Map<SensitivityCharacteristic, Double> sensitiveTo, double purchasePrice, double MRP, ProductStatus productStatus) {
        this.productId = productId;
        this.sensitiveTo = sensitiveTo;
        this.purchasePrice = purchasePrice;
        this.MRP = MRP;
        this.productStatus = productStatus;
        this.productPurchaseBudgetedAmount = 0;

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

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public void setMRP(double MRP) {
        this.MRP = MRP;
    }

    public double getProductPurchaseBudgetedAmount() {
        return productPurchaseBudgetedAmount;
    }

    public void setProductPurchaseBudgetedAmount(double productPurchaseBudgetedAmount) {
        this.productPurchaseBudgetedAmount = productPurchaseBudgetedAmount;
    }

    public long getTotalAnticipatedSubscriptions() {
        return totalAnticipatedSubscriptions;
    }

    public void setTotalAnticipatedSubscriptions(long totalAnticipatedSubscriptions) {
        this.totalAnticipatedSubscriptions = totalAnticipatedSubscriptions;
    }
}
