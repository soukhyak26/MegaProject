package com.affaince.subscription.product.registration.web.request;

/**
 * Created by rbsavaliya on 26-07-2015.
 */
public class AddProjectionParametersRequest {

    private int targetConsumptionPeriod;
    private int targetConsumptionPeriodUnit;
    private long targetSalePerConsumptionPeriod;
    private float minimumProfitMargin;
    private float maximumProfitMargin;
    private float demandToSupplyRatio;
    private short consumptionFrequency;
    private float consumptionFrequencyPeriod;
    private int consumptionFrequencyPeriodUnitCode;

    public int getTargetConsumptionPeriod() {
        return targetConsumptionPeriod;
    }

    public void setTargetConsumptionPeriod(int targetConsumptionPeriod) {
        this.targetConsumptionPeriod = targetConsumptionPeriod;
    }

    public int getTargetConsumptionPeriodUnit() {
        return targetConsumptionPeriodUnit;
    }

    public void setTargetConsumptionPeriodUnit(int targetConsumptionPeriodUnit) {
        this.targetConsumptionPeriodUnit = targetConsumptionPeriodUnit;
    }

    public long getTargetSalePerConsumptionPeriod() {
        return targetSalePerConsumptionPeriod;
    }

    public void setTargetSalePerConsumptionPeriod(long targetSalePerConsumptionPeriod) {
        this.targetSalePerConsumptionPeriod = targetSalePerConsumptionPeriod;
    }

    public float getMinimumProfitMargin() {
        return minimumProfitMargin;
    }

    public void setMinimumProfitMargin(float minimumProfitMargin) {
        this.minimumProfitMargin = minimumProfitMargin;
    }

    public float getMaximumProfitMargin() {
        return maximumProfitMargin;
    }

    public void setMaximumProfitMargin(float maximumProfitMargin) {
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

    public float getConsumptionFrequencyPeriod() {
        return consumptionFrequencyPeriod;
    }

    public void setConsumptionFrequencyPeriod(float consumptionFrequencyPeriod) {
        this.consumptionFrequencyPeriod = consumptionFrequencyPeriod;
    }

    public int getConsumptionFrequencyPeriodUnitCode() {
        return consumptionFrequencyPeriodUnitCode;
    }

    public void setConsumptionFrequencyPeriodUnitCode(int consumptionFrequencyPeriodUnitCode) {
        this.consumptionFrequencyPeriodUnitCode = consumptionFrequencyPeriodUnitCode;
    }
}
