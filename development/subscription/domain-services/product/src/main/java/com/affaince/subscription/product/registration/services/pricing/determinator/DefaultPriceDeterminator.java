package com.affaince.subscription.product.registration.services.pricing.determinator;


import com.affaince.subscription.common.vo.ProductMonthlyVersionId;
import com.affaince.subscription.product.registration.query.repository.PriceBucketViewRepository;
import com.affaince.subscription.product.registration.query.repository.ProductActualMetricsViewRepository;
import com.affaince.subscription.product.registration.query.repository.ProductForecastMetricsViewRepository;
import com.affaince.subscription.product.registration.query.view.PriceBucketView;
import com.affaince.subscription.product.registration.query.view.ProductActualMetricsView;
import com.affaince.subscription.product.registration.query.view.ProductForecastMetricsView;
import com.affaince.subscription.product.registration.services.pricing.processor.calculator.CalculatorChain;
import com.affaince.subscription.product.registration.services.pricing.processor.calculator.DemandCurveBasedPriceCalculator;
import com.affaince.subscription.product.registration.services.pricing.processor.calculator.OpeningPriceCalculator;
import com.affaince.subscription.product.registration.vo.CoefficientsType;
import com.affaince.subscription.product.registration.vo.FunctionCoefficients;
import com.affaince.subscription.product.registration.vo.PriceDeterminationCriteria;
import org.joda.time.YearMonth;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DefaultPriceDeterminator implements PriceDeterminator {
    @Autowired
    PriceBucketViewRepository priceBucketViewRepository;
    @Autowired
    ProductActualMetricsViewRepository productActualMetricsViewRepository;
    @Autowired
    ProductForecastMetricsViewRepository productForecastMetricsViewRepository;

    public DefaultPriceDeterminator() {

    }

    @Override
    public PriceBucketView calculateOfferedPrice(PriceDeterminationCriteria priceDeterminationCriteria) {

        List<FunctionCoefficients> demandAndCostFunctionCoefficients = priceDeterminationCriteria.getListOfCriteriaElements();

        //who will set demand function coeeficient
        FunctionCoefficients demandFunctionCoeffiecients = demandAndCostFunctionCoefficients.stream().filter(coefficient -> coefficient.getType().equals(CoefficientsType.DEMAND_FUNCTION_COEFFICIENT)).findFirst().get();
        final String productId = demandFunctionCoeffiecients.getProductId();

        List<PriceBucketView> activePriceBuckets = priceBucketViewRepository.findByProductVersionId_ProductId(productId);
        ProductActualMetricsView productActualMetricsView = productActualMetricsViewRepository.findOne(new ProductMonthlyVersionId(productId, YearMonth.now()));
        ProductForecastMetricsView productForecastMetricsView = productForecastMetricsViewRepository.findOne(new ProductMonthlyVersionId(productId, YearMonth.now()));
        CalculatorChain calculatorChain = new CalculatorChain();
        calculatorChain.addCalculator(new OpeningPriceCalculator());
        calculatorChain.addCalculator(new DemandCurveBasedPriceCalculator());
        PriceBucketView latestPriceBucket= calculatorChain.calculatePrice(activePriceBuckets, productActualMetricsView,productForecastMetricsView);
        priceBucketViewRepository.save(latestPriceBucket);
        return latestPriceBucket;

    }
}
