package com.calculate.price.forecast;

import java.util.Arrays;
import java.util.List;

/**
 * Created by mandark on 01-05-2016.
 */

public class DemandForecasterChain {

    SimpleMovingAverageDemandForecaster simpleMovingAverageDemandForecaster;
    SimpleExponentialSmoothingDemandForecaster simpleExponentialSmoothingDemandForecaster;
    TripleExponentialSmoothingDemandForecaster tripleExponentialSmoothingDemandForecaster;
    ARIMABasedDemandForecaster arimaBasedDemandForecaster;
    private TimeSeriesBasedForecaster initialForecaster;
   // private String forecasterChainElements;

    public DemandForecasterChain() {
        simpleMovingAverageDemandForecaster = new SimpleMovingAverageDemandForecaster();
        simpleExponentialSmoothingDemandForecaster = new SimpleExponentialSmoothingDemandForecaster();
        tripleExponentialSmoothingDemandForecaster = new TripleExponentialSmoothingDemandForecaster();
        arimaBasedDemandForecaster = new ARIMABasedDemandForecaster();
        init();

    }

    public void init() {
        //List<String> forecasterPrefixes = Arrays.asList(forecasterChainElements.split(","));
        this.addForecaster(simpleMovingAverageDemandForecaster);
        this.addForecaster(simpleExponentialSmoothingDemandForecaster);
        this.addForecaster(tripleExponentialSmoothingDemandForecaster);
        this.addForecaster(arimaBasedDemandForecaster);

    }

    private void addForecaster(TimeSeriesBasedForecaster forecaster) {
        if (null != initialForecaster) {
            initialForecaster.addNextForecaster(forecaster);
        } else {
            initialForecaster = forecaster;
        }
    }


    public List<Double> forecast(String dataIdentifier, List<Double> historicalDataList) {
        return initialForecaster.forecast(dataIdentifier, historicalDataList);
    }
}
