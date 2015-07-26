package com.affaince.subscription.subscriptionableitem.registration.command.domain;

/**
 * Created by rbsavaliya on 26-07-2015.
 */
class ProjectionParameters {

    private int targetConsumptionPeriod;
    private ConsumptionPeriodUnit targetConsumptionPeriodUnit;
    private long targetSalePerConsumptionPeriod;
    private short minimumProfitMargin;
    private short maximumProfitMargin;
    private float demandToSupplyRatio;
    private short consumptionFrequency;

    public ProjectionParameters(int targetConsumptionPeriod, String targetConsumptionPeriodUnit, long targetSalePerConsumptionPeriod, short minimumProfitMargin, short maximumProfitMargin, float demandToSupplyRatio, short consumptionFrequency) {
        this.targetConsumptionPeriod = targetConsumptionPeriod;
        this.targetConsumptionPeriodUnit = ConsumptionPeriodUnit.valueOf(targetConsumptionPeriodUnit.toUpperCase());
        this.targetSalePerConsumptionPeriod = targetSalePerConsumptionPeriod;
        this.minimumProfitMargin = minimumProfitMargin;
        this.maximumProfitMargin = maximumProfitMargin;
        this.demandToSupplyRatio = demandToSupplyRatio;
        this.consumptionFrequency = consumptionFrequency;
    }

    public int getTargetConsumptionPeriod() {
        return targetConsumptionPeriod;
    }

    public void setTargetConsumptionPeriod(int targetConsumptionPeriod) {
        this.targetConsumptionPeriod = targetConsumptionPeriod;
    }

    public ConsumptionPeriodUnit getTargetConsumptionPeriodUnit() {
        return targetConsumptionPeriodUnit;
    }

    public void setTargetConsumptionPeriodUnit(ConsumptionPeriodUnit targetConsumptionPeriodUnit) {
        this.targetConsumptionPeriodUnit = targetConsumptionPeriodUnit;
    }

    public long getTargetSalePerConsumptionPeriod() {
        return targetSalePerConsumptionPeriod;
    }

    public void setTargetSalePerConsumptionPeriod(long targetSalePerConsumptionPeriod) {
        this.targetSalePerConsumptionPeriod = targetSalePerConsumptionPeriod;
    }

    public short getMinimumProfitMargin() {
        return minimumProfitMargin;
    }

    public void setMinimumProfitMargin(short minimumProfitMargin) {
        this.minimumProfitMargin = minimumProfitMargin;
    }

    public short getMaximumProfitMargin() {
        return maximumProfitMargin;
    }

    public void setMaximumProfitMargin(short maximumProfitMargin) {
        this.maximumProfitMargin = maximumProfitMargin;
    }

    public float getDemandToSupplyRatio() {
        return demandToSupplyRatio;
    }

    public void setDemandToSupplyRatio(float demandToSupplyRatio) {
        this.demandToSupplyRatio = demandToSupplyRatio;
    }

    public short getConsumptionFrequency() {
        return consumptionFrequency;
    }

    public void setConsumptionFrequency(short consumptionFrequency) {
        this.consumptionFrequency = consumptionFrequency;
    }
}
