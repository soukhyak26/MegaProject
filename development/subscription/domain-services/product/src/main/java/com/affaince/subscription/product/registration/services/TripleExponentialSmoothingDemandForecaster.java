    package com.affaince.subscription.product.registration.services;

    import com.affaince.subscription.product.registration.query.view.ProductActualMetricsView;
    import com.affaince.subscription.product.registration.vo.ActualVsPredictionEvaluator;
    import net.sourceforge.openforecast.DataPoint;
    import net.sourceforge.openforecast.DataSet;
    import net.sourceforge.openforecast.ForecastingModel;
    import net.sourceforge.openforecast.Observation;
    import net.sourceforge.openforecast.models.SimpleExponentialSmoothingModel;
    import net.sourceforge.openforecast.models.TripleExponentialSmoothingModel;
    import org.joda.time.YearMonth;
    import org.springframework.beans.factory.annotation.Autowired;

    import java.util.ArrayList;
    import java.util.Iterator;
    import java.util.List;

    /**
     * Created by mandar on 31-05-2016.
     */
    public class TripleExponentialSmoothingDemandForecaster implements ProductDemandForecaster {

        private ProductDemandForecaster productDemandForecaster;

        @Autowired
        public TripleExponentialSmoothingDemandForecaster() {
        }

        public void addNextForecaster(ProductDemandForecaster forecaster) {
            this.productDemandForecaster = forecaster;
        }

        public List<Double> forecastDemandGrowth(List<ProductActualMetricsView> productActualMetricsViewList) {
            if (productActualMetricsViewList.size() >= 3 && productActualMetricsViewList.size() <= 15) {
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
                ForecastingModel forecaster = TripleExponentialSmoothingModel.getBestFitModel(observedData);


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
                    //ProductForecastMetricsView forecastView= new ProductForecastMetricsView();
                }


                System.out.println("$$$$$$$$$$$$$$$$Predicted value:" + predictionsSet.get(predictionsSet.size() - 1).findPrecisePrediction());
                List<Double> resultSet = new ArrayList<Double>();
                resultSet.add(predictionsSet.get(predictionsSet.size() - 1).findPrecisePrediction());
                return resultSet;

            }else{
                return null;
            }
        }
        public List<Double> forecastDemandChurn(List<ProductActualMetricsView> productActualMetricsViewList) {
            return null;
        }

    }
