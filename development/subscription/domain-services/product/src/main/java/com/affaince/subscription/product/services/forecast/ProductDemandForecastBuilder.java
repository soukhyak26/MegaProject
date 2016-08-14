package com.affaince.subscription.product.services.forecast;

import com.affaince.subscription.product.query.repository.ProductActualsViewRepository;
import com.affaince.subscription.product.query.view.ProductActualsView;
import com.affaince.subscription.product.services.aggregators.PeriodBasedAggregator;
import com.affaince.subscription.product.vo.DemandGrowthAndChurnForecast;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by mandar on 10-07-2016.
 */
public class ProductDemandForecastBuilder {

    @Autowired
    private DemandForecasterChain demandForecasterChain;
    @Autowired
    private ProductActualsViewRepository productActualsViewRepository;

    public DemandGrowthAndChurnForecast buildForecast(String productId, int chunkAggregationPeriod) {
        List<ProductActualsView> productActualsViewList = productActualsViewRepository.findByProductVersionId_ProductId(productId);
        List<ProductActualsView> aggregatedActualsViewList = new PeriodBasedAggregator().aggregate(productActualsViewList, chunkAggregationPeriod);
        List<Double> historicalDailySubscriptionCountList = aggregatedActualsViewList.stream().map(pamv -> Long.valueOf(pamv.getNewSubscriptions()).doubleValue()).collect(Collectors.toCollection(ArrayList<Double>::new));
        List<Double> historicalDailySubscriptionChurnCountList = aggregatedActualsViewList.stream().map(pamv -> Long.valueOf(pamv.getChurnedSubscriptions()).doubleValue()).collect(Collectors.toCollection(ArrayList<Double>::new));

        List<Double> forecastNewSubscriptions = demandForecasterChain.forecast(productId, historicalDailySubscriptionCountList);
        List<Double> forecastChurnedSubscriptions = demandForecasterChain.forecast(productId, historicalDailySubscriptionChurnCountList);
        return new DemandGrowthAndChurnForecast(Double.valueOf(forecastNewSubscriptions.get(0)).longValue(), Double.valueOf(forecastChurnedSubscriptions.get(0)).longValue());
    }

}
