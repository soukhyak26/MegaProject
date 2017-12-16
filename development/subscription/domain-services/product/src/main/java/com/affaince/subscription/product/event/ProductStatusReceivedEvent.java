package com.affaince.subscription.product.event;

import org.joda.time.LocalDate;

/**
 * Created by mandark on 27-12-2015.
 */
public class ProductStatusReceivedEvent {
    private String productId;
    private String categoryId;
    private String subCategoryId;
    private double currentPurchasePricePerUnit;
    private double currentMRP;
    private int currentStockInUnits;
    private LocalDate currentPriceDate;

    public ProductStatusReceivedEvent(String productId, String categoryId, String subCategoryId, double currentPurchasePricePerUnit, double currentMRP, int currentStockInUnits, LocalDate currentPriceDate) {
        this.productId = productId;
        this.categoryId = categoryId;
        this.subCategoryId = subCategoryId;
        this.currentPurchasePricePerUnit = currentPurchasePricePerUnit;
        this.currentMRP = currentMRP;
        this.currentStockInUnits = currentStockInUnits;
        this.currentPriceDate = currentPriceDate;
    }

    public ProductStatusReceivedEvent() {
    }

    public String getProductId() {
        return productId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public String getSubCategoryId() {
        return subCategoryId;
    }

    public double getCurrentPurchasePricePerUnit() {
        return currentPurchasePricePerUnit;
    }

    public double getCurrentMRP() {
        return currentMRP;
    }

    public int getCurrentStockInUnits() {
        return currentStockInUnits;
    }

    public LocalDate getCurrentPriceDate() {
        return currentPriceDate;
    }
}
