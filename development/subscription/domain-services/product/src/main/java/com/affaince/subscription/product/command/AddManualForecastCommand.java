package com.affaince.subscription.product.command;

import com.affaince.subscription.common.vo.ProductForecastParameter;
import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;
import org.joda.time.LocalDate;

/**
 * Created by mandar on 06-10-2016.
 */
public class AddManualForecastCommand {
    @TargetAggregateIdentifier
    private final String productId;
    private final ProductForecastParameter[] productForecastParameters;
    private LocalDate forecastDate;

    public AddManualForecastCommand(String productId, ProductForecastParameter[] productForecastParameters,LocalDate forecastDate) {
        this.productId = productId;
        this.productForecastParameters = productForecastParameters;
        this.forecastDate=forecastDate;
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
