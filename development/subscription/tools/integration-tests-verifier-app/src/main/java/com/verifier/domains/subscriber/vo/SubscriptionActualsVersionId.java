package com.verifier.domains.subscriber.vo;

import com.affaince.subscription.common.deserializer.LocalDateDeserializer;
import com.affaince.subscription.common.serializer.LocalDateSerializer;import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.joda.time.LocalDate;

import java.io.Serializable;

/**
 * Created by mandar on 9/30/2017.
 */
public class SubscriptionActualsVersionId implements Serializable{
   @JsonSerialize(using = LocalDateSerializer.class)
    @JsonDeserialize(using = LocalDateDeserializer.class)
    private final LocalDate startDate;
    private final double valueRangeMin;
    private final double valueRangeMax;

    public SubscriptionActualsVersionId(LocalDate startDate, double valueRangeMin, double valueRangeMax) {
        this.startDate=startDate;
        this.valueRangeMin = valueRangeMin;
        this.valueRangeMax = valueRangeMax;
    }

    public LocalDate getStartDate() {
        return startDate;
    }


    public double getValueRangeMin() {
        return valueRangeMin;
    }

    public double getValueRangeMax() {
        return valueRangeMax;
    }
}
