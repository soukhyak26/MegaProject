package com.affaince.subscription.payments.vo;

import com.affaince.subscription.common.type.DeliveryStatus;

/**
 * Created by mandar on 7/6/2017.
 */
//deliveries before which payment is expected.
//amount of payment expected
//amount of payment fulfilled
public class DeliverywisePaymentTracker {
    private String deliveryId;
    private int deliverySequence;
    private double paymentExpected;
    private double paymentReceived;
    private DeliveryStatus deliveryStatus;

    public DeliverywisePaymentTracker(int deliverySequence) {
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

    public void addToPaymentExpected(double amount){
        this.paymentExpected +=amount;
    }

    public void deductFromPaymentExpected(double amount){
        this.paymentExpected -=amount;
    }

    public void addToPaymentReceived(double amount){
        this.paymentReceived +=amount;
        this.paymentExpected-=amount;
    }

    public DeliveryStatus getDeliveryStatus() {
        return deliveryStatus;
    }

    public void setDeliveryStatus(DeliveryStatus deliveryStatus) {
        this.deliveryStatus = deliveryStatus;
    }

    public void deductFromPaymentReceived(double amount){
        this.paymentReceived -=amount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DeliverywisePaymentTracker that = (DeliverywisePaymentTracker) o;

        return deliverySequence == that.deliverySequence;

    }

    @Override
    public int hashCode() {
        return (int) deliverySequence;
    }
}
