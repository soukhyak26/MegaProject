package com.affaince.subscription.expensedistribution.vo;

import java.util.Map;

/**
 * Created by rsavaliya on 14/2/16.
 */
public class DeliveryStats {
    private Map <Integer, Integer> numberOfBasketsPerWeightRange;

    public Map<Integer, Integer> getNumberOfBasketsPerWeightRange() {
        return numberOfBasketsPerWeightRange;
    }

    public void setNumberOfBasketsPerWeightRange(Map<Integer, Integer> numberOfBasketsPerWeightRange) {
        this.numberOfBasketsPerWeightRange = numberOfBasketsPerWeightRange;
    }
}
