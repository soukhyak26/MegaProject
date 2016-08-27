package com.affaince.subscription.product.services.pricing.calculator.instant;

import com.affaince.subscription.common.type.ProductDemandTrend;
import com.affaince.subscription.date.SysDate;
import com.affaince.subscription.product.query.view.PriceBucketView;
import com.affaince.subscription.product.services.pricing.calculator.AbstractPriceCalculator;
import com.affaince.subscription.product.vo.PriceCalculationParameters;
import com.affaince.subscription.product.vo.PriceTaggedWithProduct;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by mandark on 29-04-2016.
 */
@Component
public class ProfitReductionAfterDemandGrowthPriceCalculator extends AbstractPriceCalculator {

    public PriceBucketView calculatePrice(PriceCalculationParameters priceCalculationParameters) {
        String productId = priceCalculationParameters.getProductActualsView().getProductVersionId().getProductId();
        List<PriceBucketView> bucketsWithSamePurchasePrice = findBucketsWithSamePurchasePrice(productId, priceCalculationParameters.getActivePriceBuckets());
        final PriceBucketView latestPriceBucket = getLatestPriceBucket(priceCalculationParameters.getActivePriceBuckets());

        final PriceBucketView minusOnePriceBucket = findEarlierPriceBucketTo(latestPriceBucket, bucketsWithSamePurchasePrice);
        final PriceBucketView minusTwoPriceBucket = findEarlierPriceBucketTo(minusOnePriceBucket, bucketsWithSamePurchasePrice);

        if (null != minusOnePriceBucket && null != minusTwoPriceBucket &&
                minusOnePriceBucket.getTotalProfit() < minusTwoPriceBucket.getTotalProfit() &&
                minusOnePriceBucket.getNumberOfExistingCustomersAssociatedWithAPrice() > minusTwoPriceBucket.getNumberOfExistingCustomersAssociatedWithAPrice()) {
            double slope = minusOnePriceBucket.getSlope() - (minusOnePriceBucket.getSlope() * calculateWeightedAverage(priceCalculationParameters.getActivePriceBuckets()) / 100);
            double intercept = latestPriceBucket.getTaggedPriceVersion().getMRP();
            double expectedDemand = 0;
            //double expectedDemandedQuantity = productForecastView.getTotalNumberOfExistingSubscriptions();
            if (priceCalculationParameters.getProductDemandTrend() == ProductDemandTrend.DOWNWARD) {
                expectedDemand = latestPriceBucket.getNumberOfNewCustomersAssociatedWithAPrice() - latestPriceBucket.getNumberOfNewCustomersAssociatedWithAPrice() * priceCalculationParameters.getChangeThresholdPercentageForPriceChange();
            } else {
                expectedDemand = latestPriceBucket.getNumberOfNewCustomersAssociatedWithAPrice() + latestPriceBucket.getNumberOfNewCustomersAssociatedWithAPrice() * priceCalculationParameters.getChangeThresholdPercentageForPriceChange();
            }

            double offeredPrice = calculateOfferedPrice(intercept, slope, expectedDemand);
            PriceTaggedWithProduct taggedPriceVersion = new PriceTaggedWithProduct(latestPriceBucket.getTaggedPriceVersion().getPurchasePricePerUnit(), latestPriceBucket.getTaggedPriceVersion().getMRP(), SysDate.now());
            return createPriceBucket(productId, taggedPriceVersion, slope, offeredPrice);
        } else {
            return getNextCalculator().calculatePrice(priceCalculationParameters);

        }
    }

    private double calculateWeightedAverage(List<PriceBucketView> activePriceBuckets) {
        double weightedProduct = 0.0;
        double quantitySum = 0.0;
        for (PriceBucketView tempInput : activePriceBuckets) {
            weightedProduct += tempInput.getOfferedPricePerUnit() * tempInput.getNumberOfExistingCustomersAssociatedWithAPrice();
            quantitySum += tempInput.getNumberOfExistingCustomersAssociatedWithAPrice();
        }
        return weightedProduct / quantitySum;
    }

}
