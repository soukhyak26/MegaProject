package com.affaince.subscription.integration.command;

import org.joda.time.LocalDate;

/**
 * Created by mandark on 18-07-2015.
 */
public class FetchSubscriptonableItemCommand {
    private String batchId;
    private String categroyId;
    private String categroyName;
    private String subCategoryId;
    private String subCategoryName;
    private String productId;
    private double currentPurchasePricePerUnit;
    private double currentMRP;
    private int currentStockInUnits;
    private LocalDate currentPriceDate = LocalDate.now();

    public FetchSubscriptonableItemCommand(){}

    public String getSubCategoryId() {
        return this.subCategoryId;
    }

    public void setSubCategoryId(String subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public String getSubCategoryName() {
        return this.subCategoryName;
    }

    public void setSubCategoryName(String subCategoryName) {
        this.subCategoryName = subCategoryName;
    }

    public void setBatchId(String batchId) {
        this.batchId = batchId;
    }

    public void setCategroyId(String categroyId) {
        this.categroyId = categroyId;
    }

    public void setCategroyName(String categroyName) {
        this.categroyName = categroyName;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public void setCurrentPurchasePricePerUnit(double currentPurchasePricePerUnit) {
        this.currentPurchasePricePerUnit = currentPurchasePricePerUnit;
    }

    public void setCurrentMRP(double currentMRP) {
        this.currentMRP = currentMRP;
    }

    public void setCurrentStockInUnits(int currentStockInUnits) {
        this.currentStockInUnits = currentStockInUnits;
    }

    public String getBatchId() {
        return batchId;
    }

    public String getCategroyId() {
        return categroyId;
    }

    public String getCategroyName() {
        return categroyName;
    }

    public String getProductId() {
        return productId;
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
        return this.currentPriceDate;
    }
}
