package com.verifier.domains.inventory.view;

import com.affaince.subscription.common.type.PricingStrategyType;
import com.affaince.subscription.common.type.ProductPricingCategory;
import com.affaince.subscription.common.type.QuantityUnit;
import com.affaince.subscription.common.type.SensitivityCharacteristic;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@Document(collection = "Product")
public class ProductView {

    @Id
    private String productId;
    private String productName;
    private String categoryId;
    private String subCategoryId;
    private long quantity;
    private QuantityUnit quantityUnit;
    private List<String> substitutes;
    private List<String> complements;
    private SensitivityCharacteristic sensitiveTo;
    private boolean isCrossPriceElasticityConsidered;
    private boolean isAdvertisingExpensesConsidered;
    private double currentOperatingExpensePerUnit;
    private long currentStockInUnits;
    private ProductPricingCategory productPricingCategory;
    private int creditPoints;
    private PricingStrategyType pricingStrategyType;


    public ProductView(){}
    public ProductView(String productId, String productName, String categoryId, String subCategoryId, long quantity, QuantityUnit quantityUnit, List<String> substitutes, List<String> complements,SensitivityCharacteristic sensitiveTo,ProductPricingCategory productPricingCategory) {
        this.productId = productId;
        this.productName = productName;
        this.categoryId = categoryId;
        this.subCategoryId = subCategoryId;
        this.quantity = quantity;
        this.quantityUnit = quantityUnit;
        this.substitutes = substitutes;
        this.complements = complements;
        this.sensitiveTo=sensitiveTo;
        this.productPricingCategory=productPricingCategory;
        this.pricingStrategyType = PricingStrategyType.DEFAULT_PRICING_STRATEGY;
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

    public long getQuantity() {
        return quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
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

    public double getCurrentOperatingExpensePerUnit() {
        return this.currentOperatingExpensePerUnit;
    }

    public void setCurrentOperatingExpensePerUnit(double currentOperatingExpensePerUnit) {
        this.currentOperatingExpensePerUnit = currentOperatingExpensePerUnit;
    }

    public long getCurrentStockInUnits() {
        return this.currentStockInUnits;
    }

    public void setCurrentStockInUnits(long currentStockInUnits) {
        this.currentStockInUnits = currentStockInUnits;
    }

    public ProductPricingCategory getProductPricingCategory() {
        return this.productPricingCategory;
    }

    public void setProductPricingCategory(ProductPricingCategory productPricingCategory) {
        this.productPricingCategory = productPricingCategory;
    }

    public int getCreditPoints() {
        return this.creditPoints;
    }

    public void setCreditPoints(int creditPoints) {
        this.creditPoints = creditPoints;
    }


    public boolean getIsCrossPriceElasticityConsidered() {
        return isCrossPriceElasticityConsidered;
    }

    public void setIsCrossPriceElasticityConsidered(boolean isCrossPriceElasticityConsidered) {
        this.isCrossPriceElasticityConsidered = isCrossPriceElasticityConsidered;
    }

    public boolean getIsAdvertisingExpensesConsidered() {
        return isAdvertisingExpensesConsidered;
    }

    public void setIsAdvertisingExpensesConsidered(boolean isAdvertisingExpensesConsidered) {
        this.isAdvertisingExpensesConsidered = isAdvertisingExpensesConsidered;
    }

    public PricingStrategyType getPricingStrategyType() {
        return pricingStrategyType;
    }

    public void setPricingStrategyType(PricingStrategyType pricingStrategyType) {
        this.pricingStrategyType = pricingStrategyType;
    }

    public SensitivityCharacteristic getSensitiveTo() {
        return sensitiveTo;
    }

    public void setSensitiveTo(SensitivityCharacteristic sensitiveTo) {
        this.sensitiveTo = sensitiveTo;
    }

    public boolean isCrossPriceElasticityConsidered() {
        return isCrossPriceElasticityConsidered;
    }

    public void setCrossPriceElasticityConsidered(boolean crossPriceElasticityConsidered) {
        isCrossPriceElasticityConsidered = crossPriceElasticityConsidered;
    }

    public boolean isAdvertisingExpensesConsidered() {
        return isAdvertisingExpensesConsidered;
    }

    public void setAdvertisingExpensesConsidered(boolean advertisingExpensesConsidered) {
        isAdvertisingExpensesConsidered = advertisingExpensesConsidered;
    }
}