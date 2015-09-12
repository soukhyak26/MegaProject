package com.affaince.subscription.integration.command.event.stockdemand;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

/**
 * Created by mandark on 12-09-2015.
 */
@CsvRecord(separator = ",", skipFirstLine = true,generateHeaderColumns = true)
public class SubscriptionableItemStockDemandGeneratedEvent {
    @DataField(name = "ITEM_ID", pos = 1, trim = true)
    private String itemId;
    @DataField(name = "CATEGORY_ID", pos = 2, trim = true)
    private String categroyId;
    @DataField(name = "CATEGORY_NAME", pos = 3, trim = true)
    private String categoryName;
    @DataField(name = "SUBCATEGORY_ID", pos = 4, trim = true)
    private String subCategoryId;
    @DataField(name = "SUBCATEGORY_NAME", pos = 5, trim = true)
    private String subCategoryName;
    @DataField(name = "PRODUCT_ID", pos = 6, trim = true)
    private String productId;
    @DataField(name = "CURRENT_STOCK_DEMAND", pos = 7, trim = true)
    private int currentStockDemandInUnits;

    public SubscriptionableItemStockDemandGeneratedEvent() {
    }

    public String getItemId() {
        return this.itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

    public String getCategroyId() {
        return this.categroyId;
    }

    public void setCategroyId(String categroyId) {
        this.categroyId = categroyId;
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
