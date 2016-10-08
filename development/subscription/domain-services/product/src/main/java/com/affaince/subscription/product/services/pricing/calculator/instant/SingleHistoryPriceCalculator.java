package com.affaince.subscription.product.services.pricing.calculator.instant;

import com.affaince.subscription.common.type.EntityStatus;
import com.affaince.subscription.common.type.ProductDemandTrend;
import com.affaince.subscription.date.SysDateTime;
import com.affaince.subscription.product.command.domain.PriceBucket;
import com.affaince.subscription.product.command.domain.Product;
import com.affaince.subscription.product.services.pricing.calculator.AbstractPriceCalculator;
import com.affaince.subscription.product.vo.PricingStrategyType;
import org.joda.time.LocalDateTime;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by mandark on 24-04-2016.
 */
@Component
public class SingleHistoryPriceCalculator extends AbstractPriceCalculator {

    public PriceBucket calculatePrice(Product product, ProductDemandTrend productDemandTrend) {
        final PriceBucket latestPriceBucket = product.getLatestActivePriceBucket();
        String productId = product.getProductId();
        PricingStrategyType pricingStrategyType = product.getProductConfiguration().getPricingStrategyType();
        List<PriceBucket> bucketsWithSamePurchasePrice = product.findBucketsWithSamePurchasePrice(latestPriceBucket);

        final PriceBucket minusOnePriceBucket = product.findEarlierPriceBucketTo(latestPriceBucket, bucketsWithSamePurchasePrice);
        final PriceBucket minusTwoPriceBucket = product.findEarlierPriceBucketTo(minusOnePriceBucket, bucketsWithSamePurchasePrice);
        if (pricingStrategyType != PricingStrategyType.DEFAULT_PRICING_STRATEGY && bucketsWithSamePurchasePrice.size() > maxHistoryCountforDefaultPricing) {
            return getNextCalculator().calculatePrice(product, productDemandTrend);
        }
        if (null != minusOnePriceBucket && null == minusTwoPriceBucket && latestPriceBucket.getEntityStatus() == EntityStatus.ACTIVE) {

            double y2 = minusOnePriceBucket.getOfferedPriceOrPercentDiscountPerUnit();
            double y1 = latestPriceBucket.getTaggedPriceVersion().getMRP(); //mark  price
            double x2 = latestPriceBucket.getNumberOfNewSubscriptions();
            double x1 = 0;//mark quantity
            double slope = calculateSlopeOfDemandCurve(x2, x1, y2, y1);
            double intercept = latestPriceBucket.getTaggedPriceVersion().getMRP();
            double expectedDemand = 0;
            //BIG QUESTION MARK HOW TO EXTRAPOLATE?FORECASTED DEMAND FOR NEXT HOW MANY DAYS TO BE CONSIDERED?
            //currently taking the forecast of current month(when batch is executing)
            //double expectedDemandedQuantity= productForecastMetricsView.getTotalNumberOfExistingSubscriptions();
            //final double expectedDemand = calculateExpectedDemand(productForecastView, productActualsView);
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
}
