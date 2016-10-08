package com.affaince.subscription.product.command;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;
import org.joda.time.LocalDateTime;

/**
 * Created by mandar on 07-10-2016.
 */
public class AddManualPseudoActualsCommand {
    @TargetAggregateIdentifier
    private final String productId;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;

    public AddManualPseudoActualsCommand(String productId, LocalDateTime startDate, LocalDateTime endDate) {
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
