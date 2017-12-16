package com.affaince.subscription.product.event;

/**
 * Created by mandar on 10/1/2017.
 */
public class ProductAnalyserCreatedEvent {
    private Integer productAnalyserId;

    public ProductAnalyserCreatedEvent(Integer productAnalyserId) {
        this.productAnalyserId = productAnalyserId;
    }

    public Integer getProductAnalyserId() {
        return productAnalyserId;
    }
}
