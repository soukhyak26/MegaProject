package com.affaince.subscription.subscriptionableitem.registration.query.view;

import org.apache.commons.codec.language.bm.Rule;
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
    private RuleParameters ruleParameters;

    public SubscriptionableItemView(String id, String batchId, String categoryId, String categoryName, String subCategoryId, String subCategoryName, String productId, double currentMRP, int currentStockInUnits, LocalDate currentPrizeDate, ProjectionParameters projectionParameters, RuleParameters ruleParameters) {
        this.itemId = itemId;
        this.batchId = batchId;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.subCategoryId = subCategoryId;
        this.subCategoryName = subCategoryName;
        this.productId = productId;
        this.currentMRP = currentMRP;
        this.currentStockInUnits = currentStockInUnits;
        this.currentPrizeDate = currentPrizeDate;
        this.projectionParameters = projectionParameters;
        this.ruleParameters = ruleParameters;
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

    public String getSubCategoryName() {
        return subCategoryName;
    }

    public void setSubCategoryName(String subCategoryNmae) {
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

    public RuleParameters getRuleParameters() {
        return ruleParameters;
    }

    public void setRuleParameters(RuleParameters ruleParameters) {
        this.ruleParameters = ruleParameters;
    }
}
