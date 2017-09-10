package com.affaince.subscription.common.aggregate;

import com.affaince.subscription.common.aggregate.aggregators.Aggregatable;
import com.affaince.subscription.common.aggregate.aggregators.MetricsAggregator;
import com.affaince.subscription.common.aggregate.aggregators.MonthlyAggregator;
import com.affaince.subscription.common.aggregate.aggregators.WeeklyAggregator;

/**
 * Created by mandar on 4/18/2017.
 */

public class AggregatorFactory<T extends Aggregatable> {

    private MetricsAggregator<Aggregatable> aggregator;

    private Class<Aggregatable> type;
    public AggregatorFactory(Class<Aggregatable> cls){
        type=cls;
    }
    public MetricsAggregator<Aggregatable> getAggregator(int actualsAggregationPeriodForTargetForecast){
        if(actualsAggregationPeriodForTargetForecast == 7){
            return new WeeklyAggregator(type);
        }else if(actualsAggregationPeriodForTargetForecast == 30){
            return new MonthlyAggregator(type);
        }else {
            return new MonthlyAggregator(type);
        }
    }
}
