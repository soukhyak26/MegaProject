package com.affaince.subscription.product.command;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;
import org.joda.time.LocalDate;

/**
 * Created by mandark on 29-04-2016.
 */
public class UpdateForecastFromActualsCommand {
    @TargetAggregateIdentifier
    private final String productId;
    private final LocalDate forecastDate;

    public UpdateForecastFromActualsCommand(String productId, LocalDate forecastDate) {
        this.productId = productId;
        this.forecastDate = forecastDate;
    }

    public String getProductId() {
        return productId;
    }

    public LocalDate getForecastDate() {
        return forecastDate;
    }

}
