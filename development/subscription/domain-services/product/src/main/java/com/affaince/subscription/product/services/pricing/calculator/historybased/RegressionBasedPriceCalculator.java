package com.affaince.subscription.product.services.pricing.calculator.historybased;

import com.affaince.subscription.common.service.forecast.DemandForecasterChain;
import com.affaince.subscription.common.type.EntityStatus;
import com.affaince.subscription.common.type.ProductDemandTrend;
import com.affaince.subscription.date.SysDateTime;
import com.affaince.subscription.product.command.domain.PriceBucket;
import com.affaince.subscription.product.command.domain.Product;
import com.affaince.subscription.product.services.pricing.calculator.AbstractPriceCalculator;
import com.affaince.subscription.product.services.pricing.calculator.historybased.regression.FunctionCoefficients;
import com.affaince.subscription.product.services.pricing.calculator.historybased.regression.RegressionBasedDemandFunctionProcessor;
import com.affaince.subscription.product.services.pricing.exception.PricingEligibilityViolationException;
import com.affaince.subscription.product.vo.PriceTaggedWithProduct;
import com.affaince.subscription.product.vo.PricingStrategyType;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
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
    private RegressionBasedDemandFunctionProcessor regressionBasedDemandFunctionProcessor;
    @Autowired
    private DemandForecasterChain demandForecasterChain;

    public PriceBucket calculatePrice(Product product, ProductDemandTrend productDemandTrend) {
        String productId = product.getProductId();
        final PriceBucket latestPriceBucket = product.getLatestActivePriceBucket();
        List<PriceBucket> bucketsWithSamePurchasePrice = product.findBucketsWithSamePurchasePrice(latestPriceBucket);
        final PricingStrategyType pricingStrategyType = product.getProductConfiguration().getPricingStrategyType();

        if (pricingStrategyType == PricingStrategyType.DEMAND_BASED_PRICING_STRATEGY && bucketsWithSamePurchasePrice.size() > maxHistoryCountforDefaultPricing) {
            Map<Double, Double> historicalPriceVsDemand = new HashMap<>();
            bucketsWithSamePurchasePrice.stream().forEach(priceBucket -> historicalPriceVsDemand.put(priceBucket.getOfferedPriceOrPercentDiscountPerUnit(), Long.valueOf(priceBucket.getNumberOfNewSubscriptions()).doubleValue()));
            FunctionCoefficients functionCoefficients = regressionBasedDemandFunctionProcessor.processFunction(historicalPriceVsDemand);
            List<Double> historyOfSubscriptions = new ArrayList<>();
            bucketsWithSamePurchasePrice.stream().forEach(priceBucket -> historyOfSubscriptions.add(Long.valueOf(priceBucket.getNumberOfNewSubscriptions()).doubleValue()));
            //NEED TO VERIFY IF BELOW STEP IS CORRECT???
            double expectedDemand = demandForecasterChain.forecast(productId, historyOfSubscriptions, null, historyOfSubscriptions.size() / 2).get(0);
            double offeredPrice = calculateOfferedPrice(functionCoefficients.getIntercept(), functionCoefficients.getSlope(), expectedDemand);
            DateTimeFormatter format = DateTimeFormat.forPattern("MMddyyyy");
            LocalDateTime currentDate = SysDateTime.now();
            final String taggedPriceVersionId = productId + currentDate.toLocalDate().toString(format);
            PriceTaggedWithProduct taggedPriceVersion = new PriceTaggedWithProduct(taggedPriceVersionId, product.getLatestTaggedPriceVersion().getPurchasePricePerUnit(), product.getLatestTaggedPriceVersion().getMRP(), currentDate.toLocalDate());
            PriceBucket newPriceBucket = product.createNewPriceBucket(productId, taggedPriceVersion, offeredPrice, EntityStatus.CREATED, currentDate);
            newPriceBucket.setSlope(functionCoefficients.getSlope());
            return newPriceBucket;
        }
        throw new PricingEligibilityViolationException();

    }
}
