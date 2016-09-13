package com.affaince.subscription.product.vo;

import com.affaince.subscription.common.type.ProductForecastStatus;
import org.joda.time.LocalDate;

/**
 * Created by mandark on 01-01-2016.
 */
public class ProductForecastParameter {
    private LocalDate startDate;
    private LocalDate endDate;
    private double purchasePricePerUnit;
    private double MRP;
    private long numberofNewSubscriptions;
    private long numberOfChurnedSubscriptions;
    private long numberOfTotalSubscriptions;
    private ProductForecastStatus productForecastStatus;

    public ProductForecastParameter(LocalDate startDate, LocalDate endDate, double purchasePricePerUnit, double MRP, long numberofNewSubscriptions, long numberOfChurnedSubscritptions, long numberOfTotalSubscriptions, ProductForecastStatus productForecastStatus) {
        this.purchasePricePerUnit = purchasePricePerUnit;
        this.MRP = MRP;
        this.numberofNewSubscriptions = numberofNewSubscriptions;
        this.numberOfChurnedSubscriptions = numberOfChurnedSubscritptions;
        this.numberOfTotalSubscriptions = numberOfTotalSubscriptions;
        this.productForecastStatus = productForecastStatus;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public double getPurchasePricePerUnit() {
        return this.purchasePricePerUnit;
    }

    public void setPurchasePricePerUnit(double purchasePricePerUnit) {
        this.purchasePricePerUnit = purchasePricePerUnit;
    }

    public double getMRP() {
        return this.MRP;
    }

    public void setMRP(double MRP) {
        this.MRP = MRP;
    }

    public long getNumberofNewSubscriptions() {
        return this.numberofNewSubscriptions;
    }

    public void setNumberofNewSubscriptions(long numberofNewSubscriptions) {
        this.numberofNewSubscriptions = numberofNewSubscriptions;
    }

    public long getNumberOfChurnedSubscriptions() {
        return this.numberOfChurnedSubscriptions;
    }

    public void setNumberOfChurnedSubscriptions(long numberOfChurnedSubscriptions) {
        this.numberOfChurnedSubscriptions = numberOfChurnedSubscriptions;
    }


    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public long getNumberOfTotalSubscriptions() {
        return numberOfTotalSubscriptions;
    }

    public ProductForecastStatus getProductForecastStatus() {
        return productForecastStatus;
    }
}
