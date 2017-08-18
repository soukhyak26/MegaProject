package com.affaince.subscription.payments.command.domain;

import com.affaince.subscription.command.ItemDispatchStatus;
import com.affaince.subscription.common.type.DeliveryStatus;
import com.affaince.subscription.common.type.ProductPricingCategory;
import com.affaince.subscription.date.SysDate;
import com.affaince.subscription.payments.calculator.PaymentCalculatorChain;
import com.affaince.subscription.payments.command.CreateDeliveryCommand;
import com.affaince.subscription.payments.command.CorrectDuePaymentCommand;
import com.affaince.subscription.payments.command.UpdateDeliveryStatusCommand;
import com.affaince.subscription.payments.command.accounting.*;
import com.affaince.subscription.payments.command.event.*;
import com.affaince.subscription.payments.service.DuePaymentCorrectionEngine;
import com.affaince.subscription.payments.service.ProductDetailsService;
import com.affaince.subscription.payments.service.TaggedPricingService;
import com.affaince.subscription.payments.vo.*;
import com.affaince.subscription.pojos.PaymentExpression;
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
            //separate field for percent in case of percent discount committed product. For other price bucket categories the value in this field will be junk.
            deliveredProduct.setOfferedPriceOrPercent(deliveryItem.getOfferedPricePerUnit());
            deliveredProduct.setProductPricingCategory(productDetailsService.findProductByProductId(deliveryItem.getDeliveryItemId()).getProductPricingCategory());
            deliveredProducts.add(deliveredProduct);
        }
        newDeliveryDetails.setDeliveredProductDetails(deliveredProducts);
        newDeliveryDetails.setTotalDeliveryCost(totalDeliveryCost);
        //If the delivery getting created as a replacement of earlier delivery which got dropped,
        // then find out if there is any amount  already paid for earlier delivery which is now deposited in refund account
        Map<Integer,Double>deliverySequenceWiseMoneyDistribution= assignAmountDepositedToRefundAccountToDeliveries(command.getSequence(),totalDeliveryCost);
        apply(new NewDeliveryRegisteredEvent(this.subscriberId, this.getSubscriptionId(), command.getDeliveryId(), command.getDeliveryDate(),
                command.getSequence(), newDeliveryDetails, newDeliveryDetails.getTotalDeliveryCost(), command.getRewardPoints(),
                (deliverySequenceWiseMoneyDistribution==null)?0:deliverySequenceWiseMoneyDistribution.get(command.getSequence()),command.getDeliveryCreationDate()));
        deliverySequenceWiseMoneyDistribution.remove(command.getSequence());
        deliverySequenceWiseMoneyDistribution.keySet().stream().forEach(ds-> {
            final String deliveryId=findDeliveryCostAccountByDeliverySequence(ds).getDeliveryId();
            apply(new DeliveryCostAccountCreditedEvent(this.subscriberId,this.subscriptionId,deliveryId,ds,deliverySequenceWiseMoneyDistribution.get(ds)));
        });

    }

    //When new delivery is created,it may be first time preemptive creation of delivery from Subscriber
    //or it may be new delivery getting added by subscriber while he/she altered subscription content
    //or it may be modification of existing delivery,where earlier delivery gets dropped and new one gets created.
    //when old delivery gets dropped it deposits money received if any,on account of this delivery to refund account
    //So newly created delivery should take available balance from Refund account and replenish newly created delivery as well as
    //any deliveries after this (if any) which are not yet received their full due amount.
    private Map<Integer, Double> assignAmountDepositedToRefundAccountToDeliveries(int deliverySequence, double totalDeliveryCost) {
        Collection<DeliveryCostAccount> registeredDeliveryCostAccounts = this.deliveryCostAccountMap.values();

        if (this.refundAccount.getAmount() > 0) {
            Map<Integer, Double> deliverySequenceWiseMoneyDistribution = new HashMap<>();
            double availableAmount = refundAccount.getAmount();
            //In case earlier delivery(dropped one) had deposited more money than newly created delivery
            //distribute remaining amount in next deliveries( to the newly created one)
            if (availableAmount > totalDeliveryCost) {
                deliverySequenceWiseMoneyDistribution.put(deliverySequence, totalDeliveryCost);
                availableAmount -= totalDeliveryCost;
                for (DeliveryCostAccount delivery : registeredDeliveryCostAccounts) {
                    if (delivery.getSequence() > deliverySequence) {
                        double totalDeliveryCostForADelivery=delivery.getDeliveryDetail().getTotalDeliveryCost();
                        double totalDeliveryAmountReceivedForADelivery=delivery.getPaymentReceived();
                        double amountStillDue=totalDeliveryCostForADelivery-totalDeliveryAmountReceivedForADelivery;
                        if (amountStillDue>0 && amountStillDue > availableAmount) {
                            deliverySequenceWiseMoneyDistribution.put(delivery.getSequence(), amountStillDue);
                            availableAmount -= amountStillDue;
                        } else {
                            deliverySequenceWiseMoneyDistribution.put(delivery.getSequence(), availableAmount);
                        }
                    }
                }
            } else {
                deliverySequenceWiseMoneyDistribution.put(deliverySequence, availableAmount);
            }
            return deliverySequenceWiseMoneyDistribution;
        } else {
            return null;
        }
    }

    @EventSourcingHandler
    public void on(NewDeliveryRegisteredEvent event) {
        if (this.paymentAccountStatus == PaymentAccountStatus.CREATED) {
            this.paymentAccountStatus = PaymentAccountStatus.ACTIVE;
        }
        DeliveryCostAccount deliveryCostAccount = new DeliveryCostAccount(event.getDeliveryId(), event.getSubscriptionId(), event.getSequence(), event.getDeliveryDate(), event.getDeliveryDetails(), event.getTotalDeliveryCost());
        deliveryCostAccount.creditToPaymentReceived(event.getAmountReceived());
        this.totalReceivableCostAccount.credit(event.getTotalDeliveryCost(), SysDate.now());
        this.totalReceivableCostAccount.addToRewardPoints(event.getRewardPoints());
        this.totalSubscriptionCostAccount.credit(event.getTotalDeliveryCost(), SysDate.now());
        this.deliveryCostAccountMap.put(event.getDeliveryId(), deliveryCostAccount);
        if(event.getAmountReceived()>0) {
            this.totalReceivedCostAccount.credit(event.getAmountReceived(), SysDate.now());
        }
        this.paymentProcessingContext.addNewDeliveryToContext(event.getSequence(),event.getTotalDeliveryCost());
        if(event.getAmountReceived()>0) {
            this.paymentProcessingContext.depositIncomingPaymentToDesignatedInstalmentTracker(event.getSequence(), event.getAmountReceived());
        }
    }

    @EventSourcingHandler
    public void on(DeliveryCostAccountCreditedEvent event){
        DeliveryCostAccount deliveryCostAccount=deliveryCostAccountMap.get(event.getId());
        deliveryCostAccount.creditToPaymentReceived(event.getAmountToCredit());
        this.paymentProcessingContext.depositIncomingPaymentToDesignatedInstalmentTracker(event.getSequence(),event.getAmountToCredit());
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
        int deliverySequenceFulfilledWithPayment = -1;
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
                    deliverySequenceFulfilledWithPayment = deliveryCostAccount.getSequence();
                } else if (paymentReceived <= residualAmountForDelivery) {
                    paymentMadeForDelivery = paymentReceived;
                    if (deliveryCostAccount.getAmount() == paymentMadeForDelivery) {
                        deliverySequenceFulfilledWithPayment = deliveryCostAccount.getSequence();
                    }
                }
                paymentToBeAdjustedAgainstDeliveries.put(deliveryCostAccount.getDeliveryId(), paymentMadeForDelivery);
                paymentReceived -= paymentMadeForDelivery;
            }
        }
        Map<InstalmentPaymentTracker, Double> trackersGettingFulfilled = this.paymentProcessingContext.IdentifyTrackersGettingFulfilledByIncomingPayment(paidAmount);
        apply(new PaymentInitiatedEvent(subscriptionId, paidAmount, paymentToBeAdjustedAgainstDeliveries, trackersGettingFulfilled, deliverySequenceFulfilledWithPayment, paymentDate));
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
        this.paymentProcessingContext.distributeIncomingPaymentAcrossTrackers(event.getPaidAmount(), event.getTrackersGettingFulfilledByIncomingPayment());
        this.paymentProcessingContext.setDeliverySequenceAwaitingPayment(event.getDeliverySequenceFulfilledWithPayment() + 1);
    }


    public void deleteDelivery(String subscriberId, String subscriptionId, String deliveryId, int seqeunce,LocalDate deletionDate, DuePaymentCorrectionEngine duePaymentCorrectionEngine) {
        if (this.deliveryCostAccountMap.containsKey(deliveryId)) {
            List<DeliveryCostAccount> deliveriesToBeRemoved = deliveryCostAccountMap.values().stream().filter(dca -> dca.getDeliveryId().equals(deliveryId) && dca.getSubscriptionId().equals(subscriptionId)).collect(Collectors.toList());
            depositPaidAmountOfDeletedDeliveriesToRefundAccount(deliveriesToBeRemoved);
            DeliveryCostAccount deliveryCostAccount=deliveriesToBeRemoved.get(0);
            apply(new DeliveryDestroyedEvent(subscriberId, subscriptionId, deliveryId,seqeunce, deliveryCostAccount.getAmount(),deliveryCostAccount.getPaymentReceived(),deletionDate));
        }

    }

    private void depositPaidAmountOfDeletedDeliveriesToRefundAccount(List<DeliveryCostAccount> deliveriesToBeRemoved){
        for(DeliveryCostAccount accountBeingRemoved: deliveriesToBeRemoved){
            if(accountBeingRemoved.getPaymentReceived()>0){
                apply(new RefundProcessedForDeletedDeliveriesEvent(accountBeingRemoved.getSubscriptionId(),accountBeingRemoved.getDeliveryId(),accountBeingRemoved.getSequence(),accountBeingRemoved.getPaymentReceived(),SysDate.now()));
            }
        }
    }

    @EventSourcingHandler
    public void on(DeliveryDestroyedEvent event) {
        if (this.deliveryCostAccountMap.containsKey(event.getDeliveryId())) {
            this.deliveryCostAccountMap.remove(event.getDeliveryId());
        }
        this.paymentProcessingContext.deleteDeliverySequenceFromContext(event.getSequence(),event.getTotalDeliveryCost(),event.getPaymentReceived());
    }


    @EventSourcingHandler
    public void on(RefundProcessedForDeletedDeliveriesEvent event){
        this.refundAccount.credit(event.getPaymentReceived(),event.getRefundProcessingDate());

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


    @EventSourcingHandler
    public void on(PaymentAccountCreatedEvent event) {
        this.subscriptionId = event.getSubscriptionId();
        this.subscriberId = event.getSubscriberId();
        this.deliveryCostAccountMap = new HashMap<>();
        this.totalReceivableCostAccount = new TotalReceivableCostAccount(event.getSubscriptionId(), 0, event.getCreationDate());
        this.totalReceivedCostAccount = new TotalReceivedCostAccount(event.getSubscriptionId(), 0, event.getCreationDate());
        this.totalSubscriptionCostAccount = new TotalSubscriptionCostAccount(event.getSubscriptionId(), 0, event.getCreationDate());
        this.refundAccount = new RefundAccount(event.getSubscriptionId(), 0, event.getCreationDate());
        this.paymentAccountStatus = PaymentAccountStatus.CREATED;
    }
    private DeliveryCostAccount findDeliveryCostAccountByDeliverySequence(int deliverySequence){
        return this.deliveryCostAccountMap.values().stream().filter(dca->dca.getSequence()== deliverySequence).collect(Collectors.toList()).get(0);
    }


    // On Delivery ready for dispatch the due amounts should be recalculated to accomodate price variations.
    public void correctDues(CorrectDuePaymentCommand command, DuePaymentCorrectionEngine duePaymentCorrectionEngine) {
        String subscriptionId = command.getSubscriptionId();
        ModifiedSubscriptionContent modifiedSubscriptionContent = duePaymentCorrectionEngine.correctTotalDues(subscriptionId, this.deliveryCostAccountMap.values().stream().collect(Collectors.toList()));
        apply(new DeliveriesUpdatedWithCorrectedPaymentEvent(this.subscriberId,this.getSubscriptionId(), modifiedSubscriptionContent, command.getDueCorrectionDate()));
    }

    //Here all CostAccounts should be updated with latest/revised due amount
    @EventSourcingHandler
    public void on(DeliveriesUpdatedWithCorrectedPaymentEvent event) {
        ModifiedSubscriptionContent modifiedSubscriptionContent = event.getModifiedSubscriptionContent();
        this.totalReceivableCostAccount.setAmount(modifiedSubscriptionContent.getModifiedTotalSubscriptionPayment());
        this.totalSubscriptionCostAccount.setAmount(modifiedSubscriptionContent.getModifiedTotalSubscriptionPayment());
        this.totalSubscriptionCostAccount.setTransactionDate(event.getChangeDate());
        this.paymentProcessingContext.correctDues(modifiedSubscriptionContent);

        List<ModifiedDeliveryContent> modifiedDeliveries = modifiedSubscriptionContent.getModifiedDeliveries();
        List<DeliveryCostAccount> deliveryCostAccounts = this.deliveryCostAccountMap.values().stream().collect(Collectors.toList());
        for (ModifiedDeliveryContent modifiedDeliveryContent : modifiedDeliveries) {
            DeliveryCostAccount deliveryCostAccount = deliveryCostAccounts.get(deliveryCostAccounts.indexOf(modifiedDeliveryContent.getDeliveryId()));
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
        if (event.isValidateForDispatchFlag()) {
            paymentProcessingContext.setLatestCompletedDeliverySequence(findDeliverySequenceFromDeliveryId(event.getDeliveryId()));
        }
    }

    public void updateDeliveryStatus(UpdateDeliveryStatusCommand command, ProductDetailsService productDetailsService, TaggedPricingService taggedPricingService) {
        if (command.getDeliveryStatus() == DeliveryStatus.PARTIAL || command.getDeliveryStatus() == DeliveryStatus.FAILURE) {
            List<ItemDispatchStatus> undeliveredItemsList = command.getItemWiseDispatchStatus().stream().filter(iwds -> iwds.getItemDeliveryStatus() == DeliveryStatus.FAILURE).collect(Collectors.toList());
            processRefundForUndeliveredItems(command.getDeliveryId(), undeliveredItemsList, productDetailsService, taggedPricingService);
        }
        apply(new DeliveryStatusUpdatedToPaymentAccountEvent(command.getSubscriptionId(), command.getDeliveryId(), command.getDeliveryDate(), command.getDeliveryStatus(), command.getItemWiseDispatchStatus(), command.getDeliveryCharges()));
    }

    private int findDeliverySequenceFromDeliveryId(String deliveryId) {
        return deliveryCostAccountMap.values().stream().filter(dca -> dca.getDeliveryId().equals(deliveryId)).collect(Collectors.toList()).get(0).getSequence();
    }

    @EventSourcingHandler
    public void on(DeliveryStatusUpdatedToPaymentAccountEvent event) {
        if (event.getDeliveryStatus() == DeliveryStatus.DELIVERED) {
            DeliveryCostAccount deliveryCostAccount = this.deliveryCostAccountMap.values().stream().filter(dca -> dca.getDeliveryId().equals(event.getDeliveryId())).collect(Collectors.toList()).get(0);
            deliveryCostAccount.getDeliveryDetail().setDeliveryStatus(DeliveryStatus.DELIVERED);

        }
    }

    private void processRefundForUndeliveredItems(String deliveryId, List<ItemDispatchStatus> undeliveredItemsList, ProductDetailsService productDetailsService, TaggedPricingService taggedPricingService) {
        DeliveryCostAccount deliveryCostAccount = this.deliveryCostAccountMap.values().stream().filter(dca -> dca.getDeliveryId().equals(deliveryId)).collect(Collectors.toList()).get(0);
        List<DeliveredProductDetail> deliveredProductsList = deliveryCostAccount.getDeliveryDetail().getDeliveredProductDetails();
        double totalRefundableAmount = 0;
        Map<String, Double> itemWiseRefundAmountDetails = new HashMap<>();
        for (DeliveredProductDetail deliveredProduct : deliveredProductsList) {
            long unDeliveredItemCount = undeliveredItemsList.stream().filter(udi -> udi.getItemId() == deliveredProduct.getDeliveryItemId() && udi.getItemDeliveryStatus() == DeliveryStatus.FAILURE).count();
            if (unDeliveredItemCount > 0) {
                ProductPricingCategory pricingCategory = productDetailsService.findProductByProductId(deliveredProduct.getDeliveryItemId()).getProductPricingCategory();
                double itemPriceTobeRefunded = 0;
                double totalItemsPriceToBeRefunded = 0;
                if (pricingCategory == ProductPricingCategory.DISCOUNT_COMMITMENT) {
                    double latestMRP = taggedPricingService.findLatestTaggedPriceForAProduct(deliveredProduct.getDeliveryItemId());
                    itemPriceTobeRefunded = latestMRP * (1 - deliveredProduct.getOfferedPriceOrPercent());
                } else {
                    itemPriceTobeRefunded = deliveredProduct.getOfferedPricePerUnitNew();
                }
                totalItemsPriceToBeRefunded = itemPriceTobeRefunded * unDeliveredItemCount;
                //check if the same item is already accounted before.
                if (!itemWiseRefundAmountDetails.containsKey(deliveredProduct.getDeliveryItemId())) {
                    itemWiseRefundAmountDetails.put(deliveredProduct.getDeliveryItemId(), totalItemsPriceToBeRefunded);
                    totalRefundableAmount += itemPriceTobeRefunded;
                }
            }
        }
        apply(new RefundProcessedForUndeliveredItemsEvent(subscriptionId, deliveryId, totalRefundableAmount, itemWiseRefundAmountDetails, SysDate.now()));
    }

    @EventSourcingHandler
    public void on(RefundProcessedForUndeliveredItemsEvent event) {
        boolean isDeliveryDueFulfilled = paymentProcessingContext.isDeliveryDueAmountFulfilled(event.getDeliveryId());
        if (!isDeliveryDueFulfilled) {
            this.totalReceivableCostAccount.debit(event.getTotalRefundableAmount(), event.getRefundDate());
        } else {
            this.totalReceivedCostAccount.debit(event.getTotalRefundableAmount(), event.getRefundDate());
            refundAccount.credit(event.getTotalRefundableAmount(), event.getRefundDate());
        }
        paymentProcessingContext.revertAmountForADelivery(event.getDeliveryId(), event.getTotalRefundableAmount());
        paymentProcessingContext.setLatestCompletedDeliverySequence(findDeliverySequenceFromDeliveryId(event.getDeliveryId()));
    }

    public void calculatePaymentInstallment(Map<LocalDate, Double> deliveryWisePriceMap, PaymentExpression paymentExpression, PaymentCalculatorChain paymentCalculatorChain) {
        paymentCalculatorChain.calculate (deliveryWisePriceMap, paymentExpression);
    }
}
