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
import java.util.stream.Collector;
import java.util.stream.Collectors;

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

    private List<DeliveryCostAccount> findDeliveriesContainingProduct(String productId, List<DeliveryCostAccount> deliveryCostAccounts) {
        return deliveryCostAccounts.stream().filter(delivery -> delivery.isProductInDelivery(productId)).collect(Collectors.toCollection(ArrayList<DeliveryCostAccount>::new));
    }

    public double correctDuesDueToTaggedPriceChangeInAPercentDiscountCommittedProduct(String productId, List<DeliveryCostAccount> deliveryCostAccounts) {
        ProductView productView = productDetailsService.findProductByProductId(productId);
        if (productView.getProductPricingCategory() != ProductPricingCategory.PRICE_COMMITMENT) {
            List<DeliveryCostAccount> deliveriesContainingProduct = findDeliveriesContainingProduct(productId, deliveryCostAccounts);
            double correctedDues = 0;
            for (DeliveryCostAccount delivery : deliveriesContainingProduct) {
                List<DeliveredProductDetail> deliverableProducts = delivery.getDeliveryDetail().getDeliveredProductDetails();
                for (DeliveredProductDetail product : deliverableProducts) {
                    if (product.getDeliveryItemId().equals(productId)) {
                        if (product.getProductPricingCategory() == ProductPricingCategory.DISCOUNT_COMMITMENT) {
                            double mrpAtSubscription = product.getMRPOld();
                            double latestMRP = taggedPricingService.findLatestTaggedPriceForAProduct(product.getDeliveryItemId());
                            if (latestMRP != mrpAtSubscription) {
                                correctedDues += (latestMRP - mrpAtSubscription) * (1 - product.getOfferedPricePerUnitOld());
                            }
                        }
                    }
                }
            }
            return correctedDues;
        }
        return 0;
    }

    //the main method to be called for every Delivery Dispatch event
    //due correction does not apply for deliveries which are already been dispatched in the past.
    public ModifiedSubscriptionContent correctTotalDues(String subscriptionId, List<DeliveryCostAccount> deliveryCostAccounts) {
        //create data structure for storing modifications in due payments at subscription level and at delivery level
        List<ModifiedDeliveryContent> modifiedDeliveries = new ArrayList<>();

        double totalSubscriptionPayment = 0;
        double totalDuePaymentTillDate = 0;
        //modify each delivery which has not been dispatched yet
        List<DeliveryCostAccount> costAccountsForUndeliveredDeliveries = deliveryCostAccounts.stream().filter(dca -> dca.getDeliveryDetail().getDeliveryStatus() != DeliveryStatus.DELIVERED).collect(Collectors.toList());
        for (DeliveryCostAccount deliveryCostAccount : costAccountsForUndeliveredDeliveries) {
            DeliveryDetails delivery = deliveryCostAccount.getDeliveryDetail();
            double totalPaymentOfADelivery = 0;
            double totalDuePaymentOfADelivery = 0;

            //if delivery is already made and total due for the delivery is 0, skip that delivery
            ModifiedDeliveryContent modifiedDeliveryContent = new ModifiedDeliveryContent(delivery.getSubscriptionId(), delivery.getDeliveryId(),deliveryCostAccount.getSequence());
            List<DeliveredProductDetail> itemsInDelivery = delivery.getDeliveredProductDetails();
            //for each product/item listed in delivery
            for (DeliveredProductDetail item : itemsInDelivery) {
                // get offer price or percent of a product at the time of subscription.
                double offerPriceOfAProductOld = 0;
                double latestOfferPriceOfAProduct = 0;
                //due correction should not be applied to price committed products
                if (item.getProductPricingCategory() == ProductPricingCategory.PRICE_COMMITMENT) {
                    //get committed offer price
                    double offerPriceOrPercentOld = item.getOfferedPricePerUnitOld();
                    offerPriceOfAProductOld = offerPriceOrPercentOld;
                    latestOfferPriceOfAProduct = offerPriceOrPercentOld;
                    item.setOfferedPricePerUnitOld(offerPriceOfAProductOld);
                    item.setOfferedPricePerUnitNew(latestOfferPriceOfAProduct);
                } else if (item.getProductPricingCategory() == ProductPricingCategory.DISCOUNT_COMMITMENT) {
                    //get discount percentage committed
                    double offerPriceOrPercentOld = item.getOfferedPriceOrPercent();
                    double oldMRP = item.getMRPOld();
                    offerPriceOfAProductOld = oldMRP * (1 - item.getOfferedPricePerUnitOld());
                    double latestMRP = taggedPricingService.findLatestTaggedPriceForAProduct(item.getDeliveryItemId());
                    latestOfferPriceOfAProduct = latestMRP * (1 - item.getOfferedPricePerUnitOld());
                    item.setOfferedPricePerUnitOld(offerPriceOfAProductOld);
                    item.setOfferedPricePerUnitNew(latestOfferPriceOfAProduct);
                } else if (item.getProductPricingCategory() == ProductPricingCategory.NO_COMMITMENT) {
                    //get uncommitted offer price
                    double offerPriceOrPercentOld = item.getOfferedPricePerUnitOld();
                    offerPriceOfAProductOld = offerPriceOrPercentOld;
                    latestOfferPriceOfAProduct = offerPricingService.findLatestOfferPriceOrPercentForAProduct(item.getDeliveryItemId());
                    item.setOfferedPricePerUnitOld(offerPriceOfAProductOld);
                    item.setOfferedPricePerUnitNew(latestOfferPriceOfAProduct);
                }
                //latest total payment of a delivery is sum of all latest offer prices
                totalPaymentOfADelivery += item.getOfferedPricePerUnitNew();
                //due payment = latest payment - payment already received
                totalDuePaymentOfADelivery = totalPaymentOfADelivery - deliveryCostAccount.getPaymentReceived();

                modifiedDeliveryContent.addtoItems(item);
                modifiedDeliveryContent.setCorrectedTotalPayment(totalPaymentOfADelivery);
                //not used as of now
                modifiedDeliveryContent.setCorrectedRemainingDuePayment(totalDuePaymentOfADelivery);
                modifiedDeliveries.add(modifiedDeliveryContent);
            }
            totalSubscriptionPayment += totalPaymentOfADelivery;
            //not used as of now
            totalDuePaymentTillDate += totalDuePaymentOfADelivery;
        }
        ModifiedSubscriptionContent modifiedSubscriptionContent = new ModifiedSubscriptionContent(subscriptionId);
        modifiedSubscriptionContent.setModifiedDeliveries(modifiedDeliveries);
        modifiedSubscriptionContent.setModifiedTotalSubscriptionPayment(totalSubscriptionPayment);
        modifiedSubscriptionContent.setModifiedTotalDuePayment(totalDuePaymentTillDate);
        return modifiedSubscriptionContent;
    }
}
