package com.affaince.subscription.product.registration.command.event;

import org.joda.time.LocalDate;

/**
 * Created by mandark on 05-12-2015.
 */
public class ProductForecastReceivedEvent {
    private String productId;
    private LocalDate fromDate;
    private LocalDate toDate;
    private double purchasePricePerUnit;
    private double offeredPricePerUnit;
    private double MRP;
    private long numberOfNewCustomersAssociatedWithAPrice;
    private long numberOfChurnedCustomersAssociatedWithAPrice;
    private long numberOfExistingCustomersAssociatedWithAPrice;

    public ProductForecastReceivedEvent() {
    }

    public ProductForecastReceivedEvent(String productId, LocalDate fromDate, LocalDate toDate, double purchasePricePerUnit, double offeredPricePerUnit, double MRP, long numberOfNewCustomersAssociatedWithAPrice, long numberOfChurnedCustomersAssociatedWithAPrice, long numberOfExistingCustomersAssociatedWithAPrice) {
        this.productId = productId;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.purchasePricePerUnit = purchasePricePerUnit;
        this.offeredPricePerUnit = offeredPricePerUnit;
        this.MRP = MRP;
        this.numberOfNewCustomersAssociatedWithAPrice = numberOfNewCustomersAssociatedWithAPrice;
        this.numberOfChurnedCustomersAssociatedWithAPrice = numberOfChurnedCustomersAssociatedWithAPrice;
        this.numberOfExistingCustomersAssociatedWithAPrice = numberOfExistingCustomersAssociatedWithAPrice;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
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

    public double getOfferedPricePerUnit() {
        return this.offeredPricePerUnit;
    }

    public void setOfferedPricePerUnit(double offeredPricePerUnit) {
        this.offeredPricePerUnit = offeredPricePerUnit;
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

}
