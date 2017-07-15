package com.affaince.subscription.payments.vo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Created by mandar on 7/6/2017.
 */
public class PaymentProcessingContext {
    private String subscriptionId;
    private String schemeId;
    private int totalDeliveryCount;
    private double totalDueAmount;
    private List<DeliverywisePaymentTracker> deliverywisePaymentTrackers;
    private int latestCompletedDeliverySequence;
    private int deliverySequenceAwaitingPayment;
    private PaymentAccountStatus paymentAccountStatus;

    public PaymentProcessingContext(String subscriptionId, String schemeId) {
        this.subscriptionId = subscriptionId;
        this.schemeId = schemeId;
        this.paymentAccountStatus = PaymentAccountStatus.ACTIVE;
        this.deliverywisePaymentTrackers = new ArrayList<>();
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public String getSchemeId() {
        return schemeId;
    }

    public int getTotalDeliveryCount() {
        return totalDeliveryCount;
    }

    public double getTotalDueAmount() {
        return totalDueAmount;
    }

    public void receiveIncomingPayment(double amount) {
        this.deductFromTotalDueAmount(amount);
        final List<DeliverywisePaymentTracker> unfulfilledTrackers = this.deliverywisePaymentTrackers.stream().filter(dwpt -> dwpt.getPaymentExpected() > dwpt.getPaymentReceived()).collect(Collectors.toList());
        for (DeliverywisePaymentTracker unfulfilledTracker : unfulfilledTrackers) {
            if (amount >= unfulfilledTracker.getPaymentExpected()) {
                unfulfilledTracker.addToPaymentReceived(unfulfilledTracker.getPaymentExpected());
                amount -= unfulfilledTracker.getPaymentExpected();
                this.deliverySequenceAwaitingPayment = unfulfilledTracker.getDeliverySequence() + 1;
            } else {
                unfulfilledTracker.addToPaymentReceived(amount);
                this.deliverySequenceAwaitingPayment = unfulfilledTracker.getDeliverySequence();
            }
        }
    }

    public List<DeliverywisePaymentTracker> getDeliverywisePaymentTrackers() {
        return deliverywisePaymentTrackers;
    }


    public DeliverywisePaymentTracker findPaymentTrackerByDeliverySequence(int sequenceId) {
        return deliverywisePaymentTrackers.stream().filter(dwpt -> dwpt.isGivenDeliverySequenceManagedByTracker(sequenceId)).collect(Collectors.toList()).get(0);
    }

    public void setDeliverySequenceAwaitingPayment(int deliverySequenceAwaitingPayment) {
        this.deliverySequenceAwaitingPayment = deliverySequenceAwaitingPayment;
    }

    public int getLatestCompletedDeliverySequence() {
        return latestCompletedDeliverySequence;
    }

    public PaymentAccountStatus getPaymentAccountStatus() {
        return paymentAccountStatus;
    }

    public void setTotalDeliveryCount(int totalDeliveryCount) {
        this.totalDeliveryCount = totalDeliveryCount;
    }

    public void addToTotalDeliveryCount(int additionalCount) {
        this.totalDeliveryCount += additionalCount;
    }

    public void deductFromTotalDeliveryCount(int deductibleCount) {
        this.totalDeliveryCount -= deductibleCount;
    }

    public void setTotalDueAmount(double totalDueAmount) {
        this.totalDueAmount = totalDueAmount;
    }

    public void addToTotalDueAmount(double additionalAmount) {
        this.totalDueAmount += additionalAmount;
    }

    public void deductFromTotalDueAmount(double deductibleAmount) {
        this.totalDueAmount -= deductibleAmount;
    }

    public void setLatestCompletedDeliverySequence(int latestCompletedDeliverySequence) {
        this.latestCompletedDeliverySequence = latestCompletedDeliverySequence;
    }

    public boolean isDeliveryDueAmountFulfilled(String deliveryId) {
        return getPaymentTrackerForADelivery(deliveryId).isDeliveryDueAmountFulfilled();
    }

    public void revertAmountForADelivery(String deliveryId, double amount) {
        DeliverywisePaymentTracker tracker = getPaymentTrackerForADelivery(deliveryId);
        tracker.deductFromPaymentExpected(amount);
        if (isDeliveryDueAmountFulfilled(deliveryId)) {
            tracker.deductFromPaymentReceived(amount);
        }
    }

    public DeliverywisePaymentTracker getPaymentTrackerForADelivery(String deliveryId) {
        return deliverywisePaymentTrackers.stream().filter(dwpt -> dwpt.getDeliveryId().equals(deliveryId)).collect(Collectors.toList()).get(0);
    }

    public void setPaymentAccountStatus(PaymentAccountStatus paymentAccountStatus) {
        this.paymentAccountStatus = paymentAccountStatus;
    }

    public void setSchemeId(String schemeId) {
        this.schemeId = schemeId;
    }

    //This method should be used to create trackers according to the scheme definition where the deliveries BEFORE which payment is expected.
    public void createTrackersForExpectingPayments(int totalDeliveryCount, Map<Integer, Double> deliverySequenceWisePaymentsExpected) {
        List<Integer> deliverySequencesHandledByATracker = new ArrayList<>();
        for (int i = 0; i < totalDeliveryCount; i++) {

            boolean isCurrentDeliverySequenceAPaymentReceiver = deliverySequenceWisePaymentsExpected.keySet().contains(i);
            if (isCurrentDeliverySequenceAPaymentReceiver) {
                DeliverywisePaymentTracker deliverywisePaymentTracker = new DeliverywisePaymentTracker(i);
                deliverySequencesHandledByATracker.add(i);
                deliverywisePaymentTracker.setDeliverySequencesManagedByATracker(deliverySequencesHandledByATracker);
                deliverywisePaymentTracker.setPaymentExpected(deliverySequenceWisePaymentsExpected.get(i));
                deliverywisePaymentTrackers.add(deliverywisePaymentTracker);
                deliverySequencesHandledByATracker = new ArrayList<>();
            } else {
                deliverySequencesHandledByATracker.add(i);
            }
        }
    }

    public void correctDues(ModifiedSubscriptionContent modifiedSubscriptionContent) {
        List<ModifiedDeliveryContent> modifiedDeliveries = modifiedSubscriptionContent.getModifiedDeliveries();
        double revisedInstalment = 0;
        for (ModifiedDeliveryContent modifiedDeliveryContent : modifiedDeliveries) {
            revisedInstalment += modifiedDeliveryContent.getCorrectedRemainingDuePayment();
            DeliverywisePaymentTracker tracker = findPaymentTrackerByDeliverySequence(modifiedDeliveryContent.getSequence());
            if (modifiedDeliveryContent.getSequence() == tracker.getDeliverySequence()) {
                tracker.setPaymentExpected(revisedInstalment);
                revisedInstalment = 0;
            }
        }
    }
    private DeliverywisePaymentTracker findTrackerEarlierTo(DeliverywisePaymentTracker tracker){
        return deliverywisePaymentTrackers.get(deliverywisePaymentTrackers.indexOf(tracker)-1);
    }
    public boolean validateIfDeliveryCanBeDispatched(int sequence) {
        DeliverywisePaymentTracker tracker=findPaymentTrackerByDeliverySequence(sequence);
        if(sequence== tracker.getDeliverySequence()){
            return tracker.getPaymentExpected()== tracker.getPaymentReceived();
        }else {
            DeliverywisePaymentTracker earlierTracker=findTrackerEarlierTo(tracker);
            return earlierTracker.getPaymentExpected()==earlierTracker.getPaymentReceived();
        }
    }
}
