package com.affaince.subscription.product.registration.services;

import com.affaince.subscription.product.registration.query.view.ProductActualMetricsView;
import com.affaince.subscription.product.registration.query.view.ProductForecastMetricsView;
import com.affaince.subscription.product.registration.vo.ActualVsPredictionEvaluator;
import net.sourceforge.openforecast.DataPoint;
import net.sourceforge.openforecast.DataSet;
import net.sourceforge.openforecast.ForecastingModel;
import net.sourceforge.openforecast.Observation;
import net.sourceforge.openforecast.models.DoubleExponentialSmoothingModel;
import org.joda.time.DateTimeComparator;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.*;

/**
 * Created by mandark on 01-05-2016.
 */
public class SimpleMovingAverageDemandForecaster implements ProductDemandForecaster {

    private ProductDemandForecaster productDemandForecaster;

    @Autowired
    public SimpleMovingAverageDemandForecaster() {
    }

    public void addNextForecaster(ProductDemandForecaster forecaster) {
        this.productDemandForecaster = forecaster;
    }

    public List<Double> forecastDemandGrowth(List<ProductActualMetricsView> productActualMetricsViewList) {
        //starting with simple  moving average
        if (productActualMetricsViewList.size() >= 3 && productActualMetricsViewList.size() <= 15) {
            final Queue<Double> window = new LinkedList<Double>();
            double sum=0;
       //     Collections.sort(productActualMetricsViewList, DateTimeComparator.getDateOnlyInstance());
            int i = 0;
            int[] windowSizes = {3,4,5};
            List<ActualVsPredictionEvaluator> predictionsSet= new ArrayList<>();
            for (int windSize : windowSizes) {
                if(productActualMetricsViewList.size() >= windSize) {
                    for (ProductActualMetricsView productActualMetricsView : productActualMetricsViewList) {
                        double actualValue=productActualMetricsView.getTotalNumberOfExistingSubscriptions();
                        sum += actualValue;
                        window.add(actualValue);
                        if (window.size() > windSize) {
                            sum -= window.remove();
                        }
                        double predictedValue = sum / window.size();
                        ActualVsPredictionEvaluator eval= new ActualVsPredictionEvaluator(actualValue);
                        if(predictionsSet.contains(eval)){
                            ActualVsPredictionEvaluator newPrediction= predictionsSet.get(predictionsSet.indexOf(eval));
                            newPrediction.addPredictedValue(predictedValue);
                        }else{
                            ActualVsPredictionEvaluator newPrediction= new ActualVsPredictionEvaluator(actualValue);
                            newPrediction.addPredictedValue(predictedValue);
                            predictionsSet.add(newPrediction);
                        }
                    }
                }
            }
            System.out.println("$$$$$$$$$$$$$$$$Predicted value:" + predictionsSet.get(predictionsSet.size()-1).getPrecisePrediction());
            List<Double> resultSet=new ArrayList<Double>();
            resultSet.add(predictionsSet.get(predictionsSet.size()-1).getPrecisePrediction());
            return resultSet;

        } else if(productActualMetricsViewList.size() >= 16 && productActualMetricsViewList.size() <= 30){
/*
            Collections.sort(productActualMetricsViewList, DateTimeComparator.getDateOnlyInstance());
            DataSet observedData = new DataSet();
            //LocalDate startDate = productActualMetricsViewList.get(0).getProductMonthlyVersionId().getMonthOfYear();
            int i = 0;
            for (ProductActualMetricsView productActualMetricsView : productActualMetricsViewList) {
                double totalSubscriptionCount = productActualMetricsView.getTotalNumberOfExistingSubscriptions();
                //int duration = Days.daysBetween(startDate, productActualMetricsView.getFromDate()).getDays();
                DataPoint dp = new Observation(totalSubscriptionCount);
                dp.setIndependentValue("time", 1);
                i++;
            }
            observedData.setPeriodsPerYear(12);
            DataSet fcValues = new DataSet();

            for (int k = i + 1; k < i + i; k++) {
                DataPoint dp = new Observation(0.0);
                dp.setIndependentValue("t", k);
                fcValues.add(dp);
            }
            // Get forecast values
            //forecaster.init(observedData);
            //MovingAverageModel forecaster= new MovingAverageModel(3);
            ForecastingModel forecaster = new DoubleExponentialSmoothingModel(0.2, 0.3);
            observedData.setTimeVariable("t");
            forecaster.init(observedData);

            DataSet results = forecaster.forecast(fcValues);
            Iterator<DataPoint> it = results.iterator();
            List<ProductForecastMetricsView> newForecasts= new ArrayList<ProductForecastMetricsView>();
            while (it.hasNext()) {
                // Check that the results are within specified tolerance
                //  of the expected values
                DataPoint fc = (DataPoint) it.next();
                double newSubscriptionCount = fc.getDependentValue();
                double time = fc.getIndependentValue("t");
                //ProductForecastMetricsView forecastView= new ProductForecastMetricsView();
            }



*/
        } else{
            if (null != productDemandForecaster) {
                return productDemandForecaster.forecastDemandGrowth(productActualMetricsViewList);
            }
        }
        return null;
    }

    public List<Double> forecastDemandChurn(List<ProductActualMetricsView> productActualMetricsViewList) {
        return null;
    }
}
