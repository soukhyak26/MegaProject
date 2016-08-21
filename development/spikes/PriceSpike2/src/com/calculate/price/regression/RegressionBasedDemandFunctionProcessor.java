package com.calculate.price.regression;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by mandark on 28-02-2016.
 */
@Deprecated
public class RegressionBasedDemandFunctionProcessor {
/*
    @Autowired
    PriceBucketViewRepository priceBucketViewRepository;
*/

    public static FunctionCoefficients processFunction(Map<Double,Double> historicalPriceVsDemand) throws  Exception {
      //  List<PriceBucketView> priceBucketStats = priceBucketViewRepository.findByProductVersionId_ProductId(productView.getProductId());

     //   final List<PriceBucketView> priceBucketsWithSamePurchasePrice=findBucketsWithSamePurchasePrice(priceBucketStats);
     //   Collections.sort(priceBucketsWithSamePurchasePrice);
     //   final PriceBucketView priceBucketJustBeforePurchasePriceChange = getPriceBucketJustBeforePurchasePriceChange(priceBucketStats, priceBucketsWithSamePurchasePrice.get(0));
        /*final Map <Double, Double> offerPriceVersesSubscriptionsMapping =
                offerPriceVersesSubscriptionMapping(priceBucketsWithSamePurchasePrice, priceBucketJustBeforePurchasePriceChange);*/
        List<Double> parametersList = new ArrayList<Double>();
        /*for (Map.Entry<Double, Double> entry : offerPriceVersesSubscriptionsMapping.entrySet()) {
            double offeredPrice= entry.getKey();
            double subscriptionCount = entry.getValue();
            parametersList.add(offeredPrice);
            parametersList.add(subscriptionCount);
        }*/

        RegressionResult result = MathsProcessingService.processMultipleLinearRegression(parametersList.stream().mapToDouble(Double::doubleValue).toArray(), parametersList.size() / 2, 1);
        if (result.getAdjustedRSquaredValue() < 0.5) {
            double[] regressionParamters = result.getRegressionParameters();
            FunctionCoefficients functionCoefficients = new FunctionCoefficients("1", regressionParamters[0], regressionParamters[1], CoefficientsType.DEMAND_FUNCTION_COEFFICIENT);
            return functionCoefficients;
        } else {
            throw new Exception();
        }

    }

}
