package com.calculate.price.forecast;

import com.github.signaflo.timeseries.TimePeriod;
import com.github.signaflo.timeseries.TimeUnit;
import net.sourceforge.openforecast.DataPoint;
import net.sourceforge.openforecast.DataSet;
import net.sourceforge.openforecast.ForecastingModel;
import net.sourceforge.openforecast.Observation;
import net.sourceforge.openforecast.models.TripleExponentialSmoothingModel;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.LocalDate;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

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
    protected TimePeriod determineTimePeriod(LocalDate firstInstanceDate, LocalDate secondInstanceDate) {
        int incrementDays = Days.daysBetween(firstInstanceDate, secondInstanceDate).getDays();
        TimePeriod timePeriod = null;
        if (incrementDays == 1) {
            timePeriod = new TimePeriod(TimeUnit.DAY, 1);
        } else if (incrementDays == 2) {
            timePeriod = new TimePeriod(TimeUnit.DAY, 2);
        } else if (incrementDays == 3) {
            timePeriod = new TimePeriod(TimeUnit.DAY, 3);
        } else if (incrementDays == 5) {
            timePeriod = new TimePeriod(TimeUnit.DAY, 5);
        } else if (incrementDays == 7) {
            timePeriod = new TimePeriod(TimeUnit.WEEK, 1);
        } else if (incrementDays == 14) {
            timePeriod = new TimePeriod(TimeUnit.WEEK, 2);
        } else if (incrementDays == 15) {
            timePeriod = new TimePeriod(TimeUnit.WEEK, 2);
        } else if (incrementDays == 30) {
            timePeriod = new TimePeriod(TimeUnit.MONTH, 1);
        } else if (incrementDays == 31) {
            timePeriod = new TimePeriod(TimeUnit.MONTH, 1);
        } else if (incrementDays > 80 && incrementDays <= 92) {
            timePeriod = new TimePeriod(TimeUnit.QUARTER, 1);
        } else if (incrementDays > 170 && incrementDays <= 181) {
            timePeriod = new TimePeriod(TimeUnit.QUARTER, 2);
        } else if (incrementDays > 360 && incrementDays <= 365) {
            timePeriod = new TimePeriod(TimeUnit.YEAR, 1);
        }
        return timePeriod;
    }
    private List<LocalDate> createOffSetDateTimes(List<DataFrameVO> observations,int forecastLength) {
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
        List<LocalDate> offsetDateTimes = this.createOffSetDateTimes(inputs, outputValues.size());
        List<DataFrameVO> outPutFrames = new ArrayList<>();
        for (int i = 0; i < outputValues.size(); i++) {
            LocalDate offSetDate = offsetDateTimes.get(i);
            DataFrameVO vo = new DataFrameVO(offSetDate, identifier, outputValues.get(i), AggregationType.INCREMENTAL);
            outPutFrames.add(vo);
        }
        return outPutFrames;
    }

    public List<DataFrameVO> forecast(String dataIdentifier, List<DataFrameVO> historicalDataList,int forecastSize) {
        if (historicalDataList.size() > 30 && historicalDataList.size() <= 60) {
            int i = 0;
            int[] windowSizes = {3};
            DataSet observedData = new DataSet();
            List<ActualVsPredictionEvaluator> predictionsSet = new ArrayList<>();
            for (Double dataInstance : historicalDataList.stream().map(hd->hd.getValue()).collect(Collectors.toList())) {
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
            TripleExponentialSmoothingModel forecaster = TripleExponentialSmoothingModel.getBestFitModel(observedData);
/*
            System.out.println("TEMA coefficient alpha: " + forecaster.getAlpha());
            System.out.println("TEMA coefficient beta: " + forecaster.getBeta());
            System.out.println("TEMA coefficient gamma: " + forecaster.getGamma());
*/
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


          //  System.out.println("TEMA$$$$$$$$$$$$$$$$Predicted value:" + predictionsSet.get(predictionsSet.size() - 1).findPrecisePrediction());
            List<Double> resultSet = new ArrayList<Double>();
            resultSet.add(predictionsSet.get(predictionsSet.size() - 1).findPrecisePrediction());
            return buildForecastedDataFrames(dataIdentifier,historicalDataList,resultSet);

        } else {
            //TODO : Handle NPE
            return nextForecaster == null ? null
                    : nextForecaster.forecast(dataIdentifier, historicalDataList,forecastSize);
        }
    }

}
