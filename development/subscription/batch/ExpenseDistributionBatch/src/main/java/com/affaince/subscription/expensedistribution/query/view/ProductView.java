package com.affaince.subscription.expensedistribution.query.view;

import com.affaince.subscription.common.type.QuantityUnit;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

/**
 * Created by rbsavaliya on 19-07-2015.
 */
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
    private double currentOperatingExpensePerUnit;
    private long currentStockInUnits;
    private int productPricingCategory;
    private int creditPoints;
    private double targetMonthlyConsumption;


    public ProductView(String productId, String productName, String categoryId, String subCategoryId, long quantity, QuantityUnit quantityUnit, List<String> substitutes, List<String> complements, double currentOperatingExpensePerUnit, long currentStockInUnits, int productPricingCategory, int creditPoints, double targetMonthlyConsumption) {
        this.productId = productId;
        this.productName = productName;
        this.categoryId = categoryId;
        this.subCategoryId = subCategoryId;
        this.quantity = quantity;
        this.quantityUnit = quantityUnit;
        this.substitutes = substitutes;
        this.complements = complements;
        this.currentOperatingExpensePerUnit = currentOperatingExpensePerUnit;
        this.currentStockInUnits = currentStockInUnits;
        this.productPricingCategory = productPricingCategory;
        this.creditPoints = creditPoints;
        this.targetMonthlyConsumption = targetMonthlyConsumption;
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

    public int getProductPricingCategory() {
        return this.productPricingCategory;
    }

    public void setProductPricingCategory(int productPricingCategory) {
        this.productPricingCategory = productPricingCategory;
    }

    public int getCreditPoints() {
        return this.creditPoints;
    }

    public void setCreditPoints(int creditPoints) {
        this.creditPoints = creditPoints;
    }

    public double getTargetMonthlyConsumption() {
        return targetMonthlyConsumption;
    }
}
