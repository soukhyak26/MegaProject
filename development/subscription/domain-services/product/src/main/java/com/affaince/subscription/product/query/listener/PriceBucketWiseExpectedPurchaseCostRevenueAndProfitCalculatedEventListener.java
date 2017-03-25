package com.affaince.subscription.product.query.listener;

import com.affaince.subscription.product.command.event.PriceBucketWisePurchaseCostRevenueAndProfitCalculatedEvent;
import com.affaince.subscription.product.query.repository.PriceBucketViewRepository;
import com.affaince.subscription.product.query.view.PriceBucketView;
import com.affaince.subscription.product.vo.ProductwisePriceBucketId;
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

    public void on(PriceBucketWisePurchaseCostRevenueAndProfitCalculatedEvent event){
        PriceBucketView priceBucketView=priceBucketViewRepository.findOne(new ProductwisePriceBucketId(event.getProductId(),event.getPriceBucketId()));
        priceBucketView.setExpectedPurchaseCost(event.getPurchaseCostOfDeliveredUnits());
        priceBucketView.setExpectedRevenue(event.getRevenue());
        priceBucketView.setExpectedProfit(event.getProfitAmountPerPriceBucket());
        priceBucketViewRepository.save(priceBucketView);
    }

}
