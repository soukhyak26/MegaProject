package com.affaince.subscription.product.event.listener;

import com.affaince.subscription.common.type.ProductPricingCategory;
import com.affaince.subscription.date.SysDate;
import com.affaince.subscription.product.event.OfferedPriceChangedEvent;
import com.affaince.subscription.product.query.repository.PriceBucketViewRepository;
import com.affaince.subscription.product.query.repository.ProductConfigurationViewRepository;
import com.affaince.subscription.product.query.view.PriceBucketView;
import com.affaince.subscription.product.query.view.ProductConfigurationView;
import com.affaince.subscription.common.vo.ProductwisePriceBucketId;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

/**
 * Created by mandar on 15-09-2016.
 */
@Component
public class OfferedPriceChangedEventListener {

    private final ProductConfigurationViewRepository productConfigurationViewRepository;
    private final PriceBucketViewRepository priceBucketViewRepository;

    @Autowired
    public OfferedPriceChangedEventListener(ProductConfigurationViewRepository productConfigurationViewRepository, PriceBucketViewRepository priceBucketViewRepository) {
        this.productConfigurationViewRepository = productConfigurationViewRepository;
        this.priceBucketViewRepository = priceBucketViewRepository;
    }

    @EventHandler
    public void on(OfferedPriceChangedEvent event) {
        Sort sort = new Sort(Sort.Direction.DESC, "productVersionId.fromDate");
        ProductPricingCategory productPricingStrategy=event.getProductPricingCategory();
        if(productPricingStrategy == ProductPricingCategory.PRICE_COMMITMENT || productPricingStrategy == ProductPricingCategory.DISCOUNT_COMMITMENT) {
            PriceBucketView latestPriceBucket = priceBucketViewRepository.findByProductwisePriceBucketId_ProductId(event.getProductId(), sort).get(0);
            PriceBucketView newPriceBucket = new PriceBucketView(new ProductwisePriceBucketId(event.getProductId(), event.getPriceBucketId()), event.getProductPricingCategory());
            newPriceBucket.setOfferedPriceOrPercentDiscountPerUnit(event.getOfferedPriceOrPercentDiscountPerUnit());
            newPriceBucket.setTaggedPriceVersion(event.getTaggedPriceVersion());
            newPriceBucket.setEntityStatus(event.getEntityStatus());
            newPriceBucket.setFromDate(event.getFromDate());
            newPriceBucket.setToDate(new LocalDateTime(9999, 12, 31, 23, 59));
            latestPriceBucket.setToDate(event.getFromDate().minusMillis(100));
            priceBucketViewRepository.save(latestPriceBucket);
            priceBucketViewRepository.save(newPriceBucket);
        }else{
            PriceBucketView latestPriceBucket = priceBucketViewRepository.findByProductwisePriceBucketId_ProductId(event.getProductId(), sort).get(0);
            latestPriceBucket.setOfferedPriceOrPercentDiscountPerUnit(event.getOfferedPriceOrPercentDiscountPerUnit());
            latestPriceBucket.setTaggedPriceVersion(event.getTaggedPriceVersion());
            latestPriceBucket.setFromDate(event.getFromDate());
            latestPriceBucket.setEntityStatus(event.getEntityStatus());
            priceBucketViewRepository.save(latestPriceBucket);
        }
        ProductConfigurationView productConfigurationView = productConfigurationViewRepository.findOne(event.getProductId());
        //set next forecast date as current date + configured duration for repeating forecast
        productConfigurationView.setNextForecastDate(SysDate.now().plusDays(productConfigurationView.getActualsAggregationPeriodForTargetForecast()));
        productConfigurationViewRepository.save(productConfigurationView);
    }

}