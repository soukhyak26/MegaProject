    package com.affaince.subscription.product.services.forecast;

    import com.affaince.subscription.product.query.view.ProductActualMetricsView;
    import com.affaince.subscription.product.vo.ActualVsPredictionEvaluator;
    import net.sourceforge.openforecast.DataPoint;
    import net.sourceforge.openforecast.DataSet;
    import net.sourceforge.openforecast.ForecastingModel;
    import net.sourceforge.openforecast.Observation;
    import net.sourceforge.openforecast.models.SimpleExponentialSmoothingModel;
    import org.joda.time.YearMonth;
    import org.springframework.beans.factory.annotation.Autowired;

    import java.util.*;

    /**
     * Created by mandar on 31-05-2016.
     */
    public class SimpleExponentialSmoothingDemandForecaster implements ProductDemandForecaster {

        private ProductDemandForecaster productDemandForecaster;

        @Autowired
        public SimpleExponentialSmoothingDemandForecaster() {
        }

        public void addNextForecaster(ProductDemandForecaster forecaster) {
            this.productDemandForecaster = forecaster;
        }

        public List<Double> forecastDemandGrowth(List<ProductActualMetricsView> productActualMetricsViewList) {
            if (productActualMetricsViewList.size() > 15 && productActualMetricsViewList.size() <= 30) {
                int i = 0;
                int[] windowSizes = {3};
                DataSet observedData = new DataSet();
                List<ActualVsPredictionEvaluator> predictionsSet = new ArrayList<>();
                for (ProductActualMetricsView productActualMetricsView : productActualMetricsViewList) {
                    double totalSubscriptionCount = productActualMetricsView.getTotalNumberOfExistingSubscriptions();
                    final String uniqueKey=productActualMetricsView.getProductMonthlyVersionId().toString() + ":" + (i+1);
                    ActualVsPredictionEvaluator eval= new ActualVsPredictionEvaluator(uniqueKey,totalSubscriptionCount);
                    predictionsSet.add(eval);
                    DataPoint dp = new Observation(totalSubscriptionCount);
                    YearMonth monthOfYear = productActualMetricsView.getProductMonthlyVersionId().getMonthOfYear();
                   // dp.setIndependentValue("time", new LocalDate(monthOfYear.getYear(), monthOfYear.getMonthOfYear(), monthOfYear.toDateTime(null).dayOfMonth().getMaximumValue()).toDateTimeAtStartOfDay().getMillis());
                    dp.setIndependentValue("t", i+1);
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
                    for(ActualVsPredictionEvaluator placeholder: predictionsSet){
                        String subKey=placeholder.getUniqueKey().split(":")[1];
                        if(time== Double.parseDouble(subKey)){
                            placeholder.addPrediction(newSubscriptionCount);
                            break;
                        }
                    }
                }
                System.out.println("SEMA$$$$$$$$$$$$$$$$Predicted value:" + predictionsSet.get(predictionsSet.size() - 1).findPrecisePrediction());
                List<Double> resultSet = new ArrayList<Double>();
                resultSet.add(predictionsSet.get(predictionsSet.size() - 1).findPrecisePrediction());
                return resultSet;

            }else{
                //TODO : Handle NPE
                return productDemandForecaster == null ? null
                        :productDemandForecaster.forecastDemandGrowth(productActualMetricsViewList);
            }
        }
        public List<Double> forecastDemandChurn(List<ProductActualMetricsView> productActualMetricsViewList) {
            return null;
        }

    }
