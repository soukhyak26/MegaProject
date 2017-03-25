package com.affaince.subscription.business.query.listener;

import com.affaince.subscription.SubscriptionCommandGateway;
import com.affaince.subscription.business.command.DebitFromPurchaseCostAccountPriceOfDeliveredProductsCommand;
import com.affaince.subscription.business.command.event.DeliveredSubscriptionCountAddedToPriceBucket;
import com.affaince.subscription.business.command.event.PriceBucketWisePurchaseCostRevenueAndProfitCalculatedEvent;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mandar on 19-02-2017.
 */
@Component
public class DeliveredSubscriptionCountAddedToPriceBucketListener {
    private final SubscriptionCommandGateway commandGateway;

    @Autowired
    public DeliveredSubscriptionCountAddedToPriceBucketListener(SubscriptionCommandGateway commandGateway) {
        this.commandGateway = commandGateway;
    }

    @EventHandler
    public void on(PriceBucketWisePurchaseCostRevenueAndProfitCalculatedEvent event) throws Exception{
       // DebitFromPurchaseCostAccountPriceOfDeliveredProductsCommand command= new DebitFromPurchaseCostAccountPriceOfDeliveredProductsCommand(event.getProductId(),event.getPriceBucketId(),event.getPurchasePricePerUnit(),event.getMRP(),event.getOfferPriceOrPercent(),event.getProductPricingCategory(),event.getDeliveredSubscriptionCount(),event.getTotalDeliveredSubscriptionCount());
       // commandGateway.executeAsync(command);
    }
}
