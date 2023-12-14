package com.calculate.price.forecast;


import org.joda.time.Days;
import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;

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
    private List<LocalDate> createForecastDates(List<DataFrameVO> observations, int forecastLength) {
        List<LocalDate> offsetDateTimes = new ArrayList<>();
        DataFrameVO firstRecord = observations.get(0);
        DataFrameVO secondRecord = observations.get(1);
        LocalDate firstInstanceDate = firstRecord.getDate();
        LocalDate secondInstanceDate = secondRecord.getDate();
        //Derive time period between two observations, if it is daily monthly,quarterly etc.
        Days period = Days.daysBetween(firstInstanceDate,secondInstanceDate);
        LocalDate lastDateOfTheInput = observations.get(observations.size()-1).getDate();
        for (int i=0;i<forecastLength;i++) {
           lastDateOfTheInput = lastDateOfTheInput.plusDays(period.getDays());
           offsetDateTimes.add(lastDateOfTheInput);
        }
        return offsetDateTimes;
    }

    private List<DataFrameVO> buildForecastedDataFrames(String identifier, List<DataFrameVO> inputs,List<Double> outputValues) {
        List<LocalDate> offsetDateTimes = this.createForecastDates(inputs, outputValues.size());
        List<DataFrameVO> outPutFrames = new ArrayList<>();
        for (int i = 0; i < outputValues.size(); i++) {
            LocalDate offSetDate = offsetDateTimes.get(i);
            DataFrameVO vo = new DataFrameVO(offSetDate, identifier, outputValues.get(i), AggregationType.INCREMENTAL);
            outPutFrames.add(vo);
        }
        return outPutFrames;
    }

    public List<DataFrameVO> forecast(String dataIdentifier, List<DataFrameVO> historicalDataList, int forecastSize) {
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
                    for (Double dataInstance : historicalDataList.stream().map(hd->hd.getValue()).collect(Collectors.toList())) {
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
            return buildForecastedDataFrames(dataIdentifier,historicalDataList,resultSet);

        } else {
            if (null != nextForecaster) {
                return nextForecaster.forecast(dataIdentifier, historicalDataList,forecastSize);
            }
        }
        return null;

    }
}
