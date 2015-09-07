package com.affaince.subscription.integration.command.event.shoppingitemreceipt;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;
import org.joda.time.LocalDate;

/**
 * Created by mandark on 07-09-2015.
 */
@CsvRecord(separator = ",", skipFirstLine = true)
public class ShoppingItemReceivedEvent {
    @DataField(name = "SHOPPING_ITEM_ID", pos = 1, trim = true)
    private String shoppingItemId;

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

    public ShoppingItemReceivedEvent() {
    }

    public String getShoppingItemId() {
        return this.shoppingItemId;
    }

    public void setShoppingItemId(String shoppingItemId) {
        this.shoppingItemId = shoppingItemId;
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
}
