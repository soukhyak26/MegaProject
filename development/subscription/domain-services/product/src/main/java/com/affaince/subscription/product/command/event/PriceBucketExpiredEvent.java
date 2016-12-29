package com.affaince.subscription.product.command.event;

import com.affaince.subscription.date.SysDateTime;
import org.joda.time.LocalDateTime;

/**
 * Created by mandar on 29-12-2016.
 */
public class PriceBucketExpiredEvent {
    String productId;
    String priceBucketId;
    LocalDateTime expiryTime;
    public PriceBucketExpiredEvent(String productId, String priceBucketId) {
        this.productId=productId;
        this.priceBucketId=priceBucketId;
        this.expiryTime= SysDateTime.now();
    }

    public String getProductId() {
        return productId;
    }

    public String getPriceBucketId() {
        return priceBucketId;
    }

    public LocalDateTime getExpiryTime() {
        return expiryTime;
    }
}
