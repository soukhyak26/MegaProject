package com.affaince.subscription.product.command.event;

import com.affaince.subscription.common.vo.ProductForecastParameter;
import org.joda.time.LocalDate;

/**
 * Created by mandar on 06-10-2016.
 */
public class ManualForecastAddedEvent {
    private String productId;
    private ProductForecastParameter[] productForecastParameters;
    private LocalDate forecastDate;


    public ManualForecastAddedEvent(String productId, ProductForecastParameter[] productForecastParameters,LocalDate forecastDate) {
        this.productId = productId;
        this.productForecastParameters = productForecastParameters;
        this.forecastDate=forecastDate;
    }

    public ManualForecastAddedEvent() {
    }

    public String getProductId() {
        return productId;
    }

    public ProductForecastParameter[] getProductForecastParameters() {
        return productForecastParameters;
    }

    public LocalDate getForecastDate() {
        return forecastDate;
    }
}
