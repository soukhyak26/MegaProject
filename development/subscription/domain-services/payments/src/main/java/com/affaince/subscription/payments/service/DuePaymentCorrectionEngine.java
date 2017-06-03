package com.affaince.subscription.payments.service;

import com.affaince.subscription.common.type.DeliveryStatus;
import com.affaince.subscription.common.type.ProductPricingCategory;
import com.affaince.subscription.payments.command.accounting.DeliveryCostAccount;
import com.affaince.subscription.payments.query.view.DeliveryDetailsView;
import com.affaince.subscription.payments.query.view.ProductView;
import com.affaince.subscription.payments.vo.DeliveredProductDetail;
import com.affaince.subscription.payments.vo.DeliveryDetails;
import com.affaince.subscription.payments.vo.ModifiedDeliveryContent;
import com.affaince.subscription.payments.vo.ModifiedSubscriptionContent;
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
    //private DeliveriesDetailsService deliveriesDetailsService;
    private ProductDetailsService productDetailsService;

    @Autowired
    public DuePaymentCorrectionEngine(ProductDetailsService productDetailsService, OfferPricingService offerPricingService, TaggedPricingService taggedPricingService, DeliveriesDetailsService deliveriesDetailsService) {
        this.productDetailsService = productDetailsService;
        this.offerPricingService = offerPricingService;
        this.taggedPricingService = taggedPricingService;
        // this.deliveriesDetailsService = deliveriesDetailsService;
    }

