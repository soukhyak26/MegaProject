package com.affaince.subscription.common.vo;

import com.affaince.subscription.common.aggregate.aggregators.Aggregatable;
import com.affaince.subscription.common.deserializer.DataFrameVODeserializer;
import com.affaince.subscription.common.serializer.DataFrameVOSerializer;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import org.joda.time.LocalDate;

import java.io.Serializable;

/**
 * Created by mandar on 6/21/2017.
 */
@JsonSerialize(using= DataFrameVOSerializer.class)
@JsonDeserialize(using = DataFrameVODeserializer.class)
public class DataFrameVO implements Serializable,Aggregatable {
    private LocalDate date;
    private String token;
    private double value;
    private AggregationType aggregationType;

    public DataFrameVO(LocalDate date, String token, double value,AggregationType aggregationType) {
        this.date = date;
        this.token = token;
        this.value = value;
        this.aggregationType=aggregationType;
    }

    public LocalDate getDate() {
        return date;
    }

    public String getToken() {
        return token;
    }

    public double getValue() {
        return value;
    }

    @Override
    public LocalDate getStartDate() {
        return this.date;
    }

    @Override
    public void setStartDate(LocalDate startDate) {
        this.date=startDate;
    }

    @Override
    public LocalDate getEndDate() {
        return this.date;
    }

    @Override
    public void setEndDate(LocalDate endDate) {
        this.date=endDate;
    }

    @Override
    public Aggregatable buildAggregatable(Aggregatable other) {
        this.setStartDate(other.getStartDate());
        this.setEndDate(other.getEndDate());
        this.setAggregateValue(other.getAggregateValue());
        this.setAggregationType(other.getAggregationType());
        return this;
    }

    @Override
    public void setAggregateValue(long forecastValue) {
        this.setAggregateValue(forecastValue);
    }

    @Override
    public long getAggregateValue() {
        return Double.valueOf(this.getValue()).longValue();
    }

    @Override
    public AggregationType getAggregationType() {
        return aggregationType;
    }

    @Override
    public void setAggregationType(AggregationType aggregationType) {
        this.aggregationType=aggregationType;
    }
}
