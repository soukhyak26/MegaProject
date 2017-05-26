package com.affaince.subscription.product.query.listener;

import com.affaince.subscription.common.type.EntityStatus;
import com.affaince.subscription.product.command.event.PriceBucketExpiredEvent;
import com.affaince.subscription.product.query.repository.PriceBucketViewRepository;
import com.affaince.subscription.product.query.view.PriceBucketView;
import com.affaince.subscription.common.vo.ProductwisePriceBucketId;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by mandar on 29-12-2016.
 */
@Component
public class PriceBucketExpiredEventListener {

    private final PriceBucketViewRepository priceBucketViewRepository;

    @Autowired
    public PriceBucketExpiredEventListener(PriceBucketViewRepository priceBucketViewRepository) {
        this.priceBucketViewRepository = priceBucketViewRepository;
    }

    @EventHandler
    public void on(PriceBucketExpiredEvent event){
        PriceBucketView priceBucketView=priceBucketViewRepository.findOne(new ProductwisePriceBucketId(event.getProductId(),event.getPriceBucketId()));
        priceBucketView.setEntityStatus(EntityStatus.EXPIRED);
        priceBucketViewRepository.save(priceBucketView);
    }
}
