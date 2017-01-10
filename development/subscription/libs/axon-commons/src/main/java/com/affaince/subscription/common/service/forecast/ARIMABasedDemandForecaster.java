package com.affaince.subscription.common.service.forecast;

import com.affaince.subscription.common.service.forecast.config.HistoryMaxSizeConstraints;
import com.affaince.subscription.common.service.forecast.config.HistoryMinSizeConstraints;
import com.cloudera.sparkts.models.ARIMA;
import com.cloudera.sparkts.models.ARIMAModel;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mandar on 19-06-2016.
 */
public class ARIMABasedDemandForecaster implements TimeSeriesBasedForecaster {
    private TimeSeriesBasedForecaster nextForecaster;
    @Autowired
    private HistoryMinSizeConstraints historyMinSizeConstraints;
    @Autowired
    private HistoryMaxSizeConstraints historyMaxSizeConstraints;

    public ARIMABasedDemandForecaster() {
    }

    public void addNextForecaster(TimeSeriesBasedForecaster forecaster) {
        if (null == nextForecaster) {
            this.nextForecaster = forecaster;
        } else {
            this.nextForecaster.addNextForecaster(forecaster);
        }
    }

    public List<Double> forecast(String dataIdentifier, List<Double> historicalDataList) {

        if (historicalDataList.size() > historyMinSizeConstraints.getArima()) {
            double[] values = new double[historicalDataList.size()];
            int i = 0;
            for (Double dataInstance : historicalDataList) {
                values[i] = dataInstance;
                i++;
            }
            Vector ts = Vectors.dense(values);
            ARIMAModel arimaModel = ARIMA.fitModel(1, 0, 1, ts, true, "css-cgd", null);
            double[] coefficients = arimaModel.coefficients();
            for (Double coeff : coefficients) {
                System.out.println("coefficients: " + coeff);
            }
            Vector forecast = arimaModel.forecast(ts, values.length / 2);
            List<Double> forecastedSubscriptionCounts = new ArrayList<>();
            for (int j = i; j < forecast.size(); j++) {
                double forecastedValue = forecast.apply(j);
                forecastedSubscriptionCounts.add(forecastedValue);
                System.out.println("ARIMA $$$$$forecast of next 20 observations: " + forecastedValue);
            }
            //return all predictions elements
            return forecastedSubscriptionCounts;
        } else {
            return null;
        }
    }
}
