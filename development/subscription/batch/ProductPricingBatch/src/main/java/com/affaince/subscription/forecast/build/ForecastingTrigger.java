package com.affaince.subscription.pricing.forecast;

import com.affaince.subscription.pricing.query.repository.ProductConfigurationViewRepository;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by mandar on 16-08-2016.
 */
public class ForecastingTrigger {
    @Autowired
    private ProductConfigurationViewRepository productConfigurationViewRepository;
    @Autowired
    public ForecastingTrigger() {
    }

    public String doTriggerForecast(String productId) {
        LocalDate nextForecastDate = productConfigurationViewRepository.findOne(productId).getNextForecastDate();
        LocalDate currentDate = LocalDate.now();
        if (currentDate.equals(nextForecastDate) || currentDate.isAfter(nextForecastDate)) {
            return productId;
        }
        return null;
    }
}
