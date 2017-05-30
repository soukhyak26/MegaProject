package com.affaince.subscription.payments.command.domain;

import com.affaince.subscription.common.type.ProductPricingCategory;
import com.affaince.subscription.date.SysDate;
import com.affaince.subscription.payments.command.DeliveryCreatedCommand;
import com.affaince.subscription.payments.command.DeliveryDeletedCommand;
import com.affaince.subscription.payments.command.DeliveryStatusAndDispatchDateUpdatedCommand;
import com.affaince.subscription.payments.command.accounting.*;
import com.affaince.subscription.payments.command.event.*;
import com.affaince.subscription.payments.service.ProductDetailsService;
import com.affaince.subscription.payments.service.TaggedPricingService;
import com.affaince.subscription.payments.vo.DeliveredProductDetail;
import com.affaince.subscription.payments.vo.DeliveryDetails;
import com.affaince.subscription.payments.vo.DeliveryItem;
import org.axonframework.eventsourcing.annotation.AbstractAnnotatedAggregateRoot;
import org.axonframework.eventsourcing.annotation.AggregateIdentifier;
import org.axonframework.eventsourcing.annotation.EventSourcedMember;
import org.axonframework.eventsourcing.annotation.EventSourcingHandler;
import org.joda.time.LocalDate;

import java.util.*;
import java.util.stream.Collectors;


public class PaymentAccount extends AbstractAnnotatedAggregateRoot<String> {

    @AggregateIdentifier
    private String subscriptionId;

    private String subscriberId;
    private List<DeliveryDetails> deliveryDetails;

    @EventSourcedMember
    private Map<String, DeliveryCostAccount> deliveryCostAccountMap;
    @EventSourcedMember
    //TODO: for below account, should delivery price and charge be part of DeliveryCreatedEvent/Command?
    private TotalSubscriptionCostAccount totalSubscriptionCostAccount;
    @EventSourcedMember
    private TotalReceivableCostAccount totalReceivableCostAccount;
    @EventSourcedMember
    private TotalReceivedCostAccount totalReceivedCostAccount;

    /**
     * When subscription cost increases, receivable increases
     * When balance increases, recievable decreases
     * At the end, everything balance and recievable should be 0
     */


    public PaymentAccount() {
    }

    public PaymentAccount(String subscriberId, String subscriptionId) {
        apply(new PaymentAccountCreatedEvent(subscriberId, subscriptionId));
    }

    public void addPayment(String subscriptionId, double paidAmount, LocalDate paymentDate) {
        List<DeliveryCostAccount> deliveryCostAccounts = this.deliveryCostAccountMap.values().stream().collect(Collectors.toList());

        Collections.sort(deliveryCostAccounts, new Comparator<DeliveryCostAccount>() {
            @Override
            public int compare(DeliveryCostAccount account1, DeliveryCostAccount account2) {
                return account1.getTransactionDate().compareTo(account2.getTransactionDate());
            }
        });
        Map<String, Double> paymentToBeAdjustedAgainstDeliveries = new HashMap<>();

        //this is total payment received
        double paymentReceived = paidAmount;
        for (DeliveryCostAccount deliveryCostAccount : deliveryCostAccounts) {
            //get total delivery amount
            double deliveryAmount = deliveryCostAccount.getAmount();
            //get payment made so far for this delivery
            double paymentReceivedForDelivery = deliveryCostAccount.getPaymentReceived();
            //find payment to be made from the incoming payment to the current delivery
            double paymentMadeForDelivery = 0;
            //if delivery cost is more than total payment received for this delivery so far
            //Then try to finish remaining payment to this delivery by making ALL/maximum payment to it.
            if (deliveryAmount > paymentReceivedForDelivery) {
                double residualAmountForDelivery = deliveryAmount - paymentReceivedForDelivery;
                if (paymentReceived > residualAmountForDelivery) {
                    paymentMadeForDelivery = residualAmountForDelivery;
                } else if (paymentReceived <= residualAmountForDelivery) {
                    paymentMadeForDelivery = paymentReceived;
                }
                paymentReceived -= paymentReceivedForDelivery;
                //deliveryCostAccount.creditToPaymentReceived(paymentMadeForDelivery);
                paymentToBeAdjustedAgainstDeliveries.put(deliveryCostAccount.getDeliveryId(), paymentMadeForDelivery);
            }
        }

        apply(new PaymentInitiatedEvent(subscriptionId, paidAmount, paymentToBeAdjustedAgainstDeliveries, paymentDate));
    }

