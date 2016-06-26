        package com.affaince.subscription.product.services.forecast;

        import com.affaince.subscription.product.query.view.ProductActualMetricsView;
        import com.affaince.subscription.product.vo.ActualVsPredictionEvaluator;
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
                    Queue<Double> window = null;
                    double sum=0;
                    int period=0;
               //     Collections.sort(productActualMetricsViewList, DateTimeComparator.getDateOnlyInstance());
                    int i = 0;
                    int[] windowSizes = {3};
                    List<ActualVsPredictionEvaluator> predictionsSet= new ArrayList<>();
                    for (int windSize : windowSizes) {
                        window= new LinkedList<>();
                        sum=0;
                         if(productActualMetricsViewList.size() >= windSize) {
                            for (ProductActualMetricsView productActualMetricsView : productActualMetricsViewList) {
                                double actualValue=productActualMetricsView.getTotalNumberOfExistingSubscriptions();
                                sum += actualValue;
                                window.add(actualValue);
                                if (window.size() > windSize) {
                                    sum -= window.remove();
                                }
                                double predictedValue = sum / window.size();
                                final String uniqueKey=productActualMetricsView.getProductPeriodVersionId().toString();
                                ActualVsPredictionEvaluator eval= new ActualVsPredictionEvaluator(uniqueKey,actualValue);
                                if(predictionsSet.contains(eval)){
                                    ActualVsPredictionEvaluator newPrediction= predictionsSet.get(predictionsSet.indexOf(eval));
                                    newPrediction.addPrediction(predictedValue);
                                }else{
                                    ActualVsPredictionEvaluator newPrediction= new ActualVsPredictionEvaluator(uniqueKey,actualValue);
                                    newPrediction.addPrediction(predictedValue);
                                    predictionsSet.add(newPrediction);
                                }
                            }
                        }
                    }
                    System.out.println("SMA: $$$$$$$$$$$$$$$$Predicted value:" + predictionsSet.get(predictionsSet.size()-1).findPrecisePrediction());
                    List<Double> resultSet=new ArrayList<Double>();
                    resultSet.add(predictionsSet.get(predictionsSet.size()-1).findPrecisePrediction());
                    return resultSet;

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
