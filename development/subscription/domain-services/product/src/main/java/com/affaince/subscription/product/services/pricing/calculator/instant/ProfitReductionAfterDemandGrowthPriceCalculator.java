package com.affaince.subscription.product.services.pricing.calculator.instant;

import com.affaince.subscription.common.type.EntityStatus;
import com.affaince.subscription.common.type.ProductDemandTrend;
import com.affaince.subscription.date.SysDateTime;
import com.affaince.subscription.product.command.domain.PriceBucket;
import com.affaince.subscription.product.command.domain.Product;
import com.affaince.subscription.product.services.pricing.calculator.AbstractPriceCalculator;
import com.affaince.subscription.product.vo.PriceTaggedWithProduct;
import com.affaince.subscription.product.vo.PricingStrategyType;
import org.joda.time.LocalDateTime;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;

/**
 * Created by mandark on 29-04-2016.
 */
@Component
public class ProfitReductionAfterDemandGrowthPriceCalculator extends AbstractPriceCalculator {

    public PriceBucket calculatePrice(Product product, ProductDemandTrend productDemandTrend) {
        final PriceBucket latestPriceBucket = product.getLatestActivePriceBucket();
        final String productId = product.getProductId();
        final PriceTaggedWithProduct latestTaggedPriceVersion= product.getLatestTaggedPriceVersion();
        List<PriceBucket> bucketsWithSamePurchasePrice = product.findBucketsWithSamePurchasePrice(latestPriceBucket);
        final PricingStrategyType pricingStrategyType = product.getProductConfiguration().getPricingStrategyType();
        final PriceBucket minusOnePriceBucket = product.findEarlierPriceBucketTo(latestPriceBucket, bucketsWithSamePurchasePrice);
        final PriceBucket minusTwoPriceBucket = product.findEarlierPriceBucketTo(minusOnePriceBucket, bucketsWithSamePurchasePrice);

        if (pricingStrategyType != PricingStrategyType.DEFAULT_PRICING_STRATEGY && bucketsWithSamePurchasePrice.size() > maxHistoryCountforDefaultPricing) {
            return getNextCalculator().calculatePrice(product, productDemandTrend);
        }

        if (null != minusOnePriceBucket && null != minusTwoPriceBucket &&
                minusOnePriceBucket.getRegisteredProfit() < minusTwoPriceBucket.getRegisteredProfit() &&
                minusOnePriceBucket.getNumberOfExistingSubscriptions() > minusTwoPriceBucket.getNumberOfExistingSubscriptions()) {
            double slope = minusOnePriceBucket.getSlope() - (minusOnePriceBucket.getSlope() * calculateWeightedAverage(product.getActivePriceBuckets().values()) / 100);
            double intercept = latestTaggedPriceVersion.getMRP();
            double expectedDemand = 0;
            //double expectedDemandedQuantity = productForecastView.getTotalNumberOfExistingSubscriptions();
            if (productDemandTrend == ProductDemandTrend.DOWNWARD) {
                expectedDemand = latestPriceBucket.getNumberOfNewSubscriptions() - latestPriceBucket.getNumberOfNewSubscriptions() * product.getRevenueChangeThresholdForPriceChange();
            } else {
                expectedDemand = latestPriceBucket.getNumberOfNewSubscriptions() + latestPriceBucket.getNumberOfNewSubscriptions() * product.getRevenueChangeThresholdForPriceChange();
            }

            double offeredPrice = calculateOfferedPrice(intercept, slope, expectedDemand);
/*
            DateTimeFormatter format = DateTimeFormat.forPattern("MMddyyyy");
            LocalDateTime currentDate = SysDateTime.now();
            final String taggedPriceVersionId = productId + currentDate.toString(format);

            PriceTaggedWithProduct taggedPriceVersion = new PriceTaggedWithProduct(taggedPriceVersionId, latestPriceBucket.getTaggedPriceVersion().getPurchasePricePerUnit(), latestPriceBucket.getTaggedPriceVersion().getMRP(), currentDate);
*/
            LocalDateTime currentDate = SysDateTime.now();
            PriceBucket newPriceBucket = product.createNewPriceBucket(productId, latestPriceBucket.getTaggedPriceVersion(), offeredPrice, EntityStatus.CREATED, currentDate);
            newPriceBucket.setSlope(slope);
            return newPriceBucket;

        } else {
            return getNextCalculator().calculatePrice(product, productDemandTrend);

        }
    }

    private double calculateWeightedAverage(Collection<PriceBucket> activePriceBuckets) {
        double weightedProduct = 0.0;
        double quantitySum = 0.0;
        for (PriceBucket tempInput : activePriceBuckets) {
            weightedProduct += tempInput.getOfferedPriceOrPercentDiscountPerUnit() * tempInput.getNumberOfExistingSubscriptions();
            quantitySum += tempInput.getNumberOfExistingSubscriptions();
        }
        return weightedProduct / quantitySum;
    }

}
