package com.affaince.subscription.product.command;

import org.joda.time.LocalDate;

/**
 * Created by mandar on 6/24/2017.
 */
public class UpdateForecastDateCommand {
    private String productId;
    private LocalDate nextForecastDate;

    public UpdateForecastDateCommand(String productId, LocalDate nextForecastDate) {
        this.productId = productId;
        this.nextForecastDate = nextForecastDate;
    }

    public String getProductId() {
        return productId;
    }

    public LocalDate getNextForecastDate() {
        return nextForecastDate;
    }
}
