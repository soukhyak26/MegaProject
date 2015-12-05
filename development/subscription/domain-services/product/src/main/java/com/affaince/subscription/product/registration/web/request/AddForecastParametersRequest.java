package com.affaince.subscription.product.registration.web.request;

import org.joda.time.LocalDate;

/**
 * Created by rbsavaliya on 05-12-2015.
 */
public class AddForecastParametersRequest {
    private String productId;
    private LocalDate fromDate;
    private LocalDate toDate;
    private double purchasePricePerUnit;
    private double salePricePerUnit;
    private double MRP;
    private long numberOfNewCustomersAssociatedWithAPrice;
    private long numberOfChurnedCustomersAssociatedWithAPrice;

    public AddForecastParametersRequest() {
    }

    public String getProductId() {
        return productId;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public double getPurchasePricePerUnit() {
        return purchasePricePerUnit;
    }

    public double getSalePricePerUnit() {
        return salePricePerUnit;
    }

    public double getMRP() {
        return MRP;
    }

    public long getNumberOfNewCustomersAssociatedWithAPrice() {
        return numberOfNewCustomersAssociatedWithAPrice;
    }

    public long getNumberOfChurnedCustomersAssociatedWithAPrice() {
        return numberOfChurnedCustomersAssociatedWithAPrice;
    }
}
