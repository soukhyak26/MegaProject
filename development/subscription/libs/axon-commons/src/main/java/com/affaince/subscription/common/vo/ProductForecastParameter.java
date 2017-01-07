package com.affaince.subscription.common.vo;

import com.affaince.subscription.common.deserializer.LocalDateTimeDeserializer;
import com.affaince.subscription.common.serializer.LocalDateTimeSerializer;
import com.affaince.subscription.common.type.ProductForecastStatus;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.joda.time.LocalDate;

/**
 * Created by mandark on 01-01-2016.
 */
public class ProductForecastParameter {
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDate startDate;
    @JsonSerialize(using = LocalDateTimeSerializer.class)
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDate endDate;
    private double purchasePricePerUnit;
    private double MRP;
    private long numberOfNewSubscriptions;
    private long numberOfChurnedSubscriptions;
  //  private long numberOfTotalSubscriptions;
    private ProductForecastStatus productForecastStatus;

    public ProductForecastParameter(LocalDate startDate, LocalDate endDate, double purchasePricePerUnit, double MRP, long numberOfNewSubscriptions, long numberOfChurnedSubscriptions, ProductForecastStatus productForecastStatus) {
        this.purchasePricePerUnit = purchasePricePerUnit;
        this.MRP = MRP;
        this.numberOfNewSubscriptions = numberOfNewSubscriptions;
        this.numberOfChurnedSubscriptions = numberOfChurnedSubscriptions;
        this.productForecastStatus = productForecastStatus;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public ProductForecastParameter() {
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

    public long getNumberOfNewSubscriptions() {
        return this.numberOfNewSubscriptions;
    }

    public void setNumberOfNewSubscriptions(long numberOfNewSubscriptions) {
        this.numberOfNewSubscriptions = numberOfNewSubscriptions;
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

    public ProductForecastStatus getProductForecastStatus() {
        return productForecastStatus;
    }

    public void setProductForecastStatus(ProductForecastStatus productForecastStatus) {
        this.productForecastStatus = productForecastStatus;
    }
}
