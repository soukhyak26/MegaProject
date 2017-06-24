package com.affaince.subscription.product.services.forecast;

import com.affaince.subscription.common.service.forecast.ARIMABasedDemandForecaster;
import com.affaince.subscription.common.vo.DataFrameVO;
import com.affaince.subscription.common.vo.ProductVersionId;
import com.affaince.subscription.product.factory.AggregatorFactory;
import com.affaince.subscription.product.query.repository.ProductActualsViewRepository;
import com.affaince.subscription.product.query.view.ProductActualsView;
import com.affaince.subscription.product.query.view.ProductForecastView;
import com.affaince.subscription.product.query.view.ProductPseudoActualsView;
import com.affaince.subscription.product.query.view.ProductSubscriptionMetricsView;
import com.affaince.subscription.product.services.aggregators.MetricsAggregator;
import com.affaince.subscription.product.services.aggregators.PeriodBasedAggregator;
import org.joda.time.DateTimeComparator;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by mandar on 10-07-2016.
 */

public class ProductDemandForecastBuilder<T extends ProductSubscriptionMetricsView> {


    private AggregatorFactory<ProductForecastView> aggregatorFactory;
    @Autowired
    private ARIMABasedDemandForecaster arimaBasedDemandForecaster;
    //private DemandForecasterChain demandForecasterChain;
    @Autowired
    private ProductActualsViewRepository productActualsViewRepository;

