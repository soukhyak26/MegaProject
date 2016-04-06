package com.affaince.subscription.pricing.processor;

import com.affaince.subscription.pricing.query.repository.PriceBucketViewRepository;
import com.affaince.subscription.pricing.query.repository.ProductStatisticsViewRepository;
import com.affaince.subscription.pricing.query.view.PriceBucketView;
import com.affaince.subscription.pricing.query.view.ProductStatisticsView;
import com.affaince.subscription.pricing.query.view.ProductView;
import com.affaince.subscription.pricing.vo.CoefficientsType;
import com.affaince.subscription.pricing.vo.FunctionCoefficients;
import com.affaince.subscription.pricing.vo.PriceDeterminationCriteria;
import com.affaince.subscription.pricing.vo.PricingStrategyType;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mandark on 06-04-2016.
 */
public class PriceDeterminationCriteriaComposer {
    @Autowired
    PriceBucketViewRepository priceBucketViewRepository;
    @Autowired
    ProductStatisticsViewRepository productStatisticsViewRepository;


    public PriceDeterminationCriteria buildPriceDeterminationCriteria(ProductView productView){
        if(productView.getPricingStrategyType()== PricingStrategyType.DEFAULT_PRICING_STRATEGY){
            FunctionCoefficients coefficient = new FunctionCoefficients(productView.getProductId(),0.0,0.0, CoefficientsType.DEMAND_FUNCTION_COEFFICIENT);
            List<FunctionCoefficients> functionCoefficientsList= new ArrayList<FunctionCoefficients>();
            functionCoefficientsList.add(coefficient);
            PriceDeterminationCriteria criteria= new PriceDeterminationCriteria(functionCoefficientsList);
            return criteria;
        }else if(productView.getPricingStrategyType()== PricingStrategyType.DEMAND_AND_COST_BASED_PRICING_STRATEGY){
            List<FunctionCoefficients> functionCoefficientsList= new ArrayList<FunctionCoefficients>();
            FunctionCoefficients costFunctionCoefficient=processCostFunction(productView);
            FunctionCoefficients demandFunctionCoefficient=processDemandFunction(productView);
            functionCoefficientsList.add(costFunctionCoefficient);
            functionCoefficientsList.add(demandFunctionCoefficient);
            return new PriceDeterminationCriteria(functionCoefficientsList);
        }
        return null;
    }

    private FunctionCoefficients processCostFunction(ProductView productView){
        List<ProductStatisticsView> productStats =productStatisticsViewRepository.findByProductMonthlyVersionId_ProductId(productView.getProductId());
        FunctionProcessor processor = new RegressionBasedCostFunctionProcessor();
        FunctionCoefficients coefficients = processor.processFunction(productView.getProductId(), productStats);
        return coefficients;
    }

    private FunctionCoefficients processDemandFunction(ProductView productView){
        List<PriceBucketView> priceBucketStats = priceBucketViewRepository.findByProductVersionId_ProductId(productView.getProductId());
        FunctionProcessor processor = new RegressionBasedDemandFunctionProcessor();
        FunctionCoefficients coefficients = processor.processFunction(productView.getProductId(), priceBucketStats);
        return coefficients;
    }

}
