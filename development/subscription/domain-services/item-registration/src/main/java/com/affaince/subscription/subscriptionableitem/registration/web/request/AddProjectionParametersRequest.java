package com.affaince.subscription.subscriptionableitem.registration.web.request;

/**
 * Created by rbsavaliya on 26-07-2015.
 */
public class AddProjectionParametersRequest {

    private int targetConsumptionPeriod;
    private String targetConsumptionPeriodUnit;
    private long targetSalePerConsumptionPeriod;
    private short minimumProfitMargin;
    private short maximumProfitMargin;
    private float demandToSupplyRatio;
    private short consumptionFrequency;

    public int getTargetConsumptionPeriod() {
        return targetConsumptionPeriod;
    }

    public void setTargetConsumptionPeriod(int targetConsumptionPeriod) {
        this.targetConsumptionPeriod = targetConsumptionPeriod;
    }

    public String getTargetConsumptionPeriodUnit() {
        return targetConsumptionPeriodUnit;
    }

    public void setTargetConsumptionPeriodUnit(String targetConsumptionPeriodUnit) {
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
