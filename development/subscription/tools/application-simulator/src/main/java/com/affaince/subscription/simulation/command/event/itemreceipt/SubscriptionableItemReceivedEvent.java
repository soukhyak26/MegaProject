package com.affaince.subscription.integration.command.event.itemreceipt;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;
import org.joda.time.LocalDate;

/**
 * Created by mandark on 18-07-2015.
 */
@CsvRecord(separator = ",", skipFirstLine = true)
public class SubscriptionableItemReceivedEvent {

    @DataField(name = "ITEM_ID", pos = 1, trim = true)
    private String itemId;

    @DataField(name = "BATCH_ID", pos = 2, trim = true)
    private String batchId;
    @DataField(name = "CATEGORY_ID", pos = 3, trim = true)
    private String categroyId;
    @DataField(name = "CATEGORY_NAME", pos = 4, trim = true)
    private String categoryName;
    @DataField(name = "SUBCATEGORY_ID", pos = 5, trim = true)
    private String subCategoryId;
    @DataField(name = "SUBCATEGORY_NAME", pos = 6, trim = true)
    private String subCategoryName;
    @DataField(name = "PRODUCT_ID", pos = 7, trim = true)
    private String productId;
    @DataField(name = "CURRENT_PURCHASE_PRICE_PER_UNIT", pos = 8, trim = true)
    private double currentPurchasePricePerUnit;
    @DataField(name = "CURRENT_MRP", pos = 9, trim = true)
    private double currentMRP;
    @DataField(name = "CURRENT_STOCK", pos = 10, trim = true)
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

    public String getSubCategoryName() {
        return this.subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getCategoryId() {
        return categroyId;
    }

    public void setCategoryId(String categroyId) {
        this.categroyId = categroyId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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

    public String getItemId() {
        return this.itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    @Override
    public String toString() {
        return "SubscriptionableItemReceivedEvent{" +
                "itemId='" + itemId + '\'' +
                ", batchId='" + batchId + '\'' +
                ", categroyId='" + categroyId + '\'' +
                ", categoryName='" + categoryName + '\'' +
                ", subCategoryId='" + subCategoryId + '\'' +
                ", subCategoryName='" + subCategoryName + '\'' +
                ", productId='" + productId + '\'' +
                ", currentPurchasePricePerUnit=" + currentPurchasePricePerUnit +
                ", currentMRP=" + currentMRP +
                ", currentStockInUnits=" + currentStockInUnits +
                ", currentPriceDate=" + currentPriceDate +
                '}';
    }

}
