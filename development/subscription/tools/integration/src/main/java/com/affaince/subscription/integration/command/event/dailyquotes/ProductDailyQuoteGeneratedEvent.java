package com.affaince.subscription.integration.command.event.dailyquotes;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

import java.util.Date;

/**
 * Created by mandark on 17-09-2015.
 */
@CsvRecord(separator = ",", skipFirstLine = true, generateHeaderColumns = true, crlf = "UNIX")
public class ProductDailyQuoteGeneratedEvent {
    @DataField(columnName = "PRODUCT_ID", name = "PRODUCT_ID", pos = 1, trim = true)
    private String productId;
    @DataField(columnName = "CATEGORY_ID", name = "CATEGORY_ID", pos = 2, trim = true)
    private String categoryId;
    @DataField(columnName = "SUBCATEGORY_ID", name = "SUBCATEGORY_ID", pos = 3, trim = true)
    private String subCategoryId;
    @DataField(columnName = "QUOTED_PRICE", name = "QUOTED_PRICE", pos = 4, pattern = "##.###", trim = true)
    private double quotedPrice;
    @DataField(columnName = "QUOTE_DATE", name = "DELIVERY_DATE", pos = 5, pattern = "yyyyddMM", trim = true)
    private Date quoteDate;

    public ProductDailyQuoteGeneratedEvent() {
    }

    public ProductDailyQuoteGeneratedEvent(String categoryId, String subCategoryId, String productId, double quotedPrice, Date quoteDate) {
        this.categoryId = categoryId;
        this.subCategoryId = subCategoryId;
        this.productId = productId;
        this.quotedPrice = quotedPrice;
        this.quoteDate = quoteDate;
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

    public double getQuotedPrice() {
        return this.quotedPrice;
    }

    public void setQuotedPrice(double quotedPrice) {
        this.quotedPrice = quotedPrice;
    }

    public Date getQuoteDate() {
        return this.quoteDate;
    }

    public void setQuoteDate(Date quoteDate) {
        this.quoteDate = quoteDate;
    }
}
