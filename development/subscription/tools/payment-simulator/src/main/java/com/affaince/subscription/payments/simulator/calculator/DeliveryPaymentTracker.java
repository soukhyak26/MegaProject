package com.affaince.subscription.payments.simulator.calculator;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Objects;

public class DeliveryPaymentTracker implements  Comparable<DeliveryPaymentTracker>{
    private String deliveryId;
    private int deliverySequence;
    private double paymentExpected;
    private double paymentReceived;

    public DeliveryPaymentTracker(int deliverySequence) {
        this.deliverySequence = deliverySequence;
    }
    public DeliveryPaymentTracker(){}
    public String getDeliveryId() {
        return deliveryId;
    }

    public void setDeliveryId(String deliveryId) {
        this.deliveryId = deliveryId;
    }

    public int getDeliverySequence() {
        return deliverySequence;
    }

    public double getPaymentExpected() {
        return paymentExpected;
    }

    public void setPaymentExpected(double paymentExpected) {
        this.paymentExpected = paymentExpected;
    }

    public double getPaymentReceived() {
        return paymentReceived;
    }

    public void setPaymentReceived(Double paymentReceived) {
        this.paymentReceived = paymentReceived;
    }
    @JsonIgnore
    public void addToPaymentReceived(Double paymentReceived) {
        this.paymentReceived += paymentReceived;
        this.paymentExpected -=paymentReceived;
    }
    @JsonIgnore
    public boolean ifDeliveryPaymentFulfilled(){
        return this.getPaymentExpected() == this.getPaymentReceived();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        DeliveryPaymentTracker that = (DeliveryPaymentTracker) o;
        return deliverySequence == that.deliverySequence &&
                Objects.equals(deliveryId, that.deliveryId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deliveryId, deliverySequence);
    }

    @Override
    public int compareTo(DeliveryPaymentTracker o) {
        return this.getDeliverySequence() - o.getDeliverySequence();
    }
}
