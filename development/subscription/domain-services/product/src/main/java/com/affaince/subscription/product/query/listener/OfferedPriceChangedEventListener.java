package com.affaince.subscription.product.query.listener;

import com.affaince.subscription.common.vo.ProductVersionId;
import com.affaince.subscription.date.SysDateTime;
import com.affaince.subscription.product.command.event.OfferedPriceChangedEvent;
import com.affaince.subscription.product.query.repository.PriceBucketViewRepository;
import com.affaince.subscription.product.query.repository.ProductConfigurationViewRepository;
import com.affaince.subscription.product.query.view.PriceBucketView;
import com.affaince.subscription.product.query.view.ProductConfigurationView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.joda.time.LocalDateTime;
import org.springframework.data.domain.Sort;

/**
 * Created by mandar on 15-09-2016.
 */
public class OfferedPriceChangedEventListener {

    private final ProductConfigurationViewRepository productConfigurationViewRepository;
    private final PriceBucketViewRepository priceBucketViewRepository;

    public OfferedPriceChangedEventListener(ProductConfigurationViewRepository productConfigurationViewRepository, PriceBucketViewRepository priceBucketViewRepository) {
        this.productConfigurationViewRepository = productConfigurationViewRepository;
        this.priceBucketViewRepository = priceBucketViewRepository;
    }

    @EventHandler
    public void on(OfferedPriceChangedEvent event) {
        Sort sort = new Sort(Sort.Direction.DESC, "productVersionId.fromDate");
        PriceBucketView latestPriceBucket = priceBucketViewRepository.findByProductVersionId_ProductId(event.getProductId(), sort).get(0);
        PriceBucketView newPriceBucket = new PriceBucketView(new ProductVersionId(event.getProductId(), event.getCurrentPriceDate()));
        newPriceBucket.setOfferedPricePerUnit(event.getOfferedPricePerUnit());
        newPriceBucket.setTaggedPriceVersion(event.getTaggedPriceVersion());
        newPriceBucket.setEntityStatus(event.getEntityStatus());
        newPriceBucket.setToDate(new LocalDateTime(9999, 12, 31, 23, 59));
        latestPriceBucket.setToDate(event.getCurrentPriceDate());
        priceBucketViewRepository.save(latestPriceBucket);
        priceBucketViewRepository.save(newPriceBucket);
        ProductConfigurationView productConfigurationView = productConfigurationViewRepository.findOne(event.getProductId());
        //set next forecast date as current date + configured duration for repeating forecast
        productConfigurationView.setNextForecastDate(SysDateTime.now().plusDays(productConfigurationView.getActualsAggregationPeriodForTargetForecast()));
        productConfigurationViewRepository.save(productConfigurationView);
    }

}