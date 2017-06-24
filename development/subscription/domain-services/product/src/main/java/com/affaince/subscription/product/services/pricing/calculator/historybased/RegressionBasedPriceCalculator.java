package com.affaince.subscription.product.services.pricing.calculator.historybased;

import com.affaince.subscription.common.type.ProductDemandTrend;
import com.affaince.subscription.date.SysDateTime;
import com.affaince.subscription.product.command.domain.PriceBucket;
import com.affaince.subscription.product.command.domain.Product;
import com.affaince.subscription.product.services.pricing.calculator.AbstractPriceCalculator;
import com.affaince.subscription.product.services.pricing.calculator.historybased.regression.FunctionCoefficients;
import com.affaince.subscription.product.services.pricing.calculator.historybased.regression.RegressionBasedDemandFunctionProcessor;
import com.affaince.subscription.product.services.pricing.exception.PricingEligibilityViolationException;
import com.affaince.subscription.common.vo.PriceTaggedWithProduct;
import com.affaince.subscription.common.type.PricingStrategyType;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by mandar on 27-08-2016.
 */
public class RegressionBasedPriceCalculator extends AbstractPriceCalculator {
    @Autowired
    private RegressionBasedDemandFunctionProcessor regressionBasedDemandFunctionProcessor;

    public PriceBucket calculatePrice(Product product, ProductDemandTrend productDemandTrend) {
        String productId = product.getProductId();
        final PriceBucket latestPriceBucket = product.findLatestActivePriceBucket();
        List<PriceBucket> bucketsWithSamePurchasePrice = product.findBucketsWithSamePurchasePrice(latestPriceBucket);
        final PricingStrategyType pricingStrategyType = product.getProductConfiguration().getPricingStrategyType();

        if (pricingStrategyType == PricingStrategyType.DEMAND_BASED_PRICING_STRATEGY && bucketsWithSamePurchasePrice.size() > maxHistoryCountforDefaultPricing) {
            Map<Double, Double> historicalPriceVsDemand = new HashMap<>();
            //find out map of offer price and demand at that offer price
            bucketsWithSamePurchasePrice.stream().forEach(priceBucket -> historicalPriceVsDemand.put(priceBucket.getFixedOfferedPriceOrPercentDiscountPerUnit(), Long.valueOf(priceBucket.getNumberOfExistingSubscriptions()).doubleValue()));
            FunctionCoefficients functionCoefficients = regressionBasedDemandFunctionProcessor.processFunction(historicalPriceVsDemand);
            //find expected demand
            double expectedDemand=0;
            if (productDemandTrend == ProductDemandTrend.DOWNWARD) {
                expectedDemand = latestPriceBucket.getNumberOfNewSubscriptions() - latestPriceBucket.getNumberOfNewSubscriptions() * product.getRevenueChangeThresholdForPriceChange();
            } else {
                expectedDemand = latestPriceBucket.getNumberOfNewSubscriptions() + latestPriceBucket.getNumberOfNewSubscriptions() * product.getRevenueChangeThresholdForPriceChange();
            }

            double offeredPrice = calculateOfferedPrice(functionCoefficients.getIntercept(), functionCoefficients.getSlope(), expectedDemand);
            DateTimeFormatter format = DateTimeFormat.forPattern("MMddyyyy");
            LocalDateTime currentDate = SysDateTime.now();
            final String taggedPriceVersionId = productId + currentDate.toLocalDate().toString(format);
            PriceTaggedWithProduct taggedPriceVersion = new PriceTaggedWithProduct(taggedPriceVersionId, product.getLatestTaggedPriceVersion().getPurchasePricePerUnit(), product.getLatestTaggedPriceVersion().getMRP(), currentDate.toLocalDate());
            //PriceBucket newPriceBucket = product.createNewPriceBucket(productId, taggedPriceVersion, offeredPrice, EntityStatus.CREATED, currentDate);
            PriceBucket newPriceBucket=createNewPriceBucket(productId,latestPriceBucket.getPriceBucketId(), product.getLatestTaggedPriceVersion(), offeredPrice,product.getProductAccount().getProductPricingCategory());
            newPriceBucket.setSlope(functionCoefficients.getSlope());
            return newPriceBucket;
        }else{
            if(null != getNextCalculator()) {
                return getNextCalculator().calculatePrice(product, productDemandTrend);
            }else{
                throw new PricingEligibilityViolationException();
            }
        }
    }
}
