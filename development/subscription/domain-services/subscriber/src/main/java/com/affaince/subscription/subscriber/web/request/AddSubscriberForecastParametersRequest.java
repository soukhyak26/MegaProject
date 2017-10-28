package com.affaince.subscription.subscriber.web.request;

import com.affaince.subscription.subscriber.vo.SubscriberForecastParameter;

/**
 * Created by mandar on 10/28/2017.
 */
public class AddSubscriberForecastParametersRequest {
    private SubscriberForecastParameter[] subscriberForecastParameters;

    public SubscriberForecastParameter[] getSubscriberForecastParameters() {
        return subscriberForecastParameters;
    }

    public void setSubscriberForecastParameters(SubscriberForecastParameter[] subscriberForecastParameters) {
        this.subscriberForecastParameters = subscriberForecastParameters;
    }
}
