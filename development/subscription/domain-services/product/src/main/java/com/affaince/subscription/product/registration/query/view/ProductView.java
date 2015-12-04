package com.affaince.subscription.product.registration.query.view;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by rbsavaliya on 19-07-2015.
 */
@Document(collection = "Product")
public class ProductView {

    @Id
    private String itemId;
    private String batchId;
    private String categoryId;
    private String categoryName;
    private String subCategoryId;
    private String subCategoryName;
    private String shoppingSiteProductId;
    private PriceParameters priceParameters;
    private ProjectionParameters projectionParameters;
    private RuleParameters ruleParameters;

    public ProductView(String itemId, String batchId, String categoryId, String categoryName, String subCategoryId, String subCategoryName, String shoppingSiteProductId, PriceParameters priceParameters, ProjectionParameters projectionParameters, RuleParameters ruleParameters) {
        this.itemId = itemId;
        this.batchId = batchId;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.subCategoryId = subCategoryId;
        this.subCategoryName = subCategoryName;
        this.shoppingSiteProductId = shoppingSiteProductId;
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

    public String getShoppingSiteProductId() {
        return shoppingSiteProductId;
    }

    public void setShoppingSiteProductId(String shoppingSiteProductId) {
        this.shoppingSiteProductId = shoppingSiteProductId;
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
