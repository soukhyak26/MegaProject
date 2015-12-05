package com.affaince.subscription.product.registration.command;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;
import org.joda.time.LocalDate;

/**
 * Created by rbsavaliya on 05-12-2015.
 */
public class AddForecastParametersCommand {
    @TargetAggregateIdentifier
    private String productId;
    private LocalDate fromDate;
    private LocalDate toDate;
    private double purchasePricePerUnit;
    private double salePricePerUnit;
    private double MRP;
    private long numberOfNewCustomersAssociatedWithAPrice;
    private long numberOfChurnedCustomersAssociatedWithAPrice;

    public AddForecastParametersCommand(String productId, LocalDate fromDate, LocalDate toDate, double purchasePricePerUnit, double salePricePerUnit, double MRP, long numberOfNewCustomersAssociatedWithAPrice, long numberOfChurnedCustomersAssociatedWithAPrice) {
        this.productId = productId;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.purchasePricePerUnit = purchasePricePerUnit;
        this.salePricePerUnit = salePricePerUnit;
        this.MRP = MRP;
        this.numberOfNewCustomersAssociatedWithAPrice = numberOfNewCustomersAssociatedWithAPrice;
        this.numberOfChurnedCustomersAssociatedWithAPrice = numberOfChurnedCustomersAssociatedWithAPrice;
    }

    public AddForecastParametersCommand() {
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
