package com.affaince.subscription.product.query.listener;

import com.affaince.subscription.common.type.EntityStatus;
import com.affaince.subscription.product.command.event.PriceBucketExpiredEvent;
import com.affaince.subscription.product.query.repository.PriceBucketViewRepository;
import com.affaince.subscription.product.query.view.PriceBucketView;
import com.affaince.subscription.product.vo.ProductwisePriceBucketId;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created by mandar on 29-12-2016.
 */
public class PriceBucketExpiredEventListener {

    private final PriceBucketViewRepository priceBucketViewRepository;

    @Autowired
    public PriceBucketExpiredEventListener(PriceBucketViewRepository priceBucketViewRepository) {
        this.priceBucketViewRepository = priceBucketViewRepository;
    }

    public void on(PriceBucketExpiredEvent event){
        PriceBucketView priceBucketView=priceBucketViewRepository.findOne(new ProductwisePriceBucketId(event.getProductId(),event.getPriceBucketId()));
        priceBucketView.setEntityStatus(EntityStatus.EXPIRED);
        priceBucketViewRepository.save(priceBucketView);
    }
}
