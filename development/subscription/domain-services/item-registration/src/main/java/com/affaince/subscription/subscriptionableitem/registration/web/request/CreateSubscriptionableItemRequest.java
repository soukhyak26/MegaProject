package com.affaince.subscription.subscriptionableitem.registration.web.request;


/**
 * Created by rbsavaliya on 19-07-2015.
 */
public class CreateSubscriptionableItemRequest {

    private String batchId;
    private String categoryId;
    private String categoryName;
    private String subCategoryId;
    private String getSubCategoryNmae;
    private String productId;
    private double currentMRP;
    private double currentOfferedPrice;
    private int currentStockInUnits;

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

    public String getGetSubCategoryNmae() {
        return getSubCategoryNmae;
    }

    public void setGetSubCategoryNmae(String getSubCategoryNmae) {
        this.getSubCategoryNmae = getSubCategoryNmae;
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
