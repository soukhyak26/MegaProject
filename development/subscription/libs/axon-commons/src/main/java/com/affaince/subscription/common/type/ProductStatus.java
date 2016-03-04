package com.affaince.subscription.common.type;

/**
 * Created by anayonkar on 13/2/16.
 */
public enum ProductStatus {

    //after product registration
    PRODUCT_REGISTERED(1),

    //dependent upon registration
    PRODUCT_CONFIGURED(2),

    //dependent upon registration
    PRODUCT_FORECASTED(3),

    //must be completed before sending to expense distribution batch
    //dependent upon completion
    PRODUCT_EXPENSES_DISTRIBUTED(4),

    //must be expense_distributed before sending to pricing batch
    //dependent upon expense distribution
    PRODUCT_ACTIVATED(5);

    private int statusCode;

    ProductStatus(int statusCode) {
        this.statusCode = statusCode;
    }

    public int getStatusCode() {
        return statusCode;
    }
}
