package com.affaince.subscription.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class NumericEntity implements ArithmeticExpression, ComparisonOperand {
    private final String type;

    public NumericEntity(String type) {
        this.type = type;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }
}
