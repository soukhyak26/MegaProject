package com.affaince.subscription.pricing.processor;

import com.affaince.subscription.pricing.query.repository.PriceBucketViewRepository;
import com.affaince.subscription.pricing.query.view.PriceBucketView;
import com.affaince.subscription.pricing.query.view.ProductView;
import com.affaince.subscription.pricing.vo.PricingStrategyType;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * Created by mandark on 04-03-2016.
 */
public class PricingStrategyDeterminator {
    @Autowired
    PriceBucketViewRepository priceBucketViewRepository;
    public ProductView decideProductPricingStrategy(ProductView productView) {
        List<PriceBucketView> activePriceBuckets = priceBucketViewRepository.findByProductVersionId_ProductId(productView.getProductId());
        PriceBucketView latestPriceBucket = getLatestPriceBucket(activePriceBuckets);
        //collect all the historical price buckets with same purchase price(active and inactive)
        List<PriceBucketView> priceBucketsWithSamePurchasePrice = priceBucketViewRepository.findByProductVersionId_ProductIdAndPurchasePrice(productView.getProductId(), latestPriceBucket.getPurchasePricePerUnit());

        //check if you have enough number of history records so as to decide of default pricing/demand funation based pricing needs to be applied.
        //What will happen when purchase price changes.
        if (priceBucketsWithSamePurchasePrice.size() > 10) {
            productView.setPricingStrategyType(PricingStrategyType.DEMAND_AND_COST_BASED_PRICING_STRATEGY);
            //return PricingStrategyType.DEMAND_AND_COST_BASED_PRICING_STRATEGY;
        } else {
            productView.setPricingStrategyType(PricingStrategyType.DEFAULT_PRICING_STRATEGY);
        }
        return productView;
    }

    protected PriceBucketView getLatestPriceBucket(List<PriceBucketView> activePriceBuckets) {
        PriceBucketView latestPriceBucketView = activePriceBuckets.get(0);
        for (PriceBucketView priceBucketView : activePriceBuckets) {
            if (priceBucketView.getFromDate().compareTo(latestPriceBucketView.getFromDate()) > 0) {
                latestPriceBucketView = priceBucketView;
            }
        }
        return latestPriceBucketView;
    }

}
