package com.affaince.subscription.product.query.listener;

import com.affaince.subscription.common.vo.ProductVersionId;
import com.affaince.subscription.product.command.event.DeliveryExpenseUpdatedToProductEvent;
import com.affaince.subscription.product.query.repository.PriceBucketViewRepository;
import com.affaince.subscription.product.query.view.PriceBucketView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class DeliveryExpenseUpdatedToProductEventListener {

    private final PriceBucketViewRepository priceBucketViewRepository;

    @Autowired
    public DeliveryExpenseUpdatedToProductEventListener(PriceBucketViewRepository priceBucketViewRepository) {
        this.priceBucketViewRepository = priceBucketViewRepository;
    }

    //WHAT IS THIS????It is wrong....
    @EventHandler
    public void on (DeliveryExpenseUpdatedToProductEvent event) {
        final ProductVersionId productVersionId = new ProductVersionId(event.getProductId(), event.getFromDate());
        final PriceBucketView priceBucketView = priceBucketViewRepository.findOne(productVersionId);
        priceBucketViewRepository.save(priceBucketView);
    }
}
