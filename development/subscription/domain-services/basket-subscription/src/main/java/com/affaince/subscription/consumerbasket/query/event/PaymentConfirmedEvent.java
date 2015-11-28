package com.affaince.subscription.consumerbasket.query.event;

/**
 * Created by rbsavaliya on 08-11-2015.
 */
public class PaymentConfirmedEvent {

    private String basketId;
    private double paymentAmount;

    public PaymentConfirmedEvent(String basketId, double paymentAmount) {
        this.basketId = basketId;
        this.paymentAmount = paymentAmount;
    }

    public PaymentConfirmedEvent() {
    }

    public String getBasketId() {
        return basketId;
    }

    public void setBasketId(String basketId) {
        this.basketId = basketId;
    }

    public double getPaymentAmount() {
        return paymentAmount;
    }

    public void setPaymentAmount(double paymentAmount) {
        this.paymentAmount = paymentAmount;
    }
}
