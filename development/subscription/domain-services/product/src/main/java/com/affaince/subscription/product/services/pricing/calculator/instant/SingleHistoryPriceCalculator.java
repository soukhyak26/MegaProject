package com.affaince.subscription.product.services.pricing.calculator.instant;

import com.affaince.subscription.common.type.EntityStatus;
import com.affaince.subscription.common.type.ProductDemandTrend;
import com.affaince.subscription.product.domain.PriceBucket;
import com.affaince.subscription.product.domain.Product;
import com.affaince.subscription.product.services.pricing.calculator.AbstractPriceCalculator;
import com.affaince.subscription.common.vo.PriceTaggedWithProduct;
import com.affaince.subscription.common.type.PricingStrategyType;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by mandark on 24-04-2016.
 */
@Component
public class SingleHistoryPriceCalculator extends AbstractPriceCalculator {

    public PriceBucket calculatePrice(Product product, ProductDemandTrend productDemandTrend) {
        final PriceBucket latestPriceBucket = product.findLatestActivePriceBucket();
        final PriceTaggedWithProduct latestTaggedPriceVersion= product.getLatestTaggedPriceVersion();
        final String productId = product.getProductId();
        final PricingStrategyType pricingStrategyType = product.getProductConfiguration().getPricingStrategyType();
        List<PriceBucket> bucketsWithSamePurchasePrice = product.findBucketsWithSamePurchasePrice(latestPriceBucket);

        final PriceBucket minusOnePriceBucket = latestPriceBucket;
        final PriceBucket minusTwoPriceBucket = product.findEarlierPriceBucketTo(minusOnePriceBucket, bucketsWithSamePurchasePrice);
        if (pricingStrategyType != PricingStrategyType.DEFAULT_PRICING_STRATEGY && bucketsWithSamePurchasePrice.size() > maxHistoryCountforDefaultPricing) {
            return getNextCalculator().calculatePrice(product, productDemandTrend);
        }
        if (null != minusOnePriceBucket && null == minusTwoPriceBucket && latestPriceBucket.getEntityStatus() == EntityStatus.ACTIVE) {

            double y2 = minusOnePriceBucket.getFixedOfferedPriceOrPercentDiscountPerUnit();
            double y1 = latestPriceBucket.getLatestTaggedPriceVersion().getMRP(); //mark  price
            double x2 = latestPriceBucket.getNumberOfNewSubscriptions();
            double x1 = 0;//mark quantity
            double slope = calculateSlopeOfDemandCurve(x2, x1, y2, y1);
            double intercept = latestTaggedPriceVersion.getMRP();
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

            PriceTaggedWithProduct taggedPriceVersion = new PriceTaggedWithProduct(taggedPriceVersionId, latestPriceBucket.getTaggedPriceVersion().getPurchasePricePerUnit(), latestPriceBucket.getTaggedPriceVersion().getMrp(), currentDate);
*/
/*
            LocalDateTime currentDate = SysDateTime.now();
            PriceBucket newPriceBucket=null;
            if (product.getProductAccount().getProductPricingCategory() == ProductPricingCategory.PRICE_COMMITMENT){
                newPriceBucket =createNewPriceBucket(productId, minusOnePriceBucket.getPriceBucketId(),latestPriceBucket.getLatestTaggedPriceVersion(), offeredPrice, EntityStatus.CREATED, ProductPricingCategory.PRICE_COMMITMENT,currentDate);
            }else if(product.getProductAccount().getProductPricingCategory() == ProductPricingCategory.DISCOUNT_COMMITMENT){
                double percentDiscount= (product.getLatestTaggedPriceVersion().getMrp()-offeredPrice)/product.getLatestTaggedPriceVersion().getMrp();
                newPriceBucket = createNewPriceBucket(productId, minusOnePriceBucket.getPriceBucketId(),latestPriceBucket.getLatestTaggedPriceVersion(), percentDiscount, EntityStatus.CREATED,ProductPricingCategory.DISCOUNT_COMMITMENT, currentDate);
            }else{
                newPriceBucket = createNewPriceBucket(productId, minusOnePriceBucket.getPriceBucketId(),latestPriceBucket.getLatestTaggedPriceVersion(), offeredPrice, EntityStatus.CREATED,ProductPricingCategory.NO_COMMITMENT, currentDate);
            }
*/
            PriceBucket newPriceBucket=createNewPriceBucket(productId,minusOnePriceBucket.getPriceBucketId(), product.getLatestTaggedPriceVersion(), offeredPrice,product.getProductAccount().getProductPricingCategory());
            newPriceBucket.setSlope(slope);
            return newPriceBucket;

        } else {
            return getNextCalculator().calculatePrice(product, productDemandTrend);

        }

    }
}
