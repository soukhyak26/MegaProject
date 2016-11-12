package com.affaince.subscription.business.command.event;

import com.affaince.subscription.common.type.QuantityUnit;
import com.affaince.subscription.common.type.SensitivityCharacteristic;

import java.util.List;
import java.util.Map;

/**
 * Created by mandar on 16-10-2016.
 */
public class ProductActivatedEvent {
    private String productId;
    private String productName;
    private String categoryId;
    private String subCategoryId;
    private long netQuantity;
    private QuantityUnit quantityUnit;
    private List<String> substitutes;
    private List<String> complements;
    private Map<SensitivityCharacteristic, Double> sensitiveTo;

    public ProductActivatedEvent() {
    }

    public ProductActivatedEvent(String productId, String productName, String categoryId, String subCategoryId, List<String> substitutes, List<String> complements, Map<SensitivityCharacteristic, Double> sensitiveTo, long netQuantity, QuantityUnit quantityUnit) {
        this.productId = productId;
        this.productName = productName;
        this.categoryId = categoryId;
        this.subCategoryId = subCategoryId;
        this.sensitiveTo = sensitiveTo;
        this.netQuantity = netQuantity;
        this.quantityUnit = quantityUnit;
        this.substitutes = substitutes;
        this.complements = complements;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
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

    public long getNetQuantity() {
        return netQuantity;
    }

    public void setNetQuantity(long netQuantity) {
        this.netQuantity = netQuantity;
    }

    public QuantityUnit getQuantityUnit() {
        return quantityUnit;
    }

    public void setQuantityUnit(QuantityUnit quantityUnit) {
        this.quantityUnit = quantityUnit;
    }

    public List<String> getSubstitutes() {
        return substitutes;
    }

    public void setSubstitutes(List<String> substitutes) {
        this.substitutes = substitutes;
    }

    public List<String> getComplements() {
        return complements;
    }

    public void setComplements(List<String> complements) {
        this.complements = complements;
    }

    public Map<SensitivityCharacteristic, Double> getSensitiveTo() {
        return sensitiveTo;
    }

    public void setSensitiveTo(Map<SensitivityCharacteristic, Double> sensitiveTo) {
        this.sensitiveTo = sensitiveTo;
    }
}
