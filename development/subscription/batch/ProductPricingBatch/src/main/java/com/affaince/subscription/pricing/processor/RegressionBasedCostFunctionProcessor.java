package com.affaince.subscription.pricing.processor;

import com.affaince.subscription.common.service.MathsProcessingService;
import com.affaince.subscription.common.vo.RegressionResult;
import com.affaince.subscription.pricing.processor.exception.InaccurateRegressionException;
import com.affaince.subscription.pricing.query.view.ProductStatisticsView;
import com.affaince.subscription.pricing.vo.CoefficientsType;
import com.affaince.subscription.pricing.vo.FunctionCoefficients;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mandark on 21-02-2016.
 */
public class RegressionBasedCostFunctionProcessor implements FunctionProcessor<String, ProductStatisticsView> {
    @Override
    public FunctionCoefficients processFunction(String productId, List<ProductStatisticsView> productStatistics) {
        //Cost of a product depends on
        //purchase price(fixed cost)
        //fixed operating expense per Product(rent,electiricty bill etc) as a fixed cost
        //variable operating expenses such as shipping price per product
        //TrainingData trainingData = TrainingDataBuilder.buildTrainingData()
        List<Double> parametersList = new ArrayList<Double>();
        for (ProductStatisticsView productStat : productStatistics) {
            double costPerMonth = (productStat.getPurchasePrice() + productStat.getFixedOperatingExpense() + productStat.getVariableOperatingExpense()) * productStat.getProductSubscriptionCount();
            double subscriptionCount = productStat.getProductSubscriptionCount();
            parametersList.add(costPerMonth);
            parametersList.add(subscriptionCount);
            costPerMonth = 0;
            subscriptionCount = 0;
        }

        RegressionResult result = MathsProcessingService.processMultipleLinearRegression(parametersList.stream().mapToDouble(Double::doubleValue).toArray(), parametersList.size() / 2, 1);
        if (result.getAdjustedRSquaredValue() < 0.5) {
            double[] regressionParamters = result.getRegressionParameters();
            FunctionCoefficients functionCoefficients = new FunctionCoefficients(productId, regressionParamters[0], regressionParamters[1], CoefficientsType.COST_FUNCTION_COEFFICIENT);
            return functionCoefficients;
        } else {
            throw new InaccurateRegressionException();
        }
    }
}
