package com.affaince.subscription.common.aggregate.aggregators;

import java.util.List;

/**
 * Created by mandar on 10-07-2016.
 */
public interface MetricsAggregator<T> {
    List<T> aggregate(List<T> historicalData, int aggregationPeriod);
}
