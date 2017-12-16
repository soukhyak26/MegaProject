package com.affaince.subscription.business.event;

import com.affaince.subscription.common.type.ProductPricingCategory;
import com.affaince.subscription.common.type.QuantityUnit;
import com.affaince.subscription.common.type.SensitivityCharacteristic;
import org.joda.time.LocalDate;

import java.util.List;
import java.util.Map;

/**
 * Created by rbsavaliya on 19-07-2015.
 */
public class ProductRegisteredEvent {
    private String productId;
    private String productName;
    private String categoryId;
    private String subCategoryId;
    private long quantity;
    private QuantityUnit quantityUnit;
    private List<String> substitutes;
    private List<String> complements;
    private Map<SensitivityCharacteristic,Double> sensitiveTo;
    private ProductPricingCategory productPricingCategory;
    private String taggedPriceVersionId;
    private double purchasePrice;
    private double MRP;
    private LocalDate registrationDate;

    public ProductRegisteredEvent(String productId, String productName, String categoryId, String subCategoryId, long quantity, QuantityUnit quantityUnit, List<String> substitutes, List<String> complements, Map<SensitivityCharacteristic, Double> sensitiveTo, ProductPricingCategory productPricingCategory, String taggedPriceVersionId,double purchasePrice, double MRP,LocalDate registrationDate) {
        this.productId = productId;
        this.productName = productName;
        this.categoryId = categoryId;
        this.subCategoryId = subCategoryId;
        this.quantity = quantity;
        this.quantityUnit = quantityUnit;
        this.substitutes = substitutes;
        this.complements = complements;
        this.sensitiveTo=sensitiveTo;
        this.productPricingCategory = productPricingCategory;
        this.taggedPriceVersionId=taggedPriceVersionId;
        this.purchasePrice = purchasePrice;
        this.MRP = MRP;
        this.registrationDate=registrationDate;
    }

    public ProductRegisteredEvent() {
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

    public long getQuantity() {
        return quantity;
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
        return this.sensitiveTo;
    }

    public ProductPricingCategory getProductPricingCategory() {
        return productPricingCategory;
    }

    public String getTaggedPriceVersionId() {
        return taggedPriceVersionId;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public double getMRP() {
        return MRP;
    }

    public LocalDate getRegistrationDate() {
        return registrationDate;
    }
}
