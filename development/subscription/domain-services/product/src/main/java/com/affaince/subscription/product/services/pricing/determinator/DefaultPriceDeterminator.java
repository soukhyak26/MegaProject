package com.affaince.subscription.product.services.pricing.determinator;


import com.affaince.subscription.product.query.repository.PriceBucketViewRepository;
import com.affaince.subscription.product.query.repository.ProductActualsViewRepository;
import com.affaince.subscription.product.query.repository.ProductForecastViewRepository;
import com.affaince.subscription.product.query.view.PriceBucketView;
import com.affaince.subscription.product.query.view.ProductActualsView;
import com.affaince.subscription.product.query.view.ProductForecastView;
import com.affaince.subscription.product.services.pricing.processor.calculator.CalculatorChain;
import com.affaince.subscription.product.vo.CoefficientsType;
import com.affaince.subscription.product.vo.FunctionCoefficients;
import com.affaince.subscription.product.vo.PriceDeterminationCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;

import java.util.List;

@Deprecated
public class DefaultPriceDeterminator implements PriceDeterminator {
    @Autowired
    private PriceBucketViewRepository priceBucketViewRepository;
    @Autowired
    private ProductActualsViewRepository productActualsViewRepository;
    @Autowired
    private ProductForecastViewRepository productForecastViewRepository;
    @Autowired
    private CalculatorChain calculatorChain;

    public DefaultPriceDeterminator() {

    }

    @Override
    public PriceBucketView calculateOfferedPrice(PriceDeterminationCriteria priceDeterminationCriteria) {

        List<FunctionCoefficients> demandAndCostFunctionCoefficients = priceDeterminationCriteria.getListOfCriteriaElements();

        //who will set demand function coefficient??
        FunctionCoefficients demandFunctionCoefficients = demandAndCostFunctionCoefficients.stream().filter(coefficient -> coefficient.getType().equals(CoefficientsType.DEMAND_FUNCTION_COEFFICIENT)).findFirst().get();
        final String productId = demandFunctionCoefficients.getProductId();

        List<PriceBucketView> activePriceBuckets = priceBucketViewRepository.findByProductVersionId_ProductId(productId);

        Sort sort = new Sort(Sort.Direction.DESC, "productVersionId.fromDate");
        ProductActualsView productActualsView = productActualsViewRepository.findByProductVersionId_ProductId(productId, sort).get(0);
        ProductForecastView productForecastView = productForecastViewRepository.findByProductVersionId_ProductId(productId, sort).get(0);
        PriceBucketView latestPriceBucket = calculatorChain.calculatePrice(activePriceBuckets, productActualsView, productForecastView);
        priceBucketViewRepository.save(latestPriceBucket);
        return latestPriceBucket;

    }
}
