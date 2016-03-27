package com.affaince.subscription.pricing.processor.calculator;

import com.affaince.subscription.pricing.query.view.PriceBucketView;
import com.affaince.subscription.pricing.query.view.ProductStatisticsView;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by mandark on 27-03-2016.
 */
public class DemandCurveBasedPriceCalculator extends AbstractPriceCalculator {
    public double calculatePrice(String productId, List<PriceBucketView> activePriceBuckets, ProductStatisticsView productStatisticsView) {
        List<PriceBucketView> bucketsWithSamePurchasePrice = findBucketsWithSamePurchasePrice(productId, activePriceBuckets);
        List<Double> totalQuantitySubscribedWithSamePurchasePrice = bucketsWithSamePurchasePrice.stream().map(priceBucketView -> new Long(priceBucketView.getNumberOfExistingCustomersAssociatedWithAPrice()).doubleValue()).collect(Collectors.toList());

        if ((activePriceBuckets.size() == 1 && totalQuantitySubscribedWithSamePurchasePrice.size() == 1 && totalQuantitySubscribedWithSamePurchasePrice.get(0) == activePriceBuckets.get(0).getNumberOfExistingCustomersAssociatedWithAPrice()) || (activePriceBuckets.size() > 1 && totalQuantitySubscribedWithSamePurchasePrice.size() == activePriceBuckets.size())) {
            //subscription to single price,nothing to extrapolate so far
            //here I assume that forecast subscription count will give close to reality figure as the forecast has once been corrected based on first set of subscription
            double expectedDemand = 0;
            PriceBucketView latestPriceBucket = getLatestPriceBucket(activePriceBuckets);
            final double MRP = latestPriceBucket.getMRP();
            final double breakEvenPrice = calculateBreakEvenPrice(latestPriceBucket.getPurchasePricePerUnit(), productStatisticsView.getFixedOperatingExpense(), productStatisticsView.getVariableOperatingExpense());

            if (activePriceBuckets.size() <= 5) {
                expectedDemand = productStatisticsView.getForecastedProductSubscriptionCount();
            } else {
                List<Double> extrapolatedDemands = extrapolateDemand(totalQuantitySubscribedWithSamePurchasePrice, 12);
                expectedDemand = extrapolatedDemands.get(0);
            }
            final double intercept = MRP;
            double earlierPrice = 0;
            double demandAssociatedWithEarlierPrice = 0;
            if (activePriceBuckets.size() == 1) {
                earlierPrice = MRP;
                demandAssociatedWithEarlierPrice = 0;
            } else {
                final PriceBucketView earlierPriceBucket = findEarlierPriceBucketTo(latestPriceBucket, bucketsWithSamePurchasePrice);
                earlierPrice = earlierPriceBucket.getOfferedPricePerUnit();
                demandAssociatedWithEarlierPrice = earlierPriceBucket.getNumberOfExistingCustomersAssociatedWithAPrice();
            }
            if (0.0 == latestPriceBucket.getSlope()) {
                latestPriceBucket.setSlope(calculateSlopeOfDemandCurve(latestPriceBucket.getNumberOfExistingCustomersAssociatedWithAPrice(), demandAssociatedWithEarlierPrice, latestPriceBucket.getOfferedPricePerUnit(), earlierPrice));
            }

            final double newOfferedPrice = calculatePriceBasedOnSlopeAndIntercept(latestPriceBucket.getSlope(), intercept, expectedDemand);
            return newOfferedPrice;

        }
        ////TO BE CHANGED AFTER WRITING NEXT CALCUALATOR!!!!!!
        return 0.0;
    }
}
