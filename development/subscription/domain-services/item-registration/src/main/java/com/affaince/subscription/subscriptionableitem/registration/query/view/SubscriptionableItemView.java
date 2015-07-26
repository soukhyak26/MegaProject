package com.affaince.subscription.subscriptionableitem.registration.query.view;

import org.joda.time.LocalDate;
import org.springframework.data.annotation.Id;

/**
 * Created by rbsavaliya on 19-07-2015.
 */
public class SubscriptionableItemView {

    @Id
    private String itemId;
    private String batchId;
    private String categoryId;
    private String categoryName;
    private String subCategoryId;
    private String subCategoryName;
    private String productId;
    private double currentMRP;
    private int currentStockInUnits;
    private LocalDate currentPrizeDate;
    private ProjectionParameters projectionParameters;

    public SubscriptionableItemView(String id, String batchId, String categoryId, String categoryName, String subCategoryId, String subCategoryNmae, String productId, double currentMRP, int currentStockInUnits, LocalDate currentPrizeDate, ProjectionParameters projectionParameters) {
        this.itemId = itemId;
        this.batchId = batchId;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.subCategoryId = subCategoryId;
        this.subCategoryName = subCategoryNmae;
        this.productId = productId;
        this.currentMRP = currentMRP;
        this.currentStockInUnits = currentStockInUnits;
        this.currentPrizeDate = currentPrizeDate;
        this.projectionParameters = projectionParameters;
    }

    public String getItemId() {
        return itemId;
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

    public String getGetSubCategoryName() {
        return subCategoryName;
    }

    public void setGetSubCategoryName(String subCategoryNmae) {
        this.subCategoryName = subCategoryNmae;
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

    public int getCurrentStockInUnits() {
        return currentStockInUnits;
    }

    public void setCurrentStockInUnits(int currentStockInUnits) {
        this.currentStockInUnits = currentStockInUnits;
    }

    public LocalDate getCurrentPrizeDate() {
        return currentPrizeDate;
    }

    public void setCurrentPrizeDate(LocalDate currentPrizeDate) {
        this.currentPrizeDate = currentPrizeDate;
    }

    public ProjectionParameters getProjectionParameters() {
        return projectionParameters;
    }

    public void setProjectionParameters(ProjectionParameters projectionParameters) {
        this.projectionParameters = projectionParameters;
    }
}
