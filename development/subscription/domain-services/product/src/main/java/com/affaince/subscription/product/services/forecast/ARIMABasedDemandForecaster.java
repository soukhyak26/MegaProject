package com.affaince.subscription.product.services.forecast;

import com.affaince.subscription.product.query.view.ProductActualMetricsView;
import com.cloudera.sparkts.models.ARIMA;
import com.cloudera.sparkts.models.ARIMAModel;
import org.apache.spark.mllib.linalg.Vector;
import org.apache.spark.mllib.linalg.Vectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by mandar on 19-06-2016.
 */
public class ARIMABasedDemandForecaster implements ProductDemandForecaster {
    private ProductDemandForecaster nextForecaster;

    @Value("${forecaster.arima.threshold.min}")
    private int minHistorySize;

    @Value("${forecaster.arima.threshold.max}")
    private int maxHistorySize;

    @Autowired
    public ARIMABasedDemandForecaster() {
    }

    public void addNextForecaster(ProductDemandForecaster forecaster) {
        if (null == nextForecaster) {
            this.nextForecaster = forecaster;
        } else {
            this.nextForecaster.addNextForecaster(forecaster);
        }
    }


    public List<Double> forecastDemandGrowth(List<ProductActualMetricsView> productActualMetricsViewList) {

        if (productActualMetricsViewList.size() > minHistorySize) {
            double[] values = new double[productActualMetricsViewList.size()];
            int i = 0;
            for (ProductActualMetricsView productActualMetricsView : productActualMetricsViewList) {
                values[i] = productActualMetricsView.getTotalNumberOfExistingSubscriptions();
                i++;
            }
            Vector ts = Vectors.dense(values);
            ARIMAModel arimaModel = ARIMA.fitModel(1, 0, 1, ts, true, "css-cgd", null);
            double[] coefficients = arimaModel.coefficients();
            for (Double coeff : coefficients) {
                System.out.println("coefficients: " + coeff);
            }
            Vector forecast = arimaModel.forecast(ts, values.length/2);
            List<Double> forecastedSubscriptionCounts = new ArrayList<>();
            for (int j = i; j < forecast.size(); j++) {
                double forecastedValue=forecast.apply(j);
                forecastedSubscriptionCounts.add(forecastedValue);
                System.out.println("ARIMA $$$$$forecast of next 20 observations: " + forecastedValue);
            }
            return forecastedSubscriptionCounts;
        } else {
            return null;
        }
    }

    public List<Double> forecastDemandChurn(List<ProductActualMetricsView> productActualMetricsViewList) {
        if (productActualMetricsViewList.size() > minHistorySize) {
            double[] values = new double[productActualMetricsViewList.size()];
            int i = 0;
            for (ProductActualMetricsView productActualMetricsView : productActualMetricsViewList) {
                values[i] = productActualMetricsView.getChurnedSubscriptions();
                i++;
            }
            Vector ts = Vectors.dense(values);
            ARIMAModel arimaModel = ARIMA.fitModel(1, 0, 1, ts, true, "css-cgd", null);
            double[] coefficients = arimaModel.coefficients();
            for (Double coeff : coefficients) {
                System.out.println("coefficients: " + coeff);
            }
            Vector forecast = arimaModel.forecast(ts, values.length/2);
            List<Double> forecastedChurnedSubscriptionCounts = new ArrayList<>();
            for (int j = productActualMetricsViewList.size(); j < forecast.argmax(); j++) {
                forecastedChurnedSubscriptionCounts.add(forecast.apply(j));
                System.out.println("ARIMA $$$$$forecast of churned subscriptions: " + forecast.apply(j));
            }
            return forecastedChurnedSubscriptionCounts;
        } else {
            return null;
        }

    }

    @PostConstruct
    public void init() {
        System.out.println("================== " + minHistorySize + "================== ");
    }

}
