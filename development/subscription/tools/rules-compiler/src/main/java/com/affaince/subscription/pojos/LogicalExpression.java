package com.affaince.subscription.pojos;

import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class LogicalExpression implements RuleSetPojo {
    private final String type;

    protected LogicalExpression(String type) {
        this.type = type;
    }

    @JsonProperty("type")
    public String getType() {
        return type;
    }
}
