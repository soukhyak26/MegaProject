package com.affaince.subscription.product.query.listener;

import com.affaince.subscription.common.vo.ProductVersionId;
import com.affaince.subscription.product.command.event.RecommendedPriceOverriddenEvent;
import com.affaince.subscription.product.query.repository.PriceBucketViewRepository;
import com.affaince.subscription.product.query.view.PriceBucketView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

/**
 * Created by mandar on 08-10-2016.
 */
@Component
public class RecommendedPriceOverriddenEventListener {
    private final PriceBucketViewRepository activePriceBucketViewRepository;

    @Autowired
    public RecommendedPriceOverriddenEventListener(PriceBucketViewRepository activePriceBucketViewRepository) {
        this.activePriceBucketViewRepository = activePriceBucketViewRepository;
    }

    @EventHandler
    public void on(RecommendedPriceOverriddenEvent event) {
        Sort sort = new Sort(Sort.Direction.DESC, "productVersionId.fromDate");
        PriceBucketView latestPriceBucket = activePriceBucketViewRepository.findByProductVersionId_ProductId(event.getProductId(), sort).get(0);
        PriceBucketView newPriceBucket = new PriceBucketView(new ProductVersionId(event.getProductId(), event.getFromDate()));
        newPriceBucket.setOfferedPriceOrPercentDiscountPerUnit(event.getOverriddenPriceOrPercent());
        newPriceBucket.setTaggedPriceVersion(event.getTaggedPriceVersion());
        newPriceBucket.setEntityStatus(event.getEntityStatus());
        newPriceBucket.setToDate(new LocalDateTime(9999, 12, 31, 23, 59));
        latestPriceBucket.setToDate(event.getFromDate());
        activePriceBucketViewRepository.save(latestPriceBucket);
        activePriceBucketViewRepository.save(newPriceBucket);
    }
}
