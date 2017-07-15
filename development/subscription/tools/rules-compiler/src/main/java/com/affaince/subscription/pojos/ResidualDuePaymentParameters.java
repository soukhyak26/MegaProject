package com.affaince.subscription.pojos;

import java.util.List;

/**
 * Created by rahul on 15/7/17.
 */
public class ResidualDuePaymentParameters {

    private boolean before;
    private boolean after;
    private List <DeliveryExpression> deliveries;
    private List <Integer> proportionValues;

    public boolean isBefore() {
        return before;
    }

    public void setBefore(boolean before) {
        this.before = before;
    }

    public boolean isAfter() {
        return after;
    }

    public void setAfter(boolean after) {
        this.after = after;
    }

    public List<DeliveryExpression> getDeliveries() {
        return deliveries;
    }

    public void setDeliveries(List<DeliveryExpression> deliveries) {
        this.deliveries = deliveries;
    }

    public List<Integer> getProportionValues() {
        return proportionValues;
    }

    public void setProportionValues(List<Integer> proportionValues) {
        this.proportionValues = proportionValues;
    }
}
