package com.verifier.domains.payments.vo;

public class DeliveryPaymentTracker {
    private String deliveryId;
    private int deliverySequence;
    private double paymentExpected;
    private Double paymentReceived;

    public DeliveryPaymentTracker(int deliverySequence) {
        this.deliverySequence = deliverySequence;
    }

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

    public Double getPaymentReceived() {
        return paymentReceived;
    }

    public void setPaymentReceived(Double paymentReceived) {
        this.paymentReceived = paymentReceived;
    }

    public boolean isDeliveryPaymentFulfilled(){
        return this.getPaymentExpected()==this.getPaymentReceived();
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DeliveryPaymentTracker that = (DeliveryPaymentTracker) o;

        if (deliverySequence != that.deliverySequence) return false;
        if (Double.compare(that.paymentExpected, paymentExpected) != 0) return false;
        if (deliveryId != null ? !deliveryId.equals(that.deliveryId) : that.deliveryId != null) return false;
        return paymentReceived != null ? paymentReceived.equals(that.paymentReceived) : that.paymentReceived == null;

    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = deliveryId != null ? deliveryId.hashCode() : 0;
        result = 31 * result + deliverySequence;
        temp = Double.doubleToLongBits(paymentExpected);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (paymentReceived != null ? paymentReceived.hashCode() : 0);
        return result;
    }
}
