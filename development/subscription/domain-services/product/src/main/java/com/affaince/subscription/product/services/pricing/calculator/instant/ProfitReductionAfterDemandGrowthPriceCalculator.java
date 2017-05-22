package com.affaince.subscription.product.services.pricing.calculator.instant;

import com.affaince.subscription.common.type.EntityStatus;
import com.affaince.subscription.common.type.ProductDemandTrend;
import com.affaince.subscription.common.type.ProductPricingCategory;
import com.affaince.subscription.date.SysDateTime;
import com.affaince.subscription.product.command.domain.PriceBucket;
import com.affaince.subscription.product.command.domain.Product;
import com.affaince.subscription.product.services.pricing.calculator.AbstractPriceCalculator;
import com.affaince.subscription.common.vo.PriceTaggedWithProduct;
import com.affaince.subscription.common.type.PricingStrategyType;
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
        final PriceBucket latestPriceBucket = product.findLatestActivePriceBucket();
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
                minusOnePriceBucket.getTotalRegisteredProfit() < minusTwoPriceBucket.getTotalRegisteredProfit() &&
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

            PriceTaggedWithProduct taggedPriceVersion = new PriceTaggedWithProduct(taggedPriceVersionId, latestPriceBucket.getTaggedPriceVersion().getPurchasePricePerUnit(), latestPriceBucket.getTaggedPriceVersion().getMrp(), currentDate);
*/
            LocalDateTime currentDate = SysDateTime.now();
            //PriceBucket newPriceBucket = product.createNewPriceBucket(productId, latestPriceBucket.getLatestTaggedPriceVersion(), offeredPrice, EntityStatus.CREATED, currentDate);
            PriceBucket newPriceBucket=null;
            if (product.getProductAccount().getProductPricingCategory() == ProductPricingCategory.PRICE_COMMITMENT){
                newPriceBucket =createNewPriceBucket(productId, latestPriceBucket.getLatestTaggedPriceVersion(), offeredPrice, EntityStatus.CREATED, ProductPricingCategory.PRICE_COMMITMENT,currentDate);
            }else if(product.getProductAccount().getProductPricingCategory() == ProductPricingCategory.DISCOUNT_COMMITMENT){
                double percentDiscount= (latestTaggedPriceVersion.getMRP()-offeredPrice)/latestTaggedPriceVersion.getMRP();
                newPriceBucket = createNewPriceBucket(productId, latestPriceBucket.getLatestTaggedPriceVersion(), percentDiscount, EntityStatus.CREATED,ProductPricingCategory.DISCOUNT_COMMITMENT, currentDate);
            }else{
                newPriceBucket = createNewPriceBucket(productId, latestPriceBucket.getLatestTaggedPriceVersion(), offeredPrice, EntityStatus.CREATED,ProductPricingCategory.NO_COMMITMENT, currentDate);
            }

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
            weightedProduct += tempInput.getFixedOfferedPriceOrPercentDiscountPerUnit() * tempInput.getNumberOfExistingSubscriptions();
            quantitySum += tempInput.getNumberOfExistingSubscriptions();
        }
        return weightedProduct / quantitySum;
    }

}
