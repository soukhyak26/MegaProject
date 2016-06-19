package com.affaince.subscription.product.registration.services.pricing.processor.function;

import com.affaince.subscription.common.service.MathsProcessingService;
import com.affaince.subscription.common.vo.RegressionResult;
import com.affaince.subscription.product.registration.query.repository.ProductActualMetricsViewRepository;
import com.affaince.subscription.product.registration.query.view.ProductActualMetricsView;
import com.affaince.subscription.product.registration.query.view.ProductView;
import com.affaince.subscription.product.registration.services.pricing.processor.exception.InaccurateRegressionException;
import com.affaince.subscription.product.registration.vo.CoefficientsType;
import com.affaince.subscription.product.registration.vo.FunctionCoefficients;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mandark on 21-02-2016.
 */
public class RegressionBasedCostFunctionProcessor implements FunctionProcessor<ProductView> {
    @Autowired
    ProductActualMetricsViewRepository productActualMetricsViewRepository;

    @Override
    public FunctionCoefficients processFunction(ProductView productView) {
        //Cost of a product depends on
        //purchase price(fixed cost)
        //fixed operating expense per Product(rent,electiricty bill etc) as a fixed cost
        //variable operating expenses such as shipping price per product
        //TrainingData trainingData = TrainingDataBuilder.buildTrainingData()
        List<ProductActualMetricsView> productActualMetricsViews = productActualMetricsViewRepository.findByProductMonthlyVersionId_ProductId(productView.getProductId());

        List<Double> parametersList = new ArrayList<Double>();
        for (ProductActualMetricsView productStat : productActualMetricsViews) {
            double costPerMonth = (productStat.getLatestTaggedPriceVersion().getPurchasePricePerUnit() + productStat.getFixedOperatingExpense() + productStat.getVariableOperatingExpense()) * productStat.getTotalNumberOfExistingSubscriptions();
            double subscriptionCount = productStat.getTotalNumberOfExistingSubscriptions();
            parametersList.add(costPerMonth);
            parametersList.add(subscriptionCount);
            costPerMonth = 0;
            subscriptionCount = 0;
        }

        RegressionResult result = MathsProcessingService.processMultipleLinearRegression(parametersList.stream().mapToDouble(Double::doubleValue).toArray(), parametersList.size() / 2, 1);
        if (result.getAdjustedRSquaredValue() < 0.5) {
            double[] regressionParamters = result.getRegressionParameters();
            FunctionCoefficients functionCoefficients = new FunctionCoefficients(productView.getProductId(), regressionParamters[0], regressionParamters[1], CoefficientsType.COST_FUNCTION_COEFFICIENT);
            return functionCoefficients;
        } else {
            throw new InaccurateRegressionException();
        }
    }
}
