package com.affaince.subscription.product.services.pricing.calculator.historybased;

import com.affaince.subscription.date.SysDate;
import com.affaince.subscription.product.query.view.PriceBucketView;
import com.affaince.subscription.product.services.forecast.DemandForecasterChain;
import com.affaince.subscription.product.services.pricing.calculator.AbstractPriceCalculator;
import com.affaince.subscription.product.services.pricing.calculator.historybased.regression.FunctionCoefficients;
import com.affaince.subscription.product.services.pricing.calculator.historybased.regression.RegressionBasedDemandFunctionProcessor;
import com.affaince.subscription.product.services.pricing.exception.PricingEligibilityViolationException;
import com.affaince.subscription.product.vo.PriceCalculationParameters;
import com.affaince.subscription.product.vo.PriceTaggedWithProduct;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mandar on 27-08-2016.
 */
public class RegressionBasedPriceCalculator extends AbstractPriceCalculator {
    @Autowired
    RegressionBasedDemandFunctionProcessor regressionBasedDemandFunctionProcessor;
    @Autowired
    DemandForecasterChain demandForecasterChain;

    public PriceBucketView calculatePrice(PriceCalculationParameters priceCalculationParameters) {
        String productId = priceCalculationParameters.getProductActualsView().getProductVersionId().getProductId();
        List<PriceBucketView> bucketsWithSamePurchasePrice = findBucketsWithSamePurchasePrice(productId, priceCalculationParameters.getActivePriceBuckets());
        final PriceBucketView latestPriceBucket = getLatestPriceBucket(priceCalculationParameters.getActivePriceBuckets());
        if (bucketsWithSamePurchasePrice.size() > 20) {
            Map<Double, Double> historicalPriceVsDemand = new HashMap<>();
            bucketsWithSamePurchasePrice.stream().forEach(priceBucket -> historicalPriceVsDemand.put(priceBucket.getOfferedPricePerUnit(), Long.valueOf(priceBucket.getNumberOfNewCustomersAssociatedWithAPrice()).doubleValue()));
            FunctionCoefficients functionCoefficients = regressionBasedDemandFunctionProcessor.processFunction(historicalPriceVsDemand);
            List<Double> historyOfSubscriptions = new ArrayList<>();
            bucketsWithSamePurchasePrice.stream().forEach(priceBucket -> historyOfSubscriptions.add(Long.valueOf(priceBucket.getNumberOfNewCustomersAssociatedWithAPrice()).doubleValue()));
            //NEED TO VERIFY IF BELOW STEP IS CORRECT???
            double expectedDemand = demandForecasterChain.forecast(productId, historyOfSubscriptions).get(0);
            double offeredPrice = calculateOfferedPrice(functionCoefficients.getIntercept(), functionCoefficients.getSlope(), expectedDemand);
            PriceTaggedWithProduct taggedPriceVersion = new PriceTaggedWithProduct(latestPriceBucket.getTaggedPriceVersion().getPurchasePricePerUnit(), latestPriceBucket.getTaggedPriceVersion().getMRP(), SysDate.now());
            return createPriceBucket(productId, taggedPriceVersion, functionCoefficients.getSlope(), offeredPrice);
        }
        throw new PricingEligibilityViolationException();

    }
}
