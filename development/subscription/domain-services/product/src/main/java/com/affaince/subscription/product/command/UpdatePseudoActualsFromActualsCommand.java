package com.affaince.subscription.product.command;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;
import org.joda.time.LocalDate;

/**
 * Created by mandar on 14-08-2016.
 */
public class    UpdatePseudoActualsFromActualsCommand {
    @TargetAggregateIdentifier
    private final String productId;
    private final LocalDate forecastDate;

    public UpdatePseudoActualsFromActualsCommand(String productId, LocalDate forecastDate) {
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
