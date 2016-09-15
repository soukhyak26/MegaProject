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
    PeriodBasedAggregator periodBasedAggregator;
    @Autowired
    private DemandForecasterChain demandForecasterChain;
    @Autowired
    private ProductActualsViewRepository productActualsViewRepository;

    public DemandGrowthAndChurnForecast buildForecast(String productId, int chunkAggregationPeriod) {
        List<ProductActualsView> productActualsViewList = productActualsViewRepository.findByProductVersionId_ProductId(productId);
        List<ProductActualsView> aggregatedActualsViewList = periodBasedAggregator.aggregate(productActualsViewList, chunkAggregationPeriod);
        List<Double> historicalSubscriptionCountList = aggregatedActualsViewList.stream().map(pamv -> Long.valueOf(pamv.getNewSubscriptions()).doubleValue()).collect(Collectors.toCollection(ArrayList<Double>::new));
        List<Double> historicalSubscriptionChurnCountList = aggregatedActualsViewList.stream().map(pamv -> Long.valueOf(pamv.getChurnedSubscriptions()).doubleValue()).collect(Collectors.toCollection(ArrayList<Double>::new));

        List<Double> forecastNewSubscriptions = demandForecasterChain.forecast(productId, historicalSubscriptionCountList);
        List<Double> forecastChurnedSubscriptions = demandForecasterChain.forecast(productId, historicalSubscriptionChurnCountList);
        return new DemandGrowthAndChurnForecast(
                Double.valueOf(forecastNewSubscriptions.get(0)).longValue(),
                Double.valueOf(forecastChurnedSubscriptions.get(0)).longValue(),
                aggregatedActualsViewList.get(0).getTotalNumberOfExistingSubscriptions() + Double.valueOf(forecastNewSubscriptions.get(0)).longValue() - Double.valueOf(forecastChurnedSubscriptions.get(0)).longValue(),
                aggregatedActualsViewList.get(0).getEndDate().plusDays(1),
                aggregatedActualsViewList.get(0).getEndDate().plusDays(chunkAggregationPeriod));
    }

}
