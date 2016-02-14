package com.affaince.subscription.expensedistribution.vo;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by rsavaliya on 14/2/16.
 */
public class InputDataToCalculatePerProductOpEx {
    private Map <String, Integer> totalProductsSubscriptions = new HashMap<>();

    public void addProductSubscription (String productId, int totalNumOfSubscription) {
        totalProductsSubscriptions.put(productId, totalNumOfSubscription);
    }

    public Map<String, Integer> getTotalProductsSubscriptions() {
        return totalProductsSubscriptions;
    }

    public void setTotalProductsSubscriptions(Map<String, Integer> totalProductsSubscriptions) {
        this.totalProductsSubscriptions = totalProductsSubscriptions;
    }
}
