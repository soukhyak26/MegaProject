package com.affaince.subscription.product.command;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;
import org.joda.time.LocalDateTime;

/**
 * Created by mandar on 06-10-2016.
 */
public class AddManualForecastCommand {
    @TargetAggregateIdentifier
    private final String productId;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;

    public AddManualForecastCommand(String productId, LocalDateTime startDate, LocalDateTime endDate) {
        this.productId = productId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getProductId() {
        return productId;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }
}
