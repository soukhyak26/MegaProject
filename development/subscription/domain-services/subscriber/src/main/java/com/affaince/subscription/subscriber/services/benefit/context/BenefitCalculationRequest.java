package com.affaince.subscription.subscriber.services.benefit.context;

import java.util.Map;

/**
 * Created by rbsavaliya on 29-05-2016.
 */
public class BenefitCalculationRequest {
    private double totalSubscriptionAmount;
    private double totalProfit;
    private double currentSubscriptionAmount;
    private double totalLoyaltyPeriod;
    private double currentSubscriptionPeriod;
    private double advancePaymentPercent;
    private Map<String, Double> deliveryAmounts;

    public double getTotalSubscriptionAmount() {
        return totalSubscriptionAmount;
    }

    public void setTotalSubscriptionAmount(double totalSubscriptionAmount) {
        this.totalSubscriptionAmount = totalSubscriptionAmount;
    }

    public double getTotalProfit() {
        return totalProfit;
    }

    public void setTotalProfit(double totalProfit) {
        this.totalProfit = totalProfit;
    }

    public double getCurrentSubscriptionAmount() {
        return currentSubscriptionAmount;
    }

    public void setCurrentSubscriptionAmount(double currentSubscriptionAmount) {
        this.currentSubscriptionAmount = currentSubscriptionAmount;
    }

    public double getTotalLoyaltyPeriod() {
        return totalLoyaltyPeriod;
    }

    public void setTotalLoyaltyPeriod(double totalLoyaltyPeriod) {
        this.totalLoyaltyPeriod = totalLoyaltyPeriod;
    }

    public double getCurrentSubscriptionPeriod() {
        return currentSubscriptionPeriod;
    }

    public void setCurrentSubscriptionPeriod(double currentSubscriptionPeriod) {
        this.currentSubscriptionPeriod = currentSubscriptionPeriod;
    }

    public double getAdvancePaymentPercent() {
        return advancePaymentPercent;
    }

    public void setAdvancePaymentPercent(double advancePaymentPercent) {
        this.advancePaymentPercent = advancePaymentPercent;
    }

    public Map<String, Double> getDeliveryAmounts() {
        return deliveryAmounts;
    }

    public void setDeliveryAmounts(Map<String, Double> deliveryAmounts) {
        this.deliveryAmounts = deliveryAmounts;
    }
}
