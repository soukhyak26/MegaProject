package com.affaince.subscription.product.services.triggers;

import com.affaince.subscription.date.SysDate;
import com.affaince.subscription.product.query.repository.ProductConfigurationViewRepository;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by mandar on 16-08-2016.
 */
public class ProductForecastTrigger {
    @Autowired
    private ProductConfigurationViewRepository productConfigurationViewRepository;

    @Autowired
    public ProductForecastTrigger() {
    }

    public boolean shouldTriggerForecast(String productId) {
        LocalDate nextForecastDate = productConfigurationViewRepository.findOne(productId).getNextForecastDate();
        LocalDate currentDate = SysDate.now();
        if (currentDate.equals(nextForecastDate) || currentDate.isAfter(nextForecastDate)) {
            return true;
        }
        return false;
    }

}
