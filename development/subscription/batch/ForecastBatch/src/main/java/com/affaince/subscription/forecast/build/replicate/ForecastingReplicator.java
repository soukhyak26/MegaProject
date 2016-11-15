package com.affaince.subscription.forecast.build.replicate;

import com.affaince.subscription.date.SysDateTime;
import com.affaince.subscription.forecast.query.repository.ProductForecastViewRepository;
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
        // find last one year build for given product till today

        //find actuals aggregation period to find if its weekly/monthly/quarterly monthly build
        //iterate over build and add/subtract expected gain/dip percentage in each build value
    }

}
