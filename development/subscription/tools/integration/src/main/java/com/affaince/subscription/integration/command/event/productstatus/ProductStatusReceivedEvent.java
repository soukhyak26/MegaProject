package com.affaince.subscription.integration.command.event.productstatus;

import com.affaince.subscription.date.SysDateTime;
import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;
import org.joda.time.LocalDateTime;

/**
 * Created by mandark on 18-07-2015.
 */
@CsvRecord(separator = ",", skipFirstLine = true)
public class ProductStatusReceivedEvent {

    @DataField(name = "PRODUCT_ID", pos = 1, trim = true)
    private String productId;
    @DataField(name = "CATEGORY_ID", pos = 2, trim = true)
    private String categoryId;
    @DataField(name = "SUBCATEGORY_ID", pos = 3, trim = true)
    private String subCategoryId;
    @DataField(name = "CURRENT_PURCHASE_PRICE_PER_UNIT", pos = 4, trim = true)
    private double currentPurchasePricePerUnit;
    @DataField(name = "CURRENT_MRP", pos = 5, trim = true)
    private double currentMRP;
    @DataField(name = "CURRENT_STOCK", pos = 6, trim = true)
    private int currentStockInUnits;

    private LocalDateTime currentPriceDate = SysDateTime.now();

    public ProductStatusReceivedEvent() {
    }

    public String getSubCategoryId() {
        return this.subCategoryId;
    }

    public void setSubCategoryId(String subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public String getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(String categroyId) {
        this.categoryId = categroyId;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public double getCurrentPurchasePricePerUnit() {
        return currentPurchasePricePerUnit;
    }

    public void setCurrentPurchasePricePerUnit(double currentPurchasePricePerUnit) {
        this.currentPurchasePricePerUnit = currentPurchasePricePerUnit;
    }

    public double getCurrentMRP() {
        return currentMRP;
    }

    public void setCurrentMRP(double currentMRP) {
        this.currentMRP = currentMRP;
    }

    public int getCurrentStockInUnits() {
        return currentStockInUnits;
    }

    public void setCurrentStockInUnits(int currentStockInUnits) {
        this.currentStockInUnits = currentStockInUnits;
    }

    public LocalDateTime getCurrentPriceDate() {
        return this.currentPriceDate;
    }

    public void setCurrentPriceDate(LocalDateTime currentPriceDate) {
        this.currentPriceDate = currentPriceDate;
    }


    @Override
    public String toString() {
        return "ProductStatusReceivedEvent{" +
                "productId='" + productId + '\'' +
                ", categoryId='" + categoryId + '\'' +
                ", subCategoryId='" + subCategoryId + '\'' +
                ", currentPurchasePricePerUnit=" + currentPurchasePricePerUnit +
                ", currentMRP=" + currentMRP +
                ", currentStockInUnits=" + currentStockInUnits +
                ", currentPriceDate=" + currentPriceDate +
                '}';
    }

}
