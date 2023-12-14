package com.calculate.price.forecast;

import com.github.signaflo.timeseries.TimePeriod;
import com.github.signaflo.timeseries.TimeSeries;
import com.github.signaflo.timeseries.TimeUnit;
import com.github.signaflo.timeseries.forecast.Forecast;
import com.github.signaflo.timeseries.model.arima.Arima;
import com.github.signaflo.timeseries.model.arima.ArimaCoefficients;
import com.github.signaflo.timeseries.model.arima.ArimaOrder;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.joda.time.LocalDate;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


public class SignflowARIMAForecaster implements TimeSeriesBasedForecaster {
    private TimeSeriesBasedForecaster nextForecaster;
    @Override
    public void addNextForecaster(TimeSeriesBasedForecaster forecaster){
        this.nextForecaster=null;
    }
    @Override
    public List<DataFrameVO> forecast(String dataIdentifier, List<DataFrameVO> inputs, int forecastRecordSize) {
        //retrieve historical values from the dataframes.
        double[] doubleSeries = inputs.stream().map(DataFrameVO::getValue).collect(Collectors.toList()).stream().mapToDouble(Double::doubleValue).toArray();

        DataFrameVO firstRecord = inputs.get(0);
        DataFrameVO secondRecord = inputs.get(1);
        LocalDate firstInstanceDate = firstRecord.getDate();
        LocalDate secondInstanceDate = secondRecord.getDate();
        TimePeriod timePeriod = this.determineTimePeriod(firstInstanceDate, secondInstanceDate);

        List<OffsetDateTime> observationTimes = this.createOffSetDateTimes(inputs);
        TimeSeries ts = TimeSeries.from(timePeriod, observationTimes, doubleSeries);


        // Define ARIMA model order (p, d, q)
        ArimaOrder modelOrder = ArimaOrder.order(0, 1, 1, 0, 1, 1); // Note that intercept fitting will automatically be turned off
        // Fit ARIMA model to the time series data
        //ArimaEstimation arimaEstimation = Arima.model(p, d, q).fit(timeSeriesData);
        Arima arimaEstimation = Arima.model(ts, modelOrder);
        // Get ARIMA coefficients
        ArimaCoefficients coefficients = arimaEstimation.coefficients();

        // Print coefficients
        System.out.println("Coefficients: " + coefficients);
        //System.out.println("MA Coefficients: " + coefficients.ma());

        // Print ARIMA model summary
        System.out.println(arimaEstimation);

        // Forecast future values
        int forecastHorizon = forecastRecordSize;  // Number of future time points to forecast
        Forecast forecastValues = arimaEstimation.forecast(forecastHorizon);

        // Print forecasted values
        System.out.println("Forecasted Values: " + forecastValues);
        return buildForecastedDataFrames(dataIdentifier, forecastValues);
    }

    private List<DataFrameVO> buildForecastedDataFrames(String identifier, Forecast forecast) {
        TimeSeries timeSeries = forecast.pointEstimates();
        List<OffsetDateTime> offsetDateTimes = timeSeries.observationTimes();
        List<Double> outputValues = timeSeries.asList();
        List<DataFrameVO> outPutFrames = new ArrayList<>();
        for (int i = 0; i < outputValues.size(); i++) {
            OffsetDateTime offSetDateTime = offsetDateTimes.get(i);
            java.time.LocalDate offsetDate = offSetDateTime.toLocalDate();
            DataFrameVO vo = new DataFrameVO(new LocalDate(offsetDate.getYear(), offsetDate.getMonthValue(), offsetDate.getDayOfMonth()), identifier, outputValues.get(i), AggregationType.INCREMENTAL);
            outPutFrames.add(vo);
        }
        return outPutFrames;
    }

