package com.affaince.subscription.payments.command.domain;

import com.affaince.subscription.common.type.ProductPricingCategory;
import com.affaince.subscription.date.SysDate;
import com.affaince.subscription.payments.command.CreateDeliveryCommand;
import com.affaince.subscription.payments.command.CorrectDuePaymentCommand;
import com.affaince.subscription.payments.command.accounting.*;
import com.affaince.subscription.payments.command.event.*;
import com.affaince.subscription.payments.service.DuePaymentCorrectionEngine;
import com.affaince.subscription.payments.service.ProductDetailsService;
import com.affaince.subscription.payments.service.TaggedPricingService;
import com.affaince.subscription.payments.vo.*;
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

    private String schemeId;

    @EventSourcedMember
    private Map<String, DeliveryCostAccount> deliveryCostAccountMap;
    @EventSourcedMember
    //TODO: for below account, should delivery price and charge be part of DeliveryCreatedEvent/Command?
    private TotalSubscriptionCostAccount totalSubscriptionCostAccount;
    @EventSourcedMember
    private TotalReceivableCostAccount totalReceivableCostAccount;
    @EventSourcedMember
    private TotalReceivedCostAccount totalReceivedCostAccount;

    private RefundAccount refundAccount;
    private PaymentAccountStatus paymentAccountStatus;

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
            //get total delivery amount.. it has to be latest???
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


    public void createdNewDelivery(CreateDeliveryCommand command,TaggedPricingService taggedPricingService,ProductDetailsService productDetailsService,DuePaymentCorrectionEngine duePaymentCorrectionEngine) {
        List<DeliveryCostAccount> deliveries=deliveryCostAccountMap.values().stream().collect(Collectors.toList());
        DeliveryDetails newDeliveryDetails=new DeliveryDetails(command.getDeliveryId(),command.getSubscriptionId());
        newDeliveryDetails.setDeliveryDate(command.getDeliveryDate());
        List<DeliveryItem> deliveryItems= command.getDeliveryItems();
        List<DeliveredProductDetail> deliveredProducts=new ArrayList<>();
        double totalDeliveryCost = 0;

        for(DeliveryItem deliveryItem: deliveryItems){
            ProductPricingCategory pricingCategory=productDetailsService.findProductByProductId(deliveryItem.getDeliveryItemId()).getProductPricingCategory();
            if(pricingCategory== ProductPricingCategory.DISCOUNT_COMMITMENT){
                double latestMRP= taggedPricingService.findLatestTaggedPriceForAProduct(deliveryItem.getDeliveryItemId());
                totalDeliveryCost += latestMRP*(1-deliveryItem.getOfferedPricePerUnit());
            }else {
                totalDeliveryCost += deliveryItem.getOfferedPricePerUnit();
            }

            DeliveredProductDetail deliveredProduct= new DeliveredProductDetail(deliveryItem.getDeliveryItemId(),deliveryItem.getPriceBucketId());
            deliveredProduct.setDeliveryCharges(deliveryItem.getDeliveryCharges());
            deliveredProduct.setMRPOld(taggedPricingService.findLatestTaggedPriceForAProduct(deliveryItem.getDeliveryItemId()));
            deliveredProduct.setOfferedPricePerUnitOld(deliveryItem.getOfferedPricePerUnit());
            //seperate field for percent in case of percent discount committed product. For other price bucket categories the value in this field will be junk.
            deliveredProduct.setOfferedPriceOrPercent(deliveryItem.getOfferedPricePerUnit());
            deliveredProduct.setProductPricingCategory(productDetailsService.findProductByProductId(deliveryItem.getDeliveryItemId()).getProductPricingCategory());
            deliveredProducts.add(deliveredProduct);
        }
        newDeliveryDetails.setDeliveredProductDetails(deliveredProducts);

        DeliveryCostAccount newDeliveryCostAccount= new DeliveryCostAccount(command.getDeliveryId(), command.getSubscriptionId(), command.getDeliveryDate(),newDeliveryDetails,totalDeliveryCost);
        deliveries.add(newDeliveryCostAccount);
        ModifiedSubscriptionContent modifiedSubscriptionContent=duePaymentCorrectionEngine.correctTotalDues(command.getSubscriptionId(),deliveries);
        apply(new DeliveryInitiatedEvent(command.getSubscriberId(),command.getSubscriptionId(),command.getDeliveryId(),newDeliveryCostAccount));
        apply(new DeliveriesUpdatdWithCorrectedPaymentEvent(subscriberId,modifiedSubscriptionContent,command.getDeliveryDate()));
    }

    public void deleteDelivery(String subscriberId,String subscriptionId,String deliveryId,LocalDate deletionDate,DuePaymentCorrectionEngine duePaymentCorrectionEngine) {
        if(this.deliveryCostAccountMap.containsKey(deliveryId)){
            List<DeliveryCostAccount> deliveries=deliveryCostAccountMap.values().stream().collect(Collectors.toList());
            List<DeliveryCostAccount> deliveriesToBeRemoved=deliveryCostAccountMap.values().stream().filter(dca->dca.getDeliveryId().equals(deliveryId) && dca.getSubscriptionId().equals(subscriptionId)).collect(Collectors.toList());
            deliveries.removeAll(deliveriesToBeRemoved);
            ModifiedSubscriptionContent modifiedSubscriptionContent=duePaymentCorrectionEngine.correctTotalDues(subscriptionId,deliveries);
            apply(new DeliveryDestroyedEvent(subscriberId,subscriptionId,deliveryId,deletionDate));
            apply(new DeliveriesUpdatdWithCorrectedPaymentEvent(subscriberId,modifiedSubscriptionContent,deletionDate));
        }

    }

    @EventSourcingHandler
    public void on(DeliveryInitiatedEvent event) {
        DeliveryCostAccount newDeliveryCostAccount= event.getNewDeliveryCostAccount();
        this.deliveryCostAccountMap.put(newDeliveryCostAccount.getDeliveryId(),newDeliveryCostAccount);
    }

    @EventSourcingHandler
    public void on(DeliveryDestroyedEvent event) {
        if(this.deliveryCostAccountMap.containsKey(event.getDeliveryId())){
            this.deliveryCostAccountMap.remove(event.getDeliveryId());
        }
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
            apply(new CostCalculatedForRegisteredDeliveryEvent(this.subscriberId,this.getSubscriptionId(), delivery.getDeliveryId(),delivery.getDeliveryDate(),deliveryDetails, totalDeliveryCost));
        }

        apply(new SubscriptionDetailsRegisteredEvent(this.subscriberId,this.getSubscriptionId(), totalTentativeSubscriptionAmount, totalRewardPoints, deliveries, SysDate.now()));
    }

    private DeliveryDetails createDeliveryDetails(DeliveryCreatedEvent delivery, TaggedPricingService taggedPricingService, ProductDetailsService productDetailsService) {
        DeliveryDetails deliveryDetail= new DeliveryDetails(delivery.getDeliveryId(),delivery.getSubscriptionId());
        deliveryDetail.setDeliveryDate(delivery.getDeliveryDate());
        List<DeliveryItem> deliveryItems= delivery.getDeliveryItems();
        List<DeliveredProductDetail> deliveredProducts=new ArrayList<>();
        for(DeliveryItem deliveryItem: deliveryItems){
            DeliveredProductDetail deliveredProduct= new DeliveredProductDetail(deliveryItem.getDeliveryItemId(),deliveryItem.getPriceBucketId());
            deliveredProduct.setDeliveryCharges(deliveryItem.getDeliveryCharges());
            deliveredProduct.setMRPOld(taggedPricingService.findLatestTaggedPriceForAProduct(deliveryItem.getDeliveryItemId()));
            deliveredProduct.setOfferedPricePerUnitOld(deliveryItem.getOfferedPricePerUnit());
            //separate field for percent in case of percent discount committed product. For other price bucket categories the value in this field will be junk.
            deliveredProduct.setOfferedPriceOrPercent(deliveryItem.getOfferedPricePerUnit());
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
        DeliveryCostAccount deliveryCostAccount = new DeliveryCostAccount(event.getDeliveryId(), event.getSubscriptionId(), event.getDeliveryDate(), event.getDeliveryDetails(),event.getTotalDeliveryCost());
        this.deliveryCostAccountMap.put(event.getDeliveryId(), deliveryCostAccount);
    }

    @EventSourcingHandler
    public void on(SubscriptionDetailsRegisteredEvent event) {
        this.totalReceivableCostAccount = new TotalReceivableCostAccount(event.getSubscriptionId(), event.getTotalTentativeSubscriptionAmount(), event.getRegistrationDate());
        this.totalReceivedCostAccount = new TotalReceivedCostAccount(event.getSubscriptionId(), 0, event.getRegistrationDate());
        this.totalSubscriptionCostAccount = new TotalSubscriptionCostAccount(event.getSubscriptionId(), event.getTotalTentativeSubscriptionAmount(), event.getRegistrationDate());
    }

    public void correctDues(CorrectDuePaymentCommand command, DuePaymentCorrectionEngine duePaymentCorrectionEngine) {
        String subscriptionId= command.getSubscriptionId();
        ModifiedSubscriptionContent modifiedSubscriptionContent=duePaymentCorrectionEngine.correctTotalDues(subscriptionId,this.deliveryCostAccountMap.values().stream().collect(Collectors.toList()));
        apply(new DeliveriesUpdatdWithCorrectedPaymentEvent(this.subscriberId,modifiedSubscriptionContent,command.getDispatchDate()));
    }

    @EventSourcingHandler
    public void on(DeliveriesUpdatdWithCorrectedPaymentEvent event){
        ModifiedSubscriptionContent modifiedSubscriptionContent=event.getModifiedSubscriptionContent();
        this.totalReceivableCostAccount.setAmount(modifiedSubscriptionContent.getModifiedTotalSubscriptionPayment());
        this.totalReceivableCostAccount.setTransactionDate(event.getChangeDate());

        List<ModifiedDeliveryContent> modifiedDeliveries=modifiedSubscriptionContent.getModifiedDeliveries();
        List<DeliveryCostAccount> deliveryCostAccounts=this.deliveryCostAccountMap.values().stream().collect(Collectors.toList());
        for(ModifiedDeliveryContent modifiedDeliveryContent: modifiedDeliveries){
            DeliveryCostAccount deliveryCostAccount=deliveryCostAccounts.stream().filter(dca->(dca.getDeliveryDetail().getDeliveryId().equals(modifiedDeliveryContent.getDeliveryId()))).collect(Collectors.toList()).get(0);
            deliveryCostAccount.setAmount(modifiedDeliveryContent.getCorrectedTotalPayment());
            List<DeliveredProductDetail> deliverableItems=modifiedDeliveryContent.getItems();
            for(DeliveredProductDetail deliverableItem: deliverableItems){
                List<DeliveredProductDetail> deliverableItemsWithSamePriceBucket=deliveryCostAccount.getDeliveryDetail().getDeliveredProductDetails().stream().filter(dpd->(dpd.getDeliveryItemId().equals(deliverableItem.getDeliveryItemId()))&&(dpd.getPriceBucketId().equals(deliverableItem.getPriceBucketId()))).collect(Collectors.toList());
                deliverableItemsWithSamePriceBucket.stream().forEach(diwspb-> {
                    diwspb.setOfferedPricePerUnitNew(deliverableItem.getOfferedPricePerUnitNew());
                    diwspb.setOfferedPricePerUnitOld(deliverableItem.getOfferedPricePerUnitOld());
                });
            }
        }
    }

}
