package com.affaince.subscription.pricing.forecast;

import com.affaince.subscription.common.type.ProductForecastStatus;
import com.affaince.subscription.pricing.query.repository.ForecastingCalenderViewRepository;
import com.affaince.subscription.pricing.query.repository.ProductForecastViewRepository;
import com.affaince.subscription.pricing.query.view.ProductForecastView;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by mandar on 16-08-2016.
 */
public class ForecastingTrigger {
    @Autowired
    ProductForecastViewRepository productForecastViewRepository;
    @Autowired
    ForecastingCalenderViewRepository forecastingCalenderViewRepository;

    /*
        public boolean triggerForecast(String productId) {
            ProductForecastView latestForecastView = productForecastViewRepository.findFirstByProductVersionId_ProductIdAndProductForecastStatusOrderByProductVersionId_FromDateDesc(productId, ProductForecastStatus.CORRECTED);
            ProductConfigurationView productConfigurationView = productConfigurationViewRepository.findOne(productId);
            LocalDate nextForecastDate = latestForecastView.getEndDate().plusDays(productConfigurationView.getActualsAggregationPeriodForTargetForecast());
            if (LocalDate.now().equals(nextForecastDate)) {
                return true;
            }
            return false;
        }
    */
    public boolean doTriggerForecast(String productId) {
        ProductForecastView latestForecastView = productForecastViewRepository.findFirstByProductVersionId_ProductIdAndProductForecastStatusOrderByProductVersionId_FromDateDesc(productId, ProductForecastStatus.CORRECTED);
        LocalDate nextForecastDate = forecastingCalenderViewRepository.findOne(productId).getNextForecastDate();
        if (LocalDate.now().equals(nextForecastDate)) {
            return true;
        }
        return false;

    }
}
