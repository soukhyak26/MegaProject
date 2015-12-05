package com.affaince.subscription.product.registration.query.view;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by rbsavaliya on 19-07-2015.
 */
@Document(collection = "Product")
public class ProductView {

    @Id
    private String productId;
    private String batchId;
    private String categoryId;
    private String categoryName;
    private String subCategoryId;
    private String subCategoryName;
    private PriceParameters priceParameters;

    public ProductView(String productId, String batchId, String categoryId, String categoryName, String subCategoryId, String subCategoryName, PriceParameters priceParameters) {
        this.productId = productId;
        this.batchId = batchId;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.subCategoryId = subCategoryId;
        this.subCategoryName = subCategoryName;
        this.priceParameters = priceParameters;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
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

    public PriceParameters getPriceParameters() {
        return priceParameters;
    }

    public void setPriceParameters(PriceParameters priceParameters) {
        this.priceParameters = priceParameters;
    }
}
