package com.affaince.subscription.product.services.pricing.processor.calculator.historybased.regression;

import com.affaince.subscription.common.service.MathsProcessingService;
import com.affaince.subscription.common.vo.RegressionResult;
import com.affaince.subscription.product.services.pricing.processor.exception.InaccurateRegressionException;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by mandark on 28-02-2016.
 */

public class RegressionBasedDemandFunctionProcessor {

    public FunctionCoefficients processFunction(Map<Double, Double> historicalPriceVsDemand) {
        List<Double> parametersList = new ArrayList<Double>();
        for (Map.Entry<Double, Double> entry : historicalPriceVsDemand.entrySet()) {
            double offeredPrice = entry.getKey();
            double subscriptionCount = entry.getValue();
            parametersList.add(offeredPrice);
            parametersList.add(subscriptionCount);
        }

        RegressionResult result = MathsProcessingService.processMultipleLinearRegression(parametersList.stream().mapToDouble(Double::doubleValue).toArray(), parametersList.size() / 2, 1);
        if (result.getAdjustedRSquaredValue() < 0.5) {
            double[] regressionParamters = result.getRegressionParameters();
            FunctionCoefficients functionCoefficients = new FunctionCoefficients("1", regressionParamters[0], regressionParamters[1], CoefficientsType.DEMAND_FUNCTION_COEFFICIENT);
            return functionCoefficients;
        } else {
            throw new InaccurateRegressionException();
        }

    }

}
