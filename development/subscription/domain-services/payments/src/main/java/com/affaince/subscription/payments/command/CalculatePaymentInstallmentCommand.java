package com.affaince.subscription.payments.command;

import org.joda.time.LocalDate;

import java.util.Map;

public class CalculatePaymentInstallmentCommand {
    private final String subscriptionId;
    private final Map<LocalDate, Double> deliveryWisePriceMap;

    public CalculatePaymentInstallmentCommand(String subscriptionId, Map<LocalDate, Double> deliveryWisePriceMap) {

        this.subscriptionId = subscriptionId;
        this.deliveryWisePriceMap = deliveryWisePriceMap;
    }

    public String getSubscriptionId() {
        return subscriptionId;
    }

    public Map<LocalDate, Double> getDeliveryWisePriceMap() {
        return deliveryWisePriceMap;
    }
}
