package com.affaince.subscription.integration.command.event.basketdispatch.request;


import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;

import java.util.Date;

/**
 * Created by mandark on 17-09-2015.
 */
@CsvRecord(separator = ",", skipFirstLine = true,generateHeaderColumns = true,crlf = "UNIX")
public class BasketDispatchRequestGeneratedEvent {
    @DataField(columnName = "SUBSCRIBER_ID",name = "SUBSCRIBER_ID", pos = 1, trim = true)
    private String subscriberId;
    @DataField(columnName = "ITEM_ID",name = "ITEM_ID", pos = 2, trim = true)
    private String itemId;
    @DataField(columnName = "CATEGORY_ID",name = "CATEGORY_ID", pos = 3, trim = true)
    private String categoryId;
    @DataField(columnName="SUBCATEGORY_ID",name = "SUBCATEGORY_ID", pos = 4, trim = true)
    private String subCategoryId;
    @DataField(columnName = "PRODUCT_ID",name = "PRODUCT_ID", pos = 5, trim = true)
    private String productId;
    @DataField(columnName = "QUOTED_PRICE",name = "QUOTED_PRICE", pos = 6, pattern="##.###", trim = true)
    private double quotedPrice;
    @DataField(columnName ="DELIVERY_DATE", name = "DELIVERY_DATE", pos = 7,pattern ="yyyyddMM" ,trim = true)
    private Date deliveryDate;

    public BasketDispatchRequestGeneratedEvent() {
    }

    public BasketDispatchRequestGeneratedEvent(String subscriberId, String itemId, String categoryId, String subCategoryId, String productId, double quotedPrice, Date deliveryDate) {
        this.subscriberId = subscriberId;
        this.itemId = itemId;
        this.categoryId = categoryId;
        this.subCategoryId = subCategoryId;
        this.productId = productId;
        this.quotedPrice = quotedPrice;
        this.deliveryDate = deliveryDate;
    }

    public String getSubscriberId() {
        return this.subscriberId;
    }

    public void setSubscriberId(String subscriberId) {
        this.subscriberId = subscriberId;
    }

    public String getItemId() {
        return this.itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
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

    public Date getDeliveryDate() {
        return this.deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }
}
