package com.affaince.subscription.payments.calculator;

import com.affaince.subscription.payments.vo.InstalmentPaymentTracker;
import com.affaince.subscription.pojos.PaymentExpression;
import org.joda.time.LocalDate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class PaymentInstallmentCalculationRequest {
    private Map<LocalDate, Double> deliveryPrices;
    private PaymentExpression paymentExpression;
    private List<InstalmentPaymentTracker> paymentInstallments = new ArrayList<>();

    public Map<LocalDate, Double> getDeliveryPrices() {
        return deliveryPrices;
    }

    public void setDeliveryPrices(Map<LocalDate, Double> deliveryPrices) {
        this.deliveryPrices = deliveryPrices;
    }

    public PaymentExpression getPaymentExpression() {
        return paymentExpression;
    }

    public void setPaymentExpression(PaymentExpression paymentExpression) {
        this.paymentExpression = paymentExpression;
    }

    public List<InstalmentPaymentTracker> getPaymentInstallments() {
        return paymentInstallments;
    }

    public void addAllPaymentInstallments (List<InstalmentPaymentTracker> instalmentPaymentTrackers) {
        this.paymentInstallments.addAll(instalmentPaymentTrackers);
    }
}
