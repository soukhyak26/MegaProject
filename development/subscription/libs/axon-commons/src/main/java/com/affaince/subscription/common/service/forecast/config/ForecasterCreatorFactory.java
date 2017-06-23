package com.affaince.subscription.common.service.forecast.config;

import com.affaince.subscription.common.service.forecast.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mandar on 6/3/2017.
 */
@Component
public class ForecasterCreatorFactory {
    @Autowired
    SimpleLinearForecaster simpleLinearForecaster;
    @Autowired
    SimpleMovingAverageDemandForecaster simpleMovingAverageDemandForecaster;
    @Autowired
    SimpleExponentialSmoothingDemandForecaster simpleExponentialSmoothingDemandForecaster;
    @Autowired
    TripleExponentialSmoothingDemandForecaster tripleExponentialSmoothingDemandForecaster;
    @Autowired
    ARIMABasedDemandForecaster arimaBasedDemandForecaster;

    public Map<String, TimeSeriesBasedForecaster> createForecasterChain(ForecasterConfiguration forecasterConfiguration) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        List<ForecasterConfiguration.ForecasterChainConfig> ForecasterChainConfigs = forecasterConfiguration.getForecasterchain();
        Map<String, TimeSeriesBasedForecaster> forecastersMapAgainstNextForecasterName = new HashMap<>();
        Map<String, TimeSeriesBasedForecaster> initialForecastersMap = new HashMap<>();
        for (int i = 0; i < ForecasterChainConfigs.size(); i++) {
            ForecasterConfiguration.ForecasterChainConfig config = ForecasterChainConfigs.get(i);
            String name = config.getName();
            if(null != name) {
                TimeSeriesBasedForecaster forecaster = null;
                //TimeSeriesBasedForecaster forecaster = (TimeSeriesBasedForecaster) Class.forName(config.getCls()).newInstance();
                if (name.equalsIgnoreCase("SimpleLinearForecaster")) {
                    forecaster = simpleLinearForecaster;
                } else if (name.equalsIgnoreCase("SimpleMovingAverageDemandForecaster")) {
                    forecaster = simpleMovingAverageDemandForecaster;
                } else if (name.equalsIgnoreCase("SimpleExponentialSmoothingDemandForecaster")) {
                    forecaster = simpleExponentialSmoothingDemandForecaster;
                } else if (name.equalsIgnoreCase("TripleExponentialSmoothingDemandForecaster")) {
                    forecaster = tripleExponentialSmoothingDemandForecaster;
                }else{
                    forecaster = simpleExponentialSmoothingDemandForecaster;
                }
/*
                else if (name.equalsIgnoreCase("ARIMABasedDemandForecaster")) {
                    forecaster = arimaBasedDemandForecaster;
                } else {
                    forecaster = arimaBasedDemandForecaster;
                }
*/

                String next = config.getNext();
                if (next.equals("NULL")) {
                    forecaster.addNextForecaster(null);
                } else {
                    forecastersMapAgainstNextForecasterName.put(next, forecaster);
                }
                TimeSeriesBasedForecaster earlierForecaster = forecastersMapAgainstNextForecasterName.get(name);
                if (null != earlierForecaster) {
                    earlierForecaster.addNextForecaster(forecaster);
                    forecastersMapAgainstNextForecasterName.remove(name);
                    initialForecastersMap.remove(name);
                } else {
                    initialForecastersMap.put(name, forecaster);
                }
            }
        }
        return initialForecastersMap;
    }

/*
    public static  Map<String, TimeSeriesBasedForecaster> createForecasterChain(ForecasterConfiguration forecasterConfiguration) throws ClassNotFoundException, IllegalAccessException, InstantiationException {
        List<ForecasterConfiguration.ForecasterChainConfig> ForecasterChainConfigs = forecasterConfiguration.getForecasterchain();
        Map<String, TimeSeriesBasedForecaster> forecastersMapAgainstNextForecasterName = new HashMap<>();
        Map<String, TimeSeriesBasedForecaster> initialForecastersMap = new HashMap<>();
        for (int i = 0; i < ForecasterChainConfigs.size(); i++) {
            ForecasterConfiguration.ForecasterChainConfig config = ForecasterChainConfigs.get(i);
            String name = config.getName();
            TimeSeriesBasedForecaster forecaster = (TimeSeriesBasedForecaster) Class.forName(config.getCls()).newInstance();
*/
/*
            forecaster.setHistoryMinSizeConstraints(this.historyMinSizeConstraints);
            forecaster.setHistoryMaxSizeConstraints(this.historyMaxSizeConstraints);
*//*

            String next = config.getNext();
            forecastersMapAgainstNextForecasterName.put(next, forecaster);
            if (next.equals("NULL")) {
                forecaster.addNextForecaster(null);
            } else {
                TimeSeriesBasedForecaster earlierForecaster = forecastersMapAgainstNextForecasterName.get(name);
                if (null != earlierForecaster) {
                    earlierForecaster.addNextForecaster(forecaster);
                    forecastersMapAgainstNextForecasterName.remove(name);
                    initialForecastersMap.remove(name);
                } else {
                    initialForecastersMap.put(name, forecaster);
                }
            }
        }
        return initialForecastersMap;
    }
*/
}
