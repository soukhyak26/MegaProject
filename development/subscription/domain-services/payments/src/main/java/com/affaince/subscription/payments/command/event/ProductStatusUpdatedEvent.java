package com.affaince.subscription.payments.command.event;

import com.affaince.subscription.common.vo.PriceTaggedWithProduct;

/**
 * Created by mandar on 5/27/2017.
 */
public class ProductStatusUpdatedEvent {
    private String productId;
    private PriceTaggedWithProduct newTaggedPrice;
    private int currentStockInUnits;

    public ProductStatusUpdatedEvent(String productId, PriceTaggedWithProduct newTaggedPrice, int currentStockInUnits) {
        this.productId = productId;
        this.newTaggedPrice = newTaggedPrice;
        this.currentStockInUnits = currentStockInUnits;
    }

    public ProductStatusUpdatedEvent() {
    }
    public String getProductId() {
        return this.productId;
    }
    public int getCurrentStockInUnits() {
        return this.currentStockInUnits;
    }

    public PriceTaggedWithProduct getNewTaggedPrice() {
        return newTaggedPrice;
    }

}
