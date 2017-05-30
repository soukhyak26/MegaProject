package com.affaince.subscription.payments.service;

import com.affaince.subscription.common.type.ProductPricingCategory;
import com.affaince.subscription.payments.query.view.DeliveryDetailsView;
import com.affaince.subscription.payments.query.view.ProductView;
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
public class DuePaymentCorrectionEngine {
    private OfferPricingService offerPricingService;
    private TaggedPricingService taggedPricingService;
    private DeliveriesDetailsService deliveriesDetailsService;
    private ProductDetailsService productDetailsService;

    @Autowired
    public DuePaymentCorrectionEngine(ProductDetailsService productDetailsService, OfferPricingService offerPricingService, TaggedPricingService taggedPricingService, DeliveriesDetailsService deliveriesDetailsService) {
        this.productDetailsService = productDetailsService;
        this.offerPricingService = offerPricingService;
        this.taggedPricingService = taggedPricingService;
        this.deliveriesDetailsService = deliveriesDetailsService;
    }

    public double correctDuesDueToOfferPriceChangeInAProduct(String productId) {
        ProductView productView = productDetailsService.findProductByProductId(productId);
        if (productView.getProductPricingCategory() != ProductPricingCategory.PRICE_COMMITMENT) {
            List<DeliveryDetailsView> deliveriesContainingProduct = deliveriesDetailsService.findDeliveriesContainingProduct(productId);
            double correctedDues = 0;
            for (DeliveryDetailsView delivery : deliveriesContainingProduct) {
                List<DeliveredProductDetail> deliverableProducts = delivery.getDeliveredProductDetails();
                for (DeliveredProductDetail product : deliverableProducts) {
                    if (product.getDeliveryItemId().equals(productId)) {
                        double offerPriceOrPercent = product.getOfferedPricePerUnitAtSubscription();
                        if (product.getProductPricingCategory() == ProductPricingCategory.DISCOUNT_COMMITMENT) {
                            //OFFER PRICE OR DISCOUNT PERCENTAGE CHANGE SHOULD NOT HAVE ANY IMPACT in DUES AS BOTH ARE COMMITTED
                        } else if (product.getProductPricingCategory() == ProductPricingCategory.NO_COMMITMENT) {
                            correctedDues += offerPricingService.findLatestOfferPriceOrPercentForAProduct(product.getDeliveryItemId()) - product.getOfferedPricePerUnitAtSubscription();
                        }
                    }
                }
            }
            return correctedDues;
        }
        return 0;
    }

    public double correctDuesDueToTaggedPriceChangeInAProduct(String productId) {
        ProductView productView = productDetailsService.findProductByProductId(productId);
        if (productView.getProductPricingCategory() != ProductPricingCategory.PRICE_COMMITMENT) {
            List<DeliveryDetailsView> deliveriesContainingProduct = deliveriesDetailsService.findDeliveriesContainingProduct(productId);
            double correctedDues = 0;
            for (DeliveryDetailsView delivery : deliveriesContainingProduct) {
                List<DeliveredProductDetail> deliverableProducts = delivery.getDeliveredProductDetails();
                for (DeliveredProductDetail product : deliverableProducts) {
                    if (product.getDeliveryItemId().equals(productId)) {
                        double offerPriceOrPercent = product.getOfferedPricePerUnitAtSubscription();
                        double mrpAtSubscription = product.getMRPAtSubscription();
                        if (product.getProductPricingCategory() == ProductPricingCategory.DISCOUNT_COMMITMENT) {
                            double latestMRP = taggedPricingService.findLatestTaggedPriceForAProduct(product.getDeliveryItemId());
                            correctedDues += (latestMRP - mrpAtSubscription) * (1 - product.getOfferedPricePerUnitAtSubscription());

                        } else if (product.getProductPricingCategory() == ProductPricingCategory.NO_COMMITMENT) {
                            //NO COMMITMENT HAS NO IMPACT OF TAGGED PRICE CHANGES
                        }
                    }
                }
            }
            return correctedDues;
        }
        return 0;
    }

/*
    public double correctDuesDueToSubscriptionContentChange(String subscriptionId) {
        ProductView productView = productDetailsService.findProductByProductId(productId);
        if (productView.getProductPricingCategory() != ProductPricingCategory.PRICE_COMMITMENT) {
            List<DeliveryDetailsView> deliveriesContainingProduct = deliveriesDetailsService.findDeliveriesContainingProduct(productId);
            double correctedDues = 0;
            for (DeliveryDetailsView delivery : deliveriesContainingProduct) {
                List<DeliveredProductDetail> deliverableProducts = delivery.getDeliveredProductDetails();
                for (DeliveredProductDetail product : deliverableProducts) {
                    if (product.getDeliveryItemId().equals(productId)) {
                        double offerPriceOrPercent = product.getOfferedPricePerUnitAtSubscription();
                        double mrpAtSubscription = product.getMRPAtSubscription();
                        if (product.getProductPricingCategory() == ProductPricingCategory.DISCOUNT_COMMITMENT) {
                            double latestMRP = taggedPricingService.findLatestTaggedPriceForAProduct(product.getDeliveryItemId());
                            correctedDues += (latestMRP - mrpAtSubscription) * (1 - product.getOfferedPricePerUnitAtSubscription());

                        } else if (product.getProductPricingCategory() == ProductPricingCategory.NO_COMMITMENT) {
                            //NO COMMITMENT HAS NO IMPACT OF TAGGED PRICE CHANGES
                        }
                    }
                }
            }
            return correctedDues;
        }
        return 0;
    }
*/

/*
    public List<ModifiedDeliveryContent> correctDues(String subscriptionId) {
        List<DeliveryDetailsView> deliveries = deliveriesDetailsService.findDeliveriesBySubscriptionId(subscriptionId);
        Map<String, ModifiedDeliveryContent> subscriptionWiseModifiedDeliveries = new HashMap<>();
        List<ModifiedDeliveryContent> modifiedPricesPerDelivery = new ArrayList<>();
        Map<String, Double> productwiseLatestOfferedPrices = new TreeMap<>();
        for (DeliveryDetailsView delivery : deliveries) {
            List<DeliveredProductDetail> itemsInDelivery = delivery.getDeliveredProductDetails();
            ModifiedDeliveryContent modifiedDeliveryContent = new ModifiedDeliveryContent(delivery.getSubscriptionwiseDeliveryId().getDeliveryId());
            for (DeliveredProductDetail item : itemsInDelivery) {
                //due correction should not be applied to price committed products
                if (item.getProductPricingCategory() != ProductPricingCategory.PRICE_COMMITMENT) {
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
                    productwiseLatestOfferedPrices.put(item.getDeliveryItemId(), latestOfferedPriceOfAProduct);
                    modifiedDeliveryContent.addToItemwiseModifiedPrices(item.getDeliveryItemId(), latestOfferedPriceOfAProduct);
                }
            }
            modifiedPricesPerDelivery.add(modifiedDeliveryContent);
        }
        return modifiedPricesPerDelivery;
    }
*/
}
