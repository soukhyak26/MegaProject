package com.affaince.subscription.simulation.command.event.productstatus;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;
import org.joda.time.LocalDate;

/**
 * Created by mandark on 18-07-2015.
 */
@CsvRecord(separator = ",", skipFirstLine = true)
public class SubscriptionableItemReceivedEvent {
    @DataField(name = "PRODUCT_ID", pos = 1, trim = true)
    private String productId;

    @DataField(name = "CATEGORY_ID", pos = 2, trim = true)
    private String categroyId;
    @DataField(name = "SUBCATEGORY_ID", pos = 3, trim = true)
    private String subCategoryId;
    @DataField(name = "CURRENT_PURCHASE_PRICE_PER_UNIT", pos = 4, trim = true)
    private double currentPurchasePricePerUnit;
    @DataField(name = "CURRENT_MRP", pos = 5, trim = true)
    private double currentMRP;
    @DataField(name = "CURRENT_STOCK", pos = 6, trim = true)
    private int currentStockInUnits;

    private LocalDate currentPriceDate = LocalDate.now();

    public SubscriptionableItemReceivedEvent() {
    }

    public String getSubCategoryId() {
        return this.subCategoryId;
    }

    public void setSubCategoryId(String subCategoryId) {
        this.subCategoryId = subCategoryId;
    }


    public String getCategoryId() {
        return categroyId;
    }

    public void setCategoryId(String categroyId) {
        this.categroyId = categroyId;
    }


    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
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

    public LocalDate getCurrentPriceDate() {
        return this.currentPriceDate;
    }

    public void setCurrentPriceDate(LocalDate currentPriceDate) {
        this.currentPriceDate = currentPriceDate;
    }


    @Override
    public String toString() {
        return "SubscriptionableItemReceivedEvent{" +
                ", categroyId='" + categroyId + '\'' +
                ", subCategoryId='" + subCategoryId + '\'' +
                ", productId='" + productId + '\'' +
                ", currentPurchasePricePerUnit=" + currentPurchasePricePerUnit +
                ", currentMRP=" + currentMRP +
                ", currentStockInUnits=" + currentStockInUnits +
                ", currentPriceDate=" + currentPriceDate +
                '}';
    }

}
