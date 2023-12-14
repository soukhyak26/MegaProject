package com.examples.config;

import org.joda.time.LocalDate;

public interface Aggregatable {
    LocalDate getStartDate();

    void setStartDate(LocalDate var1);

    LocalDate getEndDate();

    void setEndDate(LocalDate var1);

    Aggregatable buildAggregatable(Aggregatable var1);

    void setAggregateValue(long var1);

    long getAggregateValue();

    AggregationType getAggregationType();

    void setAggregationType(AggregationType var1);
}
