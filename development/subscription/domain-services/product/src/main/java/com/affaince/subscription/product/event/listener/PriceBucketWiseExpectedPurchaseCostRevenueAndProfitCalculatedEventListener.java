package com.affaince.subscription.product.event.listener;

import com.affaince.subscription.product.event.PriceBucketWiseExpectedPurchaseCostRevenueAndProfitCalculatedEvent;
import com.affaince.subscription.product.query.repository.PriceBucketViewRepository;
import com.affaince.subscription.product.query.view.PriceBucketView;
import com.affaince.subscription.common.vo.ProductwisePriceBucketId;
import com.affaince.subscription.product.vo.SubscriptionChangeType;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mandar on 3/25/2017.
 */
@Component
public class PriceBucketWiseExpectedPurchaseCostRevenueAndProfitCalculatedEventListener {
    private final PriceBucketViewRepository priceBucketViewRepository;

    @Autowired
    public PriceBucketWiseExpectedPurchaseCostRevenueAndProfitCalculatedEventListener(PriceBucketViewRepository priceBucketViewRepository) {
        this.priceBucketViewRepository = priceBucketViewRepository;
    }

    @EventHandler
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