    private List<OffsetDateTime> createOffSetDateTimes(List<DataFrameVO> observations) {
        List<OffsetDateTime> offsetDateTimes = new ArrayList<>();
        for (DataFrameVO vo : observations) {
            DateTime instanceDatetime = vo.getDate().toDateTimeAtStartOfDay();
            java.time.Instant instant = java.time.Instant.ofEpochMilli(instanceDatetime.getMillis());
            OffsetDateTime offsetDateTime = OffsetDateTime.ofInstant(instant,
                    instanceDatetime.getZone().toTimeZone().toZoneId());
            offsetDateTimes.add(offsetDateTime);
        }
        return offsetDateTimes;
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

/*
    public List<DataFrameVO> createObservations() {
        DataFrameVO vo1 = new DataFrameVO(LocalDate.parse("2015-1-1"), "demand", 500, AggregationType.INCREMENTAL);
        DataFrameVO vo2 = new DataFrameVO(LocalDate.parse("2015-1-15"), "demand", 750, AggregationType.INCREMENTAL);
        DataFrameVO vo3 = new DataFrameVO(LocalDate.parse("2015-2-1"), "demand", 1000, AggregationType.INCREMENTAL);
        DataFrameVO vo4 = new DataFrameVO(LocalDate.parse("2015-2-15"), "demand", 1250, AggregationType.INCREMENTAL);
        DataFrameVO vo5 = new DataFrameVO(LocalDate.parse("2015-3-1"), "demand", 1500, AggregationType.INCREMENTAL);
        DataFrameVO vo6 = new DataFrameVO(LocalDate.parse("2015-3-15"), "demand", 1750, AggregationType.INCREMENTAL);
        DataFrameVO vo7 = new DataFrameVO(LocalDate.parse("2015-4-1"), "demand", 2000, AggregationType.INCREMENTAL);
        DataFrameVO vo8 = new DataFrameVO(LocalDate.parse("2015-4-15"), "demand", 2250, AggregationType.INCREMENTAL);
        DataFrameVO vo9 = new DataFrameVO(LocalDate.parse("2015-5-1"), "demand", 2500, AggregationType.INCREMENTAL);
        DataFrameVO vo10 = new DataFrameVO(LocalDate.parse("2015-5-15"), "demand", 2750, AggregationType.INCREMENTAL);
        DataFrameVO vo11 = new DataFrameVO(LocalDate.parse("2015-6-1"), "demand", 3000, AggregationType.INCREMENTAL);
        DataFrameVO vo12 = new DataFrameVO(LocalDate.parse("2015-6-15"), "demand", 3250, AggregationType.INCREMENTAL);
        DataFrameVO vo13 = new DataFrameVO(LocalDate.parse("2015-7-1"), "demand", 3500, AggregationType.INCREMENTAL);
        DataFrameVO vo14 = new DataFrameVO(LocalDate.parse("2015-7-15"), "demand", 3750, AggregationType.INCREMENTAL);
        DataFrameVO vo15 = new DataFrameVO(LocalDate.parse("2015-8-1"), "demand", 4000, AggregationType.INCREMENTAL);
        DataFrameVO vo16 = new DataFrameVO(LocalDate.parse("2015-8-15"), "demand", 4250, AggregationType.INCREMENTAL);
        DataFrameVO vo17 = new DataFrameVO(LocalDate.parse("2015-9-1"), "demand", 4500, AggregationType.INCREMENTAL);
        DataFrameVO vo18 = new DataFrameVO(LocalDate.parse("2015-9-15"), "demand", 4750, AggregationType.INCREMENTAL);
        DataFrameVO vo19 = new DataFrameVO(LocalDate.parse("2015-10-1"), "demand", 5000, AggregationType.INCREMENTAL);
        DataFrameVO vo20 = new DataFrameVO(LocalDate.parse("2015-10-15"), "demand", 5250, AggregationType.INCREMENTAL);
        DataFrameVO vo21 = new DataFrameVO(LocalDate.parse("2015-11-1"), "demand", 5500, AggregationType.INCREMENTAL);
        DataFrameVO vo22 = new DataFrameVO(LocalDate.parse("2015-11-15"), "demand", 5750, AggregationType.INCREMENTAL);
        DataFrameVO vo23 = new DataFrameVO(LocalDate.parse("2015-12-1"), "demand", 6000, AggregationType.INCREMENTAL);
        DataFrameVO vo24 = new DataFrameVO(LocalDate.parse("2015-12-15"), "demand", 6250, AggregationType.INCREMENTAL);
        DataFrameVO vo25 = new DataFrameVO(LocalDate.parse("2016-01-01"), "demand", 6500, AggregationType.INCREMENTAL);
        DataFrameVO vo26 = new DataFrameVO(LocalDate.parse("2016-01-15"), "demand", 6750, AggregationType.INCREMENTAL);
        DataFrameVO vo27 = new DataFrameVO(LocalDate.parse("2016-02-01"), "demand", 7000, AggregationType.INCREMENTAL);
        DataFrameVO vo28 = new DataFrameVO(LocalDate.parse("2016-02-15"), "demand", 7250, AggregationType.INCREMENTAL);
        DataFrameVO vo29 = new DataFrameVO(LocalDate.parse("2016-03-01"), "demand", 7500, AggregationType.INCREMENTAL);
        DataFrameVO vo30 = new DataFrameVO(LocalDate.parse("2016-03-15"), "demand", 7750, AggregationType.INCREMENTAL);
        DataFrameVO vo31 = new DataFrameVO(LocalDate.parse("2016-04-01"), "demand", 8000, AggregationType.INCREMENTAL);
        DataFrameVO vo32 = new DataFrameVO(LocalDate.parse("2016-04-15"), "demand", 8250, AggregationType.INCREMENTAL);
        List<DataFrameVO> dataFrames = new ArrayList<>();
        dataFrames.add(vo1);
        dataFrames.add(vo2);
        dataFrames.add(vo3);
        dataFrames.add(vo4);
        dataFrames.add(vo5);
        dataFrames.add(vo6);
        dataFrames.add(vo7);
        dataFrames.add(vo8);
        dataFrames.add(vo9);
        dataFrames.add(vo10);
        dataFrames.add(vo11);
        dataFrames.add(vo12);
        dataFrames.add(vo13);
        dataFrames.add(vo14);
        dataFrames.add(vo15);
        dataFrames.add(vo16);
        dataFrames.add(vo17);
        dataFrames.add(vo18);
        dataFrames.add(vo19);
        dataFrames.add(vo20);
        dataFrames.add(vo21);
        dataFrames.add(vo22);
        dataFrames.add(vo23);
        dataFrames.add(vo24);
        dataFrames.add(vo25);
        dataFrames.add(vo26);
        dataFrames.add(vo27);
        dataFrames.add(vo28);
        dataFrames.add(vo29);
        dataFrames.add(vo30);
        dataFrames.add(vo31);
        dataFrames.add(vo32);
        return dataFrames;
    }
*/
}
