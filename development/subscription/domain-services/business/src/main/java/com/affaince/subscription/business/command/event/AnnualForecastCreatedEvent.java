package com.affaince.subscription.business.command.event;

import com.affaince.subscription.common.deserializer.LocalDateDeserializer;
import com.affaince.subscription.common.serializer.LocalDateSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.joda.time.LocalDate;

/**
 * Created by mandar on 2/23/2017.
 */
public class AnnualForecastCreatedEvent {
    String productId;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    LocalDate startDate;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    LocalDate endDate;
    double purchasePricePerUnit;
    double mrp;
    long numberOfNewSubscriptions;
    long numberOfChurnedSubscriptions;
    long revisedTotalSubscriptionCount;

    public AnnualForecastCreatedEvent(String productId, LocalDate startDate, LocalDate endDate, double purchasePricePerUnit, double mrp, long numberOfNewSubscriptions, long numberOfChurnedSubscriptions, long revisedTotalSubscriptionCount) {
        this.productId = productId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.purchasePricePerUnit = purchasePricePerUnit;
        this.mrp = mrp;
        this.numberOfNewSubscriptions = numberOfNewSubscriptions;
        this.numberOfChurnedSubscriptions = numberOfChurnedSubscriptions;
        this.revisedTotalSubscriptionCount = revisedTotalSubscriptionCount;
    }
    public AnnualForecastCreatedEvent(){};
    public String getProductId() {
        return productId;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public double getPurchasePricePerUnit() {
        return purchasePricePerUnit;
    }

    public double getMrp() {
        return mrp;
    }

    public long getNumberOfNewSubscriptions() {
        return numberOfNewSubscriptions;
    }

    public long getNumberOfChurnedSubscriptions() {
        return numberOfChurnedSubscriptions;
    }

    public long getRevisedTotalSubscriptionCount() {
        return revisedTotalSubscriptionCount;
    }
}
