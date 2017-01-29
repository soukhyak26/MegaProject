package com.affaince.subscription.product.query.listener;

import com.affaince.subscription.product.command.event.PriceBucketWisePurchaseCostRevenueAndProfitCalculatedEvent;
import com.affaince.subscription.product.query.repository.PriceBucketViewRepository;
import com.affaince.subscription.product.query.view.PriceBucketView;
import com.affaince.subscription.product.vo.ProductwisePriceBucketId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mandar on 29-01-2017.
 */
@Component
public class PriceBucketWisePurchaseCostRevenueAndProfitCalculatedEventListener {
    private final PriceBucketViewRepository priceBucketViewRepository;

    @Autowired
    public PriceBucketWisePurchaseCostRevenueAndProfitCalculatedEventListener(PriceBucketViewRepository priceBucketViewRepository) {
        this.priceBucketViewRepository = priceBucketViewRepository;
    }

    public void on(PriceBucketWisePurchaseCostRevenueAndProfitCalculatedEvent event){
        PriceBucketView priceBucketView=priceBucketViewRepository.findOne(new ProductwisePriceBucketId(event.getProductId(),event.getPriceBucketId()));
        priceBucketView.setRegisteredPurchaseCost(event.getPurchaseCostOfDeliveredUnits());
        priceBucketView.setRegisteredPurchaseCost(event.getPurchaseCostOfDeliveredUnits());
        priceBucketView.setRegisteredRevenue(event.getRevenue());
        priceBucketView.setRegisteredProfit(event.getProfitAmountPerPriceBucket());
        priceBucketViewRepository.save(priceBucketView);
    }
}
