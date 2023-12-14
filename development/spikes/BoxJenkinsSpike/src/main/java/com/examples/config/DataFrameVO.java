package com.examples.config;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.joda.time.LocalDate;

import java.io.Serializable;

public class DataFrameVO implements Serializable, Aggregatable {
    private LocalDate date;
    protected String token;
    protected double value;
    protected AggregationType aggregationType;

    public DataFrameVO() {
    }

    public DataFrameVO(LocalDate date, String token, double value, AggregationType aggregationType) {
        this.date = date;
        this.token = token;
        this.value = value;
        this.aggregationType = aggregationType;
    }

    public LocalDate getDate() {
        return this.date;
    }

    public String getToken() {
        return this.token;
    }

    public double getValue() {
        return this.value;
    }

    @JsonIgnore
    public LocalDate getStartDate() {
        return this.date;
    }

    @JsonIgnore
    public void setStartDate(LocalDate startDate) {
        this.date = startDate;
    }

    @JsonIgnore
    public LocalDate getEndDate() {
        return this.date;
    }

    @JsonIgnore
    public void setEndDate(LocalDate endDate) {
        this.date = endDate;
    }

    public Aggregatable buildAggregatable(Aggregatable other) {
        if (null == this.getStartDate()) {
            this.setStartDate(other.getStartDate());
        }

        this.setEndDate(other.getEndDate());
        if (this.aggregationType == AggregationType.INCREMENTAL) {
            this.setAggregateValue(other.getAggregateValue());
        } else {
            this.setAggregateValue(this.getAggregateValue() + other.getAggregateValue());
        }

        this.setAggregationType(other.getAggregationType());
        return this;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @JsonIgnore
    public void setAggregateValue(long forecastValue) {
        this.setValue((double)forecastValue);
    }

    @JsonIgnore
    public long getAggregateValue() {
        return Double.valueOf(this.getValue()).longValue();
    }

    public AggregationType getAggregationType() {
        return this.aggregationType;
    }

    public void setAggregationType(AggregationType aggregationType) {
        this.aggregationType = aggregationType;
    }

    public String toString() {
        return "@@@DataFrameVO{date=" + this.date + ", token='" + this.token + "', aggregationType=" + this.aggregationType + ", value=" + this.value + "}";
    }
}
