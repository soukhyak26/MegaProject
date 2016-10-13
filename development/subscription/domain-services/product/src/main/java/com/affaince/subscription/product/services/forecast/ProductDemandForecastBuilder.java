package com.affaince.subscription.product.services.forecast;

import com.affaince.subscription.common.vo.ProductVersionId;
import com.affaince.subscription.product.query.repository.ProductActualsViewRepository;
import com.affaince.subscription.product.query.view.ProductActualsView;
import com.affaince.subscription.product.query.view.ProductForecastView;
import com.affaince.subscription.product.query.view.ProductSubscriptionMetricsView;
import com.affaince.subscription.product.services.aggregators.PeriodBasedAggregator;
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

    @Autowired
    PeriodBasedAggregator periodBasedAggregator;
    @Autowired
    private DemandForecasterChain demandForecasterChain;
    @Autowired
    private ProductActualsViewRepository productActualsViewRepository;

    public List<ProductForecastView> buildForecast(String productId, LocalDate currentDate, int chunkAggregationPeriod, double demandCurvePeriod) {
        LocalDate startDate = currentDate.minusDays(Double.valueOf(demandCurvePeriod).intValue());
        List<ProductActualsView> productActualsViewList = productActualsViewRepository.findByProductVersionId_ProductIdAndEndDateBetween(productId, startDate, currentDate);
/*
        List<ProductActualsView> aggregatedActualsViewList = periodBasedAggregator.aggregate(productActualsViewList, chunkAggregationPeriod);

        List<Double> historicalSubscriptionChurnCountList = aggregatedActualsViewList.stream().map(pamv -> Long.valueOf(pamv.getChurnedSubscriptions()).doubleValue()).collect(Collectors.toCollection(ArrayList<Double>::new));
        List<Double> historicalTotalSubscriptionCountList = aggregatedActualsViewList.stream().map(pamv -> Long.valueOf(pamv.getTotalNumberOfExistingSubscriptions()).doubleValue()).collect(Collectors.toCollection(ArrayList<Double>::new));
        List<LocalDateTime> historicalEndDates = aggregatedActualsViewList.stream().map(pamv -> pamv.getEndDate()).collect(Collectors.toCollection((ArrayList<LocalDateTime>::new)));
*/

        List<Double> historicalSubscriptionChurnCountList = productActualsViewList.stream().map(pamv -> Long.valueOf(pamv.getChurnedSubscriptions()).doubleValue()).collect(Collectors.toCollection(ArrayList<Double>::new));
        List<Double> historicalTotalSubscriptionCountList = productActualsViewList.stream().map(pamv -> Long.valueOf(pamv.getTotalNumberOfExistingSubscriptions()).doubleValue()).collect(Collectors.toCollection(ArrayList<Double>::new));
        List<LocalDateTime> historicalEndDates = productActualsViewList.stream().map(pamv -> pamv.getEndDate()).collect(Collectors.toCollection((ArrayList<LocalDateTime>::new)));


        List<Double> forecastChurnedSubscriptions = demandForecasterChain.forecast(productId, historicalSubscriptionChurnCountList, null, historicalSubscriptionChurnCountList.size() / 2);
        List<Double> forecastTotalSubscriptions = demandForecasterChain.forecast(productId, historicalTotalSubscriptionCountList, null, historicalTotalSubscriptionCountList.size() / 2);

        List<ProductForecastView> forecasts = new ArrayList<>(forecastTotalSubscriptions.size());
        double previousTotalSubscriptionCount = 0;
        LocalDateTime lastHistoricalEndDate = historicalEndDates.get(historicalEndDates.size() - 1);
        LocalDateTime newForecastStartDate = lastHistoricalEndDate.plusDays(1);
        //derive new subscription from current and previous total subscription counts
        for (int i = 0; i < forecastTotalSubscriptions.size(); i++) {
            //Please verify if this calculation is right without considering churns
            double newSubscriptionCount = forecastTotalSubscriptions.get(i) - previousTotalSubscriptionCount + forecastChurnedSubscriptions.get(i);
            // this needs further treatment- instead of directly adding chunkAggregationPeriod we should find out how many days that month would have and add those many days
            LocalDateTime dates[] = deriveStartAndEndDates(newForecastStartDate, chunkAggregationPeriod);
            newForecastStartDate = dates[0];
            LocalDateTime newForecastEndDate = dates[1];
            ProductForecastView forecast = new ProductForecastView(new ProductVersionId(productId, newForecastStartDate),
                    newForecastEndDate,
                    Double.valueOf(newSubscriptionCount).longValue(),
                    Double.valueOf(forecastChurnedSubscriptions.get(i)).longValue(),
                    Double.valueOf(forecastTotalSubscriptions.get(i)).longValue());
            forecasts.add(forecast);
            previousTotalSubscriptionCount = forecastTotalSubscriptions.get(i);
            newForecastStartDate = newForecastEndDate.plusDays(1);
        }
        List<ProductForecastView> aggregatedActualsViewList = periodBasedAggregator.aggregate(forecasts, chunkAggregationPeriod);
        return aggregatedActualsViewList;
    }

    public LocalDateTime[] deriveStartAndEndDates(LocalDateTime startDate, int chunkAggregationPeriod) {
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

    private LocalDateTime[] getMonthStartEndDates(LocalDateTime localDate) {
        LocalDateTime[] dates = new LocalDateTime[2];
        dates[0] = localDate.monthOfYear().withMinimumValue();
        dates[1] = localDate.monthOfYear().withMaximumValue();
        return dates;
    }

    private LocalDateTime[] getWeekStartEndDates(LocalDateTime localDate) {
        LocalDateTime[] dates = new LocalDateTime[2];
        dates[0] = localDate.dayOfWeek().withMinimumValue();
        dates[1] = localDate.dayOfWeek().withMaximumValue();
        return dates;
    }

    private LocalDateTime[] getQuarterStartEndDates(LocalDateTime localDate) {

        LocalDateTime[] dates = new LocalDateTime[2];

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

