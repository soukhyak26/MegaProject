package com.affaince.subscription.common.aggregate;

import com.affaince.subscription.common.aggregate.aggregators.Aggregatable;
import com.affaince.subscription.common.aggregate.aggregators.MetricsAggregator;
import com.affaince.subscription.common.aggregate.aggregators.MonthlyAggregator;
import com.affaince.subscription.common.aggregate.aggregators.WeeklyAggregator;
import org.springframework.stereotype.Component;

/**
 * Created by mandar on 4/18/2017.
 */
@Component
public class AggregatorFactory<T extends Aggregatable> {

    private MetricsAggregator<T> aggregator;
    private Class<T> type;
    public MetricsAggregator<T> getAggregator(int actualsAggregationPeriodForTargetForecast,Class<T> type){
        if(actualsAggregationPeriodForTargetForecast == 7){
            return new WeeklyAggregator(type);
        }else if(actualsAggregationPeriodForTargetForecast == 30){
            return new MonthlyAggregator(type);
        }else {
            return new MonthlyAggregator(type);
        }
    }
}
