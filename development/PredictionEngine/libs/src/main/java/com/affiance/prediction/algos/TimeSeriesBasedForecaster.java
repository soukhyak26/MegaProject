package com.affiance.prediction.algos;


import com.affiance.prediction.config.HistoryMaxSizeConstraints;
import com.affiance.prediction.config.HistoryMinSizeConstraints;

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
