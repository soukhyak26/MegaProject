package com.affaince.subscription.payments.vo;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by mandar on 7/6/2017.
 */
public class PaymentProcessingContext {
    private String subscriptionId;
    private String schemeId;
    private short totalDeliveryCount;
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

    public short getTotalDeliveryCount() {
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
        ;
    }

    public List<DeliverywisePaymentTracker> getDeliverywisePaymentTrackers() {
        return deliverywisePaymentTrackers;
    }

    public DeliverywisePaymentTracker findPaymentTrackerByDeliverySequence(int sequenceId) {
        return deliverywisePaymentTrackers.stream().filter(dwpt -> dwpt.getDeliverySequence() == sequenceId).collect(Collectors.toList()).get(0);
    }

    public int getLatestCompletedDeliverySequence() {
        return latestCompletedDeliverySequence;
    }

    public PaymentAccountStatus getPaymentAccountStatus() {
        return paymentAccountStatus;
    }

    public void setTotalDeliveryCount(short totalDeliveryCount) {
        this.totalDeliveryCount = totalDeliveryCount;
    }

    public void addToTotalDeliveryCount(short additionalCount) {
        this.totalDeliveryCount += additionalCount;
    }

    public void deductFromTotalDeliveryCount(short deductibleCount) {
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

    public void setLatestCompletedDeliverySequence(short latestCompletedDeliverySequence) {
        this.latestCompletedDeliverySequence = latestCompletedDeliverySequence;
    }

    public void setPaymentAccountStatus(PaymentAccountStatus paymentAccountStatus) {
        this.paymentAccountStatus = paymentAccountStatus;
    }

    public void setSchemeId(String schemeId) {
        this.schemeId = schemeId;
    }

    //This method should be used to create trackers accordin to the scheme definition where the deliveries BEFORe which payment is expected.
    public void createTrackersForExpectingPayments(int deliverySequence) {
        DeliverywisePaymentTracker tracketObjForComparison = new DeliverywisePaymentTracker(deliverySequence);

        DeliverywisePaymentTracker deliverywisePaymentTracker = null;
        if (deliverywisePaymentTrackers.contains(tracketObjForComparison)) {
            deliverywisePaymentTracker = deliverywisePaymentTrackers.get(deliverywisePaymentTrackers.indexOf(tracketObjForComparison));
        } else {
            deliverywisePaymentTracker = new DeliverywisePaymentTracker(deliverySequence);
            deliverywisePaymentTrackers.add(deliverywisePaymentTracker);
        }
    }

    public void correctDues(ModifiedSubscriptionContent modifiedSubscriptionContent) {

    }

    public boolean validateIfDeliveryCanBeDispatched(String deliveryId, int sequence) {
        DeliverywisePaymentTracker tracker = deliverywisePaymentTrackers.stream().filter(dwpt -> dwpt.getDeliverySequence() == sequence).collect(Collectors.toList()).get(0);
        return (tracker.getPaymentExpected() <= tracker.getPaymentReceived());
    }
}
