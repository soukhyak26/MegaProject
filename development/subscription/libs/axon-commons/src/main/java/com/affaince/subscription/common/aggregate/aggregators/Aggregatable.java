package com.affaince.subscription.common.aggregate.aggregators;

import com.affaince.subscription.common.vo.AggregationType;
import org.joda.time.LocalDate;

/**
 * Created by mandar on 9/10/2017.
 */
public interface Aggregatable {
    LocalDate getStartDate();
    void setStartDate(LocalDate startDate);
    LocalDate getEndDate();
    void setEndDate(LocalDate endDate);
    Aggregatable buildAggregatable(Aggregatable other);
    void setAggregateValue(long forecastValue);
    long getAggregateValue();
    AggregationType getAggregationType();
    void setAggregationType(AggregationType aggregationType);
}
