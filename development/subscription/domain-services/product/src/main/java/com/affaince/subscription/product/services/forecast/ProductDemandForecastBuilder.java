package com.affaince.subscription.product.services.forecast;

import com.affaince.subscription.common.service.forecast.DemandForecasterChain;
import com.affaince.subscription.common.vo.ProductVersionId;
import com.affaince.subscription.product.factory.AggregatorFactory;
import com.affaince.subscription.product.query.repository.ProductActualsViewRepository;
import com.affaince.subscription.product.query.view.ProductActualsView;
import com.affaince.subscription.product.query.view.ProductForecastView;
import com.affaince.subscription.product.query.view.ProductSubscriptionMetricsView;
import com.affaince.subscription.product.services.aggregators.MetricsAggregator;
import com.affaince.subscription.product.services.aggregators.PeriodBasedAggregator;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by mandar on 10-07-2016.
 */

public class ProductDemandForecastBuilder<T extends ProductSubscriptionMetricsView> {


    private AggregatorFactory<ProductForecastView> aggregatorFactory;
    @Autowired
    private DemandForecasterChain demandForecasterChain;
    @Autowired
    private ProductActualsViewRepository productActualsViewRepository;

    public List<ProductForecastView> buildForecast(String productId, LocalDate currentDate, int chunkAggregationPeriod, double demandCurvePeriod) {
        LocalDate startDate = currentDate.minusDays(Double.valueOf(demandCurvePeriod).intValue());
        List<ProductActualsView> productActualsViewList = productActualsViewRepository.findByProductVersionId_ProductIdAndProductVersionId_FromDateBetween(productId, startDate, currentDate);
/*
        List<ProductActualsView> aggregatedActualsViewList = periodBasedAggregator.aggregate(productActualsViewList, chunkAggregationPeriod);

        List<Double> historicalSubscriptionChurnCountList = aggregatedActualsViewList.stream().map(pamv -> Long.valueOf(pamv.getChurnedSubscriptions()).doubleValue()).collect(Collectors.toCollection(ArrayList<Double>::new));
        List<Double> historicalTotalSubscriptionCountList = aggregatedActualsViewList.stream().map(pamv -> Long.valueOf(pamv.getTotalNumberOfExistingSubscriptions()).doubleValue()).collect(Collectors.toCollection(ArrayList<Double>::new));
        List<LocalDateTime> historicalEndDates = aggregatedActualsViewList.stream().map(pamv -> pamv.getEndDate()).collect(Collectors.toCollection((ArrayList<LocalDateTime>::new)));
*/

        List<Double> historicalSubscriptionChurnCountList = productActualsViewList.stream().map(pamv -> Long.valueOf(pamv.getChurnedSubscriptions()).doubleValue()).collect(Collectors.toCollection(ArrayList<Double>::new));
        List<Double> historicalTotalSubscriptionCountList = productActualsViewList.stream().map(pamv -> Long.valueOf(pamv.getTotalNumberOfExistingSubscriptions()).doubleValue()).collect(Collectors.toCollection(ArrayList<Double>::new));
        List<LocalDate> historicalEndDates = productActualsViewList.stream().map(pamv -> pamv.getEndDate()).collect(Collectors.toCollection((ArrayList<LocalDate>::new)));

        //TODO: HERE we need to ensure that forecast shall be provided minimum until end of the current year in case actuals data is less to predict forecast
        //TODO: typically we are having the rule of getting forecast values half of the actuals data size. Now we need to see that this half sized forecasts goes upto end of year
        //TODO: else add some forecasted values in the actuals data set and recalculate forecast so that it reaches end of the year
        // TODO: This will be required only until initial few months when actuals data is less.
        final LocalDate lastActualsHistoricalRecordEndDate = historicalEndDates.get(historicalEndDates.size() - 1);
        final LocalDate defaultLastForecastEndDate = lastActualsHistoricalRecordEndDate.plusDays(Math.abs(historicalTotalSubscriptionCountList.size() / 2));
        int forecastSize = 0;
        final LocalDate endOfYearDate = new LocalDate(defaultLastForecastEndDate.getYear(), 12, 31);
        if (lastActualsHistoricalRecordEndDate.getYear() == defaultLastForecastEndDate.getYear() && defaultLastForecastEndDate.isBefore(endOfYearDate)) {
            int differenceBetweenLastForecastDateAndEndOfYear = Days.daysBetween(defaultLastForecastEndDate, endOfYearDate).getDays();
            forecastSize = historicalTotalSubscriptionCountList.size() / 2 + differenceBetweenLastForecastDateAndEndOfYear;
        } else {
            forecastSize = historicalTotalSubscriptionCountList.size() / 2;
        }
        List<Double> forecastChurnedSubscriptions = demandForecasterChain.forecast(productId, historicalSubscriptionChurnCountList, null, forecastSize);
        List<Double> forecastTotalSubscriptions = demandForecasterChain.forecast(productId, historicalTotalSubscriptionCountList, null, forecastSize);

        List<ProductForecastView> forecasts = new ArrayList<>(forecastTotalSubscriptions.size());
        double previousTotalSubscriptionCount = 0;

        LocalDate newForecastStartDate = lastActualsHistoricalRecordEndDate.plusDays(1);

        //derive new subscription from current and previous total subscription counts
        for (int i = 0; i < forecastTotalSubscriptions.size(); i++) {
            //Please verify if this calculation is right without considering churns
            double newSubscriptionCount = forecastTotalSubscriptions.get(i) - previousTotalSubscriptionCount + forecastChurnedSubscriptions.get(i);
            // this needs further treatment- instead of directly adding chunkAggregationPeriod we should find out how many days that month would have and add those many days
            LocalDate[] dates = deriveStartAndEndDates(newForecastStartDate, chunkAggregationPeriod);
            newForecastStartDate = dates[0];
            LocalDate newForecastEndDate = dates[1];
            ProductForecastView forecast = new ProductForecastView(new ProductVersionId(productId, newForecastStartDate),
                    newForecastEndDate,
                    Double.valueOf(newSubscriptionCount).longValue(),
                    Double.valueOf(forecastChurnedSubscriptions.get(i)).longValue(),
                    Double.valueOf(forecastTotalSubscriptions.get(i)).longValue());
            forecasts.add(forecast);
            previousTotalSubscriptionCount = forecastTotalSubscriptions.get(i);
            newForecastStartDate = newForecastEndDate.plusDays(1);
        }
        List<ProductForecastView> aggregatedActualsViewList = new AggregatorFactory(ProductForecastView.class).getAggregator(chunkAggregationPeriod).aggregate(forecasts, chunkAggregationPeriod);
        return aggregatedActualsViewList;
    }


