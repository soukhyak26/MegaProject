package com.affaince.subscription.product.web.request;

import javax.validation.constraints.NotNull;

/**
 * Created by mandar on 10/21/2017.
 */
public class RegisterCategoryRequest {
    @NotNull
    private String categoryName;
    @NotNull
    private String description;
    @NotNull
    private String parentCategoryId;

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(String parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
    }
}
