package com.affaince.subscription.expensedistribution.processor;

import com.affaince.subscription.expensedistribution.event.SubscriptionSpecificOperatingExpenseCalculatedEvent;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * Created by rsavaliya on 26/3/16.
 */
public interface OperatingExpenseDistribution {

    public Map<String, Double> distributeDeliveryExpensesToProduct () throws IOException;
}
