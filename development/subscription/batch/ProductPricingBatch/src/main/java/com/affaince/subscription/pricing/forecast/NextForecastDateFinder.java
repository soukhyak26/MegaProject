package com.affaince.subscription.pricing.forecast;

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

    public LocalDateTime findNextForecastDateForAProduct(String productId) {
        final int actualsAggregationPeriodForTargetForecast = productConfigurationViewRepository.findOne(productId).getActualsAggregationPeriodForTargetForecast();
        return SysDateTime.now().plusDays(actualsAggregationPeriodForTargetForecast);
    }
}
