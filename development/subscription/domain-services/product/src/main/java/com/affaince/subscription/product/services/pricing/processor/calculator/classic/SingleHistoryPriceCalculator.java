package com.affaince.subscription.product.services.pricing.processor.calculator.classic;

import com.affaince.subscription.common.type.EntityStatus;
import com.affaince.subscription.common.vo.ProductVersionId;
import com.affaince.subscription.product.query.view.PriceBucketView;
import com.affaince.subscription.product.query.view.ProductActualMetricsView;
import com.affaince.subscription.product.query.view.ProductForecastMetricsView;
import com.affaince.subscription.product.services.pricing.processor.calculator.AbstractPriceCalculator;
import com.affaince.subscription.product.vo.PriceTaggedWithProduct;
import org.joda.time.LocalDate;

import java.util.List;

/**
 * Created by mandark on 24-04-2016.
 */
public class SingleHistoryPriceCalculator extends AbstractPriceCalculator {

    public PriceBucketView calculatePrice(List<PriceBucketView> activePriceBuckets, ProductActualMetricsView productActualMetricsView, ProductForecastMetricsView productForecastMetricsView) {
        String productId = productActualMetricsView.getProductVersionId().getProductId();
        List<PriceBucketView> bucketsWithSamePurchasePrice = findBucketsWithSamePurchasePrice(productId, activePriceBuckets);
        final PriceBucketView latestPriceBucket = getLatestPriceBucket(activePriceBuckets);

        final PriceBucketView minusOnePriceBucket=findEarlierPriceBucketTo(latestPriceBucket, bucketsWithSamePurchasePrice);
        final PriceBucketView minusTwoPriceBucket=findEarlierPriceBucketTo(minusOnePriceBucket, bucketsWithSamePurchasePrice);

        if(null != minusOnePriceBucket && null == minusTwoPriceBucket && latestPriceBucket.getEntityStatus()== EntityStatus.ACTIVE ){

            double y2 = minusOnePriceBucket.getOfferedPricePerUnit();
            double y1 = latestPriceBucket.getTaggedPriceVersion().getMRP(); //mark  price
            double x2 = latestPriceBucket.getNumberOfExistingCustomersAssociatedWithAPrice();
            double x1 = 0;//mark quantity
            double slope = calculateSlopeOfDemandCurve(x2, x1, y2, y1);
            double intercept = latestPriceBucket.getTaggedPriceVersion().getMRP();
            //BIG QUESTION MARK HOW TO EXTRAPOLATE?
            //currently taking the forecast of current month(when batch is executing)
            //double expectedDemandedQuantity= productForecastMetricsView.getTotalNumberOfExistingSubscriptions();
            final double expectedDemand= calculateExpectedDemand(productForecastMetricsView,productActualMetricsView);
            double offeredPrice = calculateOfferedPrice(intercept, slope, expectedDemand);
            PriceBucketView newPriceBucket=new PriceBucketView();
            PriceTaggedWithProduct taggedPriceVersion= new PriceTaggedWithProduct(latestPriceBucket.getTaggedPriceVersion().getPurchasePricePerUnit(),latestPriceBucket.getTaggedPriceVersion().getMRP(),LocalDate.now());
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
}