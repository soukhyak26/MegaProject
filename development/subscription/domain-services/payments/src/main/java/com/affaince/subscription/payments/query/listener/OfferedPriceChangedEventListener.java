package com.affaince.subscription.payments.query.listener;

import com.affaince.subscription.common.type.ProductPricingCategory;
import com.affaince.subscription.common.vo.ProductwisePriceBucketId;
import com.affaince.subscription.date.SysDate;
import com.affaince.subscription.payments.command.event.OfferedPriceChangedEvent;
import com.affaince.subscription.payments.query.repository.ProductOfferPricesViewRepository;
import com.affaince.subscription.payments.query.view.ProductOfferPricesView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.joda.time.LocalDateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

/**
 * Created by mandar on 5/26/2017.
 */
@Component
public class OfferedPriceChangedEventListener {
    private ProductOfferPricesViewRepository productOfferPricesViewRepository;
    @Autowired
    public OfferedPriceChangedEventListener(ProductOfferPricesViewRepository productOfferPricesViewRepository) {
        this.productOfferPricesViewRepository = productOfferPricesViewRepository;
    }
    @EventHandler
    public void on(OfferedPriceChangedEvent event){
        Sort sort = new Sort(Sort.Direction.DESC, "productVersionId.fromDate");
        ProductPricingCategory productPricingStrategy=event.getProductPricingCategory();
        if(productPricingStrategy == ProductPricingCategory.PRICE_COMMITMENT || productPricingStrategy == ProductPricingCategory.DISCOUNT_COMMITMENT) {
            ProductOfferPricesView latestPriceBucket = productOfferPricesViewRepository.findByProductwisePriceBucketId_ProductId(event.getProductId(), sort).get(0);
            ProductOfferPricesView newPriceBucket = new ProductOfferPricesView(new ProductwisePriceBucketId(event.getProductId(), event.getPriceBucketId()), event.getProductPricingCategory(),event.getOfferedPriceOrPercentDiscountPerUnit(),event.getFromDate());
            latestPriceBucket.setToDate(event.getFromDate().minusMillis(100));
            productOfferPricesViewRepository.save(latestPriceBucket);
            productOfferPricesViewRepository.save(newPriceBucket);
        }else{
            ProductOfferPricesView latestPriceBucket = productOfferPricesViewRepository.findByProductwisePriceBucketId_ProductId(event.getProductId(), sort).get(0);
            latestPriceBucket.setOfferPriceOrPercent(event.getOfferedPriceOrPercentDiscountPerUnit());
            latestPriceBucket.setFromDate(event.getFromDate());
            productOfferPricesViewRepository.save(latestPriceBucket);
        }
    }
}
