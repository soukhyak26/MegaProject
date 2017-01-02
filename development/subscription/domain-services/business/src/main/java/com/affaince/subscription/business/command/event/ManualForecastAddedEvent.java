package com.affaince.subscription.business.command.event;

import com.affaince.subscription.common.vo.ProductForecastParameter;
import org.joda.time.LocalDateTime;

/**
 * Created by mandar on 11-11-2016.
 */
public class ManualForecastAddedEvent {
    private String productId;
    private ProductForecastParameter[] productForecastParameters;


    public ManualForecastAddedEvent(String productId, ProductForecastParameter[] productForecastParameters) {
        this.productId = productId;
        this.productForecastParameters = productForecastParameters;
    }

    public ManualForecastAddedEvent() {
    }

    public String getProductId() {
        return productId;
    }

    public ProductForecastParameter[] getProductForecastParameters() {
        return productForecastParameters;
    }

}
