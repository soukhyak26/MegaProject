package com.affaince.subscription.pricing.forecast;

import com.affaince.subscription.common.type.WeightedProductDemandTrend;
import com.affaince.subscription.date.SysDateTime;
import com.affaince.subscription.pricing.query.repository.ProductConfigurationViewRepository;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by mandar on 24-11-2016.
 */
public class NextForecastDateFinder {
    @Autowired
    private ProductConfigurationViewRepository productConfigurationViewRepository;

    public LocalDateTime findNextForecastDateForAProduct(WeightedProductDemandTrend trend) {
        final int actualsAggregationPeriodForTargetForecast = productConfigurationViewRepository.findOne(trend.getProductId()).getActualsAggregationPeriodForTargetForecast();
        //return SysDateTime.now().plusDays(actualsAggregationPeriodForTargetForecast);
        return SysDateTime.now().plusDays(1);
    }
}
