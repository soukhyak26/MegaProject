package com.verifier.domains.product.vo;

/**
 * Created by mandar on 08-10-2016.
 */
public class CostHeader {
    private CostHeaderType costHeaderType;
    private String description;
    private double oldValue;
    private double newValue;
    private CostHeaderApplicability costHeaderApplicability;

    public CostHeader(CostHeaderType costHeaderType, String description, double oldValue,double newValue, CostHeaderApplicability costHeaderApplicability) {
        this.costHeaderType = costHeaderType;
        this.description = description;
        this.oldValue=oldValue;
        this.newValue = newValue;
        this.costHeaderApplicability = costHeaderApplicability;
    }

    public CostHeaderType getCostHeaderType() {
        return costHeaderType;
    }

    public String getDescription() {
        return description;
    }

    public double getNewValue() {
        return newValue;
    }

    public double getOldValue() {
        return oldValue;
    }

    public CostHeaderApplicability getCostHeaderApplicability() {
        return costHeaderApplicability;
    }

    public void setOldValue(double oldValue) {
        this.oldValue = oldValue;
    }

    public void setNewValue(double newValue) {
        this.newValue = newValue;
    }

    public void setCostHeaderApplicability(CostHeaderApplicability costHeaderApplicability) {
        this.costHeaderApplicability = costHeaderApplicability;
    }
}
