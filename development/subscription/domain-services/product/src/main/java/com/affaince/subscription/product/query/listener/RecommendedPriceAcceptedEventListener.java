package com.affaince.subscription.product.query.listener;

import com.affaince.subscription.product.command.event.RecommendedPriceAcceptedEvent;
import com.affaince.subscription.product.query.repository.PriceBucketViewRepository;
import com.affaince.subscription.product.query.repository.RecommendedPriceBucketViewRepository;
import com.affaince.subscription.product.query.view.PriceBucketView;
import com.affaince.subscription.product.query.view.RecommendedPriceBucketView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

/**
 * Created by mandar on 08-10-2016.
 */
@Component
public class RecommendedPriceAcceptedEventListener {
    private final PriceBucketViewRepository activePriceBucketViewRepository;
    private final RecommendedPriceBucketViewRepository recommendedPriceBucketViewRepository;

    @Autowired
    public RecommendedPriceAcceptedEventListener(PriceBucketViewRepository activePriceBucketViewRepository, RecommendedPriceBucketViewRepository recommendedPriceBucketViewRepository) {
        this.activePriceBucketViewRepository = activePriceBucketViewRepository;
        this.recommendedPriceBucketViewRepository = recommendedPriceBucketViewRepository;
    }

    @EventHandler
    public void on(RecommendedPriceAcceptedEvent event) {
        Sort sort = new Sort(Sort.Direction.DESC, "productVersionId.fromDate");
        RecommendedPriceBucketView latestPriceRecommendedBucket = recommendedPriceBucketViewRepository.findByProductVersionId_ProductId(event.getProductId(), sort).get(0);
        PriceBucketView activePriceBucketView = new PriceBucketView(latestPriceRecommendedBucket.getProductVersionId());
        activePriceBucketView.setFromDate(latestPriceRecommendedBucket.getFromDate());
        activePriceBucketView.setToDate(latestPriceRecommendedBucket.getToDate());
        activePriceBucketView.setOfferedPriceOrPercentDiscountPerUnit(latestPriceRecommendedBucket.getOfferedPriceOrPercentDiscountPerUnit());
        activePriceBucketView.setTaggedPriceVersion(latestPriceRecommendedBucket.getTaggedPriceVersion());
        activePriceBucketView.setSlope(latestPriceRecommendedBucket.getSlope());
        activePriceBucketView.setEntityStatus(latestPriceRecommendedBucket.getEntityStatus());
        PriceBucketView latestActivePriceBucket = activePriceBucketViewRepository.findByProductVersionId_ProductId(event.getProductId(), sort).get(0);
        latestActivePriceBucket.setToDate(latestPriceRecommendedBucket.getFromDate());
        activePriceBucketViewRepository.save(latestActivePriceBucket);
        activePriceBucketViewRepository.save(activePriceBucketView);

    }
}
