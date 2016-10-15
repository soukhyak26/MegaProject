package com.affaince.subscription.product.services.forecast;

import java.util.List;

/**
 * Created by mandark on 29-04-2016.
 */
public interface TimeSeriesBasedForecaster {
    List<Double> forecast(String dataIdentifier, List<Double> varList);

    void addNextForecaster(TimeSeriesBasedForecaster forecaster);
}
