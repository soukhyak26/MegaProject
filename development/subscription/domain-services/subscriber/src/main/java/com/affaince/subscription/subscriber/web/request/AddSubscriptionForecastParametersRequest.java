package com.affaince.subscription.subscriber.web.request;

import com.affaince.subscription.subscriber.vo.SubscriptionForecastParameter;

/**
 * Created by mandar on 10/28/2017.
 */
public class AddSubscriptionForecastParametersRequest {
    private SubscriptionForecastParameter[] subscriptionForecastParameters;

    public SubscriptionForecastParameter[] getSubscriptionForecastParameters() {
        return subscriptionForecastParameters;
    }

    public void setSubscriptionForecastParameters(SubscriptionForecastParameter[] subscriptionForecastParameters) {
        this.subscriptionForecastParameters = subscriptionForecastParameters;
    }
}
