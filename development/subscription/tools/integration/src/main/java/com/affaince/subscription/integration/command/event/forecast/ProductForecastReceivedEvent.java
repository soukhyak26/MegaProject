package com.affaince.subscription.integration.command.event.forecast;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;
import org.joda.time.LocalDate;

/**
 * Created by mandark on 05-12-2015.
 */
@CsvRecord(separator = ",", skipFirstLine = true)
public class ProductForecastReceivedEvent {
    @DataField(name = "PRODUCT_ID", pos = 1, trim = true)
    private String productId;

    @DataField(name = "FROM_DATE", pos = 2, trim = true)
    private LocalDate fromDate;

    @DataField(name = "TO_DATE", pos = 3, trim = true)
    private LocalDate toDate;

    @DataField(name = "PURCHASE_PRICE_PER_UNIT", pos = 4, trim = true)
    private double purchasePricePerUnit;

    @DataField(name = "SALE_PRICE_PER_UNIT", pos = 5, trim = true)
    private double salePricePerUnit;

    @DataField(name = "MRP", pos = 6, trim = true)
    private double MRP;

    @DataField(name = "NO_OF_NEW_CUSTOMERS", pos = 7, trim = true)
    private long numberOfNewCustomersAssociatedWithAPrice;

    @DataField(name = "NO_OF_CHURNED_CUSTOMERS", pos = 8, trim = true)
    private long numberOfChurnedCustomersAssociatedWithAPrice;

    @DataField(name = "NO_OF_EXISTING_CUSTOMERS", pos = 9, trim = true)
    private long numberOfExistingCustomersAssociatedWithAPrice;

    public ProductForecastReceivedEvent() {
    }

    public LocalDate getFromDate() {
        return this.fromDate;
    }

    public void setFromDate(LocalDate fromDate) {
        this.fromDate = fromDate;
    }

    public LocalDate getToDate() {
        return this.toDate;
    }

    public void setToDate(LocalDate toDate) {
        this.toDate = toDate;
    }

    public double getPurchasePricePerUnit() {
        return this.purchasePricePerUnit;
    }

    public void setPurchasePricePerUnit(double purchasePricePerUnit) {
        this.purchasePricePerUnit = purchasePricePerUnit;
    }

    public double getSalePricePerUnit() {
        return this.salePricePerUnit;
    }

    public void setSalePricePerUnit(double salePricePerUnit) {
        this.salePricePerUnit = salePricePerUnit;
    }

    public double getMRP() {
        return this.MRP;
    }

    public void setMRP(double MRP) {
        this.MRP = MRP;
    }

    public long getNumberOfNewCustomersAssociatedWithAPrice() {
        return this.numberOfNewCustomersAssociatedWithAPrice;
    }

    public void setNumberOfNewCustomersAssociatedWithAPrice(long numberOfNewCustomersAssociatedWithAPrice) {
        this.numberOfNewCustomersAssociatedWithAPrice = numberOfNewCustomersAssociatedWithAPrice;
    }

    public long getNumberOfChurnedCustomersAssociatedWithAPrice() {
        return this.numberOfChurnedCustomersAssociatedWithAPrice;
    }

    public void setNumberOfChurnedCustomersAssociatedWithAPrice(long numberOfChurnedCustomersAssociatedWithAPrice) {
        this.numberOfChurnedCustomersAssociatedWithAPrice = numberOfChurnedCustomersAssociatedWithAPrice;
    }

    public long getNumberOfExistingCustomersAssociatedWithAPrice() {
        return this.numberOfExistingCustomersAssociatedWithAPrice;
    }

    public void setNumberOfExistingCustomersAssociatedWithAPrice(long numberOfExistingCustomersAssociatedWithAPrice) {
        this.numberOfExistingCustomersAssociatedWithAPrice = numberOfExistingCustomersAssociatedWithAPrice;
    }

    public String getProductId() {
        return this.productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }


}
