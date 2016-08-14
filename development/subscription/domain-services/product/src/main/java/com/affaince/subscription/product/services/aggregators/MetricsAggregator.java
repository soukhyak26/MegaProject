package com.affaince.subscription.product.services.aggregators;

import java.util.List;

/**
 * Created by mandar on 10-07-2016.
 */
public interface MetricsAggregator<T> {
    public List<T> aggregate(List<T> historicalData, int aggregationPeriod);
}
