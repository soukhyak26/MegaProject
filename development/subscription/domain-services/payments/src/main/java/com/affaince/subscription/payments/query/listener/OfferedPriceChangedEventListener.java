package com.affaince.subscription.payments.query.listener;

import com.affaince.subscription.common.vo.ProductwisePriceBucketId;
import com.affaince.subscription.payments.command.event.OfferedPriceChangedEvent;
import com.affaince.subscription.payments.query.repository.ProductOfferPricesViewRepository;
import com.affaince.subscription.payments.query.view.ProductOfferPricesView;
import org.axonframework.eventhandling.annotation.EventHandler;
import org.springframework.beans.factory.annotation.Autowired;
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
        ProductOfferPricesView productOfferPricesView= new ProductOfferPricesView(new ProductwisePriceBucketId(event.getProductId(),event.getPriceBucketId()),event.getProductPricingCategory(),event.getOfferedPriceOrPercentDiscountPerUnit());
        productOfferPricesViewRepository.save(productOfferPricesView);
    }
}
