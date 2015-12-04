package com.affaince.subscription.product.registration.query.view;

import com.affaince.subscription.common.type.Frequency;
import com.affaince.subscription.common.type.Period;

/**
 * Created by rbsavaliya on 26-07-2015.
 */
public class ProjectionParameters {

    private Period targetConsumptionPeriod;
    private long targetSalePerConsumptionPeriod;
    private double minimumProfitMargin;
    private double maximumProfitMargin;
    private float demandToSupplyRatio;
    private Frequency consumptionFrequency;

    public ProjectionParameters(Period targetConsumptionPeriod, long targetSalePerConsumptionPeriod, double minimumProfitMargin, double maximumProfitMargin, float demandToSupplyRatio, Frequency consumptionFrequency) {
        this.targetConsumptionPeriod = targetConsumptionPeriod;
        this.targetSalePerConsumptionPeriod = targetSalePerConsumptionPeriod;
        this.minimumProfitMargin = minimumProfitMargin;
        this.maximumProfitMargin = maximumProfitMargin;
        this.demandToSupplyRatio = demandToSupplyRatio;
        this.consumptionFrequency = consumptionFrequency;
    }

    public Period getTargetConsumptionPeriod() {
        return targetConsumptionPeriod;
    }

    public void setTargetConsumptionPeriod(Period targetConsumptionPeriod) {
        this.targetConsumptionPeriod = targetConsumptionPeriod;
    }

    public long getTargetSalePerConsumptionPeriod() {
        return targetSalePerConsumptionPeriod;
    }

    public void setTargetSalePerConsumptionPeriod(long targetSalePerConsumptionPeriod) {
        this.targetSalePerConsumptionPeriod = targetSalePerConsumptionPeriod;
    }

    public double getMinimumProfitMargin() {
        return minimumProfitMargin;
    }

    public void setMinimumProfitMargin(double minimumProfitMargin) {
        this.minimumProfitMargin = minimumProfitMargin;
    }

    public double getMaximumProfitMargin() {
        return maximumProfitMargin;
    }

    public void setMaximumProfitMargin(double maximumProfitMargin) {
        this.maximumProfitMargin = maximumProfitMargin;
    }

    public float getDemandToSupplyRatio() {
        return demandToSupplyRatio;
    }

    public void setDemandToSupplyRatio(float demandToSupplyRatio) {
        this.demandToSupplyRatio = demandToSupplyRatio;
    }

    public Frequency getConsumptionFrequency() {
        return consumptionFrequency;
    }

    public void setConsumptionFrequency(Frequency consumptionFrequency) {
        this.consumptionFrequency = consumptionFrequency;
    }
}
