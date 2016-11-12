package com.affaince.subscription.pricing.forecast.replicate;

import com.affaince.subscription.date.SysDateTime;
import com.affaince.subscription.pricing.query.repository.ProductForecastViewRepository;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by mandar on 10-10-2016.
 */
public class ForecastingReplicator {
    @Autowired
    ProductForecastViewRepository productForecastViewRepository;

    public void replicateForecast(String productId) {
        // find current date
        LocalDateTime currentDate= SysDateTime.now();
        // find last one year forecast for given product till today

        //find actuals aggregation period to find if its weekly/monthly/quarterly monthly forecast
        //iterate over forecast and add/subtract expected gain/dip percentage in each forecast value
    }

}
