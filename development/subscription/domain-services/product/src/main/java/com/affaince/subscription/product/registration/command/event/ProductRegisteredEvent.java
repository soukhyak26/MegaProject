package com.affaince.subscription.product.registration.command.event;

/**
 * Created by rbsavaliya on 19-07-2015.
 */
public class ProductRegisteredEvent {

    private String productId;

    private String productName;
    private String categoryId;
    private String subCategoryId;

    public ProductRegisteredEvent(String productId, String productName, String categoryId, String subCategoryId) {
        this.productId = productId;
        this.productName = productName;
        this.categoryId = categoryId;
        this.subCategoryId = subCategoryId;
    }

    public ProductRegisteredEvent() {
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }


    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getSubCategoryId() {
        return subCategoryId;
    }

    public void setSubCategoryId(String subCategoryId) {
        this.subCategoryId = subCategoryId;
    }
}
