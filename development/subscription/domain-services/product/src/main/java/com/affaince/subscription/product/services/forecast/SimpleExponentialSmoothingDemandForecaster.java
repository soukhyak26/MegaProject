package com.affaince.subscription.product.services.forecast;

import com.affaince.subscription.product.configuration.Axon;
import com.affaince.subscription.product.query.view.ProductActualMetricsView;
import com.affaince.subscription.product.vo.ActualVsPredictionEvaluator;
import net.sourceforge.openforecast.DataPoint;
import net.sourceforge.openforecast.DataSet;
import net.sourceforge.openforecast.ForecastingModel;
import net.sourceforge.openforecast.Observation;
import net.sourceforge.openforecast.models.SimpleExponentialSmoothingModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by mandar on 31-05-2016.
 */
public class SimpleExponentialSmoothingDemandForecaster implements ProductDemandForecaster {

    private ProductDemandForecaster nextForecaster;
    @Autowired
    private Axon.HistoryMinSizeConstraints historyMinSizeConstraints;
    @Autowired
    private Axon.HistoryMaxSizeConstraints historyMaxSizeConstraints;

    public SimpleExponentialSmoothingDemandForecaster() {
    }



    public void addNextForecaster(ProductDemandForecaster forecaster) {
        if (null == nextForecaster) {
            this.nextForecaster = forecaster;
        } else {
            this.nextForecaster.addNextForecaster(forecaster);
        }
    }


    public List<Double> forecastDemandGrowth(List<ProductActualMetricsView> productActualMetricsViewList) {
        if (productActualMetricsViewList.size() > historyMinSizeConstraints.getSema() && productActualMetricsViewList.size() <= historyMaxSizeConstraints.getSema()) {
            int i = 0;
            int[] windowSizes = {3};
            DataSet observedData = new DataSet();
            List<ActualVsPredictionEvaluator> predictionsSet = new ArrayList<>();
            for (ProductActualMetricsView productActualMetricsView : productActualMetricsViewList) {
                double totalSubscriptionCount = productActualMetricsView.getTotalNumberOfExistingSubscriptions();
                final String uniqueKey = productActualMetricsView.getProductVersionId().toString() + "$" + (i + 1);
                ActualVsPredictionEvaluator eval = new ActualVsPredictionEvaluator(uniqueKey, totalSubscriptionCount);
                predictionsSet.add(eval);
                DataPoint dp = new Observation(totalSubscriptionCount);
                //YearMonth monthOfYear = productActualMetricsView.getProductVersionId().getMonthOfYear();
                // dp.setIndependentValue("time", new LocalDate(monthOfYear.getYear(), monthOfYear.getMonthOfYear(), monthOfYear.toDateTime(null).dayOfMonth().getMaximumValue()).toDateTimeAtStartOfDay().getMillis());
                dp.setIndependentValue("t", i + 1);
                observedData.add(dp);
                i++;
            }
            observedData.setPeriodsPerYear(5);
            DataSet fcValues = new DataSet();

            for (int t = 1; t <= productActualMetricsViewList.size(); t++) {
                DataPoint dp = new Observation(0.0);
                dp.setIndependentValue("t", t);
                fcValues.add(dp);
            }
            observedData.setTimeVariable("t");
            ForecastingModel forecaster = SimpleExponentialSmoothingModel.getBestFitModel(observedData);


            DataSet results = forecaster.forecast(fcValues);
            Iterator<DataPoint> it = results.iterator();
            //List<ProductForecastMetricsView> newForecasts= new ArrayList<ProductForecastMetricsView>();
            while (it.hasNext()) {
                // Check that the results are within specified tolerance
                //  of the expected values
                DataPoint fc = (DataPoint) it.next();
                double newSubscriptionCount = fc.getDependentValue();
                double time = fc.getIndependentValue("t");
                for (ActualVsPredictionEvaluator placeholder : predictionsSet) {
                    String subKey = placeholder.getUniqueKey().split("\\$")[1];
                    if (time == Double.parseDouble(subKey)) {
                        placeholder.addPrediction(newSubscriptionCount);
                        break;
                    }
                }
            }
            System.out.println("SEMA$$$$$$$$$$$$$$$$Predicted value:" + predictionsSet.get(predictionsSet.size() - 1).findPrecisePrediction());
            List<Double> resultSet = new ArrayList<Double>();
            resultSet.add(predictionsSet.get(predictionsSet.size() - 1).findPrecisePrediction());
            return resultSet;

        } else {
            //TODO : Handle NPE
            return nextForecaster == null ? null
                    : nextForecaster.forecastDemandGrowth(productActualMetricsViewList);
        }
    }

