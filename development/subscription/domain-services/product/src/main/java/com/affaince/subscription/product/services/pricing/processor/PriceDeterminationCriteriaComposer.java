package com.affaince.subscription.product.services.pricing.processor;

import com.affaince.subscription.product.query.view.ProductView;
import com.affaince.subscription.product.services.pricing.processor.function.FunctionProcessor;
import com.affaince.subscription.product.services.pricing.processor.function.RegressionBasedCostFunctionProcessor;
import com.affaince.subscription.product.services.pricing.processor.function.RegressionBasedDemandFunctionProcessor;
import com.affaince.subscription.product.vo.CoefficientsType;
import com.affaince.subscription.product.vo.FunctionCoefficients;
import com.affaince.subscription.product.vo.PriceDeterminationCriteria;
import com.affaince.subscription.product.vo.PricingStrategyType;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mandark on 06-04-2016.
 */
@Deprecated
public class PriceDeterminationCriteriaComposer {


    public PriceDeterminationCriteria buildPriceDeterminationCriteria(ProductView productView){
        if(productView.getPricingStrategyType()== PricingStrategyType.DEFAULT_PRICING_STRATEGY){
            FunctionCoefficients coefficient = new FunctionCoefficients(productView.getProductId(),0.0,0.0, CoefficientsType.DEMAND_FUNCTION_COEFFICIENT);
            List<FunctionCoefficients> functionCoefficientsList= new ArrayList<FunctionCoefficients>();
            functionCoefficientsList.add(coefficient);
            return new PriceDeterminationCriteria(functionCoefficientsList);
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
        FunctionProcessor processor = new RegressionBasedCostFunctionProcessor();
        FunctionCoefficients coefficients = processor.processFunction(productView);
        return coefficients;
    }

    private FunctionCoefficients processDemandFunction(ProductView productView){
        FunctionProcessor processor = new RegressionBasedDemandFunctionProcessor();
        FunctionCoefficients coefficients = processor.processFunction(productView);
        return coefficients;
    }

}
