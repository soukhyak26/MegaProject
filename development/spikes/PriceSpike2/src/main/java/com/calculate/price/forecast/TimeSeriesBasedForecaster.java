package com.calculate.price.forecast;

import java.util.List;

/**
 * Created by mandark on 29-04-2016.
 */
public interface TimeSeriesBasedForecaster {
    //public List<Double> forecast(String dataIdentifier, List<Double> varList);
    List<DataFrameVO> forecast(String dataIdentifier, List<DataFrameVO> varList, int forecastRecordSize);
    public void addNextForecaster(TimeSeriesBasedForecaster forecaster);
}
