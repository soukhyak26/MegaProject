package com.affaince.subscription.product.command;

import org.joda.time.LocalDate;
import org.joda.time.LocalDateTime;

/**
 * Created by mandar on 16-10-2016.
 */
public class RegisterOpeningPriceCommand {
    private final String productId;
    private final double openingPrice;
    private final LocalDateTime fromDate;

    public RegisterOpeningPriceCommand(String productId, double openingPrice,LocalDateTime fromDate) {
        this.productId = productId;
        this.openingPrice = openingPrice;
        this.fromDate=fromDate;
    }

    public String getProductId() {
        return productId;
    }

    public double getOpeningPrice() {
        return openingPrice;
    }

    public LocalDateTime getFromDate() {
        return fromDate;
    }
}
