package com.affaince.subscription.product.query.listener;

import com.affaince.subscription.product.command.event.PriceBucketWisePurchaseCostRevenueAndProfitCalculatedEvent;
import com.affaince.subscription.product.command.event.PurchaseCostRevenueAndProfitOfDeliveredSubscriptionsCalculatedEvent;
import com.affaince.subscription.product.query.repository.PriceBucketViewRepository;
import com.affaince.subscription.product.query.view.PriceBucketView;
import com.affaince.subscription.product.vo.ProductwisePriceBucketId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mandar on 29-01-2017.
 */
@Component
public class PurchaseCostRevenueAndProfitOfDeliveredSubscriptionsCalculatedEventListener {
    private final PriceBucketViewRepository priceBucketViewRepository;

    @Autowired
    public PurchaseCostRevenueAndProfitOfDeliveredSubscriptionsCalculatedEventListener(PriceBucketViewRepository priceBucketViewRepository) {
        this.priceBucketViewRepository = priceBucketViewRepository;
    }

    public void on(PurchaseCostRevenueAndProfitOfDeliveredSubscriptionsCalculatedEvent event){
        PriceBucketView priceBucketView=priceBucketViewRepository.findOne(new ProductwisePriceBucketId(event.getProductId(),event.getPriceBucketId()));
/*
        priceBucketView.setRegisteredPurchaseCost(event.getPurchaseCostOfDeliveredUnits());
        priceBucketView.setRegisteredRevenue(event.getRevenue());
        priceBucketView.setRegisteredProfit(event.getProfitAmountPerPriceBucket());
*/

        Double registeredPurchaseCostForADate= priceBucketView.getRegisteredPurchaseCostOfDeliveredUnits().get(event.getDispatchDate());
        if(null== registeredPurchaseCostForADate || registeredPurchaseCostForADate==0.0){
           // this.registeredPurchaseCostOfDeliveredUnits.put(event.getDispatchDate(),event.getPurchaseCostOfDeliveredSubscriptions());
            priceBucketView.addToRegisteredPurchaseCostOfDeliveredUnits(event.getDispatchDate(),event.getPurchaseCostOfDeliveredSubscriptions());
        }else{
            priceBucketView.addToRegisteredPurchaseCostOfDeliveredUnits(event.getDispatchDate(),registeredPurchaseCostForADate + event.getPurchaseCostOfDeliveredSubscriptions());
        }

        Double revenueForADate= priceBucketView.getRegisteredRevenue().get(event.getDispatchDate());
        if( null== revenueForADate || revenueForADate==0.0){
            priceBucketView.addToRegisteredRevenue(event.getDispatchDate(),event.getRevenueOfDeliveredSubscriptions());
        }else{
            priceBucketView.addToRegisteredRevenue(event.getDispatchDate(),revenueForADate + event.getRevenueOfDeliveredSubscriptions());
        }

        Double profitForADate=priceBucketView.getRegisteredProfit().get(event.getDispatchDate());
        if( null == profitForADate || profitForADate==0.0){
            priceBucketView.addToRegisteredProfit(event.getDispatchDate(),event.getProfitOfDeliveredSubscriptions());
        }else{
            priceBucketView.addToRegisteredProfit(event.getDispatchDate(),profitForADate + event.getProfitOfDeliveredSubscriptions());
        }

        priceBucketViewRepository.save(priceBucketView);
    }
}
