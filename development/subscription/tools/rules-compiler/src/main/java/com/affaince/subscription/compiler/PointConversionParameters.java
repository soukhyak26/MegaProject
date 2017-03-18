package com.affaince.subscription.compiler;

/**
 * Created by rbsavaliya on 12-06-2016.
 */
public class PointConversionParameters {

    private double subscriptionValue;
    private double subscriptionPeriod;
    private double pointValue;
    private double currentSubscriptionAmount;
    private double totalSubscriptionAmount;
    private int totalSubscriptionPeriod;

    public double getSubscriptionValue() {
        return subscriptionValue;
    }

    public void setSubscriptionValue(double subscriptionValue) {
        this.subscriptionValue = subscriptionValue;
    }

    public double getSubscriptionPeriod() {
        return subscriptionPeriod;
    }

    public void setSubscriptionPeriod(double subscriptionPeriod) {
        this.subscriptionPeriod = subscriptionPeriod;
    }

    public double getPointValue() {
        return pointValue;
    }

    public void setPointValue(double pointValue) {
        this.pointValue = pointValue;
    }

    public void setCurrentSubscriptionAmount(double currentSubscriptionAmount) {
        this.currentSubscriptionAmount = currentSubscriptionAmount;
    }

    public double getCurrentSubscriptionAmount() {
        return currentSubscriptionAmount;
    }

    public double getTotalSubscriptionAmount() {
        return totalSubscriptionAmount;
    }

    public void setTotalSubscriptionAmount(double totalSubscriptionAmount) {
        this.totalSubscriptionAmount = totalSubscriptionAmount;
    }

    public int getTotalSubscriptionPeriod() {
        return totalSubscriptionPeriod;
    }

    public void setTotalSubscriptionPeriod(int totalSubscriptionPeriod) {
        this.totalSubscriptionPeriod = totalSubscriptionPeriod;
    }
}
