package com.affaince.subscription.product.services.forecast;

import com.affaince.subscription.product.query.repository.*;
import com.affaince.subscription.product.query.view.ProductActualMetricsView;
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
    DemandForecasterChain demandForecasterChain;
    @Autowired
    private ProductForecastMetricsViewRepository productForecastMetricsViewRepository;
    @Autowired
    private ProductActualMetricsViewRepository productActualMetricsViewRepository;
    @Autowired
    private ProductApproximateForecastViewRepository productApproximateForecastViewRepository;
    @Autowired
    private ProductForecastViewRepository productForecastViewRepository;
    @Autowired
    private ProductActualsViewRepository productActualsViewRepository;

    public DemandGrowthAndChurnForecast buildForecast(String productId) {
        List<ProductActualMetricsView> productActualMetricsViewList = productActualMetricsViewRepository.findByProductVersionId_ProductId(productId);
        List<Double> historicalDailySubscriptionCountList = productActualMetricsViewList.stream().map(pamv -> Long.valueOf(pamv.getNewSubscriptions()).doubleValue()).collect(Collectors.toCollection(ArrayList<Double>::new));
        List<Double> historicalDailySubscriptionChurnCountList = productActualMetricsViewList.stream().map(pamv -> Long.valueOf(pamv.getChurnedSubscriptions()).doubleValue()).collect(Collectors.toCollection(ArrayList<Double>::new));
        List<Double> forecastNewSubscriptions = demandForecasterChain.forecast(productId, historicalDailySubscriptionCountList);
        List<Double> forecastChurnedSubscriptions = demandForecasterChain.forecast(productId, historicalDailySubscriptionChurnCountList);
        return new DemandGrowthAndChurnForecast(Double.valueOf(forecastNewSubscriptions.get(0)).longValue(), Double.valueOf(forecastChurnedSubscriptions.get(0)).longValue());
    }

}
