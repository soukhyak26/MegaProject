package com.affaince.subscription.product.services.forecast;

import com.affaince.subscription.product.query.repository.ProductActualsViewRepository;
import com.affaince.subscription.product.query.view.ProductActualsView;
import com.affaince.subscription.product.services.aggregators.PeriodBasedAggregator;
import com.affaince.subscription.product.vo.DemandGrowthAndChurnForecast;
import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by mandar on 10-07-2016.
 */
public class ProductDemandForecastBuilder {

    @Autowired
    PeriodBasedAggregator periodBasedAggregator;
    @Autowired
    private DemandForecasterChain demandForecasterChain;
    @Autowired
    private ProductActualsViewRepository productActualsViewRepository;

    public List<DemandGrowthAndChurnForecast> buildForecast(String productId, LocalDate currentDate, int chunkAggregationPeriod, double demandCurvePeriod) {
        LocalDate startDate = currentDate.minusDays(Double.valueOf(demandCurvePeriod).intValue());
        List<ProductActualsView> productActualsViewList = productActualsViewRepository.findByProductVersionId_ProductIdAndEndDateBetween(productId, startDate, currentDate);
        List<ProductActualsView> aggregatedActualsViewList = periodBasedAggregator.aggregate(productActualsViewList, chunkAggregationPeriod);

        List<Double> historicalSubscriptionChurnCountList = aggregatedActualsViewList.stream().map(pamv -> Long.valueOf(pamv.getChurnedSubscriptions()).doubleValue()).collect(Collectors.toCollection(ArrayList<Double>::new));
        List<Double> historicalTotalSubscriptionCountList = aggregatedActualsViewList.stream().map(pamv -> Long.valueOf(pamv.getTotalNumberOfExistingSubscriptions()).doubleValue()).collect(Collectors.toCollection(ArrayList<Double>::new));
        List<LocalDateTime> historicalEndDates = aggregatedActualsViewList.stream().map(pamv -> pamv.getEndDate()).collect(Collectors.toCollection((ArrayList<LocalDateTime>::new)));


        List<Double> forecastChurnedSubscriptions = demandForecasterChain.forecast(productId, historicalSubscriptionChurnCountList, null, historicalSubscriptionChurnCountList.size() / 2);
        List<Double> forecastTotalSubscriptions = demandForecasterChain.forecast(productId, historicalTotalSubscriptionCountList, null, historicalTotalSubscriptionCountList.size() / 2);

        List<DemandGrowthAndChurnForecast> forecasts = new ArrayList<>(forecastTotalSubscriptions.size());
        double previousTotalSubscriptionCount = 0;
        LocalDateTime lastHistoricalEndDate = historicalEndDates.get(historicalEndDates.size() - 1);
        LocalDateTime newForecastStartDate = lastHistoricalEndDate;
        //derive new subscription from current and previous total subscription counts
        for (int i = 0; i < forecastTotalSubscriptions.size(); i++) {
            //Please verify if this calculation is right without considering churns
            double newSubscriptionCount = forecastTotalSubscriptions.get(i) - previousTotalSubscriptionCount + forecastChurnedSubscriptions.get(i);
            newForecastStartDate = newForecastStartDate.plusDays(1);
            LocalDateTime newForecastEndDate = newForecastStartDate.plusDays(chunkAggregationPeriod);
            DemandGrowthAndChurnForecast forecast = new DemandGrowthAndChurnForecast(newSubscriptionCount, forecastChurnedSubscriptions.get(i), forecastTotalSubscriptions.get(i), newForecastStartDate, newForecastEndDate);
            forecasts.add(forecast);
            previousTotalSubscriptionCount = forecastTotalSubscriptions.get(i);
            newForecastStartDate = newForecastEndDate;
        }
        return forecasts;
    }

}
