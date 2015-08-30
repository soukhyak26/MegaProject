package com.affaince.subscription.subscriptionableitem.registration.command;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

/**
 * Created by rbsavaliya on 26-07-2015.
 */
public class AddProjectionParametersCommand {
    @TargetAggregateIdentifier
    private String itemId;
    private int targetConsumptionPeriod;
    private int targetConsumptionPeriodUnit;
    private long targetSalePerConsumptionPeriod;
    private float minimumProfitMargin;
    private float maximumProfitMargin;
    private float demandToSupplyRatio;
    private short consumptionFrequency;
    private float consumptionFrequencyPeriod;
    private int consumptionFrequencyPeriodUnitCode;

    public AddProjectionParametersCommand(String itemId, int targetConsumptionPeriod, int targetConsumptionPeriodUnit, long targetSalePerConsumptionPeriod, float minimumProfitMargin, float maximumProfitMargin, float demandToSupplyRatio, short consumptionFrequency, float consumptionFrequencyPeriod, int consumptionFrequencyPeriodUnitCode) {
        this.itemId = itemId;
        this.targetConsumptionPeriod = targetConsumptionPeriod;
        this.targetConsumptionPeriodUnit = targetConsumptionPeriodUnit;
        this.targetSalePerConsumptionPeriod = targetSalePerConsumptionPeriod;
        this.minimumProfitMargin = minimumProfitMargin;
        this.maximumProfitMargin = maximumProfitMargin;
        this.demandToSupplyRatio = demandToSupplyRatio;
        this.consumptionFrequency = consumptionFrequency;
        this.consumptionFrequencyPeriod = consumptionFrequencyPeriod;
        this.consumptionFrequencyPeriodUnitCode = consumptionFrequencyPeriodUnitCode;
    }

    public String getItemId() {
        return itemId;
    }

    public void setItemId(String itemId) {
        this.itemId = itemId;
    }

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