    public List<List<? extends ProductSubscriptionMetricsView>> buildForecast(String productId, LocalDate currentDate, int chunkAggregationPeriod, double demandCurvePeriod) {
        LocalDate startDate = currentDate.minusDays(Double.valueOf(demandCurvePeriod).intValue());
        List<ProductActualsView> productActualsViewList = productActualsViewRepository.findByProductVersionId_ProductIdAndEndDateBetween(productId, startDate, currentDate);

        List<DataFrameVO> historicalSubscriptionNewCountList = productActualsViewList.stream().map(pamv -> new DataFrameVO(pamv.getEndDate(),"New",Long.valueOf(pamv.getNewSubscriptions()).doubleValue())).collect(Collectors.toCollection(ArrayList<DataFrameVO>::new));
        List<DataFrameVO> historicalSubscriptionChurnCountList = productActualsViewList.stream().map(pamv -> new DataFrameVO(pamv.getEndDate(),"Churned",Long.valueOf(pamv.getChurnedSubscriptions()).doubleValue())).collect(Collectors.toCollection(ArrayList<DataFrameVO>::new));
        List<DataFrameVO> historicalTotalSubscriptionCountList = productActualsViewList.stream().map(pamv -> new DataFrameVO(pamv.getEndDate(),"Total",Long.valueOf(pamv.getTotalNumberOfExistingSubscriptions()).doubleValue())).collect(Collectors.toCollection(ArrayList<DataFrameVO>::new));

        List<LocalDate> historicalEndDates = productActualsViewList.stream().map(pamv -> pamv.getEndDate()).collect(Collectors.toCollection((ArrayList<LocalDate>::new)));

        int lastHistoricalDataIndex = historicalTotalSubscriptionCountList.size() - 1;
        //TODO: HERE we need to ensure that forecast shall be provided minimum until end of the current year in case actuals data is less to predict forecast
        //TODO: typically we are having the rule of getting forecast values half of the actuals data size. Now we need to see that this half sized forecasts goes upto end of year
        //TODO: else add some forecasted values in the actuals data set and recalculate forecast so that it reaches end of the year
        // TODO: This will be required only until initial few months when actuals data is less.
        final LocalDate lastActualsHistoricalRecordEndDate = historicalEndDates.get(historicalEndDates.size() - 1);
        int forecastSize = 0;
        final LocalDate endOfYearDate = new LocalDate(lastActualsHistoricalRecordEndDate.getYear(), 12, 31);
        final int daysBetweenLatestActualsAndEndOfYear = Days.daysBetween(lastActualsHistoricalRecordEndDate, endOfYearDate).getDays();

        if (daysBetweenLatestActualsAndEndOfYear < 90) {
            final LocalDate nextYearEndDate = new LocalDate(lastActualsHistoricalRecordEndDate.getYear() + 1, 12, 31);
            int differenceBetweenLastForecastDateAndEndOfNextYear = Days.daysBetween(lastActualsHistoricalRecordEndDate, nextYearEndDate).getDays();
            forecastSize = differenceBetweenLastForecastDateAndEndOfNextYear;
        } else {
            forecastSize = Days.daysBetween(lastActualsHistoricalRecordEndDate, endOfYearDate).getDays();
        }
        forecastSize = 90;
/*
        List<Double> forecastChurnedSubscriptions = demandForecasterChain.forecast(productId, historicalSubscriptionChurnCountList, null, forecastSize);
        List<Double> forecastTotalSubscriptions = demandForecasterChain.forecast(productId, historicalTotalSubscriptionCountList, null, forecastSize);
*/
        List<DataFrameVO> forecastNewSubscriptions=new ArrayList<>();
        forecastNewSubscriptions.addAll(this.forcastExpectedRecords(productId,historicalSubscriptionNewCountList,forecastSize));


        //List<DataFrameVO> forecastChurnedSubscriptions=new ArrayList<>();
//        forecastChurnedSubscriptions.addAll(this.forcastExpectedRecords(productId,historicalSubscriptionChurnCountList,forecastSize));

        List<DataFrameVO> forecastTotalSubscriptions=new ArrayList<>();
        forecastTotalSubscriptions.addAll(this.forcastExpectedRecords(productId,historicalTotalSubscriptionCountList,forecastSize));


        List<ProductPseudoActualsView> pseudoActuals = new ArrayList<>(forecastTotalSubscriptions.size());
        //double previousTotalSubscriptionCount = historicalTotalSubscriptionCountList.get(lastHistoricalDataIndex);
        //LocalDate newForecastStartDate = lastActualsHistoricalRecordEndDate.plusDays(1);
        //derive new subscription from current and previous total subscription counts
        for (int i = 0; i < forecastTotalSubscriptions.size(); i++) {
            // this needs further treatment- instead of directly adding chunkAggregationPeriod we should find out how many days that month would have and add those many days
           // LocalDate newForecastEndDate = newForecastStartDate;
            ProductPseudoActualsView dailyPseudoActualsView = new ProductPseudoActualsView(new ProductVersionId(productId, forecastTotalSubscriptions.get(i).getDate()),
                    forecastTotalSubscriptions.get(i).getDate(),
                    Double.valueOf(forecastNewSubscriptions.get(i).getValue()).longValue(),
          //          Double.valueOf(forecastChurnedSubscriptions.get(i).getValue()).longValue(),
                    0,
                    Double.valueOf(forecastTotalSubscriptions.get(i).getValue()).longValue(),currentDate);
            pseudoActuals.add(dailyPseudoActualsView);
        }

        List<ProductSubscriptionMetricsView> aggregatedForecastsList = new AggregatorFactory(ProductPseudoActualsView.class).getAggregator(chunkAggregationPeriod).aggregate(pseudoActuals, chunkAggregationPeriod);
        List<List<? extends ProductSubscriptionMetricsView>> pseudoActualsAndForecastsList = new ArrayList<>();
        pseudoActualsAndForecastsList.add(pseudoActuals);
        pseudoActualsAndForecastsList.add(aggregatedForecastsList);
        return pseudoActualsAndForecastsList;
    }

    private List<DataFrameVO> forcastExpectedRecords(String productId,List<DataFrameVO> content,int forecastSize){
        List<DataFrameVO> forecastableContent= new ArrayList<>();
        List<DataFrameVO> forecasts=new ArrayList<>();
        forecastableContent.addAll(content);
        while(forecasts.size()<forecastSize){
            List<DataFrameVO> forecastedRecords=arimaBasedDemandForecaster.forecast(productId,forecastableContent);
            forecasts.addAll(forecastedRecords);
            forecastableContent.addAll(forecastedRecords);
        }
        return  forecasts;
    }
    private LocalDate[] deriveStartAndEndDates(LocalDate startDate, int chunkAggregationPeriod) {
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
/*
        dates[0] = localDate.monthOfYear().withMinimumValue();
        dates[1] = localDate.monthOfYear().withMaximumValue();
*/
        dates[0] = localDate.dayOfMonth().withMinimumValue();
        dates[1] = localDate.dayOfMonth().withMaximumValue();

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

