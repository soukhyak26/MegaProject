package com.affaince.subscription.product.registration.command;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;
import org.joda.time.LocalDate;

/**
 * Created by mandar on 19-06-2016.
 */
public class UpdateProductSubscriptionCommand {
    @TargetAggregateIdentifier
    private String productId;
    private int productSubscriptionCount;
    private LocalDate subscriptionActivateDate;

    public UpdateProductSubscriptionCommand(String productId, int productSubscriptionCount,LocalDate subscriptionActivateDate ) {
        this.productId = productId;
        this.productSubscriptionCount = productSubscriptionCount;
        this.subscriptionActivateDate=subscriptionActivateDate;
    }

    public String getProductId() {
        return productId;
    }

    public int getProductSubscriptionCount() {
        return productSubscriptionCount;
    }

    public LocalDate getSubscriptionActivateDate() {
        return subscriptionActivateDate;
    }
}
