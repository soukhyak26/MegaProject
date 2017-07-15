package com.affaince.subscription.payments.command.domain;

import com.affaince.subscription.command.ItemDispatchStatus;
import com.affaince.subscription.common.type.DeliveryStatus;
import com.affaince.subscription.common.type.ProductPricingCategory;
import com.affaince.subscription.date.SysDate;
import com.affaince.subscription.payments.command.CreateDeliveryCommand;
import com.affaince.subscription.payments.command.CorrectDuePaymentCommand;
import com.affaince.subscription.payments.command.UpdateDeliveryStatusCommand;
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

    private PaymentProcessingContext paymentProcessingContext;

    /**
     * When subscription cost increases, receivable increases
     * When balance increases, receivable decreases
     * At the end, everything balance and receivable should be 0
     */


    public PaymentAccount() {
    }


    //PaymentAccount should get created when subscription is created in subscriber domain
    public PaymentAccount(String subscriberId, String subscriptionId, LocalDate creationDate) {
        apply(new PaymentAccountCreatedEvent(subscriberId, subscriptionId, creationDate));
    }

    public void addPayment(String subscriptionId, double paidAmount, LocalDate paymentDate) {
        //find only those cost accounts corresponding to deliveries which are not dispatched yet.
        List<DeliveryCostAccount> undeliveredDeliveryCostAccounts = this.deliveryCostAccountMap.values()
                .stream()
                .filter(dca -> dca.getDeliveryDetail().getDeliveryStatus() != DeliveryStatus.DELIVERED)
                .collect(Collectors.toList());
        //order them by date
        Collections.sort(undeliveredDeliveryCostAccounts, new Comparator<DeliveryCostAccount>() {
            @Override
            public int compare(DeliveryCostAccount account1, DeliveryCostAccount account2) {
                return account1.getTransactionDate().compareTo(account2.getTransactionDate());
            }
        });
        Map<String, Double> paymentToBeAdjustedAgainstDeliveries = new HashMap<>();

        //this is total payment received
        double paymentReceived = paidAmount;
        int deliverySequenceFulfilledWithPayment=-1;
        for (DeliveryCostAccount deliveryCostAccount : undeliveredDeliveryCostAccounts) {
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
                    deliverySequenceFulfilledWithPayment=deliveryCostAccount.getSequence();
                } else if (paymentReceived <= residualAmountForDelivery) {
                    paymentMadeForDelivery = paymentReceived;
                    if(deliveryCostAccount.getAmount()==paymentMadeForDelivery){
                        deliverySequenceFulfilledWithPayment=deliveryCostAccount.getSequence();
                    }
                }
                paymentToBeAdjustedAgainstDeliveries.put(deliveryCostAccount.getDeliveryId(), paymentMadeForDelivery);
                paymentReceived -= paymentMadeForDelivery;
            }
        }
        Map<InstalmentPaymentTracker,Double> trackersGettingFulfilled=this.paymentProcessingContext.IdentifyTrackersGettingFulfilledByIncomingPayment(paidAmount);
        apply(new PaymentInitiatedEvent(subscriptionId, paidAmount, paymentToBeAdjustedAgainstDeliveries,trackersGettingFulfilled,deliverySequenceFulfilledWithPayment, paymentDate));
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

        //payment expecting trackers are updated with incoming payment.
        this.paymentProcessingContext.addIncomingPaymentToTrackers(event.getPaidAmount(),event.getTrackersGettingFulfilledByIncomingPayment());
        this.paymentProcessingContext.setDeliverySequenceAwaitingPayment(event.getDeliverySequenceFulfilledWithPayment()+1);
    }

    //each delivery getting created in Subscriber domain will fill the amount data needed for all Accounts inside PaymentAccount
    public void createdNewDelivery(CreateDeliveryCommand command, TaggedPricingService taggedPricingService, ProductDetailsService productDetailsService) {
        DeliveryDetails newDeliveryDetails = new DeliveryDetails(command.getDeliveryId(), command.getSubscriptionId());
        newDeliveryDetails.setDeliveryDate(command.getDeliveryDate());
        List<DeliveryItem> deliveryItems = command.getDeliveryItems();
        List<DeliveredProductDetail> deliveredProducts = new ArrayList<>();
        double totalDeliveryCost = 0;

        for (DeliveryItem deliveryItem : deliveryItems) {
            ProductPricingCategory pricingCategory = productDetailsService.findProductByProductId(deliveryItem.getDeliveryItemId()).getProductPricingCategory();
            double latestMRP = taggedPricingService.findLatestTaggedPriceForAProduct(deliveryItem.getDeliveryItemId());
            if (pricingCategory == ProductPricingCategory.DISCOUNT_COMMITMENT) {
                //offeredPricePerUnit will actually bring discount percent as floating point value
                totalDeliveryCost += latestMRP * (1 - deliveryItem.getOfferedPricePerUnit());
            } else {
                totalDeliveryCost += deliveryItem.getOfferedPricePerUnit();
            }

            DeliveredProductDetail deliveredProduct = new DeliveredProductDetail(deliveryItem.getDeliveryItemId(), deliveryItem.getPriceBucketId());
            deliveredProduct.setDeliveryCharges(deliveryItem.getDeliveryCharges());
            deliveredProduct.setMRPOld(latestMRP);
            deliveredProduct.setMRPNew(latestMRP);
            deliveredProduct.setOfferedPricePerUnitOld(deliveryItem.getOfferedPricePerUnit());
            deliveredProduct.setOfferedPricePerUnitNew(deliveryItem.getOfferedPricePerUnit());
            //seperate field for percent in case of percent discount committed product. For other price bucket categories the value in this field will be junk.
            deliveredProduct.setOfferedPriceOrPercent(deliveryItem.getOfferedPricePerUnit());
            deliveredProduct.setProductPricingCategory(productDetailsService.findProductByProductId(deliveryItem.getDeliveryItemId()).getProductPricingCategory());
            deliveredProducts.add(deliveredProduct);
        }
        newDeliveryDetails.setDeliveredProductDetails(deliveredProducts);
        newDeliveryDetails.setTotalDeliveryCost(totalDeliveryCost);
        apply(new CostCalculatedForRegisteredDeliveryEvent(this.subscriberId, this.getSubscriptionId(), command.getDeliveryId(), command.getDeliveryDate(), command.getSequence(), newDeliveryDetails, newDeliveryDetails.getTotalDeliveryCost(), command.getRewardPoints()));

    }

    public void deleteDelivery(String subscriberId, String subscriptionId, String deliveryId, LocalDate deletionDate, DuePaymentCorrectionEngine duePaymentCorrectionEngine) {
        if (this.deliveryCostAccountMap.containsKey(deliveryId)) {
            List<DeliveryCostAccount> deliveries = deliveryCostAccountMap.values().stream().collect(Collectors.toList());
            List<DeliveryCostAccount> deliveriesToBeRemoved = deliveryCostAccountMap.values().stream().filter(dca -> dca.getDeliveryId().equals(deliveryId) && dca.getSubscriptionId().equals(subscriptionId)).collect(Collectors.toList());
            deliveries.removeAll(deliveriesToBeRemoved);
            ModifiedSubscriptionContent modifiedSubscriptionContent = duePaymentCorrectionEngine.correctTotalDues(subscriptionId, deliveries);
            apply(new DeliveryDestroyedEvent(subscriberId, subscriptionId, deliveryId, deletionDate));
            apply(new DeliveriesUpdatedWithCorrectedPaymentEvent(subscriberId, modifiedSubscriptionContent, deletionDate));
        }

    }

    @EventSourcingHandler
    public void on(DeliveryInitiatedEvent event) {
        DeliveryCostAccount newDeliveryCostAccount = event.getNewDeliveryCostAccount();
        this.deliveryCostAccountMap.put(newDeliveryCostAccount.getDeliveryId(), newDeliveryCostAccount);
    }

    @EventSourcingHandler
    public void on(DeliveryDestroyedEvent event) {
        if (this.deliveryCostAccountMap.containsKey(event.getDeliveryId())) {
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

/*
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
*/

/*
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
*/

    @EventSourcingHandler
    public void on(PaymentAccountCreatedEvent event) {
        this.subscriptionId = event.getSubscriptionId();
        this.subscriberId = event.getSubscriberId();
        this.deliveryCostAccountMap = new HashMap<>();
        this.totalReceivableCostAccount = new TotalReceivableCostAccount(event.getSubscriptionId(), 0, event.getCreationDate());
        this.totalReceivedCostAccount = new TotalReceivedCostAccount(event.getSubscriptionId(), 0, event.getCreationDate());
        this.totalSubscriptionCostAccount = new TotalSubscriptionCostAccount(event.getSubscriptionId(), 0, event.getCreationDate());
        this.refundAccount= new RefundAccount(event.getSubscriptionId(),0,event.getCreationDate());
        this.paymentAccountStatus = PaymentAccountStatus.CREATED;
    }

    @EventSourcingHandler
    public void on(CostCalculatedForRegisteredDeliveryEvent event) {
        if (this.paymentAccountStatus == PaymentAccountStatus.CREATED) {
            this.paymentAccountStatus = PaymentAccountStatus.ACTIVE;
        }
        DeliveryCostAccount deliveryCostAccount = new DeliveryCostAccount(event.getDeliveryId(), event.getSubscriptionId(), event.getSequence(), event.getDeliveryDate(), event.getDeliveryDetails(), event.getTotalDeliveryCost());
        this.totalReceivableCostAccount.credit(event.getTotalDeliveryCost(), SysDate.now());
        this.totalReceivableCostAccount.addToRewardPoints(event.getRewardPoints());
        this.totalSubscriptionCostAccount.credit(event.getTotalDeliveryCost(), SysDate.now());
        this.deliveryCostAccountMap.put(event.getDeliveryId(), deliveryCostAccount);
        this.paymentProcessingContext.addToTotalDueAmount(event.getTotalDeliveryCost());
        this.paymentProcessingContext.addToTotalDeliveryCount(1);
    }

    // On Delivery ready for dispatch the due amounts should be recalculated to accomodate price variations.
    public void correctDues(CorrectDuePaymentCommand command, DuePaymentCorrectionEngine duePaymentCorrectionEngine) {
        String subscriptionId = command.getSubscriptionId();
        ModifiedSubscriptionContent modifiedSubscriptionContent = duePaymentCorrectionEngine.correctTotalDues(subscriptionId, this.deliveryCostAccountMap.values().stream().collect(Collectors.toList()));
        apply(new DeliveriesUpdatedWithCorrectedPaymentEvent(this.subscriberId, modifiedSubscriptionContent, command.getDispatchDate()));
    }

    //Here all CostAccounts should be updated with latest/revised due amount
    @EventSourcingHandler
    public void on(DeliveriesUpdatedWithCorrectedPaymentEvent event) {
        ModifiedSubscriptionContent modifiedSubscriptionContent = event.getModifiedSubscriptionContent();
        this.totalReceivableCostAccount.setAmount(modifiedSubscriptionContent.getModifiedTotalSubscriptionPayment());
        this.totalSubscriptionCostAccount.setTransactionDate(event.getChangeDate());
        this.paymentProcessingContext.correctDues(modifiedSubscriptionContent);

        List<ModifiedDeliveryContent> modifiedDeliveries = modifiedSubscriptionContent.getModifiedDeliveries();
        List<DeliveryCostAccount> deliveryCostAccounts = this.deliveryCostAccountMap.values().stream().collect(Collectors.toList());
        for (ModifiedDeliveryContent modifiedDeliveryContent : modifiedDeliveries) {
            DeliveryCostAccount deliveryCostAccount = deliveryCostAccounts.stream().filter(dca -> (dca.getDeliveryDetail().getDeliveryId().equals(modifiedDeliveryContent.getDeliveryId()))).collect(Collectors.toList()).get(0);
            deliveryCostAccount.setAmount(modifiedDeliveryContent.getCorrectedTotalPayment());

            List<DeliveredProductDetail> deliverableItems = modifiedDeliveryContent.getItems();
            for (DeliveredProductDetail deliverableItem : deliverableItems) {
                //Since each item is placed individually in a delivery,we need to colelct them together so as to correct their due amounts
                List<DeliveredProductDetail> deliverableItemsWithSamePriceBucket = deliveryCostAccount.getDeliveryDetail().getDeliveredProductDetails().stream().filter(dpd -> (dpd.getDeliveryItemId().equals(deliverableItem.getDeliveryItemId())) && (dpd.getPriceBucketId().equals(deliverableItem.getPriceBucketId()))).collect(Collectors.toList());
                deliverableItemsWithSamePriceBucket.stream().forEach(diwspb -> {
                    diwspb.setOfferedPricePerUnitNew(deliverableItem.getOfferedPricePerUnitNew());
                    diwspb.setOfferedPricePerUnitOld(deliverableItem.getOfferedPricePerUnitOld());
                });
            }
        }
    }

    public void setPaymentScheme(String paymentSchemeId) {
        apply(new PaymentSchemeSetForPaymentEvent(this.subscriptionId, paymentSchemeId));
    }

    @EventSourcingHandler
    public void on(PaymentSchemeSetForPaymentEvent event) {
        this.schemeId = event.getPaymentSchemeId();
        //create Payment Processing Context to track payments against  selected payment scheme
        this.paymentProcessingContext = new PaymentProcessingContext(event.getSubscriptionId(), event.getPaymentSchemeId());
    }

    public void validateAndApproveDelivery(String deliveryId, int sequence) {
        boolean validateForDispatchFlag = paymentProcessingContext.validateIfDeliveryCanBeDispatched(sequence);

        apply(new DeliveryDispatchApprovalSentEvent(this.subscriberId, this.subscriptionId, deliveryId, validateForDispatchFlag));
    }

    @EventSourcingHandler
    public void on(DeliveryDispatchApprovalSentEvent event) {
        DeliveryCostAccount deliveryCostAccount = this.deliveryCostAccountMap.get(event.getDeliveryId());
        deliveryCostAccount.getDeliveryDetail().setDeliveryStatus(event.isValidateForDispatchFlag() ? DeliveryStatus.DELIVERED : DeliveryStatus.HALTED);
        if(event.isValidateForDispatchFlag()){
            paymentProcessingContext.setLatestCompletedDeliverySequence(findDeliverySequenceFromDeliveryId(event.getDeliveryId()));
        }
    }

    public void updateDeliveryStatus(UpdateDeliveryStatusCommand command,ProductDetailsService productDetailsService,TaggedPricingService taggedPricingService) {
        if(command.getDeliveryStatus()==DeliveryStatus.PARTIAL ||command.getDeliveryStatus()==DeliveryStatus.FAILURE ){
            List<ItemDispatchStatus> undeliveredItemsList=command.getItemWiseDispatchStatus().stream().filter(iwds->iwds.getItemDeliveryStatus()==DeliveryStatus.FAILURE).collect(Collectors.toList());
            processRefundForUndeliveredItems(command.getDeliveryId(),undeliveredItemsList,productDetailsService,taggedPricingService);
        }
        apply(new DeliveryStatusUpdatedToPaymentAccountEvent(command.getSubscriptionId(),command.getDeliveryId(),command.getDeliveryDate(),command.getDeliveryStatus(),command.getItemWiseDispatchStatus(),command.getDeliveryCharges()));
    }

    private int findDeliverySequenceFromDeliveryId(String deliveryId){
        return deliveryCostAccountMap.values().stream().filter(dca->dca.getDeliveryId().equals(deliveryId)).collect(Collectors.toList()).get(0).getSequence();
    }
    @EventSourcingHandler
    public void on(DeliveryStatusUpdatedToPaymentAccountEvent event){
        if(event.getDeliveryStatus()==DeliveryStatus.DELIVERED){
            DeliveryCostAccount deliveryCostAccount=this.deliveryCostAccountMap.values().stream().filter(dca->dca.getDeliveryId().equals(event.getDeliveryId())).collect(Collectors.toList()).get(0);
            deliveryCostAccount.getDeliveryDetail().setDeliveryStatus(DeliveryStatus.DELIVERED);

        }
    }

    private void processRefundForUndeliveredItems(String deliveryId,List<ItemDispatchStatus> undeliveredItemsList,ProductDetailsService productDetailsService,TaggedPricingService taggedPricingService) {
        DeliveryCostAccount deliveryCostAccount=this.deliveryCostAccountMap.values().stream().filter(dca->dca.getDeliveryId().equals(deliveryId)).collect(Collectors.toList()).get(0);
        List<DeliveredProductDetail> deliveredProductsList=deliveryCostAccount.getDeliveryDetail().getDeliveredProductDetails();
        double totalRefundableAmount=0;
        Map<String,Double> itemWiseRefundAmountDetails= new HashMap<>();
        for(DeliveredProductDetail deliveredProduct: deliveredProductsList){
            long unDeliveredItemCount=undeliveredItemsList.stream().filter(udi->udi.getItemId()==deliveredProduct.getDeliveryItemId() && udi.getItemDeliveryStatus()== DeliveryStatus.FAILURE).count();
            if(unDeliveredItemCount>0){
                ProductPricingCategory pricingCategory=productDetailsService.findProductByProductId(deliveredProduct.getDeliveryItemId()).getProductPricingCategory();
                double itemPriceTobeRefunded=0;
                double totalItemsPriceToBeRefunded=0;
                if(pricingCategory== ProductPricingCategory.DISCOUNT_COMMITMENT){
                    double latestMRP= taggedPricingService.findLatestTaggedPriceForAProduct(deliveredProduct.getDeliveryItemId());
                    itemPriceTobeRefunded= latestMRP*(1-deliveredProduct.getOfferedPriceOrPercent());
                }else {
                    itemPriceTobeRefunded=deliveredProduct.getOfferedPricePerUnitNew();
                }
                totalItemsPriceToBeRefunded = itemPriceTobeRefunded*unDeliveredItemCount;
                //check if the same item is already accounted before.
                if(!itemWiseRefundAmountDetails.containsKey(deliveredProduct.getDeliveryItemId())){
                    itemWiseRefundAmountDetails.put(deliveredProduct.getDeliveryItemId(),totalItemsPriceToBeRefunded);
                    totalRefundableAmount +=itemPriceTobeRefunded;
                }
            }
        }
        apply(new RefundProcessedForUndeliveredItemsEvent(subscriptionId,deliveryId,totalRefundableAmount,itemWiseRefundAmountDetails,SysDate.now()));
    }

    @EventSourcingHandler
    public void on(RefundProcessedForUndeliveredItemsEvent event){
        boolean isDeliveryDueFulfilled=paymentProcessingContext.isDeliveryDueAmountFulfilled(event.getDeliveryId());
        if(!isDeliveryDueFulfilled) {
            this.totalReceivableCostAccount.debit(event.getTotalRefundableAmount(), event.getRefundDate());
        }else {
            this.totalReceivedCostAccount.debit(event.getTotalRefundableAmount(), event.getRefundDate());
            refundAccount.credit(event.getTotalRefundableAmount(),event.getRefundDate());
        }
        paymentProcessingContext.revertAmountForADelivery(event.getDeliveryId(),event.getTotalRefundableAmount());
        paymentProcessingContext.setLatestCompletedDeliverySequence(findDeliverySequenceFromDeliveryId(event.getDeliveryId()));
    }
}
