package com.affaince.subscription.subscriber.web.request;

import com.affaince.subscription.subscriber.vo.DeliveryForecastParameter;

/**
 * Created by mandar on 10/28/2017.
 */
public class AddDeliveryForecastParametersRequest {
    private DeliveryForecastParameter[] deliveryForecastParameters;

    public DeliveryForecastParameter[] getDeliveryForecastParameters() {
        return deliveryForecastParameters;
    }

    public void setDeliveryForecastParameters(DeliveryForecastParameter[] deliveryForecastParameters) {
        this.deliveryForecastParameters = deliveryForecastParameters;
    }
}
