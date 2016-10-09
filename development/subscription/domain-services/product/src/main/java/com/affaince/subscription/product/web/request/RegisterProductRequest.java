package com.affaince.subscription.product.web.request;


import com.affaince.subscription.common.type.ProductPricingCategory;
import com.affaince.subscription.common.type.QuantityUnit;
import com.affaince.subscription.common.type.SensitivityCharacteristic;

import javax.validation.constraints.NotNull;
import java.util.Map;

public class RegisterProductRequest {

    //should not come from request.. but generated from aggregate creation.
    //private String productId;

    @NotNull
    private String productName;

    @NotNull
    private String categoryId;
    @NotNull
    private String subCategoryId;
    @NotNull
    private long quantity;
    @NotNull
    private QuantityUnit quantityUnit;
    private String [] substitutes;
    private String [] complements;
    private Map<SensitivityCharacteristic, Double> sensitiveTo;
    @NotNull
    private ProductPricingCategory productPricingCategory;


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

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public long getQuantity() {
        return this.quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    public QuantityUnit getQuantityUnit() {
        return this.quantityUnit;
    }

    public void setQuantityUnit(QuantityUnit quantityUnit) {
        this.quantityUnit = quantityUnit;
    }

    public String[] getSubstitutes() {
        return substitutes;
    }

    public void setSubstitutes(String[] substitutes) {
        this.substitutes = substitutes;
    }

    public String[] getComplements() {
        return complements;
    }

    public void setComplements(String[] complements) {
        this.complements = complements;
    }

    public Map<SensitivityCharacteristic, Double> getSensitiveTo() {
        return this.sensitiveTo;
    }

    public void setSensitiveTo(Map<SensitivityCharacteristic, Double> sensitiveTo) {
        this.sensitiveTo = sensitiveTo;
    }

    public ProductPricingCategory getProductPricingCategory() {
        return productPricingCategory;
    }

    public void setProductPricingCategory(ProductPricingCategory productPricingCategory) {
        this.productPricingCategory = productPricingCategory;
    }
}
