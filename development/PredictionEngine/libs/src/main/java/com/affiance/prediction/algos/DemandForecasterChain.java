package com.affiance.prediction.algos;

import com.affiance.prediction.config.ForecasterConfiguration;
import com.affiance.prediction.config.ForecasterCreatorFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

/**A
 * Created by mandark on 01-05-2016.
 */

public class DemandForecasterChain {
    @Autowired
    private ForecasterConfiguration forecasterConfiguration;
    private List<TimeSeriesBasedForecaster> initialForecastersForDifferentStrategies;
    @Autowired
    ForecasterCreatorFactory forecasterCreatorFactory;

    @PostConstruct
    public void init() throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        Map<String, TimeSeriesBasedForecaster> initialForecastersMap= forecasterCreatorFactory.createForecasterChain(forecasterConfiguration);
        this.initialForecastersForDifferentStrategies=new ArrayList<>();
        this.initialForecastersForDifferentStrategies.addAll(initialForecastersMap.values());
    }


    public List<Double> forecast(String dataIdentifier, List<Double> historicalDataList, List<Double> forecast, int forecastSize) {
        if (null == forecast) {
            forecast = new ArrayList<>();
        }
        //one has to return forecast for next periods=historicalDataList/2
        for (TimeSeriesBasedForecaster forecaster : this.initialForecastersForDifferentStrategies) {
            forecast.addAll(forecaster.forecast(dataIdentifier, historicalDataList));
        }
        if (forecast.size() < forecastSize) {
            historicalDataList.addAll(forecast);
            this.forecast(dataIdentifier, historicalDataList, forecast, forecastSize);
        }
        return forecast;
    }

}