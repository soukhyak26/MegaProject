package com.affaince.subscription.integration.command.event.forecast;

import org.apache.camel.dataformat.bindy.annotation.CsvRecord;
import org.apache.camel.dataformat.bindy.annotation.DataField;
import org.joda.time.YearMonth;

/**
 * Created by mandark on 05-12-2015.
 */
@CsvRecord(separator = ",", skipFirstLine = true)
public class ProductForecastReceivedEvent {
    @DataField(name = "PRODUCT_ID", pos = 1, trim = true)
    private String productId;

    @DataField(name = "MONTH", pos = 2, trim = true)
    private YearMonth monthOfYear;

    @DataField(name = "PURCHASE_PRICE_PER_UNIT", pos = 3, trim = true)
    private double purchasePricePerUnit;

    @DataField(name = "SALE_PRICE_PER_UNIT", pos = 4, trim = true)
    private double salePricePerUnit;

    @DataField(name = "MRP", pos = 5, trim = true)
    private double MRP;

    @DataField(name = "NO_OF_NEW_CUSTOMERS", pos = 6, trim = true)
    private long numberOfNewCustomersAssociatedWithAPrice;

    @DataField(name = "NO_OF_CHURNED_CUSTOMERS", pos = 7, trim = true)
    private long numberOfChurnedCustomersAssociatedWithAPrice;

    @DataField(name = "NO_OF_EXISTING_CUSTOMERS", pos = 8, trim = true)
    private long numberOfExistingCustomersAssociatedWithAPrice;

    public ProductForecastReceivedEvent() {
    }

    public YearMonth getMonthOfYear() {
        return this.monthOfYear;
    }

    public void setMonthOfYear(YearMonth monthOfYear) {
        this.monthOfYear = monthOfYear;
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

    @Override
    public String toString() {
        return "ProductForecastReceivedEvent{" +
                "productId='" + productId + '\'' +
                ", monthOfYear=" + monthOfYear.getMonthOfYear() + ":" + monthOfYear.getYear() +
                ", purchasePricePerUnit=" + purchasePricePerUnit +
                ", salePricePerUnit=" + salePricePerUnit +
                ", MRP=" + MRP +
                ", numberOfNewCustomersAssociatedWithAPrice=" + numberOfNewCustomersAssociatedWithAPrice +
                ", numberOfChurnedCustomersAssociatedWithAPrice=" + numberOfChurnedCustomersAssociatedWithAPrice +
                ", numberOfExistingCustomersAssociatedWithAPrice=" + numberOfExistingCustomersAssociatedWithAPrice +
                '}';
    }
}