    public LocalDate[] deriveStartAndEndDates(LocalDate startDate, int chunkAggregationPeriod) {
        if (chunkAggregationPeriod == 30) {
            return getMonthStartEndDates(startDate);
        } else if (chunkAggregationPeriod == 90) {
            return getQuarterStartEndDates(startDate);
        } else if (chunkAggregationPeriod == 7) {
            return getWeekStartEndDates(startDate);
        } else {
            return getMonthStartEndDates(startDate);
        }
    }

    private LocalDate[] getMonthStartEndDates(LocalDate localDate) {
        LocalDate[] dates = new LocalDate[2];
        dates[0] = localDate.monthOfYear().withMinimumValue();
        dates[1] = localDate.monthOfYear().withMaximumValue();
        return dates;
    }

    private LocalDate[] getWeekStartEndDates(LocalDate localDate) {
        LocalDate[] dates = new LocalDate[2];
        dates[0] = localDate.dayOfWeek().withMinimumValue();
        dates[1] = localDate.dayOfWeek().withMaximumValue();
        return dates;
    }

    private LocalDate[] getQuarterStartEndDates(LocalDate localDate) {

        LocalDate[] dates = new LocalDate[2];

        // Get the month of the current date
        int i = localDate.getMonthOfYear();

        // 1st - 3rd months represent quarter 1
        // 4th - 6th months represent quarter 2
        // 7th - 9th months represent quarter 3
        // 10th - 12th months represent quarter 4

        int startI = 0;
        int endI = 0;

        if (i >= 1 && i <= 3) {
            startI = i - 1;
            endI = i - 3;
        } else if (i >= 4 && i <= 6) {
            startI = i - 4;
            endI = i - 6;
        } else if (i >= 7 && i <= 9) {
            startI = i - 7;
            endI = i - 9;
        } else if (i >= 10 && i <= 12) {
            startI = i - 10;
            endI = i - 12;
        }

        startI = Math.abs(startI);
        endI = Math.abs(endI);

        dates[0] = localDate.minusMonths(startI).dayOfMonth().withMinimumValue();
        dates[1] = localDate.plusMonths(endI).dayOfMonth().withMaximumValue();

        return dates;
    }
}

