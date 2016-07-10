package com.affaince.subscription.product.services.forecast;

import com.affaince.subscription.product.configuration.Axon;
import com.affaince.subscription.product.query.view.ProductActualMetricsView;
import com.affaince.subscription.product.vo.ActualVsPredictionEvaluator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by mandark on 01-05-2016.
 */
public class SimpleMovingAverageDemandForecaster implements ProductDemandForecaster {

    private ProductDemandForecaster nextForecaster;
    @Autowired
    private Axon.HistoryMinSizeConstraints historyMinSizeConstraints;
    @Autowired
    private Axon.HistoryMaxSizeConstraints historyMaxSizeConstraints;

    public SimpleMovingAverageDemandForecaster() {
    }




    public void addNextForecaster(ProductDemandForecaster forecaster) {
        if (null == nextForecaster) {
            this.nextForecaster = forecaster;
        } else {
            this.nextForecaster.addNextForecaster(forecaster);
        }
    }

    public List<Double> forecastDemandGrowth(List<ProductActualMetricsView> productActualMetricsViewList) {
        //starting with simple  moving average
        if (productActualMetricsViewList.size() > historyMinSizeConstraints.getSma() && productActualMetricsViewList.size() <= historyMaxSizeConstraints.getSma()) {
            Queue<Double> window = null;
            double sum = 0;
            int i = 0;
            int[] windowSizes = {3};
            List<ActualVsPredictionEvaluator> predictionsSet = new ArrayList<>();
            for (int windSize : windowSizes) {
                window = new LinkedList<>();
                sum = 0;
                if (productActualMetricsViewList.size() >= windSize) {
                    for (ProductActualMetricsView productActualMetricsView : productActualMetricsViewList) {
                        double actualValue = productActualMetricsView.getTotalNumberOfExistingSubscriptions();
                        sum += actualValue;
                        window.add(actualValue);
                        if (window.size() > windSize) {
                            sum -= window.remove();
                        }
                        double predictedValue = sum / window.size();
                        final String uniqueKey = productActualMetricsView.getProductVersionId().toString();
                        ActualVsPredictionEvaluator eval = new ActualVsPredictionEvaluator(uniqueKey, actualValue);
                        if (predictionsSet.contains(eval)) {
                            ActualVsPredictionEvaluator newPrediction = predictionsSet.get(predictionsSet.indexOf(eval));
                            newPrediction.addPrediction(predictedValue);
                        } else {
                            ActualVsPredictionEvaluator newPrediction = new ActualVsPredictionEvaluator(uniqueKey, actualValue);
                            newPrediction.addPrediction(predictedValue);
                            predictionsSet.add(newPrediction);
                        }
                    }
                }
            }
            System.out.println("SMA: $$$$$$$$$$$$$$$$Predicted value:" + predictionsSet.get(predictionsSet.size() - 1).findPrecisePrediction());
            List<Double> resultSet = new ArrayList<Double>();
            resultSet.add(predictionsSet.get(predictionsSet.size() - 1).findPrecisePrediction());
            return resultSet;

        } else {
            if (null != nextForecaster) {
                return nextForecaster.forecastDemandGrowth(productActualMetricsViewList);
            }
        }
        return null;
    }

    public List<Double> forecastDemandChurn(List<ProductActualMetricsView> productActualMetricsViewList) {
        if (productActualMetricsViewList.size() > historyMinSizeConstraints.getSma() && productActualMetricsViewList.size() <= historyMaxSizeConstraints.getSma()) {
            Queue<Double> window = null;
            double sum = 0;
            int i = 0;
            int[] windowSizes = {3};
            List<ActualVsPredictionEvaluator> predictionsSet = new ArrayList<>();
            for (int windSize : windowSizes) {
                window = new LinkedList<>();
                sum = 0;
                if (productActualMetricsViewList.size() >= windSize) {
                    for (ProductActualMetricsView productActualMetricsView : productActualMetricsViewList) {
                        double actualValue = productActualMetricsView.getChurnedSubscriptions();
                        sum += actualValue;
                        window.add(actualValue);
                        if (window.size() > windSize) {
                            sum -= window.remove();
                        }
                        double predictedValue = sum / window.size();
                        final String uniqueKey = productActualMetricsView.getProductVersionId().toString();
                        ActualVsPredictionEvaluator eval = new ActualVsPredictionEvaluator(uniqueKey, actualValue);
                        if (predictionsSet.contains(eval)) {
                            ActualVsPredictionEvaluator newPrediction = predictionsSet.get(predictionsSet.indexOf(eval));
                            newPrediction.addPrediction(predictedValue);
                        } else {
                            ActualVsPredictionEvaluator newPrediction = new ActualVsPredictionEvaluator(uniqueKey, actualValue);
                            newPrediction.addPrediction(predictedValue);
                            predictionsSet.add(newPrediction);
                        }
                    }
                }
            }
            System.out.println("SMA: $$$$$$$$$$$$$$$$Predicted value for churned subscriptions:" + predictionsSet.get(predictionsSet.size() - 1).findPrecisePrediction());
            List<Double> resultSet = new ArrayList<Double>();
            resultSet.add(predictionsSet.get(predictionsSet.size() - 1).findPrecisePrediction());
            return resultSet;

        } else {
            if (null != nextForecaster) {
                return nextForecaster.forecastDemandChurn(productActualMetricsViewList);
            }
        }
        return null;

    }
}
