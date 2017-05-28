package com.affaince.subscription.payments.service;

import com.affaince.subscription.common.type.ProductPricingCategory;
import com.affaince.subscription.payments.query.view.DeliveryDetailsView;
import com.affaince.subscription.payments.vo.DeliveredProductDetail;
import com.affaince.subscription.payments.vo.DeliveryDetails;
import com.affaince.subscription.payments.vo.ModifiedDeliveryContent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

/**
 * Created by mandar on 5/25/2017.
 */
@Component
public class DuePaymentCalculationEngine {
    private OfferPricingService offerPricingService;
    private TaggedPricingService taggedPricingService;
    private DeliveriesDetailsService deliveriesDetailsService;

    @Autowired
    public DuePaymentCalculationEngine(OfferPricingService offerPricingService, TaggedPricingService taggedPricingService,DeliveriesDetailsService deliveriesDetailsService) {
        this.offerPricingService = offerPricingService;
        this.taggedPricingService = taggedPricingService;
        this.deliveriesDetailsService=deliveriesDetailsService;
    }

    public List<ModifiedDeliveryContent> correctDues(String subscriptionId) {
        List<DeliveryDetailsView> deliveries=deliveriesDetailsService.findDeliveriesBySubscriptionId(subscriptionId);
        Map<String,ModifiedDeliveryContent> subscriptionWiseModifiedDeliveries= new HashMap<>();
        List<ModifiedDeliveryContent> modifiedPricesPerDelivery= new ArrayList<>();
        Map<String,Double> productwiseLatestOfferedPrices= new TreeMap<>();
        for (DeliveryDetailsView delivery : deliveries) {
            List<DeliveredProductDetail> itemsInDelivery = delivery.getDeliveredProductDetails();
            ModifiedDeliveryContent modifiedDeliveryContent= new ModifiedDeliveryContent(delivery.getSubscriptionwiseDeliveryId().getDeliveryId());
            for (DeliveredProductDetail item : itemsInDelivery) {
                //due correction should not be applied to price committed products
                if(item.getProductPricingCategory() != ProductPricingCategory.PRICE_COMMITMENT) {
                    Double latestOfferedPriceOfAProduct = productwiseLatestOfferedPrices.get(item.getDeliveryItemId());
                    if (null == latestOfferedPriceOfAProduct) {
                        double offerPriceOrPercent = item.getOfferedPricePerUnitAtSubscription();
                        if (item.getProductPricingCategory() == ProductPricingCategory.DISCOUNT_COMMITMENT) {
                            double latestMRP = taggedPricingService.findLatestTaggedPriceForAProduct(item.getDeliveryItemId());
                            latestOfferedPriceOfAProduct = latestMRP * (1 - item.getOfferedPricePerUnitAtSubscription());
                        } else if (item.getProductPricingCategory() == ProductPricingCategory.NO_COMMITMENT) {
                            latestOfferedPriceOfAProduct = taggedPricingService.findLatestTaggedPriceForAProduct(item.getDeliveryItemId());
                        }
                    }
                    productwiseLatestOfferedPrices.put(item.getDeliveryItemId(),latestOfferedPriceOfAProduct);
                    modifiedDeliveryContent.addToItemwiseModifiedPrices(item.getDeliveryItemId(),latestOfferedPriceOfAProduct);
                }
            }
            modifiedPricesPerDelivery.add(modifiedDeliveryContent);
        }
        return modifiedPricesPerDelivery;
    }
}
