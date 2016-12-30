package com.affaince.subscription.product.command;

import com.affaince.subscription.product.vo.ProductForecastParameter;
import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;

/**
 * Created by mandar on 06-10-2016.
 */
public class AddManualForecastCommand {
    @TargetAggregateIdentifier
    private final String productId;
    private final ProductForecastParameter[] productForecastParameters;

    public AddManualForecastCommand(String productId, ProductForecastParameter[] productForecastParameters) {
        this.productId = productId;
        this.productForecastParameters = productForecastParameters;
    }

    public String getProductId() {
        return productId;
    }

    public ProductForecastParameter[] getProductForecastParameters() {
        return productForecastParameters;
    }
}
