package com.affaince.subscription.product.services.forecast;

import com.affaince.subscription.date.SysDate;
import com.affaince.subscription.product.query.repository.ProductActualsViewRepository;
import com.affaince.subscription.product.query.view.ProductActualsView;
import com.affaince.subscription.product.services.aggregators.PeriodBasedAggregator;
import com.affaince.subscription.product.vo.DemandGrowthAndChurnForecast;
import org.joda.time.LocalDate;
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

    public DemandGrowthAndChurnForecast buildForecast(String productId, int chunkAggregationPeriod, double demandCurvePeriod) {
        LocalDate currentDate = SysDate.now();
        LocalDate startDate = currentDate.minusDays(Double.valueOf(demandCurvePeriod).intValue());
        List<ProductActualsView> productActualsViewList = productActualsViewRepository.findByProductVersionId_ProductIdAndDateBetween(productId, startDate, currentDate);
        List<ProductActualsView> aggregatedActualsViewList = periodBasedAggregator.aggregate(productActualsViewList, chunkAggregationPeriod);
        List<Double> historicalNewSubscriptionCountList = aggregatedActualsViewList.stream().map(pamv -> Long.valueOf(pamv.getNewSubscriptions()).doubleValue()).collect(Collectors.toCollection(ArrayList<Double>::new));
        List<Double> historicalSubscriptionChurnCountList = aggregatedActualsViewList.stream().map(pamv -> Long.valueOf(pamv.getChurnedSubscriptions()).doubleValue()).collect(Collectors.toCollection(ArrayList<Double>::new));
        List<Double> historicalTotalSubscriptionCountList = aggregatedActualsViewList.stream().map(pamv -> Long.valueOf(pamv.getTotalNumberOfExistingSubscriptions()).doubleValue()).collect(Collectors.toCollection(ArrayList<Double>::new));

        //List<Double> forecastNewSubscriptions = demandForecasterChain.forecast(productId, historicalNewSubscriptionCountList);
        List<Double> forecastChurnedSubscriptions = demandForecasterChain.forecast(productId, historicalSubscriptionChurnCountList, null, historicalSubscriptionChurnCountList.size() / 2);
        List<Double> forecastTotalSubscriptions = demandForecasterChain.forecast(productId, historicalTotalSubscriptionCountList, null, historicalTotalSubscriptionCountList.size() / 2);
        List<Double> forecastNewSubscriptions = new ArrayList<Double>(forecastTotalSubscriptions.size());
        double previousTotalSubscriptionCount = 0;
        //derive new subscription from current and previous total subscription counts
        for (int i = 0; i < forecastTotalSubscriptions.size(); i++) {
            double newSubscriptionCount = forecastTotalSubscriptions.get(i) - previousTotalSubscriptionCount;
            forecastNewSubscriptions.add(newSubscriptionCount);
            previousTotalSubscriptionCount = forecastTotalSubscriptions.get(i);
        }
        return new DemandGrowthAndChurnForecast(
                forecastNewSubscriptions,
                forecastChurnedSubscriptions,
                forecastTotalSubscriptions,
                aggregatedActualsViewList.get(0).getEndDate().plusDays(1),
                aggregatedActualsViewList.get(0).getEndDate().plusDays(chunkAggregationPeriod));
    }

}