/*
    public double correctDuesDueToOfferPriceChangeInAProduct(String productId) {
        ProductView productView = productDetailsService.findProductByProductId(productId);
        if (productView.getProductPricingCategory() != ProductPricingCategory.PRICE_COMMITMENT) {
            List<DeliveryDetailsView> deliveriesContainingProduct = deliveriesDetailsService.findDeliveriesContainingProduct(productId);
            double correctedDues = 0;
            for (DeliveryDetailsView delivery : deliveriesContainingProduct) {
                List<DeliveredProductDetail> deliverableProducts = delivery.getDeliveredProductDetails();
                for (DeliveredProductDetail product : deliverableProducts) {
                    if (product.getDeliveryItemId().equals(productId)) {
                        double offerPriceOrPercent = product.getOfferedPricePerUnitOld();
                        if (product.getProductPricingCategory() == ProductPricingCategory.DISCOUNT_COMMITMENT) {
                            //OFFER PRICE OR DISCOUNT PERCENTAGE CHANGE SHOULD NOT HAVE ANY IMPACT in DUES AS BOTH ARE COMMITTED
                        } else if (product.getProductPricingCategory() == ProductPricingCategory.NO_COMMITMENT) {
                            correctedDues += offerPricingService.findLatestOfferPriceOrPercentForAProduct(product.getDeliveryItemId()) - product.getOfferedPricePerUnitOld();
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
    public double correctDuesDueToTaggedPriceChangeInAProduct(String productId) {
        ProductView productView = productDetailsService.findProductByProductId(productId);
        if (productView.getProductPricingCategory() != ProductPricingCategory.PRICE_COMMITMENT) {
            List<DeliveryDetailsView> deliveriesContainingProduct = deliveriesDetailsService.findDeliveriesContainingProduct(productId);
            double correctedDues = 0;
            for (DeliveryDetailsView delivery : deliveriesContainingProduct) {
                List<DeliveredProductDetail> deliverableProducts = delivery.getDeliveredProductDetails();
                for (DeliveredProductDetail product : deliverableProducts) {
                    if (product.getDeliveryItemId().equals(productId)) {
                        double offerPriceOrPercent = product.getOfferedPricePerUnitOld();
                        double mrpAtSubscription = product.getMRPOld();
                        if (product.getProductPricingCategory() == ProductPricingCategory.DISCOUNT_COMMITMENT) {
                            double latestMRP = taggedPricingService.findLatestTaggedPriceForAProduct(product.getDeliveryItemId());
                            correctedDues += (latestMRP - mrpAtSubscription) * (1 - product.getOfferedPricePerUnitOld());

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


    public ModifiedSubscriptionContent correctTotalDues(String subscriptionId, List<DeliveryCostAccount> deliveryCostAccounts) {
        ModifiedDeliveryContent modifiedDeliveryContent = new ModifiedDeliveryContent(subscriptionId);
        List<ModifiedDeliveryContent> modifiedDeliveries = new ArrayList<>();
        double totalSubscriptionPayment = 0;
        double totalDuePaymentTillDate = 0;
        for (DeliveryCostAccount deliveryCostAccount : deliveryCostAccounts) {
            DeliveryDetails delivery = deliveryCostAccount.getDeliveryDetail();
            DeliveryStatus deliveryStatus = delivery.getDeliveryStatus();
            double deliveryDueAmount = deliveryCostAccount.getAmount() - deliveryCostAccount.getPaymentReceived();
            //if delivery is already made and total due for the delivery is 0, skip that delivery
            if (deliveryStatus == DeliveryStatus.DELIVERED && deliveryDueAmount == 0) {
                continue;
            } else {
                List<DeliveredProductDetail> itemsInDelivery = delivery.getDeliveredProductDetails();
                double totalPaymentOfADelivery = 0;
                double totalDuePaymentOfADelivery = 0;
                for (DeliveredProductDetail item : itemsInDelivery) {
                    // get offer price or percent of a product at the time of subscription.
                    double offerPriceOfAProductOld = 0;
                    double latestOfferPriceOfAProduct = 0;
                    //due correction should not be applied to price committed products
                    if (item.getProductPricingCategory() == ProductPricingCategory.PRICE_COMMITMENT) {
                        double offerPriceOrPercentOld = item.getOfferedPricePerUnitOld();
                        offerPriceOfAProductOld = offerPriceOrPercentOld;
                        latestOfferPriceOfAProduct = offerPriceOrPercentOld;
                        item.setOfferedPricePerUnitOld(offerPriceOfAProductOld);
                        item.setOfferedPricePerUnitNew(latestOfferPriceOfAProduct);
                    } else if (item.getProductPricingCategory() == ProductPricingCategory.DISCOUNT_COMMITMENT) {
                        double offerPriceOrPercentOld = item.getOfferedPriceOrPercent();
                        double oldMRP = item.getMRPOld();
                        offerPriceOfAProductOld = oldMRP * (1 - item.getOfferedPricePerUnitOld());
                        double latestMRP = taggedPricingService.findLatestTaggedPriceForAProduct(item.getDeliveryItemId());
                        latestOfferPriceOfAProduct = latestMRP * (1 - item.getOfferedPricePerUnitOld());
                        item.setOfferedPricePerUnitOld(offerPriceOfAProductOld);
                        item.setOfferedPricePerUnitNew(latestOfferPriceOfAProduct);
                    } else if (item.getProductPricingCategory() == ProductPricingCategory.NO_COMMITMENT) {
                        double offerPriceOrPercentOld = item.getOfferedPricePerUnitOld();
                        offerPriceOfAProductOld = offerPriceOrPercentOld;
                        latestOfferPriceOfAProduct = offerPricingService.findLatestOfferPriceOrPercentForAProduct(item.getDeliveryItemId());
                        item.setOfferedPricePerUnitOld(offerPriceOfAProductOld);
                        item.setOfferedPricePerUnitNew(latestOfferPriceOfAProduct);
                    }
                    totalPaymentOfADelivery += item.getOfferedPricePerUnitNew();
                    totalDuePaymentOfADelivery=totalPaymentOfADelivery-deliveryCostAccount.getPaymentReceived();
                    modifiedDeliveryContent.addtoItems(item);
                    modifiedDeliveryContent.setCorrectedTotalPayment(totalPaymentOfADelivery);
                    modifiedDeliveryContent.setCorrectedRemainingDuePayment(totalDuePaymentOfADelivery);
                    modifiedDeliveries.add(modifiedDeliveryContent);
                }
                totalSubscriptionPayment += totalPaymentOfADelivery;
                totalDuePaymentTillDate +=totalDuePaymentOfADelivery;
            }
        }
        ModifiedSubscriptionContent modifiedSubscriptionContent = new ModifiedSubscriptionContent(subscriptionId);
        modifiedSubscriptionContent.setModifiedDeliveries(modifiedDeliveries);
        modifiedSubscriptionContent.setModifiedTotalSubscriptionPayment(totalSubscriptionPayment);
        modifiedSubscriptionContent.setModifiedTotalDuePayment(totalDuePaymentTillDate);
        return modifiedSubscriptionContent;
    }
}
