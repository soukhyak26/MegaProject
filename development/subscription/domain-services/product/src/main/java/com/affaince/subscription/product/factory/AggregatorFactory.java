package com.affaince.subscription.product.factory;

import com.affaince.subscription.product.query.view.ProductSubscriptionMetricsView;
import com.affaince.subscription.product.services.aggregators.MetricsAggregator;
import com.affaince.subscription.product.services.aggregators.MonthlyAggregator;
import com.affaince.subscription.product.services.aggregators.WeeklyAggregator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mandar on 4/18/2017.
 */

public class AggregatorFactory<T extends ProductSubscriptionMetricsView > {

    private MonthlyAggregator<T>  monthlyAggregator;
    private WeeklyAggregator<T> weeklyAggregator ;

    private Class<T> type;
    public AggregatorFactory(Class<T> cls){
        type=cls;
    }
    public MetricsAggregator<T> getAggregator(int actualsAggregationPeriodForTargetForecast){
        if(actualsAggregationPeriodForTargetForecast == 7){
            return new WeeklyAggregator(type);
        }else if(actualsAggregationPeriodForTargetForecast == 30){
            return new MonthlyAggregator(type);
        }else {
            return new MonthlyAggregator(type);
        }
    }
}
