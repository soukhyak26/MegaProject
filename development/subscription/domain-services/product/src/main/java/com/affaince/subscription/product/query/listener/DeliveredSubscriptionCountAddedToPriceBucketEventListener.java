package com.affaince.subscription.product.query.listener;

import com.affaince.subscription.product.command.event.DeliveredSubscriptionCountAddedToPriceBucketEvent;
import com.affaince.subscription.product.query.repository.PriceBucketViewRepository;
import com.affaince.subscription.product.query.view.PriceBucketView;
import com.affaince.subscription.product.vo.ProductwisePriceBucketId;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mandar on 4/22/2017.
 */
@Component
public class DeliveredSubscriptionCountAddedToPriceBucketEventListener {
    private static final Logger LOGGER = LoggerFactory.getLogger(DeliveredSubscriptionCountAddedToPriceBucketEventListener.class);
    private final PriceBucketViewRepository priceBucketViewRepository;

    @Autowired
    public DeliveredSubscriptionCountAddedToPriceBucketEventListener(PriceBucketViewRepository priceBucketViewRepository) {
        this.priceBucketViewRepository = priceBucketViewRepository;
    }
    @EventHandler
    public void on(DeliveredSubscriptionCountAddedToPriceBucketEvent event){
        PriceBucketView priceBucketView=priceBucketViewRepository.findOne(new ProductwisePriceBucketId(event.getProductId(),event.getPriceBucketId()));
        priceBucketView.addToDeliveredSubscriptionsAgainstTaggedPrices(event.getLatestDeliveredSubscriptionsAgainstTaggedPrice());
        priceBucketViewRepository.save(priceBucketView);
    }
}
