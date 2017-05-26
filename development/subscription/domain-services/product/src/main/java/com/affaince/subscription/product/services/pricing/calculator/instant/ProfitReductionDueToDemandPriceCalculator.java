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

import java.util.List;

/**
 * Created by mandark on 29-04-2016.
 */
@Component
public class ProfitReductionDueToDemandPriceCalculator extends AbstractPriceCalculator {

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
                minusOnePriceBucket.getNumberOfExistingSubscriptions() < minusTwoPriceBucket.getNumberOfExistingSubscriptions()) {
            double y2 = minusOnePriceBucket.recalculateOfferedPriceBasedOnActualDemand();
            double y1 = minusTwoPriceBucket.recalculateOfferedPriceBasedOnActualDemand();
            double x2 = minusOnePriceBucket.getNumberOfNewSubscriptions();
            double x1 = minusTwoPriceBucket.getNumberOfNewSubscriptions();

            double intercept = latestTaggedPriceVersion.getMRP();
            double slope = calculateSlopeOfDemandCurve(x2, x1, y2, y1);
            double expectedDemand = 0;
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
            PriceTaggedWithProduct taggedPriceVersion = new PriceTaggedWithProduct(taggedPriceVersionId, latestPriceBucket.getTaggedPriceVersion().getPurchasePricePerUnit(), latestPriceBucket.getTaggedPriceVersion().getMrp(), currentDate);
*/
            PriceBucket newPriceBucket=createNewPriceBucket(productId,minusOnePriceBucket.getPriceBucketId(), product.getLatestTaggedPriceVersion(), offeredPrice,product.getProductAccount().getProductPricingCategory());
            newPriceBucket.setSlope(slope);
            return newPriceBucket;

        } else {
            return getNextCalculator().calculatePrice(product, productDemandTrend);

        }
    }
}
