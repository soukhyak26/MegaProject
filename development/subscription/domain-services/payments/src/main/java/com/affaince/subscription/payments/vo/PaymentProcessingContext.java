package com.affaince.subscription.payments.vo;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by mandar on 7/6/2017.
 */
public class PaymentProcessingContext {
        private String subscriptionId;
        private String schemeId;
        private short totalDeliveryCount;
        private double totalDueAmount;
        private List<DeliverywisePaymentTracker> deliverywisePaymentTrackers;
        private short latestCompletedDeliverySequence;
        private PaymentAccountStatus paymentAccountStatus;

    public PaymentProcessingContext(String subscriptionId, String schemeId) {
        this.subscriptionId = subscriptionId;
        this.schemeId = schemeId;
        this.paymentAccountStatus=PaymentAccountStatus.ACTIVE;
        this.deliverywisePaymentTrackers= new ArrayList<>();
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

    public List<DeliverywisePaymentTracker> getDeliverywisePaymentTracker() {
        return deliverywisePaymentTrackers;
    }

    public short getLatestCompletedDeliverySequence() {
        return latestCompletedDeliverySequence;
    }

    public PaymentAccountStatus getPaymentAccountStatus() {
        return paymentAccountStatus;
    }

    public void setTotalDeliveryCount(short totalDeliveryCount){
        this.totalDeliveryCount= totalDeliveryCount;
    }
    public void addToTotalDeliveryCount(short additionalCount){
        this.totalDeliveryCount +=additionalCount;
    }
    public void deductFromTotalDeliveryCount(short deductibleCount){
        this.totalDeliveryCount -=deductibleCount;
    }
    public void setTotalDueAmount(double totalDueAmount){
        this.totalDueAmount = totalDueAmount;
    }
    public void addToTotalDueAmount(double additionalAmount){
        this.totalDueAmount +=additionalAmount;
    }
    public void deductFromTotalDueAmount(double deductibleAmount){
        this.totalDueAmount -= deductibleAmount;
    }

    public void setLatestCompletedDeliverySequence(short latestCompletedDeliverySequence) {
        this.latestCompletedDeliverySequence = latestCompletedDeliverySequence;
    }

    public void setPaymentAccountStatus(PaymentAccountStatus paymentAccountStatus) {
        this.paymentAccountStatus = paymentAccountStatus;
    }

    public void trackReceivedPayment(short deliverySequence,double incomingPayment){
        DeliverywisePaymentTracker tracketObjForCOmparison=   new DeliverywisePaymentTracker(deliverySequence);

        DeliverywisePaymentTracker deliverywisePaymentTracker=null;
        if(deliverywisePaymentTrackers.contains(tracketObjForCOmparison)) {
            deliverywisePaymentTracker = deliverywisePaymentTrackers.get(deliverywisePaymentTrackers.indexOf(tracketObjForCOmparison));
        }else{
            deliverywisePaymentTracker= new DeliverywisePaymentTracker(deliverySequence);
            deliverywisePaymentTrackers.add(deliverywisePaymentTracker);
        }
        deliverywisePaymentTracker.addToPaymentreceived(incomingPayment);
    }
}
