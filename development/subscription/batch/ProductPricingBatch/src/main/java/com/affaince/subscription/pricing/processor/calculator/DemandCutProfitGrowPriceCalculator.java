package com.affaince.subscription.pricing.processor.calculator;

import com.affaince.subscription.common.type.EntityStatus;
import com.affaince.subscription.common.vo.ProductVersionId;
import com.affaince.subscription.pricing.query.view.PriceBucketView;
import com.affaince.subscription.pricing.query.view.ProductMonthlyStatisticsView;
import org.joda.time.LocalDate;

import java.util.List;

/**
 * Created by mandark on 29-04-2016.
 */
public class DemandCutProfitGrowPriceCalculator extends AbstractPriceCalculator {

    public PriceBucketView calculatePrice(List<PriceBucketView> activePriceBuckets, ProductMonthlyStatisticsView productMonthlyStatisticsView) {
        String productId = productMonthlyStatisticsView.getProductMonthlyVersionId().getProductId();
        List<PriceBucketView> bucketsWithSamePurchasePrice = findBucketsWithSamePurchasePrice(productId, activePriceBuckets);
        final PriceBucketView latestPriceBucket = getLatestPriceBucket(activePriceBuckets);

        final PriceBucketView minusOnePriceBucket=findEarlierPriceBucketTo(latestPriceBucket, bucketsWithSamePurchasePrice);

        final PriceBucketView minusTwoPriceBucket=findEarlierPriceBucketTo(latestPriceBucket, bucketsWithSamePurchasePrice);

        if (null != minusOnePriceBucket && null != minusTwoPriceBucket &&
                minusOnePriceBucket.calculateProfitPerBucket() > minusTwoPriceBucket.calculateProfitPerBucket() &&
                minusOnePriceBucket.getNumberOfExistingCustomersAssociatedWithAPrice() < minusTwoPriceBucket.getNumberOfExistingCustomersAssociatedWithAPrice() &&
                minusOnePriceBucket.getOfferedPricePerUnit() > minusTwoPriceBucket.getOfferedPricePerUnit()){
            double y2 = minusOnePriceBucket.getOfferedPricePerUnit();
            double y1 = latestPriceBucket.getMRP();//markPrice.getOfferedPrice();
            double x2 = minusOnePriceBucket.getNumberOfExistingCustomersAssociatedWithAPrice();
            double x1 = 0;//markPrice.getQuantity();

            double intercept = latestPriceBucket.getMRP();
            double slope = calculateSlopeOfDemandCurve(x2, x1, y2, y1 );
            double expectedDemandedQuantity= productMonthlyStatisticsView.getForecastedProductSubscriptionCount();
            double offeredPrice = calculateOfferedPrice(intercept, slope, expectedDemandedQuantity);
            PriceBucketView newPrieBucket=new PriceBucketView();
            newPrieBucket.setProductVersionId(new ProductVersionId(latestPriceBucket.getProductVersionId().getProductId(), LocalDate.now()));
            newPrieBucket.setPurchasePricePerUnit(latestPriceBucket.getPurchasePricePerUnit());
            newPrieBucket.setSlope(slope);
            newPrieBucket.setEntityStatus(EntityStatus.ACTIVE);
            newPrieBucket.setMRP(latestPriceBucket.getMRP());
            newPrieBucket.setOfferedPricePerUnit(offeredPrice);
            return newPrieBucket;
        }else{
            return getNextCalculator().calculatePrice(activePriceBuckets, productMonthlyStatisticsView);

        }
    }
}
