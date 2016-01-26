package com.affaince.subscription.business.query.view;

import com.affaince.subscription.common.type.SensitivityCharacteristic;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Map;
import java.util.TreeMap;

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
    private Map<SensitivityCharacteristic, Double> sensitiveTo;
    private long totalMonthlySubscriptions;
    private double totalMonthlySaleAmount;
    private double currentOperatingExpensePerUnit;

    public ProductView(String productId, double purchasePrice, double MRP, double offeredPrice, int sensitiveTo, double sensitivityWeight, long totalMonthlySubscriptions) {
        this.productId = productId;
        this.purchasePrice = purchasePrice;
        this.MRP = MRP;
        this.offeredPrice = offeredPrice;
        this.sensitiveTo = new TreeMap<>();
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
        this.totalMonthlySubscriptions = totalMonthlySubscriptions;
        this.totalMonthlySaleAmount = this.totalMonthlySubscriptions * this.MRP;
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

    public Map<SensitivityCharacteristic, Double> getSensitiveTo() {
        return this.sensitiveTo;
    }

    public void setSensitiveTo(Map<SensitivityCharacteristic, Double> sensitiveTo) {
        this.sensitiveTo = sensitiveTo;
    }

    public long getTotalMonthlySubscriptions() {
        return this.totalMonthlySubscriptions;
    }

    public void setTotalMonthlySubscriptions(long totalMonthlySubscriptions) {
        this.totalMonthlySubscriptions = totalMonthlySubscriptions;
    }

    public double getTotalMonthlySaleAmount() {
        return this.totalMonthlySaleAmount;
    }

    public void setTotalMonthlySaleAmount(double totalMonthlySaleAmount) {
        this.totalMonthlySaleAmount = totalMonthlySaleAmount;
    }

    public double getCurrentOperatingExpensePerUnit() {
        return this.currentOperatingExpensePerUnit;
    }

    public void setCurrentOperatingExpensePerUnit(double currentOperatingExpensePerUnit) {
        this.currentOperatingExpensePerUnit = currentOperatingExpensePerUnit;
    }
}
