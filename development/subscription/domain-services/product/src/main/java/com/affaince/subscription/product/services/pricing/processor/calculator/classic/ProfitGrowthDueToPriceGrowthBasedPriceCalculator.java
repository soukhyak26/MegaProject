package com.affaince.subscription.product.services.pricing.processor.calculator.classic;

import com.affaince.subscription.common.type.EntityStatus;
import com.affaince.subscription.common.type.ProductDemandTrend;
import com.affaince.subscription.common.vo.ProductVersionId;
import com.affaince.subscription.product.query.view.PriceBucketView;
import com.affaince.subscription.product.query.view.ProductActualsView;
import com.affaince.subscription.product.services.pricing.processor.calculator.AbstractPriceCalculator;
import com.affaince.subscription.product.vo.PriceTaggedWithProduct;
import org.joda.time.LocalDate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by mandark on 29-04-2016.
 */
@Component
public class ProfitGrowthDueToPriceGrowthBasedPriceCalculator extends AbstractPriceCalculator {

    public PriceBucketView calculatePrice(List<PriceBucketView> activePriceBuckets, ProductActualsView productActualsView, ProductDemandTrend productDemandTrend, double changeThresholdForPriceChange) {
        String productId = productActualsView.getProductVersionId().getProductId();
        List<PriceBucketView> bucketsWithSamePurchasePrice = findBucketsWithSamePurchasePrice(productId, activePriceBuckets);
        final PriceBucketView latestPriceBucket = getLatestPriceBucket(activePriceBuckets);

        final PriceBucketView minusOnePriceBucket = findEarlierPriceBucketTo(latestPriceBucket, bucketsWithSamePurchasePrice);
        final PriceBucketView minusTwoPriceBucket = findEarlierPriceBucketTo(minusOnePriceBucket, bucketsWithSamePurchasePrice);

        if (null != minusOnePriceBucket && null != minusTwoPriceBucket &&
                minusOnePriceBucket.getTotalProfit() > minusTwoPriceBucket.getTotalProfit() &&
                minusOnePriceBucket.getOfferedPricePerUnit() > minusTwoPriceBucket.getOfferedPricePerUnit() &&
                (minusOnePriceBucket.getNumberOfExistingCustomersAssociatedWithAPrice() < minusTwoPriceBucket.getNumberOfExistingCustomersAssociatedWithAPrice()) ||
                (minusOnePriceBucket.getNumberOfExistingCustomersAssociatedWithAPrice() > minusTwoPriceBucket.getNumberOfExistingCustomersAssociatedWithAPrice())
                ) {
            double y2 = minusOnePriceBucket.getOfferedPricePerUnit();
            double y1 = latestPriceBucket.getTaggedPriceVersion().getMRP();//markPrice.getOfferedPrice();
            double x2 = minusOnePriceBucket.getNumberOfExistingCustomersAssociatedWithAPrice();
            double x1 = 0;//markPrice.getQuantity();
            double expectedDemand = 0;
            double intercept = latestPriceBucket.getTaggedPriceVersion().getMRP();
            double slope = calculateSlopeOfDemandCurve(x2, x1, y2, y1);
            //double expectedDemandedQuantity = productForecastMetricsView.getTotalNumberOfExistingSubscriptions();
            //final double expectedDemand = calculateExpectedDemand(productForecastView, productActualsView);
            if (productDemandTrend == ProductDemandTrend.DOWNWARD) {
                expectedDemand = latestPriceBucket.getNumberOfNewCustomersAssociatedWithAPrice() - latestPriceBucket.getNumberOfNewCustomersAssociatedWithAPrice() * changeThresholdForPriceChange;
            } else {
                expectedDemand = latestPriceBucket.getNumberOfNewCustomersAssociatedWithAPrice() + latestPriceBucket.getNumberOfNewCustomersAssociatedWithAPrice() * changeThresholdForPriceChange;
            }

            double offeredPrice = calculateOfferedPrice(intercept, slope, expectedDemand);
            PriceBucketView newPrieBucket = new PriceBucketView();
            PriceTaggedWithProduct taggedPriceVersion = new PriceTaggedWithProduct(latestPriceBucket.getTaggedPriceVersion().getPurchasePricePerUnit(), latestPriceBucket.getTaggedPriceVersion().getMRP(), LocalDate.now());
            newPrieBucket.setProductVersionId(new ProductVersionId(latestPriceBucket.getProductVersionId().getProductId(), LocalDate.now()));
            newPrieBucket.setTaggedPriceVersion(taggedPriceVersion);
            newPrieBucket.setSlope(slope);
            newPrieBucket.setEntityStatus(EntityStatus.ACTIVE);
            newPrieBucket.setOfferedPricePerUnit(offeredPrice);
            return newPrieBucket;
        } else {
            return getNextCalculator().calculatePrice(activePriceBuckets, productActualsView, productDemandTrend, changeThresholdForPriceChange);

        }
    }
}
