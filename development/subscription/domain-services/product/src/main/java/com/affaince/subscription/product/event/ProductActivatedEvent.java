package com.affaince.subscription.product.event;

import com.affaince.subscription.common.type.ProductPricingCategory;
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
    private ProductPricingCategory productPricingCategory;

    public ProductActivatedEvent() {
    }

    public ProductActivatedEvent(String productId, String productName, String categoryId, String subCategoryId, List<String> substitutes, List<String> complements, Map<SensitivityCharacteristic, Double> sensitiveTo, long netQuantity, QuantityUnit quantityUnit, ProductPricingCategory productPricingCategory) {
        this.productId = productId;
        this.productName = productName;
        this.categoryId = categoryId;
        this.subCategoryId = subCategoryId;
        this.sensitiveTo = sensitiveTo;
        this.netQuantity = netQuantity;
        this.quantityUnit = quantityUnit;
        this.substitutes = substitutes;
        this.complements = complements;
        this.productPricingCategory = productPricingCategory;
    }

    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public String getSubCategoryId() {
        return subCategoryId;
    }

    public long getNetQuantity() {
        return netQuantity;
    }

    public QuantityUnit getQuantityUnit() {
        return quantityUnit;
    }

    public List<String> getSubstitutes() {
        return substitutes;
    }

    public List<String> getComplements() {
        return complements;
    }

    public Map<SensitivityCharacteristic, Double> getSensitiveTo() {
        return sensitiveTo;
    }

    public ProductPricingCategory getProductPricingCategory() {
        return productPricingCategory;
    }
}
