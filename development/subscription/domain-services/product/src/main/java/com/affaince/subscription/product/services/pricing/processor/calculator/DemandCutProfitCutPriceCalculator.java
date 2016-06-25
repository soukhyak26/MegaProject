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
public class DemandCutProfitCutPriceCalculator extends AbstractPriceCalculator {

    public PriceBucketView calculatePrice(List<PriceBucketView> activePriceBuckets, ProductActualMetricsView productActualMetricsView, ProductForecastMetricsView productForecastMetricsView) {
        String productId = productActualMetricsView.getProductMonthlyVersionId().getProductId();
        List<PriceBucketView> bucketsWithSamePurchasePrice = findBucketsWithSamePurchasePrice(productId, activePriceBuckets);
        final PriceBucketView latestPriceBucket = getLatestPriceBucket(activePriceBuckets);

        final PriceBucketView minusOnePriceBucket=findEarlierPriceBucketTo(latestPriceBucket, bucketsWithSamePurchasePrice);

        final PriceBucketView minusTwoPriceBucket=findEarlierPriceBucketTo(latestPriceBucket, bucketsWithSamePurchasePrice);

        if (null != minusOnePriceBucket && null != minusTwoPriceBucket &&
                minusOnePriceBucket.getTotalProfit() < minusTwoPriceBucket.getTotalProfit() &&
                minusOnePriceBucket.getNumberOfExistingCustomersAssociatedWithAPrice() < minusTwoPriceBucket.getNumberOfExistingCustomersAssociatedWithAPrice() ){
            double y2 = minusOnePriceBucket.getOfferedPricePerUnit();
            double y1 = minusTwoPriceBucket.getOfferedPricePerUnit();
            double x2 = minusOnePriceBucket.getNumberOfExistingCustomersAssociatedWithAPrice();
            double x1 = minusTwoPriceBucket.getNumberOfExistingCustomersAssociatedWithAPrice();

            double intercept = latestPriceBucket.getTaggedPriceVersion().getMRP();
            double slope = calculateSlopeOfDemandCurve(x2, x1, y2, y1 );
            double expectedDemandedQuantity= productForecastMetricsView.getTotalNumberOfExistingSubscriptions();
            double offeredPrice = calculateOfferedPrice(intercept, slope, expectedDemandedQuantity);
            PriceBucketView newPrieBucket=new PriceBucketView();
            PriceTaggedWithProduct taggedPriceVersion= new PriceTaggedWithProduct(latestPriceBucket.getTaggedPriceVersion().getPurchasePricePerUnit(),latestPriceBucket.getTaggedPriceVersion().getMRP(),LocalDate.now());
            newPrieBucket.setProductVersionId(new ProductVersionId(latestPriceBucket.getProductVersionId().getProductId(), LocalDate.now()));
            newPrieBucket.setTaggedPriceVersion(taggedPriceVersion);
            newPrieBucket.setSlope(slope);
            newPrieBucket.setEntityStatus(EntityStatus.ACTIVE);
            newPrieBucket.setOfferedPricePerUnit(offeredPrice);
            return newPrieBucket;
        }else{
            return getNextCalculator().calculatePrice(activePriceBuckets, productActualMetricsView,productForecastMetricsView);

        }
    }
}