package com.affaince.subscription.common.service.forecast;

import com.affaince.subscription.common.service.forecast.config.ForecasterConfiguration;
import com.affaince.subscription.common.service.forecast.config.ForecasterCreatorFactory;
import com.affaince.subscription.common.service.forecast.config.HistoryMaxSizeConstraints;
import com.affaince.subscription.common.service.forecast.config.HistoryMinSizeConstraints;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;

/**A
 * Created by mandark on 01-05-2016.
 */
@Component
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
