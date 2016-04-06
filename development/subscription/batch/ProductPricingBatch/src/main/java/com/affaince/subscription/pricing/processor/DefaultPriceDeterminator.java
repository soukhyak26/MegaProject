package com.affaince.subscription.pricing.processor;


import com.affaince.subscription.common.vo.ProductMonthlyVersionId;
import com.affaince.subscription.pricing.processor.calculator.CalculatorChain;
import com.affaince.subscription.pricing.processor.calculator.DemandCurveBasedPriceCalculator;
import com.affaince.subscription.pricing.processor.calculator.OpeningPriceCalculator;
import com.affaince.subscription.pricing.query.repository.PriceBucketViewRepository;
import com.affaince.subscription.pricing.query.repository.ProductStatisticsViewRepository;
import com.affaince.subscription.pricing.query.view.PriceBucketView;
import com.affaince.subscription.pricing.query.view.ProductStatisticsView;
import com.affaince.subscription.pricing.vo.CoefficientsType;
import com.affaince.subscription.pricing.vo.FunctionCoefficients;
import com.affaince.subscription.pricing.vo.PriceDeterminationCriteria;
import org.joda.time.YearMonth;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public class DefaultPriceDeterminator implements PriceDeterminator {
    @Autowired
    PriceBucketViewRepository priceBucketViewRepository;
    @Autowired
    ProductStatisticsViewRepository productStatisticsViewRepository;

    public DefaultPriceDeterminator() {

    }

    @Override
    public double calculateOfferedPrice(PriceDeterminationCriteria priceDeterminationCriteria) {

      //  List<CrudRepository> repositories = priceDeterminationCriteria.getDataRepositories();
        List<FunctionCoefficients> demandAndCostFunctionCoefficients = priceDeterminationCriteria.getListOfCriteriaElements();

/*
        PriceBucketViewRepository priceBucketViewRepository = (PriceBucketViewRepository) repositories.stream().filter(repository -> repository.getClass().isAssignableFrom(PriceBucketViewRepository.class)).findFirst().get();
        ProductStatisticsViewRepository productStatisticsViewRepository = (ProductStatisticsViewRepository) repositories.stream().filter(repository -> repository.getClass().isAssignableFrom(ProductStatisticsViewRepository.class)).findFirst().get();
*/
        //who will set demand function coeeficient
        FunctionCoefficients demandFunctionCoeffiecients = demandAndCostFunctionCoefficients.stream().filter(coefficient -> coefficient.getType().equals(CoefficientsType.DEMAND_FUNCTION_COEFFICIENT)).findFirst().get();
        final String productId = demandFunctionCoeffiecients.getProductId();

        List<PriceBucketView> activePriceBuckets = priceBucketViewRepository.findByProductVersionId_ProductId(productId);
        ProductStatisticsView productStatisticsView = productStatisticsViewRepository.findOne(new ProductMonthlyVersionId(productId, YearMonth.now()));

        CalculatorChain calculatorChain = new CalculatorChain();
        calculatorChain.addCalculator(new OpeningPriceCalculator());
        calculatorChain.addCalculator(new DemandCurveBasedPriceCalculator());
        return calculatorChain.calculatePrice(productId, activePriceBuckets, productStatisticsView);
    }
}
