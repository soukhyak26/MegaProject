package com.affaince.subscription.expensedistribution.processor;

import java.io.IOException;
import java.util.Map;

/**
 * Created by rsavaliya on 26/3/16.
 */
public interface OperatingExpenseDistribution {

    public Map<String, Double> distributeDeliveryExpensesToProduct () throws IOException;
}
