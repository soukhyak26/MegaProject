package com.affaince.subscription.pricing.forecast;

import com.affaince.subscription.common.type.WeightedProductDemandTrend;
import com.affaince.subscription.date.SysDate;
import com.affaince.subscription.date.SysDateTime;
import com.affaince.subscription.pricing.query.repository.ProductConfigurationViewRepository;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by mandar on 24-11-2016.
 */
public class NextForecastDateFinder {
    @Autowired
    private ProductConfigurationViewRepository productConfigurationViewRepository;

    public Map<String, LocalDate> findNextForecastDateForAProduct(WeightedProductDemandTrend trend) {
        final int actualsAggregationPeriodForTargetForecast = productConfigurationViewRepository.findOne(trend.getProductId()).getActualsAggregationPeriodForTargetForecast();
        //return SysDateTime.now().plusDays(actualsAggregationPeriodForTargetForecast);
        Map<String, LocalDate> productIdToNextForecastDateMap = new HashMap<>(1);
        productIdToNextForecastDateMap.put(trend.getProductId(), SysDate.now().plusDays(1));
        return productIdToNextForecastDateMap;
    }
}
