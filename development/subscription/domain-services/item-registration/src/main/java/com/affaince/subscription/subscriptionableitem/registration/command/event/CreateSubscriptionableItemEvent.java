package com.affaince.subscription.subscriptionableitem.registration.command.event;

import org.joda.time.LocalDate;

/**
 * Created by rbsavaliya on 19-07-2015.
 */
public class CreateSubscriptionableItemEvent {

    private String itemId;
    private String batchId;
    private String categoryId;
    private String categoryName;
    private String subCategoryId;
    private String subCategoryName;
    private String productId;
    private double currentMRP;
    private double purchasePricePerUnit;
    private double currentOfferedPrice;
    private int currentStockInUnits;
    private LocalDate currentPriceDate;

    public CreateSubscriptionableItemEvent(String itemId, String batchId, String categoryId, String categoryName, String subCategoryId, String subCategoryName, String productId, double currentMRP, double purchasePricePerUnit, double currentOfferedPrice, int currentStockInUnits, LocalDate currentPriceDate) {
        this.itemId = itemId;
        this.batchId = batchId;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.subCategoryId = subCategoryId;
        this.subCategoryName = subCategoryName;
        this.productId = productId;
        this.currentMRP = currentMRP;
        this.purchasePricePerUnit = purchasePricePerUnit;
        this.currentOfferedPrice = currentOfferedPrice;
        this.currentStockInUnits = currentStockInUnits;
        this.currentPriceDate = currentPriceDate;
    }

    public CreateSubscriptionableItemEvent() {
    }

    public String getItemId() {
        return itemId;
    }

    public String getSubCategoryName() {
        return this.subCategoryName;
    }

    public double getPurchasePricePerUnit() {
        return this.purchasePricePerUnit;
    }

    public void setPurchasePricePerUnit(double purchasePricePerUnit) {
        this.purchasePricePerUnit = purchasePricePerUnit;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getBatchId() {
        return batchId;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(String subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public double getCurrentMRP() {
        return currentMRP;
    }

    public void setCurrentMRP(double currentMRP) {
        this.currentMRP = currentMRP;
    }

    public double getCurrentOfferedPrice() {
        return currentOfferedPrice;
    }

    public void setCurrentOfferedPrice(double currentOfferedPrice) {
        this.currentOfferedPrice = currentOfferedPrice;
    }

    public int getCurrentStockInUnits() {
        return currentStockInUnits;
    }

    public void setCurrentStockInUnits(int currentStockInUnits) {
        this.currentStockInUnits = currentStockInUnits;
    }

    public LocalDate getCurrentPriceDate() {
        return currentPriceDate;
    }

    public void setCurrentPriceDate(LocalDate currentPriceDate) {
        this.currentPriceDate = currentPriceDate;
    }
}
