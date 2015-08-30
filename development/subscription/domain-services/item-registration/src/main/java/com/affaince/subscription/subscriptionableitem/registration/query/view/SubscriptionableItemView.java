package com.affaince.subscription.subscriptionableitem.registration.query.view;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by rbsavaliya on 19-07-2015.
 */
@Document(collection = "SubscriptionableItem")
public class SubscriptionableItemView {

    @Id
    private String itemId;
    private String batchId;
    private String categoryId;
    private String categoryName;
    private String subCategoryId;
    private String subCategoryName;
    private String productId;
    private PriceParameters priceParameters;
    private ProjectionParameters projectionParameters;
    private RuleParameters ruleParameters;

    public SubscriptionableItemView(String itemId, String batchId, String categoryId, String categoryName, String subCategoryId, String subCategoryName, String productId, PriceParameters priceParameters, ProjectionParameters projectionParameters, RuleParameters ruleParameters) {
        this.itemId = itemId;
        this.batchId = batchId;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.subCategoryId = subCategoryId;
        this.subCategoryName = subCategoryName;
        this.productId = productId;
        this.priceParameters = priceParameters;
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

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public PriceParameters getPriceParameters() {
        return priceParameters;
    }

    public void setPriceParameters(PriceParameters priceParameters) {
        this.priceParameters = priceParameters;
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
