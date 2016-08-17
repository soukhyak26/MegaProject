package com.affaince.subscription.product.services.pricing.determinator;


import com.affaince.subscription.common.type.ProductDemandTrend;
import com.affaince.subscription.product.query.repository.PriceBucketViewRepository;
import com.affaince.subscription.product.query.repository.ProductActualsViewRepository;
import com.affaince.subscription.product.query.repository.ProductConfigurationViewRepository;
import com.affaince.subscription.product.query.repository.ProductForecastViewRepository;
import com.affaince.subscription.product.query.view.PriceBucketView;
import com.affaince.subscription.product.query.view.ProductActualsView;
import com.affaince.subscription.product.query.view.ProductConfigurationView;
import com.affaince.subscription.product.query.view.ProductForecastView;
import com.affaince.subscription.product.services.pricing.processor.calculator.CalculatorChain;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import java.util.List;


public class DefaultPriceDeterminator implements PriceDeterminator {
    @Autowired
    private PriceBucketViewRepository priceBucketViewRepository;
    @Autowired
    private ProductActualsViewRepository productActualsViewRepository;
    @Autowired
    private ProductForecastViewRepository productForecastViewRepository;
    @Autowired
    private ProductConfigurationViewRepository productConfigurationViewRepository;
    @Autowired
    private CalculatorChain calculatorChain;

    public DefaultPriceDeterminator() {

    }

    @Override
    public PriceBucketView calculateOfferedPrice(String productId, ProductDemandTrend productDemandTrend) {

        //List<FunctionCoefficients> demandAndCostFunctionCoefficients = priceDeterminationCriteria.getListOfCriteriaElements();

        //who will set demand function coefficient??
/*
        FunctionCoefficients demandFunctionCoefficients = demandAndCostFunctionCoefficients.stream().filter(coefficient -> coefficient.getType().equals(CoefficientsType.DEMAND_FUNCTION_COEFFICIENT)).findFirst().get();
        final String productId = demandFunctionCoefficients.getProductId();
*/

        List<PriceBucketView> activePriceBuckets = priceBucketViewRepository.findByProductVersionId_ProductId(productId);
        ProductConfigurationView productConfigurationView = productConfigurationViewRepository.findOne(productId);
        Sort sort = new Sort(Sort.Direction.DESC, "productVersionId.fromDate");
        ProductActualsView productActualsView = productActualsViewRepository.findByProductVersionId_ProductId(productId, sort).get(0);
        ProductForecastView productForecastView = productForecastViewRepository.findByProductVersionId_ProductId(productId, sort).get(0);
        PriceBucketView latestPriceBucket = calculatorChain.calculatePrice(activePriceBuckets, productActualsView, productDemandTrend, productConfigurationView.getChangeThresholdForPriceChange());
        priceBucketViewRepository.save(latestPriceBucket);
        return latestPriceBucket;

    }
}
