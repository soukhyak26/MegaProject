package com.affaince.subscription.product.services.pricing.processor.calculator.instant;

import com.affaince.subscription.common.type.EntityStatus;
import com.affaince.subscription.common.type.ProductDemandTrend;
import com.affaince.subscription.common.vo.ProductVersionId;
import com.affaince.subscription.date.SysDate;
import com.affaince.subscription.product.query.view.PriceBucketView;
import com.affaince.subscription.product.services.pricing.processor.calculator.AbstractPriceCalculator;
import com.affaince.subscription.product.vo.PriceCalculationParameters;
import com.affaince.subscription.product.vo.PriceTaggedWithProduct;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by mandark on 29-04-2016.
 */
@Component
public class ProfitGrowthDueToPriceGrowthBasedPriceCalculator extends AbstractPriceCalculator {

    public PriceBucketView calculatePrice(PriceCalculationParameters priceCalculationParameters) {
        String productId = priceCalculationParameters.getProductActualsView().getProductVersionId().getProductId();
        List<PriceBucketView> bucketsWithSamePurchasePrice = findBucketsWithSamePurchasePrice(productId, priceCalculationParameters.getActivePriceBuckets());
        final PriceBucketView latestPriceBucket = getLatestPriceBucket(priceCalculationParameters.getActivePriceBuckets());

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
            //IS THIS RIGHT??
            if (priceCalculationParameters.getProductDemandTrend() == ProductDemandTrend.DOWNWARD) {
                expectedDemand = latestPriceBucket.getNumberOfNewCustomersAssociatedWithAPrice() - latestPriceBucket.getNumberOfNewCustomersAssociatedWithAPrice() * priceCalculationParameters.getChangeThresholdPercentageForPriceChange();
            } else {
                expectedDemand = latestPriceBucket.getNumberOfNewCustomersAssociatedWithAPrice() + latestPriceBucket.getNumberOfNewCustomersAssociatedWithAPrice() * priceCalculationParameters.getChangeThresholdPercentageForPriceChange();
            }

            double offeredPrice = calculateOfferedPrice(intercept, slope, expectedDemand);
            PriceBucketView newPrieBucket = new PriceBucketView();
            PriceTaggedWithProduct taggedPriceVersion = new PriceTaggedWithProduct(latestPriceBucket.getTaggedPriceVersion().getPurchasePricePerUnit(), latestPriceBucket.getTaggedPriceVersion().getMRP(), SysDate.now());
            newPrieBucket.setProductVersionId(new ProductVersionId(latestPriceBucket.getProductVersionId().getProductId(), SysDate.now()));
            newPrieBucket.setTaggedPriceVersion(taggedPriceVersion);
            newPrieBucket.setSlope(slope);
            newPrieBucket.setEntityStatus(EntityStatus.ACTIVE);
            newPrieBucket.setOfferedPricePerUnit(offeredPrice);
            return newPrieBucket;
        } else {
            return getNextCalculator().calculatePrice(priceCalculationParameters);

        }
    }
}
