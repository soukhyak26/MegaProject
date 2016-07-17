package com.calculate.price.forecast;

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

    public DemandForecasterChain() {
        simpleMovingAverageDemandForecaster = new SimpleMovingAverageDemandForecaster();
        simpleExponentialSmoothingDemandForecaster = new SimpleExponentialSmoothingDemandForecaster();
        tripleExponentialSmoothingDemandForecaster = new TripleExponentialSmoothingDemandForecaster();
        arimaBasedDemandForecaster = new ARIMABasedDemandForecaster();
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
