package com.affaince.subscription.product.services.pricing.processor.calculator;

import com.affaince.subscription.common.type.EntityStatus;
import com.affaince.subscription.common.vo.ProductVersionId;
import com.affaince.subscription.product.query.view.PriceBucketView;
import com.affaince.subscription.product.query.view.ProductActualMetricsView;
import com.affaince.subscription.product.query.view.ProductForecastMetricsView;
import com.affaince.subscription.product.vo.PriceTaggedWithProduct;
import org.joda.time.LocalDate;

import java.util.List;

/**
 * Created by mandark on 29-04-2016.
 */
public class ProfitReductionAfterDemandGrowthPriceCalculator extends AbstractPriceCalculator {

    public PriceBucketView calculatePrice(List<PriceBucketView> activePriceBuckets, ProductActualMetricsView productActualMetricsView, ProductForecastMetricsView productForecastMetricsView) {
        String productId = productActualMetricsView.getProductVersionId().getProductId();
        List<PriceBucketView> bucketsWithSamePurchasePrice = findBucketsWithSamePurchasePrice(productId, activePriceBuckets);
        final PriceBucketView latestPriceBucket = getLatestPriceBucket(activePriceBuckets);

        final PriceBucketView minusOnePriceBucket=findEarlierPriceBucketTo(latestPriceBucket, bucketsWithSamePurchasePrice);
        final PriceBucketView minusTwoPriceBucket=findEarlierPriceBucketTo(minusOnePriceBucket, bucketsWithSamePurchasePrice);

        if (null != minusOnePriceBucket && null != minusTwoPriceBucket &&
                minusOnePriceBucket.getTotalProfit() < minusTwoPriceBucket.getTotalProfit() &&
                minusOnePriceBucket.getNumberOfExistingCustomersAssociatedWithAPrice() > minusTwoPriceBucket.getNumberOfExistingCustomersAssociatedWithAPrice() ){
            double slope = minusOnePriceBucket.getSlope() - (minusOnePriceBucket.getSlope() * calculateWeightedAverage(activePriceBuckets) / 100);
            double intercept = latestPriceBucket.getTaggedPriceVersion().getMRP();
            double expectedDemandedQuantity= productForecastMetricsView.getTotalNumberOfExistingSubscriptions();
            double offeredPrice = calculateOfferedPrice(intercept, slope, expectedDemandedQuantity);
            PriceBucketView newPriceBucket=new PriceBucketView();
            PriceTaggedWithProduct taggedPriceVersion = new PriceTaggedWithProduct(latestPriceBucket.getTaggedPriceVersion().getPurchasePricePerUnit(),latestPriceBucket.getTaggedPriceVersion().getMRP(),LocalDate.now());
            newPriceBucket.setProductVersionId(new ProductVersionId(latestPriceBucket.getProductVersionId().getProductId(), LocalDate.now()));
            newPriceBucket.setTaggedPriceVersion(taggedPriceVersion);
            newPriceBucket.setSlope(slope);
            newPriceBucket.setEntityStatus(EntityStatus.ACTIVE);
            newPriceBucket.setOfferedPricePerUnit(offeredPrice);
            return newPriceBucket;
        }else{
            return getNextCalculator().calculatePrice(activePriceBuckets, productActualMetricsView,productForecastMetricsView);

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
