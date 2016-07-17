package com.calculate.price.forecast;

import net.sourceforge.openforecast.DataPoint;
import net.sourceforge.openforecast.DataSet;
import net.sourceforge.openforecast.ForecastingModel;
import net.sourceforge.openforecast.Observation;
import net.sourceforge.openforecast.models.TripleExponentialSmoothingModel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by mandar on 31-05-2016.
 */
public class TripleExponentialSmoothingDemandForecaster implements TimeSeriesBasedForecaster {

    private TimeSeriesBasedForecaster nextForecaster;

    public TripleExponentialSmoothingDemandForecaster() {
    }


    public void addNextForecaster(TimeSeriesBasedForecaster forecaster) {
        if (null == nextForecaster) {
            this.nextForecaster = forecaster;
        } else {
            this.nextForecaster.addNextForecaster(forecaster);
        }
    }

    public List<Double> forecast(String dataIdentifier, List<Double> historicalDataList) {
        if (historicalDataList.size() > 25 && historicalDataList.size() <= 50) {
            int i = 0;
            int[] windowSizes = {3};
            DataSet observedData = new DataSet();
            List<ActualVsPredictionEvaluator> predictionsSet = new ArrayList<>();
            for (Double dataInstance : historicalDataList) {
                final String uniqueKey = dataIdentifier + "$" + (i + 1);
                ActualVsPredictionEvaluator eval = new ActualVsPredictionEvaluator(uniqueKey, dataInstance);
                predictionsSet.add(eval);
                DataPoint dp = new Observation(dataInstance);
                dp.setIndependentValue("t", i + 1);
                observedData.add(dp);
                i++;
            }
            observedData.setPeriodsPerYear(5);
            DataSet fcValues = new DataSet();

            for (int t = 1; t <= historicalDataList.size(); t++) {
                DataPoint dp = new Observation(0.0);
                dp.setIndependentValue("t", t);
                fcValues.add(dp);
            }
            observedData.setTimeVariable("t");
            ForecastingModel forecaster = TripleExponentialSmoothingModel.getBestFitModel(observedData);


            DataSet results = forecaster.forecast(fcValues);
            Iterator<DataPoint> it = results.iterator();
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


            System.out.println("TEMA$$$$$$$$$$$$$$$$Predicted value:" + predictionsSet.get(predictionsSet.size() - 1).findPrecisePrediction());
            List<Double> resultSet = new ArrayList<Double>();
            resultSet.add(predictionsSet.get(predictionsSet.size() - 1).findPrecisePrediction());
            return resultSet;

        } else {
            //TODO : Handle NPE
            return nextForecaster == null ? null
                    : nextForecaster.forecast(dataIdentifier, historicalDataList);
        }
    }

}
