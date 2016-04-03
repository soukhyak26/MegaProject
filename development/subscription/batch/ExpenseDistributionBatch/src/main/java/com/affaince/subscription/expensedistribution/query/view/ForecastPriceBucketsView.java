package com.affaince.subscription.expensedistribution.query.view;

import org.joda.time.LocalDate;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Created by mandark on 28-01-2016.
 */
@Document
public class ForecastPriceBucketsView {
    @Id
    private String productId;
    private double purchasePricePerUnit;
    private double offeredPricePerUnit;
    private double MRP;
    private LocalDate fromDate;
    private LocalDate toDate;
    private long numberOfNewCustomersAssociatedWithAPrice;
    private long numberOfChurnedCustomersAssociatedWithAPrice;
    private long numberOfExistingCustomersAssociatedWithAPrice;

    public ForecastPriceBucketsView(String productId, double purchasePricePerUnit, double offeredPricePerUnit, double MRP, LocalDate fromDate, LocalDate toDate, long numberOfNewCustomersAssociatedWithAPrice, long numberOfChurnedCustomersAssociatedWithAPrice, long numberOfExistingCustomersAssociatedWithAPrice) {
        this.productId = productId;
        this.purchasePricePerUnit = purchasePricePerUnit;
        this.offeredPricePerUnit = offeredPricePerUnit;
        this.MRP = MRP;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.numberOfNewCustomersAssociatedWithAPrice = numberOfNewCustomersAssociatedWithAPrice;
        this.numberOfChurnedCustomersAssociatedWithAPrice = numberOfChurnedCustomersAssociatedWithAPrice;
        this.numberOfExistingCustomersAssociatedWithAPrice = numberOfExistingCustomersAssociatedWithAPrice;
    }

    public String getProductId() {
        return productId;
    }

    public double getPurchasePricePerUnit() {
        return purchasePricePerUnit;
    }

    public double getOfferedPricePerUnit() {
        return offeredPricePerUnit;
    }

    public double getMRP() {
        return MRP;
    }

    public LocalDate getFromDate() {
        return fromDate;
    }

    public LocalDate getToDate() {
        return toDate;
    }

    public long getNumberOfNewCustomersAssociatedWithAPrice() {
        return numberOfNewCustomersAssociatedWithAPrice;
    }

    public long getNumberOfChurnedCustomersAssociatedWithAPrice() {
        return numberOfChurnedCustomersAssociatedWithAPrice;
    }

    public long getNumberOfExistingCustomersAssociatedWithAPrice() {
        return numberOfExistingCustomersAssociatedWithAPrice;
    }
}
