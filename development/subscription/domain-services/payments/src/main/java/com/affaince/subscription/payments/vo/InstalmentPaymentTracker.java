package com.affaince.subscription.payments.vo;

import com.affaince.subscription.common.type.DeliveryStatus;

import java.util.Map;

/**
 * Created by mandar on 7/6/2017.
 */
//deliveries "before" which payment is expected.
//amount of payment expected
//amount of payment fulfilled
public class InstalmentPaymentTracker {
    private String deliveryId;
    private int deliverySequence;
    private Map<Integer, Double> deliverySequencesManagedByATracker;
    private double paymentExpected;
    private double paymentReceived;
    private DeliveryStatus deliveryStatus;

    public InstalmentPaymentTracker(int deliverySequence) {
        this.deliverySequence = deliverySequence;
    }

    public void setDeliveryId(String deliveryId) {
        this.deliveryId = deliveryId;
    }

    public String getDeliveryId() {
        return deliveryId;
    }

    public int getDeliverySequence() {
        return deliverySequence;
    }

    public double getPaymentExpected() {
        return paymentExpected;
    }

    public double getPaymentReceived() {
        return paymentReceived;
    }

    public void addToPaymentExpected(double amount) {
        this.paymentExpected += amount;
    }

    public void deductFromPaymentExpected(double amount) {
        this.paymentExpected -= amount;
    }

    public void addToPaymentReceived(double amount) {
        this.paymentReceived += amount;
        this.paymentExpected -= amount;
    }

    public void setDeliverySequence(int deliverySequence) {
        this.deliverySequence = deliverySequence;
    }

    public Map<Integer, Double> getDeliverySequencesManagedByATracker() {
        return deliverySequencesManagedByATracker;
    }

    public void setDeliverySequencesManagedByATracker(Map<Integer, Double> deliverySequencesManagedByATracker) {
        this.deliverySequencesManagedByATracker = deliverySequencesManagedByATracker;
    }

    public void setPaymentExpected(double paymentExpected) {
        this.paymentExpected = paymentExpected;
    }

    public void setPaymentReceived(double paymentReceived) {
        this.paymentReceived = paymentReceived;
    }

    public DeliveryStatus getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(DeliveryStatus deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public void deductFromPaymentReceived(double amount) {
        this.paymentReceived -= amount;
    }

    public boolean isDeliveryDueAmountFulfilled() {
        return (paymentExpected == paymentReceived);
    }

    public boolean isGivenDeliverySequenceManagedByTracker(int deliverySequence) {
        if (deliverySequencesManagedByATracker.containsKey(deliverySequence)) {
            return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        InstalmentPaymentTracker that = (InstalmentPaymentTracker) o;

        return deliverySequence == that.deliverySequence;

    }

    @Override
    public int hashCode() {
        return (int) deliverySequence;
    }

    public void setTotalDuePaymentToDelivery(int sequence, double totalDeliveryCost) {
        this.deliverySequencesManagedByATracker.put(sequence, totalDeliveryCost);
    }
}
