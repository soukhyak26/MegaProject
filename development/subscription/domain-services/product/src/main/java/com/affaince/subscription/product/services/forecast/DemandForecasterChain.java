package com.affaince.subscription.product.services.forecast;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import java.util.Arrays;
import java.util.List;

/**
 * Created by mandark on 01-05-2016.
 */

public class DemandForecasterChain {

    @Autowired
    SimpleMovingAverageDemandForecaster simpleMovingAverageDemandForecaster;
    @Autowired
    SimpleExponentialSmoothingDemandForecaster simpleExponentialSmoothingDemandForecaster;
    @Autowired
    TripleExponentialSmoothingDemandForecaster tripleExponentialSmoothingDemandForecaster;
    @Autowired
    ARIMABasedDemandForecaster arimaBasedDemandForecaster;
    private TimeSeriesBasedForecaster initialForecaster;
    @Value("${forecaster.chain.list}")
    private String forecasterChainElements;

    public DemandForecasterChain() {
    }

    @PostConstruct
    public void init(){
        List<String> forecasterPrefixes = Arrays.asList(forecasterChainElements.split(","));
        for (String prefix : forecasterPrefixes) {

            if (prefix.equals("sma")) {
                this.addForecaster(simpleMovingAverageDemandForecaster);
            } else if (prefix.equals("sema")) {
                this.addForecaster(simpleExponentialSmoothingDemandForecaster);
            } else if (prefix.equals("tema")) {
                this.addForecaster(tripleExponentialSmoothingDemandForecaster);
            } else {
                this.addForecaster(arimaBasedDemandForecaster);
            }
        }

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
