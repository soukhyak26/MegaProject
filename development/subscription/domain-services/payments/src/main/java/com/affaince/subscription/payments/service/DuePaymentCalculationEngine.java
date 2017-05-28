package com.affaince.subscription.payments.service;

import com.affaince.subscription.common.type.ProductPricingCategory;
import com.affaince.subscription.payments.vo.DeliveredProductDetail;
import com.affaince.subscription.payments.vo.DeliveryDetails;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by mandar on 5/25/2017.
 */
@Component
public class DuePaymentCalculationEngine {
    private OfferPricingService offerPricingService;
    private TaggedPricingService taggedPricingService;

    @Autowired
    public DuePaymentCalculationEngine(OfferPricingService offerPricingService, TaggedPricingService taggedPricingService) {
        this.offerPricingService = offerPricingService;
        this.taggedPricingService = taggedPricingService;
    }

    public void correctDues(List<DeliveryDetails> deliveries) {
        for (DeliveryDetails delivery : deliveries) {
            List<DeliveredProductDetail> itemsInDelivery = delivery.getDeliveredProductDetails();
            for (DeliveredProductDetail item : itemsInDelivery) {
                double offerPriceOrPercent = item.getOfferedPricePerUnitAtSubscription();
                if (item.getProductPricingCategory() == ProductPricingCategory.DISCOUNT_COMMITMENT) {
                    double latestMRP = taggedPricingService.findLatestTaggedPriceForAProduct(item.getDeliveryItemId());
                    double latestOfferPriceToBeCharged=latestMRP*(1-item.getOfferedPricePerUnitAtSubscription());

                } else if (item.getProductPricingCategory() == ProductPricingCategory.NO_COMMITMENT) {

                } else if (item.getProductPricingCategory() == ProductPricingCategory.PRICE_COMMITMENT) {

                } else {

                }
            }
        }
    }
}
