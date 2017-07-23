package com.affaince.subscription.payments.command;

import org.joda.time.LocalDate;

import java.util.Map;

public class CalculatePaymentInstallmentCommand {
    private final String subscriptionId;
    private final Map<LocalDate, Double> deliveryWisePriceMap;
    private String paymentScheme;

    public CalculatePaymentInstallmentCommand(String subscriptionId, Map<LocalDate, Double> deliveryWisePriceMap, String paymentScheme) {
        this.subscriptionId = subscriptionId;
        this.deliveryWisePriceMap = deliveryWisePriceMap;
        this.paymentScheme = paymentScheme;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public Map<LocalDate, Double> getDeliveryWisePriceMap() {
        return deliveryWisePriceMap;
    }

    public String getPaymentScheme() {
        return paymentScheme;
    }
}
