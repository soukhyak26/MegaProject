package com.affaince.subscription.integration.command.event.stockdemand;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

/**
 * Created by mandark on 12-09-2015.
 */
@CsvRecord(separator = ",", skipFirstLine = true,generateHeaderColumns = true,crlf = "UNIX")
public class SubscriptionableItemStockDemandGeneratedEvent {
    @DataField(columnName = "PRODUCT_ID", name = "PRODUCT_ID", pos = 1, trim = true)
    private String productId;
    @DataField(columnName="CATEGORY_ID",name = "CATEGORY_ID", pos = 2, trim = true)
    private String categoryId;
    @DataField(columnName="CATEGORY_NAME",name = "CATEGORY_NAME", pos = 3, trim = true)
    private String categoryName;
    @DataField(columnName="SUBCATEGORY_ID",name = "SUBCATEGORY_ID", pos = 4, trim = true)
    private String subCategoryId;
    @DataField(columnName="SUBCATEGORY_NAME",name = "SUBCATEGORY_NAME", pos = 5, trim = true)
    private String subCategoryName;
    @DataField(columnName = "CURRENT_STOCK_DEMAND", name = "CURRENT_STOCK_DEMAND", pos = 6, trim = true)
    private int currentStockDemandInUnits;

    public SubscriptionableItemStockDemandGeneratedEvent() {
    }


    public String getCategoryId() {
        return this.categoryId;
    }

    public void setCategoryId(String categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return this.categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

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
