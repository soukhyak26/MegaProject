package com.affaince.subscription.payments.vo;

/**
 * Created by mandar on 7/6/2017.
 */
//deliveries before which payment is expected.
//amount of payment expected
//amount of payment fulfilled
public class DeliverywisePaymentTracker {
    private short deliverySequence;
    private double paymentExpected;
    private double paymentReceived;

    public DeliverywisePaymentTracker(short deliverySequence) {
        this.deliverySequence = deliverySequence;
    }

    public short getDeliverySequence() {
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

    public void addToPaymentreceived(double amount){
        this.paymentReceived +=amount;
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
