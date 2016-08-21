package com.calculate.price.forecast;


import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/**
 * Created by mandark on 01-05-2016.
 */
public class SimpleMovingAverageDemandForecaster implements TimeSeriesBasedForecaster {

    private TimeSeriesBasedForecaster nextForecaster;
/*
    @Autowired
    private Axon.HistoryMinSizeConstraints historyMinSizeConstraints;
    @Autowired
    private Axon.HistoryMaxSizeConstraints historyMaxSizeConstraints;
*/

    public SimpleMovingAverageDemandForecaster() {
    }


    public void addNextForecaster(TimeSeriesBasedForecaster forecaster) {
        if (null == nextForecaster) {
            this.nextForecaster = forecaster;
        } else {
            this.nextForecaster.addNextForecaster(forecaster);
        }
    }

    public List<Double> forecast(String dataIdentifier, List<Double> historicalDataList) {
        //starting with simple  moving average
        if (historicalDataList.size() > 3 && historicalDataList.size() <= 15) {
            Queue<Double> window = null;
            double sum = 0;
            int i = 0;
            int[] windowSizes = {3};
            List<ActualVsPredictionEvaluator> predictionsSet = new ArrayList<>();
            for (int windSize : windowSizes) {
                window = new LinkedList<>();
                sum = 0;
                if (historicalDataList.size() >= windSize) {
                    for (Double dataInstance : historicalDataList) {
                        sum += dataInstance;
                        window.add(dataInstance);
                        if (window.size() > windSize) {
                            sum -= window.remove();
                        }
                        double predictedValue = sum / window.size();
                        ActualVsPredictionEvaluator eval = new ActualVsPredictionEvaluator(dataIdentifier, dataInstance);
                        if (predictionsSet.contains(eval)) {
                            ActualVsPredictionEvaluator newPrediction = predictionsSet.get(predictionsSet.indexOf(eval));
                            newPrediction.addPrediction(predictedValue);
                        } else {
                            ActualVsPredictionEvaluator newPrediction = new ActualVsPredictionEvaluator(dataIdentifier, dataInstance);
                            newPrediction.addPrediction(predictedValue);
                            predictionsSet.add(newPrediction);
                        }
                    }
                }
            }
           // System.out.println("SMA: $$$$$$$$$$$$$$$$Predicted value:" + predictionsSet.get(predictionsSet.size() - 1).findPrecisePrediction());
            List<Double> resultSet = new ArrayList<Double>();
            resultSet.add(predictionsSet.get(predictionsSet.size() - 1).findPrecisePrediction());
            return resultSet;

        } else {
            if (null != nextForecaster) {
                return nextForecaster.forecast(dataIdentifier, historicalDataList);
            }
        }
        return null;

    }
}