    @EventSourcingHandler
    public void on(PaymentInitiatedEvent event) {
        this.totalReceivedCostAccount.credit(event.getPaidAmount(), event.getPaymentDate());
        this.totalReceivableCostAccount.debit(event.getPaidAmount(), event.getPaymentDate());
        Map<String, Double> paymentToBeAdjustedAgainstDeliveries = event.getPaymentToBeAdjustedAgainstDeliveries();
        java.util.Iterator<String> deliveryCostAccountKeysIterator = deliveryCostAccountMap.keySet().iterator();
        while (deliveryCostAccountKeysIterator.hasNext()) {
            String deliveryId = deliveryCostAccountKeysIterator.next();
            DeliveryCostAccount deliveryCostAccount = deliveryCostAccountMap.get(deliveryId);
            deliveryCostAccount.creditToPaymentReceived(paymentToBeAdjustedAgainstDeliveries.get(deliveryId));
        }
    }


    public void handleDeliveryCreatedCommand(DeliveryCreatedCommand command) {
        apply(new DeliveryInitiatedEvent(command.getDeliveryId(), command.getSubscriptionId()));
    }

    public void handleDeliveryDeletedCommand(DeliveryDeletedCommand command) {
        apply(new DeliveryDestroyedEvent(command.getDeliveryId(), command.getSubscriberId()));
    }

    @EventSourcingHandler
    public void on(DeliveryInitiatedEvent event) {
    }

