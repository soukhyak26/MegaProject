package com.affaince.subscription.subscriber.services.benefit.state;

import com.affaince.subscription.subscriber.services.benefit.context.BenefitExecutionContext;

import java.util.Map;

/**
 * Created by rbsavaliya on 17-09-2016.
 */
public interface PaymentStrategy {
    void distributeRewardPoints(BenefitExecutionContext context);
}
