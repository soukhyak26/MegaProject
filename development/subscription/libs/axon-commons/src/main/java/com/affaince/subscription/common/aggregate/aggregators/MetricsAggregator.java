package com.affaince.subscription.common.aggregate.aggregators;

import java.util.List;

/**
 * Created by mandar on 10-07-2016.
 */
public interface MetricsAggregator<Aggregatable> {
    List<Aggregatable> aggregate(List<Aggregatable> historicalData, int aggregationPeriod);
}
