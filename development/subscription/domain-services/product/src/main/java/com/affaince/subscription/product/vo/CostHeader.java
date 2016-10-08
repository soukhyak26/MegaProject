package com.affaince.subscription.product.vo;

/**
 * Created by mandar on 08-10-2016.
 */
public class CostHeader {
    final CostHeaderType costHeaderType;
    final String description;
    final double value;
    final CostHeaderApplicability costHeaderApplicability;

    public CostHeader(CostHeaderType costHeaderType, String description, double value, CostHeaderApplicability costHeaderApplicability) {
        this.costHeaderType = costHeaderType;
        this.description = description;
        this.value = value;
        this.costHeaderApplicability = costHeaderApplicability;
    }

    public CostHeaderType getCostHeaderType() {
        return costHeaderType;
    }

    public String getDescription() {
        return description;
    }

    public double getValue() {
        return value;
    }

    public CostHeaderApplicability getCostHeaderApplicability() {
        return costHeaderApplicability;
    }
}
