package com.affaince.subscription.pricing.detereminator;


import com.affaince.subscription.common.vo.ProductMonthlyVersionId;
import com.affaince.subscription.pricing.processor.calculator.CalculatorChain;
import com.affaince.subscription.pricing.processor.calculator.DemandCurveBasedPriceCalculator;
import com.affaince.subscription.pricing.processor.calculator.OpeningPriceCalculator;
import com.affaince.subscription.pricing.query.repository.PriceBucketViewRepository;
import com.affaince.subscription.pricing.query.repository.ProductMonthlyStatisticsViewRepository;
import com.affaince.subscription.pricing.query.view.PriceBucketView;
import com.affaince.subscription.pricing.query.view.ProductMonthlyStatisticsView;
import com.affaince.subscription.pricing.vo.CoefficientsType;
import com.affaince.subscription.pricing.vo.FunctionCoefficients;
import com.affaince.subscription.pricing.vo.PriceDeterminationCriteria;
import org.joda.time.YearMonth;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class DefaultPriceDeterminator implements PriceDeterminator {
    @Autowired
    PriceBucketViewRepository priceBucketViewRepository;
    @Autowired
    ProductMonthlyStatisticsViewRepository productMonthlyStatisticsViewRepository;

    public DefaultPriceDeterminator() {

    }

    @Override
    public PriceBucketView calculateOfferedPrice(PriceDeterminationCriteria priceDeterminationCriteria) {

        List<FunctionCoefficients> demandAndCostFunctionCoefficients = priceDeterminationCriteria.getListOfCriteriaElements();

        //who will set demand function coeeficient
        FunctionCoefficients demandFunctionCoeffiecients = demandAndCostFunctionCoefficients.stream().filter(coefficient -> coefficient.getType().equals(CoefficientsType.DEMAND_FUNCTION_COEFFICIENT)).findFirst().get();
        final String productId = demandFunctionCoeffiecients.getProductId();

        List<PriceBucketView> activePriceBuckets = priceBucketViewRepository.findByProductVersionId_ProductId(productId);
        ProductMonthlyStatisticsView productMonthlyStatisticsView = productMonthlyStatisticsViewRepository.findOne(new ProductMonthlyVersionId(productId, YearMonth.now()));

        CalculatorChain calculatorChain = new CalculatorChain();
        calculatorChain.addCalculator(new OpeningPriceCalculator());
        calculatorChain.addCalculator(new DemandCurveBasedPriceCalculator());
        PriceBucketView latestPriceBucket= calculatorChain.calculatePrice(activePriceBuckets, productMonthlyStatisticsView);
        priceBucketViewRepository.save(latestPriceBucket);
        return latestPriceBucket;

    }
}
