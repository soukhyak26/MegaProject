package com.affaince.subscription.integration.command.event.shoppingitemreceipt;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

/**
 * Created by mandark on 07-09-2015.
 */
@CsvRecord(separator = ",", skipFirstLine = true)
public class ProductReceivedForRegistrationEvent {
    @DataField(name = "PRODUCT_ID", pos = 1, trim = true)
    private String productId;

    @DataField(name = "PRODUCT_NAME", pos = 2, trim = true)
    private String productName;
    @DataField(name = "CATEGORY_ID", pos = 3, trim = true)
    private String categoryId;
    @DataField(name = "SUBCATEGORY_ID", pos = 4, trim = true)
    private String subCategoryId;

    public ProductReceivedForRegistrationEvent() {
    }

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
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

    @Override
    public String toString() {
        return "ProductReceivedForRegistrationEvent{" +
                "productId='" + productId + '\'' +
                ", productName='" + productName + '\'' +
                ", categoryId='" + categoryId + '\'' +
                ", subCategoryId='" + subCategoryId + '\'' +
                '}';
    }
}
