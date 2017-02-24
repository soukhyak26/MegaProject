package com.affaince.subscription.testdata.generator;

import com.affaince.subscription.common.deserializer.LocalDateDeserializer;
import com.affaince.subscription.common.deserializer.LocalDateTimeDeserializer;
import com.affaince.subscription.common.serializer.LocalDateSerializer;
import com.affaince.subscription.common.serializer.LocalDateTimeSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

/**
 * Created by rbsavaliya on 30-12-2016.
 */
public class Forecast {
    private String productId;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate startDate;
    @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private LocalDate endDate;
    private double purchasePricePerUnit;
    private double MRP;
    private long numberOfNewSubscriptions;
    private long numberOfChurnedSubscriptions;

    public Forecast(String productId, LocalDate startDate, LocalDate endDate, double purchasePricePerUnit, double MRP, long numberOfNewSubscriptions, long numberOfChurnedSubscriptions) {
        this.productId = productId;
        this.startDate = startDate;
        this.endDate = endDate;
        this.purchasePricePerUnit = purchasePricePerUnit;
        this.MRP = MRP;
        this.numberOfNewSubscriptions = numberOfNewSubscriptions;
        this.numberOfChurnedSubscriptions = numberOfChurnedSubscriptions;
    }

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

    public double getMRP() {
        return MRP;
    }

    public long getNumberOfNewSubscriptions() {
        return numberOfNewSubscriptions;
    }

    public long getNumberOfChurnedSubscriptions() {
        return numberOfChurnedSubscriptions;
    }
}
