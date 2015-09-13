package com.affaince.subscription.subscriptionableitem.registration.web.request;


import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotNull;

/**
 * Created by rbsavaliya on 19-07-2015.
 */
public class CreateSubscriptionableItemRequest {

    @NotNull
    private String batchId;
    @NotNull
    private String categoryId;
    @NotNull
    private String categoryName;
    @NotNull
    private String subCategoryId;
    @NotNull
    private String subCategoryName;
    @NotNull
    private String productId;
    @DecimalMin(value = "0.1")
    @Digits(integer = 6, fraction = 2)
    private double currentPurchasePricePerUnit;
    @DecimalMin(value = "0.1")
    @Digits(integer = 6, fraction = 2)
    private double currentMRP;
    @DecimalMin(value = "0.1")
    @Digits(integer = 6, fraction = 2)
    private double currentOfferedPrice;
    @Digits(integer = 6, fraction = 0)
    private int currentStockInUnits;

    public double getCurrentPurchasePricePerUnit() {
        return currentPurchasePricePerUnit;
    }

    public void setCurrentPurchasePricePerUnit(double currentPurchasePricePerUnit) {
        this.currentPurchasePricePerUnit = currentPurchasePricePerUnit;
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

    public String getSubCategoryName() {
        return subCategoryName;
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
}
