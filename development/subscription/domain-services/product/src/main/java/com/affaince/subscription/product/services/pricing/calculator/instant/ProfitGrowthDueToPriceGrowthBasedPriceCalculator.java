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

import java.util.List;

/**
 * Created by mandark on 29-04-2016.
 */
@Component
public class ProfitGrowthDueToPriceGrowthBasedPriceCalculator extends AbstractPriceCalculator {

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
                minusOnePriceBucket.getRegisteredProfit() > minusTwoPriceBucket.getRegisteredProfit() &&
                minusOnePriceBucket.getOfferedPriceOrPercentDiscountPerUnit() > minusTwoPriceBucket.getOfferedPriceOrPercentDiscountPerUnit() &&
                ((minusOnePriceBucket.getNumberOfExistingSubscriptions() < minusTwoPriceBucket.getNumberOfExistingSubscriptions()) ||
                (minusOnePriceBucket.getNumberOfExistingSubscriptions() > minusTwoPriceBucket.getNumberOfExistingSubscriptions()))
                ) {
            double y2 = minusOnePriceBucket.getOfferedPriceOrPercentDiscountPerUnit();
            double y1 = latestPriceBucket.getTaggedPriceVersion().getMRP();//markPrice.getOfferedPrice();
            double x2 = minusOnePriceBucket.getNumberOfNewSubscriptions();
            double x1 = 0;//markPrice.getQuantity();
            double expectedDemand = 0;
            double intercept = latestTaggedPriceVersion.getMRP();
            double slope = calculateSlopeOfDemandCurve(x2, x1, y2, y1);
            //double expectedDemandedQuantity = productForecastMetricsView.getTotalNumberOfExistingSubscriptions();
            //final double expectedDemand = calculateExpectedDemand(productForecastView, productActualsView);
            //IS THIS RIGHT??
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

            //Tagged price version should not be created new but to be obtained from Product aggregate.THE BELOW IS WRONG
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
}
