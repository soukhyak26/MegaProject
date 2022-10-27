package com.affaince.distribution.profiles;

public class RatePerUnitWeight {
    private final String rateIdentifier;
    private final double rateInCurrency;
    private final double minWeight;
    private final double maxWeight;
    private final UnitForRate unitForRate;

    public RatePerUnitWeight(String rateIdentifier, double rateInCurrency, double minWeight, double maxWeight, UnitForRate unitForRate) {
        this.rateIdentifier = rateIdentifier;
        this.rateInCurrency = rateInCurrency;
        this.minWeight = minWeight;
        this.maxWeight = maxWeight;
        this.unitForRate = unitForRate;
    }

    public String getRateIdentifier() {
        return rateIdentifier;
    }

    public double getRateInCurrency() {
        return rateInCurrency;
    }

    public double getMinWeight() {
        return minWeight;
    }

    public double getMaxWeight() {
        return maxWeight;
    }

    public UnitForRate getUnitForRate() {
        return unitForRate;
    }
}
