package com.affaince.subscription.pojos;

import java.util.List;

/**
 * Created by rahul on 15/7/17.
 */
public class BenefitDistributionParameters {

    private List <DeliveryExpression> deliveries;
    private List <Integer> proportionValues;

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