    public List<Double> forecastDemandChurn(List<ProductActualMetricsView> productActualMetricsViewList) {
        if (productActualMetricsViewList.size() > historyMinSizeConstraints.getSema() && productActualMetricsViewList.size() <= historyMaxSizeConstraints.getSema()) {
            int i = 0;
            int[] windowSizes = {3};
            DataSet observedData = new DataSet();
            List<ActualVsPredictionEvaluator> predictionsSet = new ArrayList<>();
            for (ProductActualMetricsView productActualMetricsView : productActualMetricsViewList) {
                double churnedSubscriptionCount = productActualMetricsView.getChurnedSubscriptions();
                final String uniqueKey = productActualMetricsView.getProductVersionId().toString() + "$" + (i + 1);
                ActualVsPredictionEvaluator eval = new ActualVsPredictionEvaluator(uniqueKey, churnedSubscriptionCount);
                predictionsSet.add(eval);
                DataPoint dp = new Observation(churnedSubscriptionCount);
                //YearMonth monthOfYear = productActualMetricsView.getProductVersionId().getMonthOfYear();
                // dp.setIndependentValue("time", new LocalDate(monthOfYear.getYear(), monthOfYear.getMonthOfYear(), monthOfYear.toDateTime(null).dayOfMonth().getMaximumValue()).toDateTimeAtStartOfDay().getMillis());
                dp.setIndependentValue("t", i + 1);
                observedData.add(dp);
                i++;
            }
            observedData.setPeriodsPerYear(5);
            DataSet fcValues = new DataSet();

            for (int t = 1; t <= productActualMetricsViewList.size(); t++) {
                DataPoint dp = new Observation(0.0);
                dp.setIndependentValue("t", t);
                fcValues.add(dp);
            }
            observedData.setTimeVariable("t");
            ForecastingModel forecaster = SimpleExponentialSmoothingModel.getBestFitModel(observedData);


            DataSet results = forecaster.forecast(fcValues);
            Iterator<DataPoint> it = results.iterator();
            //List<ProductForecastMetricsView> newForecasts= new ArrayList<ProductForecastMetricsView>();
            while (it.hasNext()) {
                // Check that the results are within specified tolerance
                //  of the expected values
                DataPoint fc = (DataPoint) it.next();
                double churnedSubscriptionCount = fc.getDependentValue();
                double time = fc.getIndependentValue("t");
                for (ActualVsPredictionEvaluator placeholder : predictionsSet) {
                    String subKey = placeholder.getUniqueKey().split("\\$")[1];
                    if (time == Double.parseDouble(subKey)) {
                        placeholder.addPrediction(churnedSubscriptionCount);
                        break;
                    }
                }
            }
            System.out.println("SEMA$$$$$$$$$$$$$$$$Predicted value:" + predictionsSet.get(predictionsSet.size() - 1).findPrecisePrediction());
            List<Double> resultSet = new ArrayList<Double>();
            resultSet.add(predictionsSet.get(predictionsSet.size() - 1).findPrecisePrediction());
            return resultSet;

        } else {
            //TODO : Handle NPE
            return nextForecaster == null ? null
                    : nextForecaster.forecastDemandChurn(productActualMetricsViewList);
        }

    }

}
