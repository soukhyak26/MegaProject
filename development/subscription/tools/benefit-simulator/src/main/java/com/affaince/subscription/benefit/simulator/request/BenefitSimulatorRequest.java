package com.affaince.subscription.benefit.simulator.request;

public class BenefitSimulatorRequest {

    private Benefit benefit;
    private Subscription subscription;
    private double totalSubscription;
    private int duration;

    public Benefit getBenefit() {
        return benefit;
    }

    public void setBenefit(Benefit benefit) {
        this.benefit = benefit;
    }

    public Subscription getSubscription() {
        return subscription;
    }

    public void setSubscription(Subscription subscription) {
        this.subscription = subscription;
    }

    public double getTotalSubscription() {
        return totalSubscription;
    }

    public void setTotalSubscription(double totalSubscription) {
        this.totalSubscription = totalSubscription;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String toString() {
        return "BenefitSimulatorRequest{" +
                "benefit=" + benefit +
                ", subscription=" + subscription +
                ", totalSubscription=" + totalSubscription +
                ", duration=" + duration +
                '}';
    }
}