    @EventSourcingHandler
    public void on(DeliveryDestroyedEvent event) {
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public void setSubscriptionId(String subscriptionId) {
        this.subscriptionId = subscriptionId;
    }

    public Map<String, DeliveryCostAccount> getDeliveryCostAccountMap() {
        return deliveryCostAccountMap;
    }

    public void setDeliveryCostAccountMap(Map<String, DeliveryCostAccount> deliveryCostAccountMap) {
        this.deliveryCostAccountMap = deliveryCostAccountMap;
    }


    public TotalSubscriptionCostAccount getTotalSubscriptionCostAccount() {
        return totalSubscriptionCostAccount;
    }

    public TotalReceivableCostAccount getTotalReceivableCostAccount() {
        return totalReceivableCostAccount;
    }

    public TotalReceivedCostAccount getTotalReceivedCostAccount() {
        return totalReceivedCostAccount;
    }

    public void registerSubscriptionDetails(List<DeliveryCreatedEvent> totalSubscriptionDeliveries, TaggedPricingService taggedPricingService,ProductDetailsService productDetailsService) {
        double totalTentativeSubscriptionAmount = 0;
        double totalRewardPoints = 0;
        List<DeliveryDetails> deliveries = new ArrayList<>();
        for (DeliveryCreatedEvent delivery : totalSubscriptionDeliveries) {
            DeliveryDetails deliveryDetails = createDeliveryDetails(delivery,taggedPricingService,productDetailsService);
            deliveries.add(deliveryDetails);

            List<DeliveryItem> itemsInADelivery = delivery.getDeliveryItems();
            totalRewardPoints += delivery.getRewardPoints();
            double totalDeliveryCost = 0;
            for (DeliveryItem item : itemsInADelivery) {
                ProductPricingCategory pricingCategory=productDetailsService.findProductByProductId(item.getDeliveryItemId()).getProductPricingCategory();
                if(pricingCategory== ProductPricingCategory.DISCOUNT_COMMITMENT){
                    double latestMRP= taggedPricingService.findLatestTaggedPriceForAProduct(item.getDeliveryItemId());
                    totalTentativeSubscriptionAmount += latestMRP*(1-item.getOfferedPricePerUnit());
                    totalDeliveryCost += latestMRP*(1-item.getOfferedPricePerUnit());
                }else {
                    totalTentativeSubscriptionAmount += item.getOfferedPricePerUnit();
                    totalDeliveryCost += item.getOfferedPricePerUnit();
                }
            }
            apply(new CostCalculatedForRegisteredDeliveryEvent(this.getSubscriptionId(), delivery.getDeliveryId(), delivery.getDeliveryDate(), totalDeliveryCost));
        }

        apply(new SubscriptionDetailsRegisteredEvent(this.getSubscriptionId(), totalTentativeSubscriptionAmount, totalRewardPoints, deliveries, SysDate.now()));
    }

    private DeliveryDetails createDeliveryDetails(DeliveryCreatedEvent delivery, TaggedPricingService taggedPricingService, ProductDetailsService productDetailsService) {
        DeliveryDetails deliveryDetail= new DeliveryDetails(delivery.getDeliveryId(),delivery.getSubscriptionId());
        deliveryDetail.setDeliveryDate(delivery.getDeliveryDate());
        List<DeliveryItem> deliveryItems= delivery.getDeliveryItems();
        List<DeliveredProductDetail> deliveredProducts=new ArrayList<>();
        for(DeliveryItem deliveryItem: deliveryItems){
            DeliveredProductDetail deliveredProduct= new DeliveredProductDetail(deliveryItem.getDeliveryItemId(),deliveryItem.getPriceBucketId());
            deliveredProduct.setDeliveryCharges(deliveryItem.getDeliveryCharges());
            deliveredProduct.setMRPAtSubscription(taggedPricingService.findLatestTaggedPriceForAProduct(deliveryItem.getDeliveryItemId()));
            deliveredProduct.setOfferedPricePerUnitAtSubscription(deliveryItem.getOfferedPricePerUnit());
            deliveredProduct.setProductPricingCategory(productDetailsService.findProductByProductId(deliveryItem.getDeliveryItemId()).getProductPricingCategory());
            deliveredProducts.add(deliveredProduct);
        }
        deliveryDetail.setDeliveredProductDetails(deliveredProducts);
        return deliveryDetail;
    }

    @EventSourcingHandler
    public void on(PaymentAccountCreatedEvent event) {
        this.subscriptionId = event.getSubscriptionId();
        this.subscriberId = event.getSubscriberId();
        this.deliveryCostAccountMap = new HashMap<>();
    }

    @EventSourcingHandler
    public void on(CostCalculatedForRegisteredDeliveryEvent event) {
        DeliveryCostAccount deliveryCostAccount = new DeliveryCostAccount(event.getDeliveryId(), event.getSubscriptionId(), event.getDeliveryDate(), event.getTotalDeliveryCost());
        this.deliveryCostAccountMap.put(event.getDeliveryId(), deliveryCostAccount);
    }

    @EventSourcingHandler
    public void on(SubscriptionDetailsRegisteredEvent event) {
        this.totalReceivableCostAccount = new TotalReceivableCostAccount(event.getSubscriptionId(), event.getTotalTentativeSubscriptionAmount(), event.getRegistrationDate());
        this.totalReceivedCostAccount = new TotalReceivedCostAccount(event.getSubscriptionId(), 0, event.getRegistrationDate());
        this.totalSubscriptionCostAccount = new TotalSubscriptionCostAccount(event.getSubscriptionId(), event.getTotalTentativeSubscriptionAmount(), event.getRegistrationDate());
        this.deliveryDetails = event.getDeliveries();
    }

    public void correctDues(DeliveryStatusAndDispatchDateUpdatedCommand command) {
    }

    private class Iterator<T> {
    }
}
