package com.affaince.subscription.product.command;

import org.axonframework.commandhandling.annotation.TargetAggregateIdentifier;
import org.joda.time.LocalDate;

/**
 * Created by rbsavaliya on 31-12-2016.
 */
public class UpdateDeliveryCountPerPriceBucketCommand {

    @TargetAggregateIdentifier
    private String productId;
    private String priceBucketId;
    private LocalDate dispatchDate;


    public UpdateDeliveryCountPerPriceBucketCommand(String productId, String priceBucketId,LocalDate DispatchDate) {
        this.productId = productId;
        this.priceBucketId = priceBucketId;
        this.dispatchDate=dispatchDate;
    }

    public UpdateDeliveryCountPerPriceBucketCommand() {
    }

    public String getProductId() {
        return productId;
    }

    public String getPriceBucketId() {
        return priceBucketId;
    }

    public LocalDate getDispatchDate() {
        return dispatchDate;
    }
}
