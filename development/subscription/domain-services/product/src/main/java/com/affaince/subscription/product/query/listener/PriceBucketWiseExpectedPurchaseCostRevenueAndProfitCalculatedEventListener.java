package com.affaince.subscription.product.query.listener;

import com.affaince.subscription.product.command.event.PriceBucketWiseExpectedPurchaseCostRevenueAndProfitCalculatedEvent;
import com.affaince.subscription.product.command.event.PriceBucketWisePurchaseCostRevenueAndProfitCalculatedEvent;
import com.affaince.subscription.product.query.repository.PriceBucketViewRepository;
import com.affaince.subscription.product.query.view.PriceBucketView;
import com.affaince.subscription.product.vo.ProductwisePriceBucketId;
import com.affaince.subscription.product.vo.SubscriptionChangeType;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by mandar on 3/25/2017.
 */
public class PriceBucketWiseExpectedPurchaseCostRevenueAndProfitCalculatedEventListener {
    private final PriceBucketViewRepository priceBucketViewRepository;

    @Autowired
    public PriceBucketWiseExpectedPurchaseCostRevenueAndProfitCalculatedEventListener(PriceBucketViewRepository priceBucketViewRepository) {
        this.priceBucketViewRepository = priceBucketViewRepository;
    }

    public void on(PriceBucketWiseExpectedPurchaseCostRevenueAndProfitCalculatedEvent event){
        PriceBucketView priceBucketView=priceBucketViewRepository.findOne(new ProductwisePriceBucketId(event.getProductId(),event.getPriceBucketId()));
        if( event.getSubscriptionChangeType()== SubscriptionChangeType.GAIN) {
            priceBucketView.addToExpectedPurchaseCost(event.getPurchaseCostOfDeliveredUnits());
            priceBucketView.addToExpectedRevenue(event.getRevenue());
            priceBucketView.addToExpectedProfit(event.getProfitAmountPerPriceBucket());
        }else{
            priceBucketView.deductFromExpectedPurchaseCost(event.getPurchaseCostOfDeliveredUnits());
            priceBucketView.deductFromExpectedRevenue(event.getRevenue());
            priceBucketView.deductFromExpectedProfit(event.getProfitAmountPerPriceBucket());
        }
        priceBucketViewRepository.save(priceBucketView);
    }

}
