package com.affaince.subscription.subscriber.services;

/**
 * Created by rbsavaliya on 29-05-2016.
 */
public class BenefitCalculationRequest {
    private double totalSubscriptionAmount;
    private double totalProfit;
    private double currentSubscriptionAmount;
    private double totalLoyaltyPeriod;
    private double currentSubscriptionPeriod;

    public BenefitCalculationRequest(double totalSubscriptionAmount, double totalProfit, double currentSubscriptionAmount, double totalLoyaltyPeriod, double currentSubscriptionPeriod) {
        this.totalSubscriptionAmount = totalSubscriptionAmount;
        this.totalProfit = totalProfit;
        this.currentSubscriptionAmount = currentSubscriptionAmount;
        this.totalLoyaltyPeriod = totalLoyaltyPeriod;
        this.currentSubscriptionPeriod = currentSubscriptionPeriod;
    }

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
}
