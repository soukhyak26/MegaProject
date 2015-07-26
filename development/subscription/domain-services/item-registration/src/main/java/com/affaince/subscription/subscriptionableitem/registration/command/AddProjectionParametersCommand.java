package com.affaince.subscription.subscriptionableitem.registration.command;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

/**
 * Created by rbsavaliya on 26-07-2015.
 */
public class AddProjectionParametersCommand {
    @TargetAggregateIdentifier
    private String itemId;
    private int targetConsumptionPeriod;
    private String targetConsumptionPeriodUnit;
    private long targetSalePerConsumptionPeriod;
    private short minimumProfitMargin;
    private short maximumProfitMargin;
    private float demandToSupplyRatio;
    private short consumptionFrequency;

    public AddProjectionParametersCommand(String itemId, int targetConsumptionPeriod, String targetConsumptionPeriodUnit, long targetSalePerConsumptionPeriod, short minimumProfitMargin, short maximumProfitMargin, float demandToSupplyRatio, short consumptionFrequency) {
        this.itemId = itemId;
        this.targetConsumptionPeriod = targetConsumptionPeriod;
        this.targetConsumptionPeriodUnit = targetConsumptionPeriodUnit;
        this.targetSalePerConsumptionPeriod = targetSalePerConsumptionPeriod;
        this.minimumProfitMargin = minimumProfitMargin;
        this.maximumProfitMargin = maximumProfitMargin;
        this.demandToSupplyRatio = demandToSupplyRatio;
        this.consumptionFrequency = consumptionFrequency;
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
