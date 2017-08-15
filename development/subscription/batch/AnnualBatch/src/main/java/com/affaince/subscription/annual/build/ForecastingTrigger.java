package com.affaince.subscription.annual.build;

import com.affaince.subscription.date.SysDate;
import com.affaince.subscription.annual.query.repository.ProductConfigurationViewRepository;
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
        LocalDate currentDate = SysDate.now();
        if (currentDate.equals(nextForecastDate) || currentDate.isAfter(nextForecastDate)) {
            return productId;
        }
        return null;
    }
}
