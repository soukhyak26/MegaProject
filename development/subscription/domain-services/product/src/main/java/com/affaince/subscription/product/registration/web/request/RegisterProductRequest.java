package com.affaince.subscription.product.registration.web.request;


import com.affaince.subscription.common.type.QuantityUnit;
import com.affaince.subscription.common.type.SensitivityCharacteristic;

import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Map;

/**
 * Created by rbsavaliya on 19-07-2015.
 */
public class RegisterProductRequest {

    @NotNull
    private String productId;

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
}
