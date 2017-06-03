package com.affaince.subscription.common.service.forecast;

import com.affaince.subscription.common.service.forecast.config.HistoryMaxSizeConstraints;
import com.affaince.subscription.common.service.forecast.config.HistoryMinSizeConstraints;

import java.util.List;

/**
 * Created by mandark on 29-04-2016.
 */
public interface TimeSeriesBasedForecaster {
    List<Double> forecast(String dataIdentifier, List<Double> varList);
    void setHistoryMinSizeConstraints(HistoryMinSizeConstraints historyMinSizeConstraints);
    void setHistoryMaxSizeConstraints(HistoryMaxSizeConstraints historyMaxSizeConstraints);
    void addNextForecaster(TimeSeriesBasedForecaster forecaster);
}
