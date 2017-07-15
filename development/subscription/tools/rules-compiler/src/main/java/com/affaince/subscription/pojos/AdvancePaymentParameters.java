package com.affaince.subscription.pojos;

import com.affaince.subscription.common.type.PaymentEvent;
import com.affaince.subscription.common.type.PaymentSource;

/**
 * Created by rahul on 15/7/17.
 */
public class AdvancePaymentParameters {

    private double percentValue;
    private PaymentSource paymentSource;
    private PaymentEvent paymentEvent;
    private DeliveryExpression deliveryExpression;

    public double getPercentValue() {
        return percentValue;
    }

    public void setPercentValue(double percentValue) {
        this.percentValue = percentValue;
    }

    public PaymentSource getPaymentSource() {
        return paymentSource;
    }

    public void setPaymentSource(PaymentSource paymentSource) {
        this.paymentSource = paymentSource;
    }

    public PaymentEvent getPaymentEvent() {
        return paymentEvent;
    }

    public void setPaymentEvent(PaymentEvent paymentEvent) {
        this.paymentEvent = paymentEvent;
    }

    public DeliveryExpression getDeliveryExpression() {
        return deliveryExpression;
    }

    public void setDeliveryExpression(DeliveryExpression deliveryExpression) {
        this.deliveryExpression = deliveryExpression;
    }
}
