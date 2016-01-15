package com.affaince.subscription.integration.command.event.stockdemand;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

/**
 * Created by mandark on 12-09-2015.
 */
@CsvRecord(separator = ",", skipFirstLine = true, generateHeaderColumns = true, crlf = "UNIX")
public class ProductStockDemandGeneratedEvent {
    @DataField(columnName = "PRODUCT_ID", name = "PRODUCT_ID", pos = 1, trim = true)
    private String productId;
    @DataField(columnName = "CATEGORY_ID", name = "CATEGORY_ID", pos = 2, trim = true)
    private String categoryId;
    @DataField(columnName = "SUBCATEGORY_ID", name = "SUBCATEGORY_ID", pos = 3, trim = true)
    private String subCategoryId;
    @DataField(columnName = "CURRENT_STOCK_DEMAND", name = "CURRENT_STOCK_DEMAND", pos = 4, trim = true)
    private int currentStockDemandInUnits;

    public ProductStockDemandGeneratedEvent() {
    }


    public String getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }


    public String getSubCategoryId() {
        return this.subCategoryId;
    }

    public void setSubCategoryId(String subCategoryId) {
        this.subCategoryId = subCategoryId;
    }

    public String getProductId() {
        return this.productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public int getCurrentStockDemandInUnits() {
        return this.currentStockDemandInUnits;
    }

    public void setCurrentStockDemandInUnits(int currentStockDemandInUnits) {
        this.currentStockDemandInUnits = currentStockDemandInUnits;
    }
}
