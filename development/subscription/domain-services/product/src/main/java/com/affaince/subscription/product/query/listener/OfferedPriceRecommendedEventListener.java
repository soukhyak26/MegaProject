package com.affaince.subscription.product.query.listener;

import com.affaince.subscription.common.type.ProductDemandTrend;
import com.affaince.subscription.common.vo.ProductVersionId;
import com.affaince.subscription.date.SysDate;
import com.affaince.subscription.date.SysDateTime;
import com.affaince.subscription.product.command.event.OfferedPriceRecommendedEvent;
import com.affaince.subscription.product.query.repository.ProductConfigurationViewRepository;
import com.affaince.subscription.product.query.repository.RecommendedPriceBucketViewRepository;
import com.affaince.subscription.product.query.view.ProductConfigurationView;
import com.affaince.subscription.product.query.view.RecommendedPriceBucketView;
import com.affaince.subscription.product.vo.ProductwisePriceBucketId;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

/**
 * Created by mandar on 15-09-2016.
 */
@Component
public class OfferedPriceRecommendedEventListener {

    private final ProductConfigurationViewRepository productConfigurationViewRepository;
    private final RecommendedPriceBucketViewRepository recommendedPriceBucketViewRepository;

    @Autowired
    public OfferedPriceRecommendedEventListener(ProductConfigurationViewRepository productConfigurationViewRepository, RecommendedPriceBucketViewRepository recommendedPriceBucketViewRepository) {
        this.productConfigurationViewRepository = productConfigurationViewRepository;
        this.recommendedPriceBucketViewRepository = recommendedPriceBucketViewRepository;
    }

    @EventHandler
    public void on(OfferedPriceRecommendedEvent event) {
        Sort sort = new Sort(Sort.Direction.DESC, "productVersionId.fromDate");
        RecommendedPriceBucketView latestPriceBucket = recommendedPriceBucketViewRepository.findByProductVersionId_ProductId(event.getProductId(), sort).get(0);
        RecommendedPriceBucketView newPriceBucket = new RecommendedPriceBucketView(new ProductwisePriceBucketId(event.getProductId(), event.getNewPriceBucket().getPriceBucketId()));
        newPriceBucket.setOfferedPriceOrPercentDiscountPerUnit(event.getNewPriceBucket().getOfferedPriceOrPercentDiscountPerUnit());
        newPriceBucket.setTaggedPriceVersion(event.getNewPriceBucket().getTaggedPriceVersion());
        newPriceBucket.setEntityStatus(event.getNewPriceBucket().getEntityStatus());
        newPriceBucket.setToDate(new LocalDateTime(9999, 12, 31, 23, 59));
        latestPriceBucket.setToDate(event.getNewPriceBucket().getToDate());
        recommendedPriceBucketViewRepository.save(latestPriceBucket);
        recommendedPriceBucketViewRepository.save(newPriceBucket);
        ProductConfigurationView productConfigurationView = productConfigurationViewRepository.findOne(event.getProductId());
        //set next forecast date as current date + configured duration for repeating forecast
        productConfigurationView.setNextForecastDate(SysDate.now().plusDays(productConfigurationView.getActualsAggregationPeriodForTargetForecast()));
        productConfigurationViewRepository.save(productConfigurationView);
    }

}
