package com.affaince.subscription.subscriptionableitem.registration.query.event;

import org.joda.time.LocalDate;

/**
 * Created by mandark on 18-07-2015.
 */

public class SubscriptionableItemReceivedEvent {
    private String itemId;
    private String batchId;
    private String categroyId;
    private String categoryName;
    private String subCategoryId;
    private String subCategoryName;
    private String productId;
    private double currentPurchasePricePerUnit;
    private double currentMRP;
    private int currentStockInUnits;

    private LocalDate currentPriceDate = LocalDate.now();

    public SubscriptionableItemReceivedEvent() {
    }

    public String getItemId() {
        return this.itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
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
