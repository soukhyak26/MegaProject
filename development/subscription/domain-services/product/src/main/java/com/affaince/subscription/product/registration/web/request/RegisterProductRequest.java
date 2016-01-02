package com.affaince.subscription.product.registration.web.request;


import javax.validation.constraints.NotNull;

/**
 * Created by rbsavaliya on 19-07-2015.
 */
public class RegisterProductRequest {

    @NotNull
    private String categoryId;
    @NotNull
    private String subCategoryId;
    @NotNull
    private String productId;

    @NotNull
    private String productName;

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


}
