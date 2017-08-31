package com.affaince.subscription.payments.vo;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by mandar on 7/6/2017.
 */
public class PaymentProcessingContext {
    private String subscriptionId;
    private String schemeId;
    private int totalDeliveryCount;
    private double totalDueAmount;
    private List<InstalmentPaymentTracker> instalmentPaymentTrackers;
    private int latestCompletedDeliverySequence;
    private int deliverySequenceAwaitingPayment;
    private PaymentAccountStatus paymentAccountStatus;

    public PaymentProcessingContext(String subscriptionId, String schemeId) {
        this.subscriptionId = subscriptionId;
        this.schemeId = schemeId;
        this.paymentAccountStatus = PaymentAccountStatus.ACTIVE;
        this.instalmentPaymentTrackers = new ArrayList<>();
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

    public void distributeIncomingPaymentAcrossTrackers(double totalIncomingAmount, Map<InstalmentPaymentTracker, Double> trackersWithPayments) {
        this.deductFromTotalDueAmount(totalIncomingAmount);
        Iterator<InstalmentPaymentTracker> trackersIterator = trackersWithPayments.keySet().iterator();
        while (trackersIterator.hasNext()) {
            InstalmentPaymentTracker tracker = trackersIterator.next();
            tracker.addToPaymentReceived(trackersWithPayments.get(tracker));
        }
    }

    public Map<InstalmentPaymentTracker, Double> IdentifyTrackersGettingFulfilledByIncomingPayment(double amount) {
        this.deductFromTotalDueAmount(amount);
        final List<InstalmentPaymentTracker> unfulfilledTrackers = this.instalmentPaymentTrackers.stream().filter(dwpt -> dwpt.getPaymentExpected() > dwpt.getPaymentReceived()).collect(Collectors.toList());
        Map<InstalmentPaymentTracker, Double> trackersGettingFulfilled = new HashMap<>();
        for (InstalmentPaymentTracker unfulfilledTracker : unfulfilledTrackers) {
            if (amount >= unfulfilledTracker.getPaymentExpected()) {
                //unfulfilledTracker.addToPaymentReceived(unfulfilledTracker.getPaymentExpected());
                trackersGettingFulfilled.put(unfulfilledTracker, unfulfilledTracker.getPaymentExpected());
                amount -= unfulfilledTracker.getPaymentExpected();
                //this.deliverySequenceAwaitingPayment = unfulfilledTracker.getDeliverySequence() + 1;
            } else {
                trackersGettingFulfilled.put(unfulfilledTracker, amount);
                //amount should be zero here
                //unfulfilledTracker.addToPaymentReceived(amount);
                //this.deliverySequenceAwaitingPayment = unfulfilledTracker.getDeliverySequence();
            }
        }
        return trackersGettingFulfilled;
    }

    public List<InstalmentPaymentTracker> getInstalmentPaymentTrackers() {
        return instalmentPaymentTrackers;
    }

    public void setInstalmentPaymentTrackers(List<InstalmentPaymentTracker> instalmentPaymentTrackers) {
        this.instalmentPaymentTrackers = instalmentPaymentTrackers;
    }

    public InstalmentPaymentTracker findPaymentTrackerByDeliverySequence(int sequenceId) {
        return instalmentPaymentTrackers.stream().filter(dwpt -> dwpt.isGivenDeliverySequenceManagedByTracker(sequenceId)).collect(Collectors.toList()).get(0);
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
        InstalmentPaymentTracker tracker = getPaymentTrackerForADelivery(deliveryId);
        tracker.deductFromPaymentExpected(amount);
        if (isDeliveryDueAmountFulfilled(deliveryId)) {
            tracker.deductFromPaymentReceived(amount);
        }
    }

    public InstalmentPaymentTracker getPaymentTrackerForADelivery(String deliveryId) {
        return instalmentPaymentTrackers.stream().filter(dwpt -> dwpt.getDeliveryId().equals(deliveryId)).collect(Collectors.toList()).get(0);
    }

    public void setPaymentAccountStatus(PaymentAccountStatus paymentAccountStatus) {
        this.paymentAccountStatus = paymentAccountStatus;
    }

    public void setSchemeId(String schemeId) {
        this.schemeId = schemeId;
    }

    //This method should be used to create trackers according to the scheme definition where the deliveries BEFORE which payment is expected.
    public void createTrackersForExpectingPayments(int totalDeliveryCount, Map<Integer, Double> milestoneWisePaymentsExpected) {
        List<Integer> deliverySequencesHandledByATracker = new ArrayList<>();
        for (int i = 0; i < totalDeliveryCount; i++) {
            deliverySequencesHandledByATracker.add(i);
            boolean isCurrentDeliverySequenceAPaymentReceiver = milestoneWisePaymentsExpected.keySet().contains(i);
            if (isCurrentDeliverySequenceAPaymentReceiver) {
                InstalmentPaymentTracker instalmentPaymentTracker = new InstalmentPaymentTracker(i);
                //deliverySequencesHandledByATracker.add(i);
                instalmentPaymentTracker.setDeliverySequencesManagedByATracker(deliverySequencesHandledByATracker);
                instalmentPaymentTracker.setPaymentExpected(milestoneWisePaymentsExpected.get(i));
                instalmentPaymentTrackers.add(instalmentPaymentTracker);
                deliverySequencesHandledByATracker = new ArrayList<>();
            }
        }
    }

    public void addNewDeliveryToContext(int deliverySequence,double paymentExpected){
        this.addToTotalDeliveryCount(1);
        this.addToTotalDueAmount(paymentExpected);
        InstalmentPaymentTracker tracker=findPaymentTrackerByDeliverySequence(deliverySequence);
        tracker.addToDeliverySequencesManagedByTracker(deliverySequence);
        tracker.addToPaymentExpected(paymentExpected);
    }
    public void deleteDeliverySequenceFromContext(int deliverySequence,double paymentExpected,double paymentReceived){
        this.deductFromTotalDeliveryCount(1);
        this.deductFromTotalDueAmount(paymentExpected);
        InstalmentPaymentTracker tracker=findPaymentTrackerByDeliverySequence(deliverySequence);
        //TODO: check if payment should be deducted from both??
        tracker.deductFromPaymentReceived(paymentReceived);
        tracker.deductFromPaymentExpected(paymentExpected);
        tracker.removeFromDeliverySequencesManagedByTracker(deliverySequence);
    }
    public void correctDues(ModifiedSubscriptionContent modifiedSubscriptionContent) {
        List<ModifiedDeliveryContent> modifiedDeliveries = modifiedSubscriptionContent.getModifiedDeliveries();
        double revisedInstalment = 0;
        for (ModifiedDeliveryContent modifiedDeliveryContent : modifiedDeliveries) {
            revisedInstalment += modifiedDeliveryContent.getCorrectedRemainingDuePayment();
            InstalmentPaymentTracker tracker = findPaymentTrackerByDeliverySequence(modifiedDeliveryContent.getSequence());
            if (modifiedDeliveryContent.getSequence() == tracker.getDeliverySequence()) {
                tracker.setPaymentExpected(revisedInstalment);
                revisedInstalment = 0;
            }
        }
    }

    private InstalmentPaymentTracker findTrackerEarlierTo(InstalmentPaymentTracker tracker) {
        return instalmentPaymentTrackers.get(instalmentPaymentTrackers.indexOf(tracker) - 1);
    }

    public boolean validateIfDeliveryCanBeDispatched(int sequence) {
        InstalmentPaymentTracker tracker = findPaymentTrackerByDeliverySequence(sequence);
        if (sequence == tracker.getDeliverySequence()) {
            return tracker.getPaymentExpected() == tracker.getPaymentReceived();
        } else {
            InstalmentPaymentTracker earlierTracker = findTrackerEarlierTo(tracker);
            return earlierTracker.getPaymentExpected() == earlierTracker.getPaymentReceived();
        }
    }

    public void processDeliveryDeletion(int sequence) {

    }

    public void processDeliveryInsertion(int sequence) {

    }

    //when amount in refund account is brought back on creation of new delivery, it gets distributed across trackers
    public void depositIncomingPaymentToDesignatedInstalmentTracker(int deliverySequence,double amount) {
            InstalmentPaymentTracker tracker= findPaymentTrackerByDeliverySequence(deliverySequence);
            tracker.addToPaymentReceived(amount);
    }
}
